package cmd_test

import (
	"reflect"
	"testing"

	"rain/cmd"
)

func TestNormalizeArgs(t *testing.T) {
	input := []string{"build", "-android-jar", "./sdk/android.jar", "-release", "-verbose", "-quiet"}
	expected := []string{"build", "--android-jar", "./sdk/android.jar", "--release", "--verbose", "--quiet"}

	got := cmd.NormalizeArgs(input)
	if !reflect.DeepEqual(got, expected) {
		t.Errorf("NormalizeArgs(%v) = %v; want %v", input, got, expected)
	}
}

func TestCobraCmdExecution(t *testing.T) {
	_ = cmd.Execute
}
