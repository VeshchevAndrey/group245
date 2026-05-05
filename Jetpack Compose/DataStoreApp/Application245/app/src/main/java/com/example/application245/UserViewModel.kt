package com.example.application245

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserViewModel(private val settingsManager: SettingsManager) : ViewModel() {
    val inputText =  mutableStateOf("")

    val currentUser: StateFlow<String> = settingsManager.userNameFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(1000),
        initialValue = "Loading..."
    )

    fun updateName(newName: String){
        viewModelScope.launch {
            settingsManager.saveName(name = newName)

        }
    }

}