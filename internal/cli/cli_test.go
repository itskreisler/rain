package cli

import (
	"bytes"
	"encoding/json"
	"errors"
	"os"
	"path/filepath"
	"strings"
	"testing"

	"github.com/itskreisler/rain/internal/config"
	"github.com/itskreisler/rain/internal/doctor"
)

// runCmd ejecuta el árbol de comandos con args, capturando stdout, y devuelve
// la salida y el error (sin os.Exit).
func runCmd(t *testing.T, args ...string) (string, error) {
	t.Helper()
	root := NewRootCmd(BuildInfo{Version: "test", Commit: "abc", Date: "ayer"})
	var out bytes.Buffer
	root.SetOut(&out)
	root.SetErr(&out)
	root.SetArgs(args)
	err := root.Execute()
	return out.String(), err
}

func TestVersionCommand(t *testing.T) {
	out, err := runCmd(t, "version")
	if err != nil {
		t.Fatalf("version: %v", err)
	}
	for _, want := range []string{"rain test", "commit:   abc", "built:    ayer", "platform:"} {
		if !strings.Contains(out, want) {
			t.Errorf("salida de version no contiene %q:\n%s", want, out)
		}
	}
}

func TestVersionFlag(t *testing.T) {
	out, err := runCmd(t, "--version")
	if err != nil {
		t.Fatalf("--version: %v", err)
	}
	if !strings.Contains(out, "rain test") {
		t.Errorf("--version = %q, quiero la versión del build", out)
	}
}

func TestInitWritesConfig(t *testing.T) {
	dir := t.TempDir()
	chdir(t, dir)

	out, err := runCmd(t, "init", "--non-interactive", "--mode", "simple", "--name", "Demo", "--package", "com.demo", "--version", "3.2")
	if err != nil {
		t.Fatalf("init: %v", err)
	}
	if !strings.Contains(out, "rain.toml creado") {
		t.Errorf("salida de init = %q", out)
	}

	cfg, err := config.Read(filepath.Join(dir, "rain.toml"))
	if err != nil {
		t.Fatalf("leyendo rain.toml: %v", err)
	}
	if cfg.Project.Name != "Demo" || cfg.Project.Package != "com.demo" || cfg.Project.Version != "3.2" {
		t.Errorf("Project = %+v", cfg.Project)
	}
	if cfg.Mode != "simple" {
		t.Errorf("Mode = %q, quiero simple", cfg.Mode)
	}
}

func TestInitDefaultsNonInteractive(t *testing.T) {
	dir := t.TempDir()
	chdir(t, dir)

	if _, err := runCmd(t, "init", "--non-interactive"); err != nil {
		t.Fatalf("init: %v", err)
	}

	cfg, err := config.Read(filepath.Join(dir, "rain.toml"))
	if err != nil {
		t.Fatalf("leyendo rain.toml: %v", err)
	}
	if cfg.Project.Name != "MiApp" || cfg.Mode != "simple" {
		t.Errorf("valores por defecto = %+v (mode %q)", cfg.Project, cfg.Mode)
	}
}

func TestInitInteractiveChoiceCompleto(t *testing.T) {
	dir := t.TempDir()
	chdir(t, dir)

	root := NewRootCmd(BuildInfo{Version: "test"})
	var out bytes.Buffer
	root.SetOut(&out)
	root.SetErr(&out)
	root.SetIn(strings.NewReader("2\n"))
	root.SetArgs([]string{"init"})
	if err := root.Execute(); err != nil {
		t.Fatalf("init interactivo: %v", err)
	}

	cfg, err := config.Read(filepath.Join(dir, "rain.toml"))
	if err != nil {
		t.Fatalf("leyendo rain.toml: %v", err)
	}
	if cfg.Mode != "completo" {
		t.Errorf("Mode = %q, quiero completo tras elegir 2", cfg.Mode)
	}
}

func TestInitDoesNotOverwriteWithoutForce(t *testing.T) {
	dir := t.TempDir()
	chdir(t, dir)

	if _, err := runCmd(t, "init", "--non-interactive", "--name", "Primero"); err != nil {
		t.Fatalf("init inicial: %v", err)
	}

	out, err := runCmd(t, "init", "--non-interactive", "--name", "Segundo")
	if err != nil {
		t.Fatalf("init segundo: %v", err)
	}
	if !strings.Contains(out, "ya existe") {
		t.Errorf("se esperaba aviso de que ya existe:\n%s", out)
	}

	cfg, _ := config.Read(filepath.Join(dir, "rain.toml"))
	if cfg.Project.Name != "Primero" {
		t.Errorf("Name = %q, no debería haberse sobrescrito", cfg.Project.Name)
	}

	if _, err := runCmd(t, "init", "--non-interactive", "--name", "Segundo", "--force"); err != nil {
		t.Fatalf("init --force: %v", err)
	}
	cfg, _ = config.Read(filepath.Join(dir, "rain.toml"))
	if cfg.Project.Name != "Segundo" {
		t.Errorf("Name = %q, --force debería sobrescribir", cfg.Project.Name)
	}
}

func TestInitInvalidModeIsUsageError(t *testing.T) {
	chdir(t, t.TempDir())

	_, err := runCmd(t, "init", "--mode", "inventado")
	if err == nil {
		t.Fatal("init con modo inválido debería fallar")
	}
	if code := ExitCode(err); code != 2 {
		t.Errorf("ExitCode = %d, quiero 2 (error de uso)", code)
	}
}

func TestCleanRemovesArtifacts(t *testing.T) {
	dir := t.TempDir()
	chdir(t, dir)

	for _, sub := range []string{"libs", "cache", filepath.Join("app", "build")} {
		if err := os.MkdirAll(filepath.Join(dir, sub), 0o755); err != nil {
			t.Fatal(err)
		}
	}
	// Un módulo personalizado declarado en rain.toml.
	cfg := config.Config{
		Project: config.Project{Name: "x"},
		Modules: []config.Module{{Name: "core", Path: "core"}},
	}
	if err := config.Write(filepath.Join(dir, "rain.toml"), cfg); err != nil {
		t.Fatal(err)
	}
	if err := os.MkdirAll(filepath.Join(dir, "core", "build"), 0o755); err != nil {
		t.Fatal(err)
	}

	if _, err := runCmd(t, "clean"); err != nil {
		t.Fatalf("clean: %v", err)
	}

	for _, sub := range []string{"libs", "cache", filepath.Join("core", "build")} {
		if _, err := os.Stat(filepath.Join(dir, sub)); !os.IsNotExist(err) {
			t.Errorf("%s debería haberse borrado", sub)
		}
	}
	// app/build no se declara en rain.toml (módulos: core), así que no se toca.
	if _, err := os.Stat(filepath.Join(dir, "app", "build")); err != nil {
		t.Errorf("app/build no debería borrarse si no está en los módulos: %v", err)
	}
}

func TestDoctorUnknownProfileIsUsageError(t *testing.T) {
	_, err := runCmd(t, "doctor", "--profile", "inventado")
	if err == nil {
		t.Fatal("doctor con perfil inválido debería fallar")
	}
	if code := ExitCode(err); code != 2 {
		t.Errorf("ExitCode = %d, quiero 2", code)
	}
}

func TestDoctorJSONIsValidAndVersioned(t *testing.T) {
	out, err := runCmd(t, "doctor", "--json", "--profile", "simple")
	// Puede faltar algo en el entorno de test; el exit code 3 es aceptable.
	if err != nil && ExitCode(err) != 3 {
		t.Fatalf("doctor --json: %v", err)
	}

	var report doctor.Report
	if err := json.Unmarshal([]byte(out), &report); err != nil {
		t.Fatalf("salida JSON inválida: %v\n%s", err, out)
	}
	if report.SchemaVersion == "" {
		t.Error("el reporte JSON debe incluir SchemaVersion")
	}
	if report.Profile != doctor.Simple {
		t.Errorf("Profile = %q, quiero simple", report.Profile)
	}
	if len(report.Checks) == 0 {
		t.Error("el reporte debería incluir checks")
	}
}

func TestDoctorPrintInstall(t *testing.T) {
	out, err := runCmd(t, "doctor", "--print-install")
	if err != nil && ExitCode(err) != 3 {
		t.Fatalf("doctor --print-install: %v", err)
	}
	if !strings.Contains(out, "Comandos recomendados") {
		t.Errorf("salida = %q, quiero comandos recomendados", out)
	}
}

func TestExitCodeDefault(t *testing.T) {
	if code := ExitCode(nil); code != 1 {
		t.Errorf("ExitCode(nil) = %d, quiero 1", code)
	}
	if code := ExitCode(errors.New("x")); code != 1 {
		t.Errorf("ExitCode(error normal) = %d, quiero 1", code)
	}
	if code := ExitCode(&exitError{code: 3, err: errors.New("x")}); code != 3 {
		t.Errorf("ExitCode(exitError 3) = %d, quiero 3", code)
	}
}

func TestNormalizeLegacyArgs(t *testing.T) {
	tests := []struct {
		in   []string
		want []string
	}{
		{[]string{"-release"}, []string{"--release"}},
		{[]string{"-android-jar", "/x.jar"}, []string{"--android-jar", "/x.jar"}},
		{[]string{"--release"}, []string{"--release"}},
		{[]string{"-mode=completo"}, []string{"--mode=completo"}},
		{[]string{"-x"}, []string{"-x"}},
		{[]string{"build", "-release"}, []string{"build", "--release"}},
	}
	for _, tc := range tests {
		got := normalizeLegacyArgs(tc.in)
		if strings.Join(got, " ") != strings.Join(tc.want, " ") {
			t.Errorf("normalizeLegacyArgs(%v) = %v, quiero %v", tc.in, got, tc.want)
		}
	}
}

func TestBuildRequiresProject(t *testing.T) {
	chdir(t, t.TempDir())

	// Sin AndroidManifest.xml en ningún sitio, build debe fallar con error claro.
	_, err := runCmd(t, "build", ".")
	if err == nil {
		t.Fatal("build sin proyecto debería fallar")
	}
}

func chdir(t *testing.T, dir string) {
	t.Helper()
	old, err := os.Getwd()
	if err != nil {
		t.Fatal(err)
	}
	if err := os.Chdir(dir); err != nil {
		t.Fatal(err)
	}
	t.Cleanup(func() { _ = os.Chdir(old) })
}
