package com.example.application245

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ToDoViewModel(private val repository: TaskRepository) : ViewModel() {
    val tasks: StateFlow<List<Task>> = repository.getTasks().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(1000),
        initialValue = emptyList()
    )
    val newTask = mutableStateOf("")
    private var nextId = 0

    fun addTask(title: String) {
        if (title.isEmpty()) return

        val newTask = Task(id = nextId++, title = title.trim())

        viewModelScope.launch {
            val currentList = tasks.value.toMutableList()
            currentList.add(newTask)
            repository.saveTasks(currentList)
        }
    }

    fun toggleTaskStatus(task: Task){
        viewModelScope.launch {
            val updatedList = tasks.value.map {
                if (it.id == task.id) it.copy(status = !it.status) else it
            }
            repository.saveTasks(updatedList)
        }
    }

    fun deleteTask(task: Task){
        viewModelScope.launch {
            val updatedList = tasks.value.filter { it.id != task.id }
            repository.saveTasks(updatedList)
        }
    }
}