package cli

import (
	"bytes"
	"strings"
	"testing"

	"github.com/spf13/cobra"

	"github.com/itskreisler/rain/internal/doctor"
	"github.com/itskreisler/rain/internal/ui"
)

func TestDoctorAceptaDevlogDelRoot(t *testing.T) {
	defer func() {
		DevlogEnabled = false
		DevlogOut = nil
	}()

	out, err := runCmd(t, "doctor", "--devlog", "--profile", "simple")
	if err != nil && ExitCode(err) != 3 {
		t.Fatalf("doctor --devlog: %v", err)
	}
	if !strings.Contains(out, "[DEVLOG]") {
		t.Errorf("doctor --devlog no imprimió devlogs:\n%s", out)
	}
	if !strings.Contains(out, "doctor.RunE") {
		t.Errorf("debería aparecer el devlog del comando doctor:\n%s", out)
	}
	if !strings.Contains(out, "HERRAMIENTA") {
		t.Errorf("el informe debería seguir imprimiéndose con --devlog:\n%s", out)
	}
}

func TestPrintDoctorReportAlignedAndPerToolInstall(t *testing.T) {
	ui.NoColor = true
	defer func() { ui.NoColor = false }()

	report := doctor.Report{
		Profile:  doctor.Complex,
		Platform: doctor.Ubuntu,
		OS:       "linux",
		Arch:     "amd64",
		Ready:    false,
		Missing:  []string{"javac"},
		Checks: []doctor.Check{
			{Name: "java", Required: true, OK: true, Status: doctor.StatusOK, Version: "21.0.12.1", Path: "/usr/bin/java"},
			{Name: "javac", Required: true, OK: false, Status: doctor.StatusMissing, Reason: doctor.ReasonNotInstalled,
				Detail: "no instalado", Install: "sudo apt install -y openjdk-17-jdk-headless"},
			{Name: "ktlint", Required: false, OK: false, Status: doctor.StatusWarning, Detail: "opcional: no instalado",
				Install: "rain doctor --fix --lint"},
		},
		Commands: []string{"sudo apt install -y openjdk-17-jdk-headless"},
	}

	var buf bytes.Buffer
	cmd := &cobra.Command{}
	cmd.SetOut(&buf)
	printDoctorReport(cmd, report, false)
	out := buf.String()

	for _, want := range []string{"HERRAMIENTA", "ESTADO", "VERSIÓN", "DETALLE / INSTALACIÓN", "instalado", "no instalado", "opcional"} {
		if !strings.Contains(out, want) {
			t.Errorf("la salida no contiene %q:\n%s", want, out)
		}
	}

	lines := strings.Split(out, "\n")
	var header, javaRow, javacRow string
	for _, line := range lines {
		switch {
		case strings.HasPrefix(line, "HERRAMIENTA"):
			header = line
		case strings.HasPrefix(line, "java  "):
			javaRow = line
		case strings.HasPrefix(line, "javac "):
			javacRow = line
		}
	}
	if header == "" || javaRow == "" || javacRow == "" {
		t.Fatalf("no se encontraron las filas esperadas:\n%s", out)
	}

	if strings.Index(header, "HERRAMIENTA") != strings.Index(javaRow, "java") {
		t.Errorf("la columna HERRAMIENTA no está alineada:\n%s\n%s", header, javaRow)
	}
	if strings.Index(header, "ESTADO") != strings.Index(javaRow, "instalado") {
		t.Errorf("la columna ESTADO no está alineada:\n%s\n%s", header, javaRow)
	}
	if strings.Index(header, "VERSIÓN") != strings.Index(javaRow, "21.0.12.1") {
		t.Errorf("la columna VERSIÓN no está alineada:\n%s\n%s", header, javaRow)
	}
	if strings.Index(javacRow, "no instalado") < 0 {
		t.Errorf("javac debería mostrar su estado:\n%s", javacRow)
	}
	if !strings.Contains(out, "→ sudo apt install -y openjdk-17-jdk-headless") {
		t.Errorf("falta el comando concreto de javac:\n%s", out)
	}
	if !strings.Contains(out, "→ rain doctor --fix --lint") {
		t.Errorf("falta el comando concreto de ktlint:\n%s", out)
	}
}
