package campalans.m8.retrofitjc

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import campalans.m8.retrofitjc.ui.theme.RetrofitJCTheme
import campalans.m8.retrofitjc.ui.theme.Typography

@Composable
fun MainView(
    modifier: Modifier = Modifier,
    users: List<User>,
    onDeleteClick: (Int) -> Unit = {},
    onUpdateClick: (Int) -> Unit = {}
) {
    Column(modifier) {
        Text(
            text = "Gestión de Usuarios",
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
            style = Typography.displayMedium,
            textAlign = TextAlign.Center
        )

        LazyColumn {
            items(users.size) { index ->
                val user = users[index]
                UserItemView(
                    user = user,
                    onDelete = { id -> onDeleteClick(id) },
                    onUpdate = { id -> onUpdateClick(id) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainViewPreview() {
    RetrofitJCTheme {
        MainView(
            modifier = Modifier.padding(top = 24.dp),
            users = listOf(
                User(1, "Pepe Botella", "nose@gmail.com", "13241234"),
                User(2, "Otilio Chapuzas", "nidea@gmail.com", "123412334")
            )
        )
    }
}