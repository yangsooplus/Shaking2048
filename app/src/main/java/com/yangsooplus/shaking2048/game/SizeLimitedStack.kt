package com.yangsooplus.shaking2048.game

import java.util.LinkedList

class SizeLimitedStack<E>(private val limitedSize: Int = 3) : LinkedList<E>() {

    override fun add(element: E): Boolean {
        if (size < limitedSize) {
            super.addLast(element)
        } else {
            super.removeFirst()
            super.addLast(element)
        }
        return true
    }
}
