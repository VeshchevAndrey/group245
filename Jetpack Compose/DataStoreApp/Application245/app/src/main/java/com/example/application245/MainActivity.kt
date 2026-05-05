package com.example.application245

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.application245.ui.theme.Application245Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val settingsManager = SettingsManager(this)
        var viewModel = UserViewModel(settingsManager)
        setContent {
            Application245Theme() {
                Scaffold() { paddingValues ->
                    ApplicationScreen(
                        modifier = Modifier.padding(paddingValues),
                        vM = viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun ApplicationScreen(modifier: Modifier, vM: UserViewModel){
    val userName = vM.currentUser.collectAsState()

    Column(modifier = modifier) {
        TextField(value = vM.inputText.value, onValueChange = { vM.inputText.value = it })
        Button(onClick = {
            vM.updateName(vM.inputText.value)
            vM.inputText.value = ""
        }) { Text("Войти") }
        Text(text = "Привет, ${userName.value}")
    }
}