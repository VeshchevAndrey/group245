// package com.example.application245

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Отрисовка элементов интерфейса (Composable-функций) на экране приложения
        setContent {
            Scaffold() {
                DataFunction(modifier = Modifier.padding(it))
            }
        }
    }
}



@Composable
fun DataFunction(modifier: Modifier = Modifier){
    // Создание объекта data-класса
    val companion1 = Companion("Walter White", R.drawable.comp1, "Where are you?")

    // список объектов data-класса
    val companions = arrayOf(
        Companion("Walter White", R.drawable.comp1, "Where are you?"),
        Companion("Jessie Pinkman", R.drawable.comp1, "Where are you?"),
        Companion("Hank Shreider", R.drawable.comp1, "Where are you?")
    )

    LazyColumn (modifier = modifier) {
        items(companions){ comp ->
            CompanionFunction(comp)
        }
    }
}

@Composable
fun CompanionFunction(companion: Companion){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(100.dp).clip(CircleShape),
            bitmap = ImageBitmap.imageResource(companion.image),
            contentDescription = "${companion.name} avatar"
        )
        Column() {
            Text(text = companion.name, fontWeight = FontWeight.Bold)
            Text(text = companion.lastMessage, fontWeight = FontWeight.W300)
        }
    }
}

@Preview(showSystemUi = false, showBackground = true)
@Composable
fun PreviewForMyFunction(){
    DataFunction()
}

// Объявление data-класса
data class Companion(val name: String, val image: Int, val lastMessage: String)

