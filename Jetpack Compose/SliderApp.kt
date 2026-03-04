package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


// Точка сборки и запуска окна мобильного приложения
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // отрисовка элементов интерфейса (Composable-функций) на экране приложения
        setContent {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Опросник") },
                        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFFF6D00))
                    )
                }
            ) {
                Application4(modifier = Modifier.padding(it))
            }
        }
    }
}

@Composable
fun Application4(modifier: Modifier = Modifier){
    val textInputState = remember { mutableStateOf("") }
    var sliderState by remember { mutableStateOf(0.5f) }
    val questions = arrayOf("Ты человек?", "Ты считаешь себя ИИ?", "Видишь ли ты сны?", "У тебя есть воспоминания?")

    Column(
        modifier = modifier.padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = textInputState.value,
            onValueChange = {newValue -> textInputState.value = newValue},
            placeholder = { Text(text = "Введите имя") }
        )
        Text(text = "Ваш возраст: ${sliderState.toInt()}", modifier = Modifier.padding(5.dp))
        Slider(
            value = sliderState,
            onValueChange = {sliderState = it},
            valueRange = 1f..20f,
            steps = 20,
            colors = SliderDefaults.colors(
                activeTrackColor = Color(0xFFFFB74D),
                thumbColor = Color(0xFFFF6D00)
            )
        )
        questions.forEach {question ->
            Column() {
                Question(question)
            }

        }
    }
}

@Composable
fun Question(question: String){
    val answers = arrayOf("Да", "Нет", "Не уверен")
    val (currentAnswer, onAnswerSelected) = remember { mutableStateOf(answers[0]) }
    Column(
        modifier = Modifier.padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = question,
            modifier = Modifier.padding(5.dp))
        Row(
            modifier = Modifier.selectableGroup()
        ) {
            answers.forEach { answer ->
                Answer(answer, currentAnswer, onAnswerSelected)
            }
        }
    }
}

@Composable
fun Answer(name: String, curAnsw: String, selected: (String) -> Unit){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = (name == curAnsw),
            onClick = {selected(name)}
        )
        Text(
            text = name
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewForMyFunctions(){
    Application4()
}
