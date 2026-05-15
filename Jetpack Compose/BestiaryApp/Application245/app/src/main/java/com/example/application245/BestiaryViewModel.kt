package com.example.application245

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BestiaryViewModel : ViewModel() {
    private val creatures = MutableStateFlow(CreaturesRepository.creatures)
    val currentCreatures = creatures.asStateFlow()

    fun getCreatureById(id: Int): Creature? {
        return CreaturesRepository.getById(id)
    }
}