package aplicativo.sistema_gestion.model

data class Cliente(
    val idUsuario: Long? = null,
    var nombre: String,
    var correo: String,
    var contraseña: String,
    var confirmarContraseña: String? = null,
    var rol: String = "Cliente",
    var dpi: String,
    var telefono: String,
    var direccion: String,
    var historialsesiones: String? = null,
    var estado: Boolean = true

)
