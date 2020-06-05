package com.aumarbello.carowners.utils

import java.util.*

fun String.toInitialCap(): String {
    if (isNotEmpty()) {
        val firstChar = this[0]
        if (firstChar.isLowerCase()) {
            return buildString {
                val titleChar = firstChar.toTitleCase()
                if (titleChar != firstChar.toUpperCase()) {
                    append(titleChar)
                } else {
                    append(this@toInitialCap.substring(0, 1).toUpperCase(Locale.getDefault()))
                }
                append(this@toInitialCap.substring(1))
            }
        }
    }
    return this
}