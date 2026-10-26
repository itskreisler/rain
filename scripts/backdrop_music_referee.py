#!/usr/bin/env python3
import os
import re
import subprocess
import sys

def run_referee(iteration_name=""):
    print(f"=== [ÁRBITRO / REFEREE] Evaluando Iteración: {iteration_name} ===")

    base_dir = os.path.abspath(os.path.join(os.path.dirname(__file__), ".."))
    backdrop_dir = os.path.join(base_dir, "examples", "backdrop-music")

    # 1. Audit placeholders
    print("\n[1/3] Auditando placeholders y comentarios inconclusos...")
    found_placeholders = []
    app_src_dir = os.path.join(backdrop_dir, "app", "java")
    for root, _, files in os.walk(app_src_dir):
        for file in files:
            if file.endswith(".kt") or file.endswith(".java"):
                filepath = os.path.join(root, file)
                relpath = os.path.relpath(filepath, backdrop_dir)
                with open(filepath, "r", encoding="utf-8") as f:
                    for line_num, line in enumerate(f, 1):
                        if re.search(r"\bTODO\b", line) or re.search(r"\bFIXME\b", line) or \
                           re.search(r"placeholder", line, re.IGNORECASE) or \
                           re.search(r"no implementado", line, re.IGNORECASE) or \
                           re.search(r"maqueta", line, re.IGNORECASE):
                            found_placeholders.append(f"{relpath}:{line_num}: {line.strip()}")

    if found_placeholders:
        print("❌ Placeholders o textos pendientes detectados:")
        for item in found_placeholders[:10]:
            print(f"   - {item}")
        sys.exit(1)
    else:
        print("✅ 0 placeholders o comentarios inconclusos detectados.")

    # 2. Build with Rain
    print("\n[2/3] Ejecutando compilación con Rain (`rain build`)...")
    rain_bin = os.path.join(base_dir, "rain")
    if not os.path.exists(rain_bin):
        print("🔨 Compilando binario Rain de Go...")
        subprocess.run(["go", "build", "-o", "rain", "."], cwd=base_dir, check=True)

    build_cmd = [rain_bin, "build"]
    result = subprocess.run(build_cmd, cwd=backdrop_dir, capture_output=True, text=True)

    if result.returncode != 0:
        print("❌ Fallo en la compilación de Rain:")
        print(result.stderr or result.stdout)
        sys.exit(1)
    else:
        print("✅ Compilación limpia de Rain completada con éxito.")

    # 3. Verify APK
    print("\n[3/3] Verificando APKs generados...")
    debug_apk = os.path.join(backdrop_dir, "app", "build", "app-debug.apk")
    release_apk = os.path.join(backdrop_dir, "app", "build", "app-release.apk")

    if os.path.exists(debug_apk) and os.path.getsize(debug_apk) > 0:
        size_mb = os.path.getsize(debug_apk) / (1024 * 1024)
        print(f"✅ APK Debug verificado: {debug_apk} ({size_mb:.2f} MiB)")
    else:
        print(f"❌ APK Debug no encontrado o vacío en {debug_apk}")
        sys.exit(1)

    if os.path.exists(release_apk) and os.path.getsize(release_apk) > 0:
        size_mb = os.path.getsize(release_apk) / (1024 * 1024)
        print(f"✅ APK Release verificado: {release_apk} ({size_mb:.2f} MiB)")

    print(f"\n🏆 Resultado del Árbitro para '{iteration_name}': APROBADO ✅\n")

if __name__ == "__main__":
    name = sys.argv[1] if len(sys.argv) > 1 else "Evaluación General"
    run_referee(name)
