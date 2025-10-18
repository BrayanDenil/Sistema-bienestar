package aplicativo.sistema_gestion.UIP


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import aplicativo.sistema_gestion.R
import aplicativo.sistema_gestion.model.Cliente
import aplicativo.sistema_gestion.ViewModel.ClienteViewModel

class AddClienteActivity : ComponentActivity() {

    private val viewModel: ClienteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cliente_form)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val etContraseña = findViewById<EditText>(R.id.etContraseña)
        val etConfirmar = findViewById<EditText>(R.id.etConfirmar)
        val etDpi = findViewById<EditText>(R.id.etDpi)
        val etTelefono = findViewById<EditText>(R.id.etTelefono)
        val etDireccion = findViewById<EditText>(R.id.etDireccion)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        btnLogout.setOnClickListener {
            val prefs = getSharedPreferences("loginPrefs", Context.MODE_PRIVATE)
            prefs.edit().clear().apply()
            Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        btnRegistrar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val correo = etCorreo.text.toString().trim()
            val contraseña = etContraseña.text.toString()
            val confirmar = etConfirmar.text.toString()
            val dpi = etDpi.text.toString().trim()
            val telefono = etTelefono.text.toString().trim()
            val direccion = etDireccion.text.toString().trim()

            if (nombre.isEmpty() || correo.isEmpty() || contraseña.isEmpty() ||
                confirmar.isEmpty() || dpi.isEmpty() || telefono.isEmpty() || direccion.isEmpty()
            ) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (contraseña != confirmar) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val rol= "cliente"
            val cliente = Cliente(
                nombre = nombre,
                correo = correo,
                contraseña = contraseña,
                confirmarContraseña = confirmar,
                dpi = dpi,
                telefono = telefono,
                direccion = direccion,
                rol = rol
            )

            viewModel.registrarCliente(cliente) { success, mensaje ->
                if (success) {
                    Toast.makeText(this, "Cliente registrado correctamente", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Error: $mensaje", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}