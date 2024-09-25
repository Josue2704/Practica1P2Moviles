Descripción General
Este proyecto consiste en una aplicación de Android donde trabajamos con diferentes tipos de permisos: permisos normales como el acceso a Internet, permisos peligrosos como la ubicación y la cámara, y permisos especiales como la posibilidad de mostrar ventanas sobre otras aplicaciones.
Permisos Implementados
1.	Permiso de Internet (Normal):
o	Este permiso es automático, es decir, el usuario no tiene que aprobar nada. Se usa para que la app pueda conectarse a Internet.
o	Se agrega directamente en el archivo AndroidManifest.xml
<uses-permission android:name="android.permission.INTERNET" />

Permiso de Ubicación (Peligroso):
•	En este caso, solicitamos acceso tanto a la ubicación precisa (GPS) como a la aproximada (red móvil o Wi-Fi). Como es un permiso peligroso, la app le pide al usuario que acepte o rechace el acceso.
•	Si el usuario concede el permiso, mostramos un mensaje diciendo que ya tiene acceso; si no, le avisamos que el permiso fue denegado.
•	Este permiso se maneja en el código de la siguiente manera
if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
    ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
    // Pedimos los permisos
    ActivityCompat.requestPermissions(
        this,
        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
        LOCATION_PERMISSION_REQUEST_CODE
    )
} else {
    // El permiso ya está concedido
    Toast.makeText(this, "Permiso de ubicación ya concedido", Toast.LENGTH_SHORT).show()
}

Permiso de Cámara (Peligroso):
•	Similar al de ubicación, este permiso también es peligroso, por lo que hay que pedir permiso al usuario en tiempo real. Si se concede, mostramos un mensaje; si no, también se le informa al usuario.
•	Este permiso se solicita de esta forma
if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_REQUEST_CODE)
} else {
    Toast.makeText(this, "Permiso de cámara ya concedido", Toast.LENGTH_SHORT).show()
}

Manejo de Respuestas:
•	Para ambos permisos (ubicación y cámara), el sistema tiene que manejar la respuesta del usuario. Si el usuario da permiso, todo sigue funcionando, pero si no lo da, se le avisa con un mensaje.
•	Esto lo manejamos en el método onRequestPermissionsResult
override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
) {
    if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permiso de ubicación concedido", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show()
        }
    }
    if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permiso de cámara concedido", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show()
        }
    }
}
