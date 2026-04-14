// package com.example.application245

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Application6()
        }
    }
}


@Composable
fun Application6(){
    val tapType = remember { mutableStateOf("Неопределено") }

    Column() {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.pointerInput(Unit){
                detectTapGestures(
                    onTap = { tapType.value = "Вы тапнули по строке" },
                    onPress = { tapType.value = "Вы нажали по строке" },
                    onLongPress = { tapType.value = "Вы уже долго жмёте строку" },
                    onDoubleTap = { tapType.value = "Вы нажали два раза подряд" }
                )
            }
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.p1),
                contentDescription = "Android"
            )
            Text(text = tapType.value)
        }

        val scale = remember { mutableStateOf(1f) }
        val offset = remember { mutableStateOf(Offset.Zero) }
        val angle = remember { mutableStateOf(0f) }
        val transformState = rememberTransformableState { scaleChange, offsetChange, rotationChange ->
            scale.value *= scaleChange
            offset.value += offsetChange
            angle.value += rotationChange
        }

        Box(
            modifier = Modifier.fillMaxSize().background(Color.LightGray),
            contentAlignment = Alignment.Center
        ){
            Box(modifier = Modifier
                .graphicsLayer(
                    scaleX = scale.value,
                    scaleY = scale.value,
                    translationX = offset.value.x,
                    translationY = offset.value.y,
                    rotationZ = angle.value
                )
                .transformable(state = transformState)
                .background(Color.Red)
                .size(150.dp)
            )
        }
    }

}
