package com.qoqokoi.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.qoqokoi.myapp.ui.GameViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GameScreen()
                }
            }
        }
    }
}

@Composable
fun GameScreen(gameViewModel: GameViewModel = viewModel()) {
    val gameUiState by gameViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Unscramble Game", fontSize = 28.sp, modifier = Modifier.padding(bottom = 24.dp))
        Text(text = "${gameUiState.currentWordCount} / 10 Kata", fontSize = 18.sp)
        Text(text = "Skor: ${gameUiState.score}", fontSize = 20.sp, modifier = Modifier.padding(bottom = 24.dp))
        
        Text(
            text = gameUiState.currentScrambledWord,
            fontSize = 40.sp,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = gameViewModel.userGuess,
            onValueChange = { gameViewModel.updateUserGuess(it) },
            label = { Text("Masukkan tebakanmu") },
            isError = gameUiState.isGuessedWordWrong,
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        Button(
            onClick = { gameViewModel.checkUserGuess() },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Text("Kirim Tebakan")
        }

        OutlinedButton(
            onClick = { gameViewModel.skipWord() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Lewati Kata")
        }
    }
}
