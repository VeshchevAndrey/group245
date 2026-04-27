// package com.example.application245

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.example.application245.ui.theme.Application245Theme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Application245Theme() {
                AppScreen()
            }
        }
    }
}

@Composable
fun AppScreen(){
    val coroutine = rememberCoroutineScope()
    val counter = remember { mutableStateOf(0) }
    val enabled = remember { mutableStateOf(true) }
    val count = remember { mutableStateOf(5) }
    val progress = remember { mutableStateOf(0.0f) }

    Column() {
        Button(onClick = {
            coroutine.launch { imitateWork() }
        }) { Text(text = "Запускаем корутину!") }
        Button(onClick = { counter.value++ }) { Text(text = "Счетчик нажатий: ${counter.value}") }
        Button(onClick = {
            coroutine.launch {
                enabled.value = false
                for (i in 5 downTo 1){
                    count.value = i
                    progress.value += 0.2f
                    delay(1000)
                }
                enabled.value = true
                progress.value = 0.0f
            }
        }, enabled = enabled.value) { Text(text = "Ждите ${count.value} секунд") }
        CircularProgressIndicator(progress = { progress.value })
    }
}

suspend fun imitateWork(){
    println("Начинаю работу!")
    delay(5000)
    println("Работа завершена!")
}
