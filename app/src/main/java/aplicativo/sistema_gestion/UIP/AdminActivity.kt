package aplicativo.sistema_gestion.UIP

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import aplicativo.sistema_gestion.R

class AdminActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_activity) // Asegúrate de tener este layout creado

        val btnLogout = findViewById<Button>(R.id.btnLogout) // ID corregido para coincidir con el XML

        btnLogout.setOnClickListener {
            val prefs = getSharedPreferences("loginPrefs", Context.MODE_PRIVATE)
            prefs.edit().clear().apply()

            Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
