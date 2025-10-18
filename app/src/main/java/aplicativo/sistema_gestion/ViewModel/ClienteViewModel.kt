package aplicativo.sistema_gestion.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import aplicativo.sistema_gestion.model.Cliente
import aplicativo.sistema_gestion.repository.ClienteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClienteViewModel : ViewModel() {
    private val repository = ClienteRepository()
    val listaClientes = MutableLiveData<List<Cliente>>()

    fun cargarClientes() {
        repository.listarClientes().enqueue(object : Callback<List<Cliente>> {
            override fun onResponse(call: Call<List<Cliente>>, response: Response<List<Cliente>>) {
                if (response.isSuccessful) {
                    listaClientes.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<Cliente>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
    fun registrarCliente(cliente: Cliente, callback: (Boolean, String?) -> Unit) {
        repository.registrarCliente(cliente).enqueue(object : retrofit2.Callback<Cliente> {
            override fun onResponse(call: retrofit2.Call<Cliente>, response: retrofit2.Response<Cliente>) {
                if (response.isSuccessful) {
                    callback(true, null)
                } else {
                    callback(false, response.message())
                }
            }

            override fun onFailure(call: retrofit2.Call<Cliente>, t: Throwable) {
                callback(false, t.message)
            }
        })


}
    fun validarLogin(correo: String, contraseña: String, callback: (Cliente?) -> Unit) {
        // Simulación: usuarios hardcodeados
        val admin = Cliente(
            nombre = "Administrador",
            correo = "admin@example.com",
            contraseña = "admin123",
            dpi = "",
            telefono = "",
            direccion = "",
            rol = "admin"
        )

        val cliente = Cliente(
            nombre = "Usuario Cliente",
            correo = "cliente@example.com",
            contraseña = "cliente123",
            dpi = "",
            telefono = "",
            direccion = "",
            rol = "cliente"
        )

        val usuarioValidado = when {
            correo == admin.correo && contraseña == admin.contraseña -> admin
            correo == cliente.correo && contraseña == cliente.contraseña -> cliente
            else -> null
        }

        callback(usuarioValidado)
    }


}
