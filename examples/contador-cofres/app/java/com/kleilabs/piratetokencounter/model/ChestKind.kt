package com.kleilabs.piratetokencounter.model

/**
 * Tipos de contenedor que el jugador puede registrar.
 *
 * El vocabulario viene del analizador de repeticiones del Contador de
 * escritorio (`ChestKinds` en el proyecto original): seis clases de cofre
 * suman al total y el resto de contenedores se registra aparte, porque no son
 * cofres aunque se parezcan.
 *
 * En el móvil no hay repeticiones que analizar, así que la misma taxonomía se
 * usa como rejilla de botones: el jugador elige a mano qué ha abierto.
 */
enum class ChestKind(
    /** Nombre completo, como aparece en el historial y en el resumen. */
    val label: String,
    /** Nombre abreviado, para los botones de la superposición. */
    val shortLabel: String,
    /** Si el contenedor cuenta para el total de cofres. */
    val counted: Boolean,
    /** Color del tipo, taken del informe HTML del Contador de escritorio. */
    val color: Int,
) {
    NORMAL("Cofre", "Cofre", true, 0xFFFFC857.toInt()),
    RARE("Cofre raro", "Raro", true, 0xFF67B7F0.toInt()),
    BURIED("Cofre enterrado", "Enterrado", true, 0xFFE8AA79.toInt()),
    SPIRIT("Cofre de espíritu (raro)", "Espíritu", true, 0xFF9EDDD6.toInt()),
    POSTER("Cofre de los carteles de espíritu", "Carteles", true, 0xFFD7B6FF.toInt()),
    OTHER_CHEST("Otro cofre", "Otro", true, 0xFF9A8F7A.toInt()),
    LOOT_CANNON("Cañón de botín", "Cañón", false, 0xFF7F8FA6.toInt()),
    AMMO_BOX("Caja de munición", "Munición", false, 0xFF8FA3BF.toInt()),
    FOOD_BOX("Caja de comida", "Comida", false, 0xFFB9A98C.toInt()),
    OTHER_CONTAINER("Otro contenedor", "Contenedor", false, 0xFF6F8098.toInt()),
    ;

    companion object {
        /** Los cofres que suman al total, en el orden en que se muestran. */
        val chests: List<ChestKind> = entries.filter { it.counted }

        /** Los contenedores que se registran pero no suman al total. */
        val containers: List<ChestKind> = entries.filter { !it.counted }

        /** Tipos que se pueden marcar como destruidos en vez de abiertos. */
        val destructible: List<ChestKind> = chests

        fun fromName(name: String?): ChestKind = entries.firstOrNull { it.name == name } ?: NORMAL
    }
}
