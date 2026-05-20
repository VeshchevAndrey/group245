package com.example.application245

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProfileViewModel(private val profileRepository: ProfileRepository) : ViewModel() {
    val myName = mutableStateOf("")
    val phone = mutableStateOf("")
    val email = mutableStateOf("")

    fun updateName(newValue: String){
        myName.value = newValue
    }

    fun updatePhone(newValue: String){
        phone.value = newValue
    }

    fun updateEmail(newValue: String){
        email.value = newValue
    }

    val currentProfile = profileRepository.getProfile.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = UserProfile()
    )

    fun saveCurrentProfile(name: String, phone: String, email: String){
        viewModelScope.launch {
            val newProfile = UserProfile(name.trim(), phone.trim(), email.trim())
            profileRepository.saveProfile(newProfile)
        }
    }

    fun shareProfile(context: Context, profile: UserProfile){
        val text = """
            Имя: ${profile.name}
            Телефон: ${profile.phone}
            Email: ${profile.email}
        """.trimIndent()

        val sentIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sentIntent, "Поделиться профилем")
        context.startActivity(shareIntent)
    }
}