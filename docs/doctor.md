# Diagnóstico y Entorno — Rain Doctor

`rain doctor` comprueba que el entorno contenga las herramientas necesarias para compilar aplicaciones Android.

## Uso

```bash
rain doctor [opciones]
```

### Opciones

- `--profile <simple|complex>`: Perfil de comprobación.
  - `simple`: Comprueba JDK 17+, AAPT2, zipalign, apksigner y android.jar (para proyectos Java).
  - `complex`: Comprueba `kotlinc`, el plugin de Kotlin Compose, R8 y dependencias avanzadas.
- `--json`: Devuelve el diagnóstico en formato JSON estructurado.
- `--print-install`: Imprime las instrucciones de instalación del sistema.
- `--fix`: Descarga y configura automáticamente las herramientas que faltan en `~/.rain/bin` (sin requerir acceso root).

## Ejemplo

```bash
# Diagnóstico simple
rain doctor

# Instalar/corregir herramientas faltantes en el espacio de usuario (~/.rain)
rain doctor --fix
```
