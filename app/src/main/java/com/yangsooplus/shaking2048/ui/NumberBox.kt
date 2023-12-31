package com.yangsooplus.shaking2048.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yangsooplus.shaking2048.ui.theme.NumBoxColorMap
import com.yangsooplus.shaking2048.ui.theme.Shaking2048Theme

@Composable
fun NumberBox(number: Long) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .aspectRatio(1f)
            .background(color = NumBoxColorMap[number] ?: Color.Transparent, shape = RoundedCornerShape(8.dp))

    ) {
        if (number > 0L) {
            Text(
                text = "$number",
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NumberBoxPreview() {
    Shaking2048Theme {
        NumberBox(2L)
    }
}

@Preview(showBackground = true)
@Composable
fun NumberBoxEmptyPreview() {
    Shaking2048Theme {
        NumberBox(0L)
    }
}
