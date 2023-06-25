package ru.cococo.netologytest.kot

import java.math.RoundingMode

class GetCountFormat {
    fun getFormat(count: Int): String {
        return when (count) {
            in 0..999 -> count.toString()
            in 1000..9999 -> "${changeFormat(count / 1000.0)} K"
            in 10000..999999 -> "${count / 1000} K"
            in 1000000..9999999 -> "${changeFormat(count / 1000000.0)} М"
            else -> "${(count / 1000000)} M"
        }
    }

    private fun changeFormat(count: Double): String {
        val newCount = count.toBigDecimal().setScale(1, RoundingMode.DOWN).toDouble()
        return if (newCount % 1 == 0.0) {
            "${newCount.toInt()}"
        } else {
            newCount.toString()
        }
    }
}
