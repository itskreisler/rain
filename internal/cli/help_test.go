package cli

import (
	"strings"
	"testing"
)

func TestAyudaRaizIncluyeFlujoYEntorno(t *testing.T) {
	out, err := runCmd(t, "--help")
	if err != nil {
		t.Fatalf("rain --help: %v", err)
	}
	for _, want := range []string{
		"rain doctor --fix --profile complex",
		"RAIN_ANDROID_JAR",
		"RAIN_JAVA_MAX_HEAP",
		"NO_COLOR",
		"~/.rain/store/v1",
	} {
		if !strings.Contains(out, want) {
			t.Errorf("la ayuda raíz no menciona %q:\n%s", want, out)
		}
	}
}

func TestAyudaRaizComandosEnEspanol(t *testing.T) {
	out, err := runCmd(t, "--help")
	if err != nil {
		t.Fatalf("rain --help: %v", err)
	}
	for _, want := range []string{
		"Genera el script de autocompletado",
		"Muestra ayuda sobre cualquier comando",
		"muestra esta ayuda",
		"muestra la versión de Rain",
	} {
		if !strings.Contains(out, want) {
			t.Errorf("la ayuda raíz no muestra el texto traducido (%q):\n%s", want, out)
		}
	}
	for _, unwanted := range []string{
		"Help about any command", "help for rain", "version for rain",
		"Available Commands:", "Flags:", "for more information about a command",
	} {
		if strings.Contains(out, unwanted) {
			t.Errorf("la ayuda raíz aún contiene %q:\n%s", unwanted, out)
		}
	}
	for _, want := range []string{"Comandos disponibles:", "Opciones:", "Uso:"} {
		if !strings.Contains(out, want) {
			t.Errorf("la ayuda raíz no tradujo %q:\n%s", want, out)
		}
	}
}

func TestAyudaCleanExplicaQueConservaDeps(t *testing.T) {
	out, err := runCmd(t, "clean", "--help")
	if err != nil {
		t.Fatalf("clean --help: %v", err)
	}
	for _, want := range []string{"libs/", "cache/", ".rain-deps/"} {
		if !strings.Contains(out, want) {
			t.Errorf("la ayuda de clean no menciona %q:\n%s", want, out)
		}
	}
}

func TestAyudaDepsTreeExplicaLaCache(t *testing.T) {
	out, err := runCmd(t, "deps", "tree", "--help")
	if err != nil {
		t.Fatalf("deps tree --help: %v", err)
	}
	if !strings.Contains(out, "cache/") {
		t.Errorf("la ayuda de deps tree no menciona la caché:\n%s", out)
	}
}

func TestAyudaDoctorExplicaPrintInstall(t *testing.T) {
	out, err := runCmd(t, "doctor", "--help")
	if err != nil {
		t.Fatalf("doctor --help: %v", err)
	}
	if !strings.Contains(out, "--print-install") {
		t.Errorf("la ayuda de doctor no explica --print-install:\n%s", out)
	}
}
