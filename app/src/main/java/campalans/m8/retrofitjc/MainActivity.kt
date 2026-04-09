package campalans.m8.retrofitjc

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import campalans.m8.retrofitjc.ui.theme.RetrofitJCTheme
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(UserService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RetrofitJCTheme {
                var users by remember { mutableStateOf(listOf<User>()) }

                LaunchedEffect(Unit) {
                    getUsers { result -> users = result }
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        // 2. POST (Crear elemento)
                        FloatingActionButton(onClick = {
                            val nuevoUser = User(name = "Nuevo Usuario", email = "test@mail.com", phone = "600000000")
                            addUser(nuevoUser)
                        }) {
                            Icon(Icons.Default.Add, contentDescription = "Añadir")
                        }
                    }
                ) { innerPadding ->
                    MainView(
                        modifier = Modifier.padding(innerPadding),
                        users = users,
                        onDeleteClick = { id -> deleteUser(id) },
                        onUpdateClick = { id ->
                            val userActualizado = User(name = "Editado", email = "edit@mail.com", phone = "000")
                            updateUser(id, userActualizado)
                        }
                    )
                }
            }
        }
    }

    // --- MÉTODOS DE LA API ---

    private fun getUsers(onResult: (List<User>) -> Unit) {
        lifecycleScope.launch {
            try {
                val response = service.getUsers()
                onResult(response)
            } catch (e: Exception) {
                Log.e("API_ERROR", "Error al obtener usuarios: ${e.message}")
            }
        }
    }

    private fun addUser(user: User) {
        lifecycleScope.launch {
            try {
                val response = service.addUser(user)
                if (response.isSuccessful) {
                    Toast.makeText(this@MainActivity, "Usuario creado (ID: ${response.body()?.id})", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.e("API_ERROR", "Error al crear: ${e.message}")
            }
        }
    }

    private fun deleteUser(id: Int) {
        lifecycleScope.launch {
            try {
                val response = service.deleteUser(id)
                if (response.isSuccessful) {
                    Toast.makeText(this@MainActivity, "Usuario $id eliminado", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.e("API_ERROR", "Error al borrar: ${e.message}")
            }
        }
    }

    private fun updateUser(id: Int, user: User) {
        lifecycleScope.launch {
            try {
                val response = service.updateUser(id, user)
                if (response.isSuccessful) {
                    Toast.makeText(this@MainActivity, "Usuario $id actualizado", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.e("API_ERROR", "Error al actualizar: ${e.message}") //error
            }
        }
    }
}