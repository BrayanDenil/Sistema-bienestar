package aplicativo.sistema_gestion.repository

import aplicativo.sistema_gestion.model.Cliente
import aplicativo.sistema_gestion.Network.RetrofitClient
import aplicativo.sistema_gestion.Network.ApiServiceCliente
import retrofit2.Call
class ClienteRepository {
    private val api = RetrofitClient.instance.create(ApiServiceCliente::class.java)

    fun listarClientes(): Call<List<Cliente>> = api.listarClientes()
    fun registrarCliente(cliente: Cliente): Call<Cliente> = api.registrarCliente(cliente)
    fun obtenerCliente(id: Long): Call<Cliente> = api.obtenerCliente(id)
    fun editarCliente(id: Long, cliente: Cliente): Call<Cliente> = api.editarCliente(id, cliente)
    fun desactivarCliente(id: Long): Call<Cliente> = api.desactivarCliente(id)
    suspend fun buscarPorCorreoYContrasena(correo: String, contraseña: String): Cliente? {
        return TODO("Provide the return value")
    }
}