package aplicativo.sistema_gestion.Network
import aplicativo.sistema_gestion.model.Cliente
import retrofit2.Call
import retrofit2.http.*


interface ApiServiceCliente{
    @GET("clientes")
    fun listarClientes():Call <List<Cliente>>

    @GET("clientes/{id}")
    fun obtenerCliente(@Path("id") id: Long): Call<Cliente>

    @POST("clientes")
    fun registrarCliente(@Body cliente: Cliente): Call<Cliente>

    @PUT("clientes/{id}")
    fun editarCliente(@Path("id") id: Long, @Body cliente: Cliente): Call<Cliente>

    @DELETE("clientes/{id}")
    fun eliminarCliente(@Path("id") id: Long): Call<Void>

    @PUT("clientes/{id}/desactivar")
    fun desactivarCliente(@Path("id") id: Long): Call<Cliente>

}