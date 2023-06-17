package com.yangsooplus.shaking2048

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.yangsooplus.shaking2048.ui.GameSurface
import com.yangsooplus.shaking2048.ui.theme.Shaking2048Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Shaking2048Theme {
                GameSurface()
            }
        }
    }
}
