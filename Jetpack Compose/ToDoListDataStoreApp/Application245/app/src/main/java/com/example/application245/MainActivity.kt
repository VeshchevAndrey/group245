package com.example.application245

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.application245.ui.theme.Application245Theme

class MainActivity : ComponentActivity() {

    private val repository by lazy { TaskRepository((this)) }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: ToDoViewModel = viewModel{
                ToDoViewModel(TaskRepository(this@MainActivity))
            }

            Application245Theme() {
                Scaffold() { paddingValues ->
                    ToDoApplication(modifier = Modifier.padding(paddingValues), vm = viewModel)
                }
            }
        }
    }
}

@Composable
fun ToDoApplication(modifier: Modifier = Modifier, vm: ToDoViewModel){
    val tasks = vm.tasks.collectAsState()

    Column(modifier = modifier) {
        Text(text = "Выполнено ${tasks.value.count{ it.status }} задач из ${tasks.value.size}")
        Row() {
            TextField(
                value = vm.newTask.value,
                onValueChange = {vm.newTask.value = it}
            )
            Button(
                onClick = { vm.addTask(vm.newTask.value) }
            ) { Text("Добавить") }
        }
        LazyColumn() {
            items(tasks.value){task ->
                TaskItem(
                    task = task,
                    onTaskDone = { vm.toggleTaskStatus(task = task) },
                    onDelete = { vm.deleteTask(task = task) })
            }
        }
    }
}

@Composable
fun TaskItem(task: Task, onTaskDone: () -> Unit, onDelete: () -> Unit){
    val color = when (task.priority){
        2 -> Color(0xFFEF5350)
        1 -> Color(0xFFFFEE58)
        else -> Color(0xFFE1F5FE)
    }
    Row(
        modifier = Modifier.background(color).fillMaxWidth()
    ) {
        Checkbox(
            checked = task.status,
            onCheckedChange = { onTaskDone() }
        )
        Text(
            text = task.title,
            textDecoration = if (task.status) TextDecoration.LineThrough else null
        )
        IconButton(
            onClick = onDelete
        ) { Icon(Icons.Rounded.Delete, "Delete") }
    }
}