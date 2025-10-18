package aplicativo.sistema_gestion.UIP

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import aplicativo.sistema_gestion.R
import aplicativo.sistema_gestion.ViewModel.ClienteViewModel
import aplicativo.sistema_gestion.UIP.AddClienteActivity

class LoginActivity : AppCompatActivity() {

    private val viewModel: ClienteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs = getSharedPreferences("loginPrefs", Context.MODE_PRIVATE)
        val isLoggedIn = prefs.getBoolean("isLoggedIn", false)

        if (isLoggedIn) {
            startActivity(Intent(this, AddClienteActivity::class.java))
            finish()
            return
        }

        setContentView(R.layout.login_cliente)

        val edtCorreo = findViewById<EditText>(R.id.edtCorreo) // cambia el id si es necesario
        val edtContrasena = findViewById<EditText>(R.id.edtContrasena)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val correo = edtCorreo.text.toString().trim()
            val contraseña = edtContrasena.text.toString()

            if (correo.isEmpty() || contraseña.isEmpty()) {
                Toast.makeText(this, "Debe ingresar correo y contraseña", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewModel.validarLogin(correo, contraseña) { cliente ->
                if (cliente != null) {
                    prefs.edit().putBoolean("isLoggedIn", true).apply()
                    // Aquí chequeas el rol y diriges a la actividad adecuada
                    when (cliente.rol) {
                        "admin" -> {
                            // Por ejemplo, una pantalla AdminActivity (crea esta)
                            startActivity(Intent(this, AdminActivity::class.java))
                        }
                        "cliente" -> {
                            // Pantalla para clientes
                            startActivity(Intent(this, AddClienteActivity::class.java))
                        }
                        else -> {
                            Toast.makeText(this, "Rol no reconocido", Toast.LENGTH_SHORT).show()
                            return@validarLogin
                        }
                    }
                    finish()
                } else {
                    Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                }
            }


        }
    }
}