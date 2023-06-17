package com.yangsooplus.shaking2048.ui

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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yangsooplus.shaking2048.Direction
import com.yangsooplus.shaking2048.game.GameManager
import com.yangsooplus.shaking2048.ui.theme.Shaking2048Theme
import kotlin.math.abs

@Composable
fun GameSurface(data: MutableState<LongArray>, gameManager: GameManager) {
    var currentDirection: Direction = Direction.DOWN
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures(onDragEnd = {
                    data.value = gameManager.swipe(currentDirection)
                }) { _, dragAmount ->
                    currentDirection = if (abs(dragAmount.x) > abs(dragAmount.y)) {
                        if (dragAmount.x < 0) {
                            Direction.LEFT
                        } else {
                            Direction.RIGHT
                        }
                    } else {
                        if (dragAmount.y < 0) {
                            Direction.UP
                        } else {
                            Direction.DOWN
                        }
                    }
                }
            }
    ) {
        Grid2048(data)
    }
}

@Composable
fun Grid2048(data: MutableState<LongArray>) {
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
        items(data.value.size) {
            NumberBox(data.value[it])
        }
    }
}

@Composable
@Preview
fun GameSurfacePreview() {
    Shaking2048Theme {
        val testData = remember {
            mutableStateOf(
                longArrayOf(
                    0L, 2L, 4L, 8L,
                    16L, 32L, 64L, 128L,
                    256L, 512L, 1024L, 2048L,
                    0L, 0L, 0L, 0L
                )
            )
        }
        GameSurface(testData, GameManager())
    }
}
