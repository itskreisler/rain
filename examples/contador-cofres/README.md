# Contador de cofres (`examples/contador-cofres`)

Contador de cofres para **Android**, escrito en **Kotlin + Jetpack Compose**,
con una **superposición flotante** que va por encima del juego.

Es el port móvil del [Contador de cofres para
Windows](https://github.com/Axxon-g/contador-cofres-fornite) (licencia MIT),
que cuenta los cofres que abres leyendo las repeticiones que deja Fortnite en
el ordenador.

## Por qué cambia el producto

En el móvil **no hay repeticiones**: Fortnite en Android e iOS no tiene
sistema de repeticiones, así que no hay ningún fichero que analizar. El
lector automático no se puede portar.

Lo que sí se porta entero es la otra mitad del Contador de escritorio: el
vocabulario de cofres, la sesión, el historial por cuenta, el resumen con los
desgloses y la exportación a CSV. Y el overlay, que en el escritorio flotaba
encima del juego, aquí se convierte además en el **panel de botones** con el
que se cuenta.

> En el escritorio el recuento es automático y la app lo cuenta todo.
> En el móvil el recuento es manual: tú pulsas lo que abres. La app no finge
> detectar la partida ni el juego, igual que el escritorio no finge haber
> leído una repetición.

## Qué hace

Cuatro pantallas:

- **Resumen.** El total en grande, la media por partida, el récord, los
  tesoros encontrados, el reparto por tipo de cofre, el gráfico de cofres por
  día de los últimos 30 días y las partidas recientes.
- **Contador.** La partida en curso (modo, cofres, tesoros, contenedores), los
  chips de modo de juego, la rejilla de contenedores y el registro de
  tesoros.
- **Historial.** Todas las partidas con buscador por fecha o modo, el detalle
  de cada una y el borrado.
- **Ajustes.** La superposición (activar, permiso del sistema, estilo,
  tamaño, opacidad, plegar, bloquear), las cuentas y los datos (exportar el
  historial en CSV, borrarlo).

Y por encima de todo eso, la **superposición**:

- **Dos estilos.** HUD (cifras grandes sobre fondo casi transparente, al
  estilo del overlay del escritorio) o clásica (tarjeta oscura con borde).
- **Dos tamaños.** Completa, con la rejilla de contenedores, o compacta, con
  solo las cifras y un botón grande de cofre.
- **Se arrastra** por la cabecera y guarda la posición.
- **Se bloquea** para que no reciba toques ni se pueda mover por delante.
- **Se pliega** para no tapar el HUD del juego.
- Ajusta **tamaño** (70-175 %) y **opacidad** (30-100 %).

## Los cofres y los tesoros

El vocabulario viene del analizador de repeticiones del Contador de escritorio
(`ChestKinds`). Los seis primeros suman al total; el resto se registra aparte
y se marca como "(fuera del total)", porque una caja de munición no es un
cofre aunque se parezca.

| Suman al total | Fuera del total |
| --- | --- |
| Cofre | Cañón de botín |
| Cofre raro | Caja de munición |
| Cofre enterrado | Caja de comida |
| Cofre de espíritu (raro) | Otro contenedor |
| Cofre de los carteles de espíritu | |
| Otro cofre | |

Los **tesoros** son lo que da el Cazatesoros. En el escritorio se detectan
leyendo la repetición; aquí los registra el jugador y puede elegir cuál fue.
La app conoce las probabilidades publicadas por Epic y las enseña: un
artilugio sale 1 de cada 127 cofres, un atuendo dorado 1 de cada 1.562.500.
Con los cofres que llevas, la app calcula la probabilidad de haber sacado al
menos un artilugio.

| Tesoro | Probabilidad |
| --- | --- |
| Acelerador de extracción | 1 de cada 127 cofres |
| Extractor portátil | 1 de cada 127 cofres |
| Localizador de espíritus | 1 de cada 127 cofres |
| Localizador de la suerte | 1 de cada 127 cofres |
| Fuego artificial dorado | 1 de cada 127 cofres |
| Pantalla de carga | 1 de cada 272 cofres |
| Pista de improvisación | 1 de cada 391 cofres |
| Pico o mochila retro | 1 de cada 568 cofres |
| Vehículo y calcomanía | 1 de cada 7.813 cofres |
| Acceso a la Tienda pirata | 1 de cada 137.500 cofres |
| Atuendo dorado | 1 de cada 1.562.500 cofres |

## La superposición, en Android

Una ventana flotante es un caso aparte del resto de la app, y el código lo
trata como tal:

- **Permiso especial.** `SYSTEM_ALERT_WINDOW` no se concede con un diálogo:
  el jugador tiene que ir a los ajustes del sistema. La app lleva ahí y
  vuelve a comprobar el permiso en cada `ON_RESUME`.
- **Servicio en primer plano.** La ventana pertenece a la app que la crea: en
  cuanto el proceso puede morir, la ventana se va con él. El servicio usa el
  tipo `specialUse`, que es el previsto para los casos que no encajan en otro
  tipo de servicio en primer plano, y declara el subtipo en el manifest.
- **Views clásicas, no Compose.** El proceso está vivo durante toda la
  partida y no tiene sentido cargarle el runtime de Compose. La superposición
  son etiquetas y botones: `OverlayPanel`, `OverlayHeader` y `OverlayGrid` la
  construyen a mano. Los colores salen de `Palette.kt`, la misma fuente de
  verdad que usa la interfaz.
- **El foco sigue siendo del juego.** La ventana usa `FLAG_NOT_FOCUSABLE`, así
  que los botones funcionan sin que el teclado se quede atrapado en la
  superposición. Bloqueada añade `FLAG_NOT_TOUCHABLE` y el ratón la atraviesa.
- **Los OEM matan las apps en segundo plano.** Xiaomi, Oppo y Samsung cierran
  los servicios aunque la app esté en primer plano. Ajustes explica el
  problema y lleva a los ajustes de batería.

## Los datos

Todo se queda en el teléfono, en la carpeta privada de la app:

| Fichero | Contenido |
| --- | --- |
| `data.json` | Historial de partidas, partida en curso, cuentas y preferencias. |
| `config.json` | Preferencias de la superposición y cuenta seleccionada. |

Se escriben de forma atómica (a un temporal y se renombra) y, si un fichero
queda ilegible, se aparta una copia en lugar de perderlo en silencio. La
exportación a CSV usa el `FileProvider` de AndroidX y sale por el menú de
compartir: no hace falta ningún permiso de almacenamiento.

## Compilar

```bash
# desde la raíz del repo
make build
cd examples/contador-cofres
../../rain build              # APK de debug: app/build/app-debug.apk
../../rain build --release    # APK de release: app/build/app-release.apk
```

El release usa la keystore de ejemplo `release.keystore`, que **no** está en
el control de versiones (la ignora el `.gitignore` del repo) y cuyas
contraseñas están en `rain.toml` solo para poder compilar. Para una app de
verdad, usa tu propia keystore.

Comprobar la firma:

```bash
java -jar ~/.rain/toolchain/apksigner.jar verify --verbose app/build/app-release.apk
```

## Estilo y análisis

El ejemplo trae su propia configuración para que `rain lint` sea útil:

- `.editorconfig` con el estilo `android_studio` de ktlint, que es el de los
  proyectos de Compose (el de fábrica, `ktlint_official`, exige cosas que no
  encajan con los composables).
- `detekt.yml` con los umbrales del proyecto y las reglas que se adaptan a
  Compose: los composables se nombran en PascalCase y en la interfaz los
  tamaños y los colores son literales de por sí.

```bash
../../rain lint   # avisos de estilo, complejidad y code smells
../../rain fmt    # corrige lo que ktlint sabe corregir solo
```

## Estructura

```text
examples/contador-cofres/
├── rain.toml
├── detekt.yml
├── .editorconfig
└── app/
    ├── AndroidManifest.xml
    ├── res/
    │   ├── drawable/          iconos de Material Symbols y del launcher
    │   ├── mipmap-anydpi-v26/ iconos adaptativos
    │   ├── values/strings.xml
    │   └── xml/file_paths.xml
    └── java/com/kleilabs/piratetokencounter/
        ├── MainActivity.kt     la app: barra, pestañas y navegación
        ├── Contador.kt         el estado y las acciones
        ├── Changes.kt          avisos de cambio para la superposición
        ├── Profiles.kt         las cuentas
        ├── History.kt          borrar, vaciar y exportar
        ├── Palette.kt          los tokens del diseño
        ├── model/              cofres, tesoros, partidas y preferencias
        ├── core/               las cifras (Stats, Breakdown)
        ├── data/               persistencia JSON y CSV
        ├── overlay/            servicio, panel, cabecera y rejilla
        └── ui/                 las cuatro pantallas
```

## Créditos y avisos

- Port del [Contador de cofres para
  Windows](https://github.com/Axxon-g/contador-cofres-fornite) de Axxon-g,
  bajo licencia MIT. De ahí vienen el vocabulario de cofres, la semántica de
  la sesión, la estructura de las pantallas y los tokens del diseño.
- Los iconos son [Material
  Symbols](https://fonts.google.com/icons) de Google, descargados como
  vectores XML y guardados en `res/drawable/`.
- No es una aplicación oficial ni está aprobada por Epic Games. Fortnite es
  una marca de Epic Games.
