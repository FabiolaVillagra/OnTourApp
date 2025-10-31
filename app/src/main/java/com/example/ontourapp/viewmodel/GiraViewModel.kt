// GiraViewModel.kt (en com.example.ontourapp.viewmodel)
package com.example.ontourapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ontourapp.data.GiraProgreso

class GiraViewModel : ViewModel() {

    private val _progreso = MutableLiveData<GiraProgreso>()
    val progreso: LiveData<GiraProgreso> = _progreso

    init {
        // Carga de datos iniciales (Simulación del estado de Katherine Rojas)
        _progreso.value = GiraProgreso(
            nombreUsuario = "Fabiola Villagra",
            montoAportado = 450000, // 60%
            montoMeta = 750000,
            alumnosInscritos = 8,
            alumnosMeta = 20,
            estadoParticipacion = "¡Ya estás inscrito oficialmente en la gira!"
        )
    }

    // Función clave: actualiza el monto aportado y notifica a la HomeActivity
    fun registrarAporte(montoNuevo: Int) {
        val progresoActual = _progreso.value ?: return
        val nuevoMonto = progresoActual.montoAportado + montoNuevo

        // Crea un nuevo estado inmutable con el monto actualizado
        _progreso.value = progresoActual.copy(montoAportado = nuevoMonto)
    }
}