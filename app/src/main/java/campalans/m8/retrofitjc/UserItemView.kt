package campalans.m8.retrofitjc

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import campalans.m8.retrofitjc.ui.theme.Typography

@Composable
fun UserItemView(
    user: User,
    onDelete: (Int) -> Unit, // Callback para eliminar
    onUpdate: (Int) -> Unit  // Callback para editar/ver uno
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        // Icono de Usuario
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = null,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        // Datos del Usuario
        Column(modifier = Modifier.weight(1f)) {
            Text(text = user.name, style = Typography.headlineMedium)
            Text(text = user.email, style = Typography.bodyMedium)
            Text(text = user.phone, style = Typography.bodySmall)
            HorizontalDivider(modifier = Modifier.padding(top = 8.dp))
        }

        // Botón Editar (Punto: Llistar un element / Actualitzar)
        IconButton(onClick = { user.id?.let { onUpdate(it) } }) {
            Icon(Icons.Default.Edit, contentDescription = "Editar", tint = Color.Gray)
        }

        // Botón Borrar (Punto: Eliminar algun element)
        IconButton(onClick = { user.id?.let { onDelete(it) } }) {
            Icon(Icons.Default.Delete, contentDescription = "Borrar", tint = Color.Red)
        }
    }
}