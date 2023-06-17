package com.yangsooplus.shaking2048

enum class Direction(val pos: Pos) {
    UP(Pos(-1, 0)),
    DOWN(Pos(1, 0)),
    LEFT(Pos(0, -1)),
    RIGHT(Pos(0, 1))
}

data class Pos(val r: Int, val c: Int)
