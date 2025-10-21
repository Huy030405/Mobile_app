import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ComponentsListScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("UI Components List", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Spacer(Modifier.height(16.dp))

        val items = listOf(
            "Text" to "text_detail",
            "Image" to "image_screen",
            "TextField" to "textfield_screen",
            "Row Layout" to "rowlayout_screen"
        )

        items.forEach { (title, route) ->
            Button(
                onClick = { navController.navigate(route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFBBDEFB))
            ) {
                Text(title, color = Color.Black)
            }
        }
    }
}
