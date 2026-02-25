 package com.example.application245

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


 class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Отрисовка элементов интерфейса (Composable-функций) на экране приложения
        setContent {
            Application2()
        }
    }
}

@Composable
fun Application2(){
    val message = remember { mutableStateOf("Click on me!") }
    val radioState = remember { mutableStateOf(true) }
    var checkState by remember { mutableStateOf(true) }
    val message2 = rememberSaveable { mutableStateOf("And click on me too!") }

    Column() {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = radioState.value,
                onClick = { radioState.value = true }
            )
            Text("Choice 1")
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = !radioState.value,
                onClick = { radioState.value = false }
            )
            Text("Choice 2")
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checkState,
                onCheckedChange = {checkState = it}
            )
            Text("True of False?")
        }
        Text(
            text = message.value,
            modifier = Modifier
                .clickable(onClick = {
                    message.value = "Thanks!"
                })
                .background(Color(0xFF6650a4))
                .padding(20.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = message2.value,
            modifier = Modifier
                .clickable(onClick = {
                    message2.value = "Thank you!"
                })
                .background(Color(0xFF6650a4))
                .padding(20.dp)
        )
    }
}

@Preview(showBackground = true) // аннотация для предпросмотра Composable-функций
@Composable
fun ComposablePreview(){
    Application2()
}
