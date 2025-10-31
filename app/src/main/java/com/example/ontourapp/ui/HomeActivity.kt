package com.example.ontourapp.ui

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.example.ontourapp.data.GiraProgreso
import com.example.ontourapp.viewmodel.GiraViewModel
import com.example.ontourapp.databinding.ActivityHomeBinding // Importar Binding

class HomeActivity : AppCompatActivity() {

    private val viewModel: GiraViewModel by viewModels()
    private lateinit var binding: ActivityHomeBinding // Declarar Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicializar Binding y establecer la vista raíz
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Observa los cambios en el estado de la gira (Gestión de Estado)
        viewModel.progreso.observe(this) { progreso ->
            actualizarUI(progreso)
        }

        // Navegación a la actividad de la cámara/aporte
        binding.btnNuevoAporte.setOnClickListener {
            val intent = Intent(this, AporteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun actualizarUI(progreso: GiraProgreso) {
        // Acceso a las vistas a través de 'binding'
        binding.tvSaludo.text = "¡Bienvenida, ${progreso.nombreUsuario}!"
        binding.tvMontos.text = "$${progreso.montoAportado} / $${progreso.montoMeta}"
        binding.tvEstado.text = progreso.estadoParticipacion

        val nuevoPorcentaje = progreso.getPorcentajeProgreso()
        binding.tvPorcentaje.text = "$nuevoPorcentaje%"

        // Implementación de Animación
        animarProgreso(nuevoPorcentaje)
    }

    private fun animarProgreso(nuevoProgreso: Int) {
        // Objeto de animación accedido a través de 'binding'
        val animacion = ObjectAnimator.ofInt(binding.progressBar, "progress", binding.progressBar.progress, nuevoProgreso)
        animacion.duration = 800
        animacion.interpolator = DecelerateInterpolator()
        animacion.start()
    }
}