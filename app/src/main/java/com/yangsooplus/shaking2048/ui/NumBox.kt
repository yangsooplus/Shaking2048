package com.yangsooplus.shaking2048.ui

import androidx.compose.ui.graphics.Color

enum class NumBox(val number: Long, val color: Color) {
    EMPTY(0L, Color(0xFFCCC0B3)),
    TYPE2(2L, Color(0xFFFFF5EE)),
    TYPE4(4L, Color(0xFFFFE5D2)),
    TYPE8(8L, Color(0xFFFFB886)),
    TYPE16(16L, Color(0xFFFF8E51)),
    TYPE32(32L, Color(0xFFFF6030)),
    TYPE64(64L, Color(0xFFFF4F1A)),
    TYPE128(128L, Color(0xFFFFF0B6)),
    TYPE256(256L, Color(0xFFFFE58D)),
    TYPE512(512L, Color(0xFFFFD454)),
    TYPE1024(1024L, Color(0xFFFFCA2B)),
    TYPE2048(2048L, Color(0xFFFFBF00))
}
