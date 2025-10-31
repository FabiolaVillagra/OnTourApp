package com.example.ontourapp.data

data class GiraProgreso(
    val nombreUsuario: String,
    val montoAportado: Int,
    val montoMeta: Int,
    val alumnosInscritos: Int,
    val alumnosMeta: Int,
    val estadoParticipacion: String
) {
    // Función para calcular el porcentaje financiero de la gira
    fun getPorcentajeProgreso(): Int {
        if (montoMeta == 0) return 0
        return ((montoAportado.toDouble() / montoMeta.toDouble()) * 100).toInt()
    }
}