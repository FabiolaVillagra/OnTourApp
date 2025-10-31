package com.example.ontourapp.ui

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.ontourapp.databinding.ActivityAporteBinding // Importar Binding
import com.example.ontourapp.viewmodel.GiraViewModel
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AporteActivity : AppCompatActivity() {

    private val viewModel: GiraViewModel by viewModels()
    private lateinit var binding: ActivityAporteBinding // Declarar Binding

    private val REQUEST_CAMERA_PERMISSION = 100
    private val REQUEST_IMAGE_CAPTURE = 101
    private lateinit var currentPhotoPath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicializar Binding y establecer la vista raíz
        binding = ActivityAporteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. SIMULACIÓN (Reemplaza la llamada a la cámara)
        binding.btnTomarFoto.setOnClickListener {
            Toast.makeText(this, "Simulación de foto exitosa. Procede a registrar el aporte.", Toast.LENGTH_LONG).show()

            // Acceso a vistas a través de 'binding'
            binding.tvInstruccion.text = "Comprobante [SIMULADO] capturado. Presiona Simular Aporte."
            binding.btnSimularAporte.isEnabled = true
        }

        // 2. REGISTRO DE APORTE (Gestión de Estado)
        binding.btnSimularAporte.setOnClickListener {
            if (binding.btnSimularAporte.isEnabled) {
                simularRegistroYActualizarEstado()
            }
        }
    }

    private fun simularRegistroYActualizarEstado() {
        val montoAporte = 100000
        viewModel.registrarAporte(montoAporte)
        Toast.makeText(this, "Aporte de $${montoAporte} simulado y registrado.", Toast.LENGTH_LONG).show()
        finish()
    }

    // --- MÉTODOS DE CÁMARA ORIGINALES (Mantenidos para demostración del requisito) ---
    // Nota: El uso de 'binding' NO es necesario en estos métodos ya que no tocan la UI.

    private fun verificarPermisosYCapturar() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), REQUEST_CAMERA_PERMISSION)
        } else {
            dispatchTakePictureIntent()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent()
            } else {
                Toast.makeText(this, "Permiso de cámara denegado.", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            val photoFile: File? = try { createImageFile() } catch (ex: IOException) { null }

            photoFile?.also {
                val photoURI: Uri = FileProvider.getUriForFile(
                    this,
                    "${applicationContext.packageName}.fileprovider",
                    it
                )
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply { currentPhotoPath = absolutePath }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            // No podemos mostrar la foto sin el archivo real, solo activamos el botón de simulación visualmente.
            binding.tvInstruccion.text = "Comprobante capturado (Visualmente). Presiona Simular Aporte."
            binding.btnSimularAporte.isEnabled = true
        } else {
            binding.btnSimularAporte.isEnabled = false
        }
    }
}