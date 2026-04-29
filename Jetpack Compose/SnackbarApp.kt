// package com.example.application245

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.application245.ui.theme.Application245Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Application245Theme() {
                ApplicationScreen()
            }
        }
    }
}

@Composable
fun ApplicationScreen(){
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val count = remember { mutableStateOf(0) }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState){
                Snackbar(
                    snackbarData = it,
//                    containerColor = Color.Red,
                    actionOnNewLine = true
                )
            }
        },
        floatingActionButton = {
//            FloatingActionButton(onClick = { count.value++ }) {
//                Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add")
//            }
            ExtendedFloatingActionButton(
                onClick = { count.value++ },
                text = { Text("Добавить") },
                icon = {Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add")}
            )
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Text(text = "Нажми кнопку снизу (${count.value})")
            Button(onClick = {
                scope.launch {
                    val result = snackbarHostState.showSnackbar(
                        message = "Ого, Вы нажали на кнопку!",
                        duration = SnackbarDuration.Indefinite,
                        withDismissAction = true,
                        actionLabel = "Действие"
                    )
                    if (result == SnackbarResult.ActionPerformed) count.value++
                    else snackbarHostState.showSnackbar(message = "Действие не выполнено!")
                }
            }) { Text(text = "Нажми меня!") }
        }
    }
}
