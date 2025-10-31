package com.example.ontourapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ontourapp.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {

    // Declarar la variable de View Binding
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar View Binding y establecer la vista raíz
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Listener principal para el botón de Ingresar
        binding.btnLogin.setOnClickListener {
            if (validarFormulario()) {
                iniciarHome()
            }
        }

        // Configuración para el botón de Login con Google (simulado)
        setupSocialLogin()

        // Configuración inicial de credenciales de prueba en el campo Email
        binding.etEmail.setText("fabiolavillagra@gmail.com")
        binding.etPassword.setText("123456")
    }

    private fun validarFormulario(): Boolean {
        var esValido = true

        // 1. Validar Email
        val email = binding.etEmail.text.toString()
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = "Ingresa un email válido."
            esValido = false
        } else {
            binding.tilEmail.error = null
        }

        // 2. Validar Contraseña (mínimo 6 caracteres)
        val password = binding.etPassword.text.toString()
        if (password.length < 6) {
            binding.tilPassword.error = "La contraseña debe tener al menos 6 caracteres."
            esValido = false
        } else {
            binding.tilPassword.error = null
        }

        // 3. Validar Credenciales (Credenciales de Prueba Personalizadas)
        if (esValido) {
            // Verifica que las credenciales coincidan con las personalizadas
            if (email != "fabiolavillagra@gmail.com" || password != "123456") {

                // Muestra Snackbar con acción (cumple Animaciones y Diseño)
                mostrarSnackbarConAccion(
                    "Credenciales incorrectas. ¿Deseas registrarte?",
                    "REGISTRARSE"
                )
                esValido = false
            }
        }

        return esValido
    }

    private fun iniciarHome() {
        // Navegación funcional a la HomeActivity
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    // --- MÉTODOS DE SIMULACIÓN PARA NAVEGACIÓN (Llamados desde el XML o el Snackbar) ---

    // Función que muestra el Snackbar animado con la opción de registro
    private fun mostrarSnackbarConAccion(mensaje: String, accionTexto: String) {
        // Muestra el Snackbar (Animación de Material Design)
        Snackbar.make(binding.root, mensaje, Snackbar.LENGTH_LONG)
            .setAction(accionTexto) {
                // Llama a la simulación de registro cuando se presiona el botón del Snackbar
                onSignUpClicked(it)
            }
            .show()
    }

    // Simulación de navegación para "Olvidaste tu contraseña?"
    fun onForgotPasswordClicked(view: View) {
        Toast.makeText(this, "Simulando recuperación de contraseña...", Toast.LENGTH_SHORT).show()
    }

    // Simulación de navegación para la pantalla de Registro
    fun onSignUpClicked(view: View) {
        Toast.makeText(this, "Simulando navegación a la pantalla de Registro...", Toast.LENGTH_SHORT).show()
    }

    // Simulación para el botón de Google/Redes Sociales
    private fun setupSocialLogin() {
        binding.btnGoogleLogin.setOnClickListener {
            Toast.makeText(this, "Simulando Login con Google...", Toast.LENGTH_SHORT).show()
        }
    }
}