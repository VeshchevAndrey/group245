package com.example.application245

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Отрисовка элементов интерфейса (Composable-функций) на экране приложения
        setContent {

        }
    }
}

@Composable
fun ToDoApplication(){
    val tasks = remember { mutableStateListOf<Task>() }
    val newTask = remember { mutableStateOf("") }
    val nextId = remember { mutableStateOf(0) }

    Column() {
        Text(text = "Выполнено ${tasks.count{ it.status }} задач из ${tasks.size}")
        Row() {
            TextField(
                value = newTask.value,
                onValueChange = {newTask.value = it}
            )
            Button(
                onClick = {
                    if (newTask.value.isNotEmpty()){
                        tasks.add(Task(id = nextId.value++, title = newTask.value))
                        newTask.value = ""
                    }
                }
            ) { Text("Добавить") }
        }
        LazyColumn() {
            itemsIndexed(tasks){id, task ->
                TaskItem(
                    task = task,
                    onTaskDone = {
                        tasks[id] = task.copy(status = !task.status)
                    },
                    onDelete = {tasks.remove(task)})
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
        modifier = Modifier.background(color)
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

@Preview(showSystemUi = false, showBackground = true)
@Composable
fun PreviewForMyFunction(){
    ToDoApplication()
}

data class Task(
    val id: Int,
    val title: String,
    val status: Boolean = false,
    val priority: Int = 0,
    val dueDate: String = ""
)
