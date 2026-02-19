// package com.example.application245 - здесь название Вашего приложения
 
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Отрисовка элементов интерфейса (Composable-функций) на экране приложения
        setContent {
            Application1()
        }
    }
}

@Composable
fun Application1(){
    val textInput = remember { mutableStateOf("") }
    val hiddenText = remember { mutableStateOf("") }

    Column() {
        Text("My first app", fontSize = 25.sp, color = Color(0xFFFF6F00))
        TextField(
            value = textInput.value,
            onValueChange = {textInput.value = it},
            placeholder = { Text("Placeholder") }
        )
        Button(
            onClick = {
                if (textInput.value != "") {
                    hiddenText.value = textInput.value
                    textInput.value = ""
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6F00))
        ) { Text("Click on me!") }
        Text(text = hiddenText.value)
    }
}

@Preview(showBackground = true) // аннотация для предпросмотра Composable-функций
@Composable
fun ComposablePreview(){
    Application1()
}
