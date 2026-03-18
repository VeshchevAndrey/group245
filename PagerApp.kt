// package com.example.application245

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Отрисовка элементов интерфейса (Composable-функций) на экране приложения
        setContent {
            Scaffold() {
                PagerFunction(modifier = Modifier.padding(it))
            }

        }
    }
}

@Composable
fun PagerFunction(modifier: Modifier = Modifier){
    val data = arrayOf(
        DataForPager("Cloud Strife", R.drawable.img1),
        DataForPager("Tifa Lockhart", R.drawable.img2),
        DataForPager("Aerith Gainsborough", R.drawable.img3),
        DataForPager("Barret Wallace", R.drawable.img4)
    )

    val myPagerState = rememberPagerState() { data.size }

    HorizontalPager(
        state = myPagerState, modifier = modifier.fillMaxSize()
    ) { person ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(data[person].image),
                contentDescription = data[person].name,
                modifier.size(200.dp)
            )
            Text(
                text = data[person].name,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showSystemUi = false, showBackground = true)
@Composable
fun PreviewForMyFunction(){

}

data class DataForPager(var name: String, var image: Int)
