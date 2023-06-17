package com.yangsooplus.shaking2048.game

import com.yangsooplus.shaking2048.Direction
import com.yangsooplus.shaking2048.Pos
import kotlin.random.Random

class GameManager(private val size: Int = 4) {

    private var board: Array<Array<Long>> = Array(size) { Array(size) { 0 } }
    private val redoStack = SizeLimitedStack<Array<Array<Long>>>()
    private var score: Long = 0L

    fun initGame(): LongArray {
        create2Block()
        return board.flatten().toLongArray()
    }

    fun redo() {
        if (redoStack.isNotEmpty()) {
            board = redoStack.removeLast()
        }
    }

    fun swipe(direction: Direction): LongArray {
        if (!canSwipe(direction)) return board.flatten().toLongArray()

        enqueueBoard()
        moveTo(direction)
        create2Block()
        checkBoardState()

        return board.flatten().toLongArray()
    }

    private fun moveTo(direction: Direction) {
        when (direction) {
            Direction.UP -> moveUp()
            Direction.DOWN -> moveDown()
            Direction.LEFT -> moveLeft()
            Direction.RIGHT -> moveRight()
        }
    }

    private fun moveUp() {
        for (r in 1 until size) {
            for (c in 0 until size) {
                if (board[r][c] == 0L) continue
                var tr = r
                var limit = 0
                while (tr > limit) {
                    if (board[tr - 1][c] == 0L) {
                        board[tr - 1][c] = board[tr][c]
                        board[tr][c] = 0L
                        tr--
                    } else if (board[tr - 1][c] == board[tr][c]) {
                        board[tr - 1][c] += board[tr][c]
                        board[tr][c] = 0L
                        limit++
                        score += board[tr - 1][c]
                        break
                    } else {
                        break
                    }
                }
            }
        }
    }

    private fun moveDown() {
        for (r in size - 2 downTo 0) {
            for (c in 0 until size) {
                if (board[r][c] == 0L) continue
                var tr = r
                var limit = size - 1
                while (tr < limit) {
                    if (board[tr + 1][c] == 0L) {
                        board[tr + 1][c] = board[tr][c]
                        board[tr][c] = 0L
                        tr++
                    } else if (board[tr + 1][c] == board[tr][c]) {
                        board[tr + 1][c] += board[tr][c]
                        board[tr][c] = 0L
                        limit--
                        score += board[tr + 1][c]
                        break
                    } else {
                        break
                    }
                }
            }
        }
    }

    private fun moveLeft() {
        for (c in 1 until size) {
            for (r in 0 until size) {
                if (board[r][c] == 0L) continue
                var tc = c
                var limit = 0
                while (tc > limit) {
                    if (board[r][tc - 1] == 0L) {
                        board[r][tc - 1] = board[r][tc]
                        board[r][tc] = 0L
                        tc--
                    } else if (board[r][tc - 1] == board[r][tc]) {
                        board[r][tc - 1] += board[r][tc]
                        board[r][tc] = 0L
                        limit++
                        score += board[r][tc - 1]
                        break
                    } else {
                        break
                    }
                }
            }
        }
    }

    private fun moveRight() {
        for (c in size - 2 downTo 0) {
            for (r in 0 until size) {
                if (board[r][c] == 0L) continue
                var tc = c
                var limit = size - 1
                while (tc < limit) {
                    if (board[r][tc + 1] == 0L) {
                        board[r][tc + 1] = board[r][tc]
                        board[r][tc] = 0L
                        tc++
                    } else if (board[r][tc + 1] == board[r][tc]) {
                        board[r][tc + 1] += board[r][tc]
                        board[r][tc] = 0L
                        limit--
                        score += board[r][tc + 1]
                        break
                    } else {
                        break
                    }
                }
            }
        }
    }

    private fun checkBoardState() {
        if (!canSwipe(Direction.UP) && !canSwipe(Direction.DOWN) && !canSwipe(Direction.LEFT) && !canSwipe(Direction.RIGHT)) {
            println("GameOver!!")
        }

        for (i in 0 until size) {
            for (j in 0 until size) {
                if (board[i][j] == 2048L) {
                    println("Clear!!")
                }
            }
        }
    }

    private fun canSwipe(direction: Direction): Boolean {
        for (i in 0 until size) {
            for (j in 0 until size) {
                if (canMove(i, j, direction)) {
                    return true
                }
            }
        }
        return false
    }

    private fun canMove(r: Int, c: Int, dir: Direction): Boolean {
        if (board[r][c] == 0L) return false

        val moveR = r + dir.pos.r
        val moveC = c + dir.pos.c
        if (moveR < 0 || moveR >= size || moveC < 0 || moveC >= size) return false
        return board[moveR][moveC] == 0L || board[moveR][moveC] == board[r][c]
    }

    private fun enqueueBoard() {
        redoStack.add(
            Array(size) { i ->
                board[i].copyOf()
            }
        )
    }

    private fun create2Block() {
        val emptyPosList = mutableListOf<Pos>()
        for (i in 0 until size) {
            for (j in 0 until size) {
                if (board[i][j] == 0L) emptyPosList.add(Pos(i, j))
            }
        }
        val targetIdx = Random.nextInt(emptyPosList.size)
        val targetPos = emptyPosList[targetIdx]
        board[targetPos.r][targetPos.c] = 2
    }
}
