package com.example.application245

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.application245.ui.theme.Application245Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val profileRepository = ProfileRepository(this)
        val viewModel = ProfileViewModel(profileRepository)
        setContent {
            Application245Theme() {
                MainScreen(viewModel = viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: ProfileViewModel){
    val context = LocalContext.current
    val profile = viewModel.currentProfile.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Мой профиль") },
                actions = {
                    IconButton(onClick = { viewModel.shareProfile(context, profile.value) }) {
                        Icon(Icons.Rounded.Share, "Поделиться")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 15.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            TextField(
                value = viewModel.myName.value,
                onValueChange = { viewModel.updateName(it) },
                placeholder = { Text(text = "Введите имя") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = viewModel.phone.value,
                onValueChange = { viewModel.updatePhone(it) },
                placeholder = { Text(text = "Введите номер телефона") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = viewModel.email.value,
                onValueChange = { viewModel.updateEmail(it) },
                placeholder = { Text(text = "Введите email") },
                modifier = Modifier.fillMaxWidth()
            )
            Button(onClick = {
                viewModel.saveCurrentProfile(
                    viewModel.myName.value,
                    viewModel.phone.value,
                    viewModel.email.value
                )
            }) { Text(text = "Сохранить данные") }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(text = "Имя: ${profile.value.name}")
                    Text(text = "Телефон: ${profile.value.phone}")
                    Text(text = "Email: ${profile.value.email}")
                }
            }
        }
    }
}
