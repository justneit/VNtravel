import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SelectRoom() {
    var expanded by remember { mutableStateOf(false) }
    val options = listOf("Option 1", "Option 2", "Option 3")
    var selectedOption by remember { mutableStateOf(options.first()) }
    Box(modifier = Modifier
        .padding(top = 20.dp)
        .height(50.dp)
        .border(2.dp, Color.LightGray, shape = RoundedCornerShape(20.dp))
        .wrapContentSize()) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row {
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = null)
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.padding(16.dp)
                ) {
                    options.forEach { option ->
                        DropdownMenuItem(onClick = {
                            expanded = false
                            selectedOption = option // Set the selected option
                        }) {
                            Text(text = option)
                        }
                    }
                }

                // Display the selected option
                Text(text = "Selected: $selectedOption", modifier = Modifier.padding(16.dp))
            }
            Button(
                onClick = {},
                modifier = Modifier.width(195.dp).height(50.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFF2B705))
            ) {
                Text(text = "Book now")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun Press() {
    SelectRoom()
}
