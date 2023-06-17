package com.yangsooplus.shaking2048.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yangsooplus.shaking2048.ui.theme.Shaking2048Theme

@Composable
fun GameSurface() {
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
