// package com.example.application245

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Отрисовка элементов интерфейса (Composable-функций) на экране приложения
        setContent {
            ModifierFunction()
        }
    }
}

@Composable
fun ModifierFunction(){
    Column() {
        Text(
            text = "Привет сосед!",
            modifier = Modifier
                .padding(5.dp) // Модификатор внешнего отступа (от фона)
                .background(color = Color.Yellow, shape = RoundedCornerShape(10.dp)) // Модификатор заднего фона
                .padding(5.dp), // Модификатор внутреннего отступа (от текста)
            fontSize = 15.sp,
            color = Color(0xFFF44336) // Цвет текста
        )
        Text(
            text = "Как дела?",
            modifier = Modifier
                .clickable(onClick = {})
        )
    }
}

@Preview(showBackground = true) // аннотация для предпросмотра Composable-функций
@Composable
fun ComposablePreview(){
    ModifierFunction()
}
