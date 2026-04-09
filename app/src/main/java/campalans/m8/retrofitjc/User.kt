package campalans.m8.retrofitjc

// Este sustituye a Device
data class User(
    val id: Int? = null,
    val name: String,
    val email: String,
    val phone: String
)

// Si la API devuelve un listado envuelto o necesitas una estructura de respuesta
data class UsuariosResponse(
    val users: List<User>
)