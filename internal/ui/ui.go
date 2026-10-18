package ui

import (
"fmt"
"os"
"strings"
)

const (
Reset  = "\033[0m"
Red    = "\033[31m"
Green  = "\033[32m"
Yellow = "\033[33m"
Blue   = "\033[34m"
Cyan   = "\033[36m"
Gray   = "\033[90m"
)

var Verbose bool
var Quiet bool

func Success(format string, a ...interface{}) {
if Quiet { return }
fmt.Printf(Green+"✅ "+Reset+format+"\n", a...)
}

func Warn(format string, a ...interface{}) {
if Quiet { return }
fmt.Printf(Yellow+"⚠️  "+Reset+format+"\n", a...)
}

func Error(format string, a ...interface{}) {
fmt.Fprintf(os.Stderr, Red+"❌ "+Reset+format+"\n", a...)
}

func Info(format string, a ...interface{}) {
if Quiet { return }
fmt.Printf(Cyan+"[rain] "+Reset+format+"\n", a...)
}

func VerboseLog(format string, a ...interface{}) {
if !Verbose || Quiet { return }
fmt.Printf(Gray+"       "+format+Reset+"\n", a...)
}

// ProgressBar dibuja una barra de progreso de una sola línea.
type ProgressBar struct {
Total   int
Current int
Width   int
Label   string
}

func NewProgressBar(total int, label string) *ProgressBar {
return &ProgressBar{
Total: total,
Width: 20, // Ancho fijo para una línea delgada
Label: label,
}
}

// Render dibuja la barra en la línea actual (sin salto de línea).
func (p *ProgressBar) Render() {
if p.Total <= 0 || Quiet {
return
}
if p.Current > p.Total {
p.Current = p.Total
}
percentage := float64(p.Current) / float64(p.Total)
filled := int(percentage * float64(p.Width))
empty := p.Width - filled

// Colores dinámicos según el progreso
color := Cyan
if percentage > 0.33 { color = Blue }
if percentage > 0.66 { color = Green }
if percentage >= 1.0 { color = Green }

// Construir la barra: Llena (█) + Vacía (░) o similar
bar := strings.Repeat("█", filled) + strings.Repeat("░", empty)

// \r para volver al inicio de la línea, sin \n
fmt.Printf("\033[2K\r%s[%s%s%s] %3d%% %s", p.Label, color, bar, Reset, int(percentage*100), strings.Repeat(" ", 5))
os.Stdout.Sync() // Forzar vaciado del buffer en Termux
}

func (p *ProgressBar) Finish() {
if Quiet { return }
p.Current = p.Total
p.Render()
fmt.Println() // Salto de línea final
}
