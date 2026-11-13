package com.kleilabs.piratetokencounter.model

/**
 * Recompensas que puede dar un cofre durante una temporada de Cazatesoros
 * (Treasure Hunters).
 *
 * En el Contador de escritorio los tesoros se detectan leyendo la repetición:
 * el juego manda el `PrizeId` del cofre dorado y el manejador del premio. En
 * el móvil no hay repeticiones, así que el jugador registra el tesoro a mano y
 * puede elegir cuál fue.
 *
 * Las probabilidades son las que publica Epic para la temporada y no cambian
 * durante el día, salvo en las Horas del tesoro, donde se multiplican. Sirven
 * para darle contexto al contador: un artilugio cae 1 de cada 127 cofres, un
 * atuendo dorado 1 de cada 1.562.500.
 *
 * @param odds un tesoro de este tipo sale 1 de cada [odds] cofres.
 * @param gizmo si es un artilugio: marca el sitio de un cofre de tesoro.
 */
enum class TreasureKind(val label: String, val odds: Int, val color: Int, val gizmo: Boolean = false) {
    EXTRACTION_ACCELERATOR("Acelerador de extracción", 127, 0xFF9EDDD6.toInt(), true),
    PORTABLE_EXTRACTOR("Extractor portátil", 127, 0xFF9EDDD6.toInt(), true),
    SPRITE_LOCATOR("Localizador de espíritus", 127, 0xFF9EDDD6.toInt(), true),
    LUCKY_LOCATOR("Localizador de la suerte", 127, 0xFF9EDDD6.toInt(), true),
    GOLDEN_FIREWORK("Fuego artificial dorado", 127, 0xFFD7B6FF.toInt(), true),

    LOADING_SCREEN("Pantalla de carga", 272, 0xFFD7B6FF.toInt()),
    JAM_TRACK("Pista de improvisación", 391, 0xFF67B7F0.toInt()),
    RETRO_PICKAXE("Pico o mochila retro", 568, 0xFFE8AA79.toInt()),
    VEHICLE("Vehículo y calcomanía", 7_813, 0xFF9EDDD6.toInt()),
    PIRATE_SHOP("Acceso a la Tienda pirata", 137_500, 0xFFFFC857.toInt()),
    GOLDEN_OUTFIT("Atuendo dorado", 1_562_500, 0xFFFFC857.toInt()),
    ;

    /** Texto corto para la superposición, donde no cabe el nombre entero. */
    val shortLabel: String
        get() = when (this) {
            EXTRACTION_ACCELERATOR -> "Acelerador"
            PORTABLE_EXTRACTOR -> "Extractor"
            SPRITE_LOCATOR -> "Localizador"
            LUCKY_LOCATOR -> "Localizador suerte"
            GOLDEN_FIREWORK -> "Fuego dorado"
            LOADING_SCREEN -> "Pantalla"
            JAM_TRACK -> "Pista"
            RETRO_PICKAXE -> "Pico"
            VEHICLE -> "Vehículo"
            PIRATE_SHOP -> "Tienda pirata"
            GOLDEN_OUTFIT -> "Atuendo"
        }

    companion object {
        /** Los artilugios: lo más común que puede salir de un cofre. */
        val gizmos: List<TreasureKind> = entries.filter { it.gizmo }

        /** Probabilidad de al menos un artilugio en [chests] cofres. */
        fun gizmoChance(chests: Int): Double {
            if (chests <= 0) return 0.0
            val perChest = 1.0 / (GOLDEN_FIREWORK.odds.toDouble())
            return 1.0 - Math.pow(1.0 - perChest, chests.toDouble())
        }

        fun fromName(name: String?): TreasureKind? = entries.firstOrNull { it.name == name }
    }
}
