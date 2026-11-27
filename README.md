<div align="center">
  <img src="docs/assets/rain-logo.svg" alt="Rain" width="96">
  <h1>Rain</h1>
  <p>Build Android APKs without Gradle, on phones and small Linux boxes.</p>
</div>

<p align="center">
  <a href="https://github.com/itskreisler/rain/actions/workflows/ci.yml"><img src="https://github.com/itskreisler/rain/actions/workflows/ci.yml/badge.svg" alt="CI"></a>
  <a href="https://github.com/itskreisler/rain/releases"><img src="https://img.shields.io/github/v/release/itskreisler/rain" alt="Release"></a>
  <a href="LICENSE"><img src="https://img.shields.io/github/license/itskreisler/rain" alt="License"></a>
  <a href="https://go.dev/"><img src="https://img.shields.io/badge/Go-stable-00ADD8?logo=go&amp;logoColor=white" alt="Go"></a>
  <a href="https://developer.android.com/"><img src="https://img.shields.io/badge/Android-APK-3DDC84?logo=android&amp;logoColor=white" alt="Android"></a>
</p>

## Overview

Gradle is heavy for Termux, small containers and older phones: it needs a JVM
daemon, large caches and a Gradle wrapper. Rain is a single Go binary that runs
the essential Android build pipeline directly, with no persistent daemon and no
Gradle project required.

Rain targets:

- Android projects created with `rain init`.
- Flat `android2apk` projects that already have `AndroidManifest.xml`.
- Java, Kotlin and Jetpack Compose apps, including multi-module layouts.

### Build pipeline

Rain executes the standard Android build pipeline:

1. **Dependency resolution** (full mode) - Parse Maven POMs, resolve transitive dependencies, download AAR/JAR to `libs/`.
2. **Resource compilation** - `aapt2 compile` compiles `res/` to `.flat` files.
3. **Resource linking** - `aapt2 link` generates `R.java` and the base APK from the manifest and resources.
4. **BuildConfig generation** - Create `BuildConfig.java` with `DEBUG`, `APPLICATION_ID`, `VERSION_NAME`, etc.
5. **Kotlin compilation** - `kotlinc` compiles `.kt` files to `.class` (if present).
6. **Java compilation** - `javac` compiles `.java` files, including generated `R.java` and `BuildConfig.java`.
7. **DEX conversion** - `d8` converts `.class` to `classes.dex`. In release mode, `R8` performs minification and obfuscation.
8. **APK packaging** - Add `classes.dex` to the base APK.
9. **Alignment** - `zipalign` optimizes the APK for memory.
10. **Signing** - `apksigner` signs with a debug or release keystore.

Incremental builds use SHA-256 hashes of sources and resources to reuse the
previous `classes.dex` and base APK when nothing changed.

## Features

- **Lightweight** - Single Go binary with instant startup and low memory footprint.
- **Dependency resolution** - Reads Maven POMs, handles parent inheritance and `dependencyManagement` (full mode).
- **Mixed language support** - Compile Java and Kotlin in the same project.
- **Incremental builds** - SHA-256 hashing of sources and resources skips unnecessary recompilation.
- **Fast type checking** - `rain check` validates types without generating an APK.
- **Static analysis** - `rain lint` integrates ktlint, detekt and PMD.
- **Formatting** - `rain fmt` formats Kotlin and Java in place or checks it with `--check`.
- **Release builds** - Minify with R8 and sign with your keystore.
- **Multi-module support** - Define multiple modules in `rain.toml`.
- **Managed toolchain** - Downloads R8, `apksigner`, `android.jar`, Kotlin and the Compose plugin to `~/.rain/`.
- **android2apk compatible** - Drop-in replacement for projects using the flat `android2apk` layout.

## Installation

### Requirements

- **OS**: Termux or Linux with glibc/musl (tested on Alpine in containers).
- **JDK**: Java 17 or higher. JDK 17 is recommended; JDK 21 may be unstable under PRoot.
- **aapt2**: Install it from the system package manager: `pkg install aapt2` on Termux or `apk add android-build-tools` on Alpine.
- **zipalign**: Needed by the packaging step; it comes from the same system packages.

On Linux arm64, Rain uses the system `aapt2` and `zipalign`. Google only ships
x86-64 Linux build-tools binaries, so Rain does not copy them into `~/.rain/`.

### Prebuilt binaries

Every [release](https://github.com/itskreisler/rain/releases) includes binaries
for Linux amd64, Linux arm64, Windows amd64 and Windows arm64. `SHA256SUMS`
contains the checksums for all four files.

### From source

```bash
git clone https://github.com/itskreisler/rain.git
cd rain
make build
# Optional: install globally in Termux
cp rain "$PREFIX/bin/"
```

`go build -o rain .` also works, but `make build` injects version, commit and
build date.

## Usage

### 1. Install the toolchain

```bash
rain doctor --fix
```

Add `--profile complex` for Kotlin, Compose and AndroidX. If you already have
`android.jar`, pass it with `--android-jar <path>` to `build` or `check`.

### 2. Initialize a project

```bash
rain init
```

Choose a resolution mode:

- `simple` - Explicit dependencies only, no transitive resolution. Best for small projects.
- `completo` (full) - Resolves transitive dependencies from Maven POMs. Required for AndroidX.

### 3. Build an APK

```bash
# Debug build with --devlog enabled while diagnosing a build
rain --devlog build

# Release build with R8 minification
rain build --release

# Build from a specific directory
rain build /path/to/project

# Type-check without producing an APK
rain check

# Remove generated artifacts
rain clean
```

APKs are generated in `app/build/` as `app-debug.apk`, `app-release.apk` and a
descriptive copy such as `<name>-<version>-<abi>.apk`.

### Commands

| Command | Description | Flags |
| --- | --- | --- |
| `rain init` | Initialize a new project | `--template simple\|completo` |
| `rain build [dir]` | Build and sign APK; auto-detects the flat android2apk layout | `--android-jar <path>`, `--release`, `--allow-debug-signing`, `--verbose`, `--quiet` |
| `rain check [dir]` | Type-check without generating an APK | `--fast`, `--android-jar <path>`, `--release` |
| `rain lint [dir]` | Run static analysis (ktlint, detekt, PMD) | `--install`, `--tools`, `--fail-on`, `--type-resolution` |
| `rain fmt [dir]` | Format Kotlin (ktlint) and Java (google-java-format) | `--check` |
| `rain doctor` | Diagnose and install the toolchain | `--profile simple\|complex`, `--fix`, `--lint`, `--install`, `--yes`, `--dry-run`, `--json`, `--print-install` |
| `rain gradle` | Import a Gradle project and generate `rain.toml` | — |
| `rain deps` | Dependency management commands | — |
| `rain version` | Show the Rain version | — |
| `rain clean` | Remove build artifacts, dependencies and caches | — |
| `rain completion` | Generate shell autocompletion scripts | — |

### Project layout

```text
my-app/
├── rain.toml          # Build configuration
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── AndroidManifest.xml
│   │       ├── java/      # Java sources
│   │       ├── kotlin/    # Kotlin sources (optional)
│   │       └── res/       # Resources
│   └── build/         # Generated APKs and build artifacts
├── libs/              # Downloaded dependencies (generated)
└── cache/             # POM cache (generated)
```

### Examples

| Example | What it shows |
| --- | --- |
| `examples/compose` | Jetpack Compose with Material 3, icons and an adaptive launcher icon. |
| `examples/liquid-glass` | A glass UI effect over the content behind it. |
| `examples/backdrop-music` | A real audio player: `MediaStore`, `MediaPlayer` and a real equalizer. |
| `examples/hello-buddy` | The flat android2apk layout, without `rain.toml`. |
| `examples/contador-cofres` | A floating overlay with a foreground service, Compose UI and local JSON history. |

### Migration from android2apk

Projects using the flat `android2apk` layout (`app.properties`, setup scripts)
work without modification:

```bash
rain build /path/to/android2apk-project
```

Rain detects the layout automatically: without `rain.toml`, if the current
directory has `AndroidManifest.xml` at the root or in `src/main/`, `rain build`
and `rain check` use the flat flow. See the [migration guide](docs/migracion-android2apk.md)
for details on converting `app.properties`, Compose dependencies, toolchain
setup and signing.

### `check` vs `lint`

| Feature | `rain check` | `rain lint` |
| --- | --- | --- |
| Purpose | Type checking via compiler | Style and code quality analysis |
| Detects | Unresolved references, broken imports, type errors | Formatting issues, unused imports, complexity, code smells |
| Tool | kotlinc/javac | ktlint, detekt, PMD |
| Speed | Fast with incremental cache (~3s) | ~20s |
| Output | Compilation errors | Warnings and style violations |

A linter cannot replace type checking: `check` compiles the code, while `lint`
analyzes source text.

## Configuration

### `rain.toml`

```toml
mode = "completo"

dependencies = [
    "androidx.core:core:1.12.0",
    "androidx.appcompat:appcompat:1.6.1",
    "androidx.compose.material3:material3-android:1.5.0-beta01",
    # "local:milibreria:1.0" reads native/milibreria-1.0.aar (or .jar).
]

[project]
name = "MyApp"
package = "com.example.myapp"
version = "1.0"

[build]
debug = true

[signing]
store_file = "release.keystore"
store_password = "password"
key_alias = "alias"
key_password = "password"  # Optional, defaults to store_password

[[modules]]
name = "app"
path = "app"

[repositories]
extra = ["https://my-nexus.local/repository/maven"]  # Optional custom repos
```

Unknown keys fail parsing. Root keys (`mode`, `dependencies`, `modules`) must
appear before any table. Without `rain.toml`, `app.properties` is used as a
fallback and the flat android2apk layout is auto-detected.

A `local:<artifact>:<version>` dependency is read from
`native/<artifact>-<version>.aar` (or `.jar`) and copied into Rain's dependency
cache; it skips Maven and POM resolution entirely.

For Gradle projects with `productFlavors`, set `flavor = "github"` under
`[build]` to compile `app/src/<flavor>/java` and `app/src/<flavor>/res` in
addition to `src/main` (flavor resources override main resources of the same
name). `rain gradle` writes the first declared flavor automatically. Only the
selected variant is compiled.

Native libraries under `app/src/main/jniLibs/<abi>/*.so` (or `jniLibs/<abi>/`
in a flat project) are packaged into `lib/<abi>/` automatically and take
precedence over libraries coming from AAR dependencies. `rain gradle` warns
when a project declares `productFlavors`: Rain only compiles `src/main`, so
the files of the chosen variant must be copied there first.

### Environment variables

| Variable | Purpose |
| --- | --- |
| `RAIN_ANDROID_JAR` | Override the detected `android.jar`. |
| `RAIN_JAVA_MAX_HEAP` | Heap for d8, R8 and kotlinc (for example `2g`). |
| `RAIN_GOMEMLIMIT` | Soft memory limit for the Go runtime. |
| `RAIN_GOGC` | Go garbage collector target. |
| `NO_COLOR` | Disable ANSI colors. |

### Global flags

| Flag | Description |
| --- | --- |
| `--devlog` | Show development logs for CLI functions. |
| `-v, --verbose` | Show detailed execution output. |
| `-q, --quiet` | Show only errors. |
| `--no-color` | Disable ANSI colors. |
| `-h, --help` | Show help for any command. |

### Lint and formatting

Install the optional analyzers:

```bash
rain lint --install
# or
rain doctor --fix --lint
```

Configure them in `rain.toml`:

```toml
[lint]
tools = ["ktlint", "detekt", "pmd"]     # or ["auto"]
fail_on = "error"                       # never | warning | error
type_resolution = false                 # Enable detekt type resolution (slower)
pmd_ruleset = "config/pmd-ruleset.xml"  # Optional custom PMD ruleset
pmd_baseline = "config/pmd-baseline.txt" # Known PMD findings to ignore

[format]
java_style = "aosp"                    # google | aosp
java_line_length = 120                 # Max columns
java_skip_imports = true               # Keep existing import order
java_files = ["app/java/**/New*.java"] # Optional include globs
java_exclude = ["**/Generated.java"]   # Optional exclude globs
```

`rain lint` respects `.editorconfig` for ktlint, `detekt.yml` for detekt, and
`pmd-ruleset.xml` or `config/pmd-ruleset.xml` for PMD. The PMD baseline uses one
`path|rule` per line and hides known findings.

`rain fmt` reformats Kotlin and Java in place. Kotlin takes its style from
`.editorconfig`; Java uses `[format]` in `rain.toml`. Use `java_files` and
`java_exclude` to limit formatting to files you own. Patterns accept `*`, `?`
and `**`; `!` also works inside `java_files`.

```bash
rain fmt            # Current directory
rain fmt path/to/project
rain fmt --check    # Dry run: fail if any file needs formatting
```

## Documentation

| Document | Description |
| --- | --- |
| [docs/doctor.md](docs/doctor.md) | `rain doctor` checks and installation flags. |
| [docs/toolchain.md](docs/toolchain.md) | Toolchain contents in `~/.rain/` and rationale. |
| [docs/compatibilidad.md](docs/compatibilidad.md) | JDK / Kotlin / R8 / compileSdk compatibility matrix. |
| [docs/memoria.md](docs/memoria.md) | Memory management for containers (`GOMEMLIMIT`, `GOGC`, JVM heap). |
| [docs/migracion-android2apk.md](docs/migracion-android2apk.md) | Porting guide from android2apk. |
| [SECURITY.md](SECURITY.md) | Supported versions and vulnerability reporting. |

### Troubleshooting

| Issue | Solution |
| --- | --- |
| `aapt2: command not found` or a broken `aapt2` on Linux arm64 | Install system build tools: `pkg install aapt2` (Termux) or `apk add android-build-tools` (Alpine). Rain removes stale x86-64 copies from `~/.rain`. |
| `package X does not exist` during compilation | Dependencies may be missing. Delete `libs/` and rebuild to re-download. |
| Jansi error when compiling Kotlin | Install libjansi: `pkg install libjansi`. Rain passes `-J-Dlibrary.jansi.path=...` automatically. |
| Progress bar not showing | Ensure `libs/` does not exist or is empty. Rain only shows download progress while downloading. |
| `fatal error: found pointer to free object` or OOM | Common in low-RAM containers or under PRoot. Rain detects PRoot and applies conservative GC. Try `export GOGC=15 GOMEMLIMIT=1200MiB`. See [docs/memoria.md](docs/memoria.md). |

## Development

Use the latest stable Go release, both locally and in CI. JDK 17 is recommended.

```bash
make build                 # Build ./rain with version, commit and date
go build ./...             # Compile check only
make test                  # go test ./...
go vet ./...               # Static analysis
make lint                  # golangci-lint (goimports included)
make tools                 # Install lint tools if missing
make fmt                   # gofmt
```

Run a single package test with `go test ./internal/build -run TestName -v`.
A regular `go test ./...` may skip the full APK integration test when the local
toolchain is incomplete. To exercise a real build, run
`./rain --devlog build` inside `examples/compose`, `examples/liquid-glass`,
`examples/backdrop-music` or `examples/hello-buddy`. Do not run `rain build` at
the repository root: its `rain.toml` points to an absent `app/` directory.

CI runs tests, CLI smoke checks, APK builds for the examples and a release job.
The last commit message can include `[skip test]`, `[skip windows]`,
`[skip examples]` or `[skip release]` to skip a whole job. For finer control,
use `[skip windows-tests]`, `[skip windows-apk]`, `[skip examples-debug]` or
`[skip examples-release]` to skip only those steps. Skipped jobs and steps do
not block the release; real failures do. These tokens do not apply to pull
requests, because the workflow reads the pushed commit message.

## Contributing

1. Open an issue before a large change so the approach can be discussed.
2. Keep changes focused and add tests for behavior changes.
3. Use Conventional Commits: `feat:`, `fix:`, `docs:`, etc. They drive releases.
4. Run `go vet ./...`, `go test ./...` and `make lint` before opening a PR.
5. Keep comments, CLI output, tests and docs in Spanish; keep this README in English.
6. Update `CHANGELOG.md` under `[No publicado]` for user-visible changes.

Do not commit the `rain` binary, APKs, keystores or generated cache directories.

## Security

Report vulnerabilities following [SECURITY.md](SECURITY.md). Prefer a private
contact with the maintainers and do not include exploit details in a public
issue. Only the latest release from `main` receives security patches.

## License

See [LICENSE](LICENSE) for details.
