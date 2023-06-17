package com.yangsooplus.shaking2048.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yangsooplus.shaking2048.ui.theme.Shaking2048Theme
import kotlin.math.abs

enum class Drag {
    LEFT, RIGHT, UP, DOWN
}

@Composable
fun GameSurface() {
    var currentDrag: Drag = Drag.DOWN
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures(onDragEnd = {
                    Log.d("Drag", currentDrag.name)
                }) { _, dragAmount ->
                    currentDrag = if (abs(dragAmount.x) > abs(dragAmount.y)) {
                        if (dragAmount.x < 0) {
                            Drag.LEFT
                        } else {
                            Drag.RIGHT
                        }
                    } else {
                        if (dragAmount.y < 0) {
                            Drag.UP
                        } else {
                            Drag.DOWN
                        }
                    }
                }
            }
    ) {
        Grid2048()
    }
}

@Composable
fun Grid2048() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        userScrollEnabled = false,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxSize()
            .aspectRatio(1f)
            .background(color = Color(0xFFBAAC9F), shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        items(16) {
            NumberBox(numBox = NumBox.TYPE2)
        }
    }
}

@Composable
@Preview
fun GameSurfacePreview() {
    Shaking2048Theme {
        GameSurface()
    }
}
