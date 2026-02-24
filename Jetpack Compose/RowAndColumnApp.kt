// package com.example.application245 - здесь название вашего проекта

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Отрисовка элементов интерфейса (Composable-функций) на экране приложения
        setContent {

        }
    }
}

@Composable
fun FunctionWithColumn(name: String, surname: String, group: String){
    // Контейнер для размещения элементов в одну колонку (вертикально друг другу)
    Column(
        modifier = Modifier
            .size(200.dp, 100.dp), // указание размеров контейнера
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally // горизонтальное выравнивание внутри колонки
    ) {
        Text("Имя: $name")
        Text("Фамилия: $surname")
        Text("Группа: $group")
    }
}

@Composable
fun FunctionWithRow(){
    // Контейнер для размещения элементов в одну строку (горизонтально друг другу)
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically, // вертикальное выравнивание внутри контейнера
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text("Нажми на кнопку!")
        Button(onClick = {}) {
            Text("Нажимай!")
        }
    }
}

@Composable
fun RowAndColumnFunction(){
    Row() {
        Column() {
            Text("Привет")
        }
        Column() {
            Text("Сосед")
        }
    }
}

@Preview(showBackground = true) // аннотация для предпросмотра Composable-функций
@Composable
fun ComposablePreview(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FunctionWithColumn("Олег", "Семенов", "255")
        FunctionWithRow()
        RowAndColumnFunction()
    }
}
