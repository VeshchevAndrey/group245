// package com.example.application245 - укажите Ваш package

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Отрисовка элементов интерфейса (Composable-функций) на экране приложения
        setContent {
            Welcome("Джон", 12)
        }
    }
}


@Composable // аннотация, указывающая на функцию, задающую элемент интерфейса и взаимодействие с ним
fun Welcome(name: String, age: Byte){
        Text(
            text = "Ваше имя: $name, возраст: $age. Добрый вечер!"
        )
}

@Preview(showBackground = true) // аннотация для предпросмотра Composable-функций
@Composable
fun ComposablePreview(){
    val userName = "Андрей"
    val userAge = 27.toByte()
    Welcome(userName, userAge)
}
