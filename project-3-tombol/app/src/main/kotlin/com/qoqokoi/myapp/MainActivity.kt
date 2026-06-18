package com.qoqokoi.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qoqokoi.myapp.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun LemonadeApp() {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }
    var requiredSqueezes by remember { mutableStateOf(0) }

    val imageResource = when (currentStep) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val textInstruction = when (currentStep) {
        1 -> "Ketuk pohon lemon untuk memetik buah lemon"
        2 -> "Ketuk buah lemon secara berkala untuk memerasnya"
        3 -> "Ketuk segelas lemonde hangat untuk meminumnya"
        else -> "Ketuk gelas kosong untuk memulai kembali proses"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = textInstruction,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFC3F2C4)) // Aksen latar belakang visual kontras
                .clickable {
                    when (currentStep) {
                        1 -> {
                            requiredSqueezes = (2..4).random()
                            squeezeCount = 0
                            currentStep = 2
                        }
                        2 -> {
                            squeezeCount++
                            if (squeezeCount >= requiredSqueezes) {
                                currentStep = 3
                            }
                        }
                        3 -> {
                            currentStep = 4
                        }
                        4 -> {
                            currentStep = 1
                        }
                    }
                }
                .padding(24.dp)
        ) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = textInstruction
            )
        }
    }
}
