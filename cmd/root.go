package cmd

import (
	"fmt"
	"os"

	"github.com/spf13/cobra"
)

var rootCmd = &cobra.Command{
	Use:   "rain",
	Short: "Rain 🌧️ — Build system ligero para Android sin Gradle",
	Long: `Rain 🌧️ — Un build system ligero para Android, escrito en Go y diseñado para ser rápido y eficiente sin la pesadez de Gradle.

REQUISITOS DEL SISTEMA:
  - Java JDK 21 / OpenJDK 21
  - Herramientas SDK de Android / Termux:
      * openjdk-21
      * aapt2
      * d8
      * apksigner
      * zipalign
      * kotlin / kotlinc
      * libjansi
  - Archivo android.jar del SDK de Android (API 33+ recomendado).

  En Termux, instala dependencias con:
    pkg install openjdk-21 aapt2 d8 apksigner zipalign kotlin libjansi

MODOS DE RESOLUCIÓN DE DEPENDENCIAS (rain.toml):
  - simple   : Declaración explícita de AAR/JAR sin resolución de transitivas.
  - completo : Resuelve POMs de Maven Central y Google Maven, incluyendo transitivas,
               parents y dependencyManagement (necesario para AndroidX / Jetpack Compose).

ESTRUCTURA ESPERADA DEL PROYECTO:
  mi-app/
  ├── rain.toml              # Configuración del build
  ├── app/
  │   ├── src/
  │   │   └── main/
  │   │       ├── AndroidManifest.xml
  │   │       ├── java/      # Código fuente Java (.java) (opcional)
  │   │       ├── kotlin/    # Código fuente Kotlin (.kt) (opcional)
  │   │       └── res/       # Recursos de la aplicación (layouts, strings, etc.)
  │   └── build/             # Generado por Rain (APKs, DEX, etc.)
  ├── libs/                  # Dependencias AAR/JAR descargadas (generado)
  └── cache/                 # Caché de POMs descargados (generado)

EJEMPLOS DE USO:
  1. Inicializar proyecto:
     rain init

  2. Compilar modo debug:
     rain build --android-jar ./sdk/android.jar

  3. Compilar modo release:
     rain build --android-jar ./sdk/android.jar --release
`,
}

// Execute inicia la CLI de Cobra.
func Execute() {
	if err := rootCmd.Execute(); err != nil {
		fmt.Fprintln(os.Stderr, err)
		os.Exit(1)
	}
}

func init() {
	rootCmd.AddCommand(initCmd)
	rootCmd.AddCommand(buildCmd)
	rootCmd.AddCommand(cleanCmd)
}
