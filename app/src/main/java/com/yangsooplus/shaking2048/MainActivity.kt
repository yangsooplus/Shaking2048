package com.yangsooplus.shaking2048

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import com.yangsooplus.shaking2048.ui.GameSurface
import com.yangsooplus.shaking2048.ui.theme.Shaking2048Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Shaking2048Theme {
                val testData = remember {
                    mutableListOf(
                        0L, 2L, 4L, 8L,
                        16L, 32L, 64L, 128L,
                        256L, 512L, 1024L, 2048L,
                        0L, 0L, 0L, 0L
                    )
                }
                GameSurface(testData)
            }
        }
    }
}
