package josue.hernandez.practica1moviles

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    companion object {
        const val LOCATION_PERMISSION_REQUEST_CODE = 1
        const val CAMERA_PERMISSION_REQUEST_CODE = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Configuración para compatibilidad con el diseño edge-to-edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Botón para solicitar permiso de ubicación
        val btnSolicitarUbicacion = findViewById<Button>(R.id.btn_solicitar_ubicacion)
        btnSolicitarUbicacion.setOnClickListener {
            requestLocationPermission() // Llama a la función para solicitar permisos de ubicación
        }

        // Botón para solicitar permiso de cámara
        val btnSolicitarCamara = findViewById<Button>(R.id.btn_solicitar_camara)
        btnSolicitarCamara.setOnClickListener {
            requestCameraPermission() // Llama a la función para solicitar permisos de cámara
        }
    }

    // Función para solicitar permisos de ubicación (tanto fina como aproximada)
    private fun requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Solicita los permisos tanto para ubicación precisa (fine) como aproximada (coarse)
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            // Si el permiso ya ha sido concedido
            Toast.makeText(this, "Permiso de ubicación ya concedido", Toast.LENGTH_SHORT).show()
        }
    }

    // Función para solicitar permiso de cámara
    private fun requestCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // Solicita el permiso de cámara
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_REQUEST_CODE)
        } else {
            // Si el permiso ya ha sido concedido
            Toast.makeText(this, "Permiso de cámara ya concedido", Toast.LENGTH_SHORT).show()
        }
    }

    // Manejo del resultado de las solicitudes de permisos
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // El permiso de ubicación ha sido concedido
                    Toast.makeText(this, "Permiso de ubicación concedido", Toast.LENGTH_SHORT).show()
                } else {
                    // El permiso de ubicación ha sido denegado
                    Toast.makeText(this, "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show()
                }
            }
            CAMERA_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // El permiso de cámara ha sido concedido
                    Toast.makeText(this, "Permiso de cámara concedido", Toast.LENGTH_SHORT).show()
                } else {
                    // El permiso de cámara ha sido denegado
                    Toast.makeText(this, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
