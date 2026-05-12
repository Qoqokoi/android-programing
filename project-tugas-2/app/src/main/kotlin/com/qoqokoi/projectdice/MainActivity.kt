package com.qoqokoi.dice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Pake tema standar MaterialTheme biar enteng
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AnimatedDiceRollerApp()
                }
            }
        }
    }
}

@Composable
fun AnimatedDiceRollerApp() {
    AnimatedDiceWithButtonAndImage(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun AnimatedDiceWithButtonAndImage(modifier: Modifier = Modifier) {
    // --- STATE MANAGER ---
    // State angka dadu akhir
    var result by remember { mutableIntStateOf(1) }
    // State buat nentuin lagi proses animasi atau nggak
    var isRolling by remember { mutableStateOf(false) }

    // --- LOGIKA ANIMASI (The Spin Effect) ---
    // Transition yang jalan terus menerus
    val infiniteTransition = rememberInfiniteTransition(label = "DiceRotation")
    
    // Animasi putaran (Rotation Z) dari 0 ke 360 derajat
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(150, easing = LinearEasing), // Speed muternya
            repeatMode = RepeatMode.Restart
        ),
        label = "DiceRotationAnimation"
    )

    // Gunakan nilai rotasi cuma kalau lagi 'isRolling'
    val currentRotation = if (isRolling) rotation else 0f

    // --- SIDE EFFECT (Logika Timing Kocok) ---
    // LaunchedEffect bakal jalan tiap kali 'isRolling' berubah jadi true
    LaunchedEffect(isRolling) {
        if (isRolling) {
            // Efek dadu muter selama 1 detik (1000 milidetik)
            delay(1000) 
            // Setelah 1 detik, random angkanya
            result = (1..6).random()
            // Matikan animasi
            isRolling = false
        }
    }

    // --- UI RESOURCES ---
    val imageResource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    // --- LAYOUT ---
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = result.toString(),
            modifier = Modifier
                .size(150.dp)
                // --- KUNCI ANIMASI ---
                // graphicsLayer dipake buat aplikasiin transformasi rotasi
                .graphicsLayer(
                    rotationZ = currentRotation,
                    cameraDistance = 8 * density // Efek 3D dikit
                )
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            // Klik tombol cuma ngerubah state 'isRolling'
            onClick = { isRolling = true },
            // Tombol mati kalau lagi muter
            enabled = !isRolling 
        ) {
            Text(
                text = if (isRolling) "Lagi Ngocok..." else "Kocok Dadu!",
                fontSize = 18.sp
            )
        }
    }
}
