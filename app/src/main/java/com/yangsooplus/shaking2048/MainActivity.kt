package com.yangsooplus.shaking2048

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.yangsooplus.shaking2048.game.GameManager
import com.yangsooplus.shaking2048.ui.GameSurface
import com.yangsooplus.shaking2048.ui.theme.Shaking2048Theme

class MainActivity : ComponentActivity() {

    private val gameManager = GameManager()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Shaking2048Theme {
                val testData = remember {
                    mutableStateOf(gameManager.initGame())
                }

                GameSurface(testData, gameManager)
            }
        }
    }
}
