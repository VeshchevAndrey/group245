// package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SliderState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
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

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Отрисовка элементов интерфейса (Composable-функций) на экране приложения
        setContent {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = "Опрос") },
                        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFFF6D00))
                    )
                }
            ) {
                Application3(Modifier.padding(it))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Application3(modifier: Modifier){
    val questions = arrayOf("Ты человек?", "Хотел бы ты стать андроидом?", "Ты счастлив?")
    val textInputState = remember { mutableStateOf("") }
    var sliderState by remember { mutableStateOf(5f) }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            value = textInputState.value,
            onValueChange = {textInputState.value = it},
            label = { Text(text = "Введите имя:") },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color(0xFFFF6D00),
                unfocusedIndicatorColor = Color(0xFFFFB74D)
            )
        )

        Text(text = "Ваш возраст: $sliderState")

        Slider(
            modifier = Modifier
                .padding(5.dp),
            value = sliderState,
            onValueChange =  {sliderState = it},
            valueRange = 1f..100f,
            colors = SliderDefaults.colors(
                thumbColor = Color(0xFFFF6D00),
                activeTrackColor = Color(0xFFFFB74D)
            )
        )

        questions.forEach {
            Column(
                modifier = Modifier
                    .padding(2.5f.dp)
                    .background(Color(0xFFFFB74D))
                    .padding(5f.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = it)
                Answers()
            }
        }
    }
}

@Composable
fun Answers(){
    val options: Array<String> = arrayOf("Да", "Нет", "Воздержусь от ответа")
    val (optionValue, onOptionChange) = remember { mutableStateOf(options[0]) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        options.forEach {
            Answer(option = it, optionValue = optionValue, onOptionChange = onOptionChange)
        }
    }
}

@Composable
fun Answer(option: String, optionValue: String, onOptionChange: (String) -> Unit){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = (option == optionValue),
            onClick = {onOptionChange(option)}
        )
        Text(text = option)
    }
}




@Preview(showBackground = true) // аннотация для предпросмотра Composable-функций
@Composable
fun ComposablePreview(){

}
