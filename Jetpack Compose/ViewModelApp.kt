// package com.example.application245

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.application245.ui.theme.Application245Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Application245Theme() {
                Scaffold() {
                    CounterScreen(modifier = Modifier.padding(it))
                }
            }
        }
    }
}

//@Composable
//fun CounterScreen(modifier: Modifier = Modifier){
//    val count = remember { mutableStateOf(0) }
//
//    Column(modifier = modifier) {
//        Text(text = "Ваш счёт: ${count.value}")
//        Button(onClick = { count.value++ }) { Text(text = "Нажми на меня!") }
//    }
//}

// Объявление класса ViewModel - содержит данные состояния и функции для управления ими
class CounterViewModel : ViewModel() {
    val count = mutableStateOf(0)

    fun increase() {
        count.value++
    }
}

@Composable
fun CounterScreen(modifier: Modifier = Modifier, vm: CounterViewModel = viewModel()) {
        Column(modifier = modifier) {
        Text(text = "Ваш счёт: ${vm.count.value}")
        Button(onClick = { vm.increase() }) { Text(text = "Нажми на меня!") }
    }
}
