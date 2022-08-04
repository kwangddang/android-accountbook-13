package com.example.android_accountbook_13.presenter.history

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import com.example.android_accountbook_13.utils.longToMoneyUnit

fun priceVisualTransformation(text: String): TransformedText {
    var out = if(text == "") "" else longToMoneyUnit(text.toLong())
    val priceOffSetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            val commas = out.count{ it == ','}
            return offset + commas
        }

        override fun transformedToOriginal(offset: Int): Int {
            val commas = out.count{ it == ','}
            return when (offset) {
                8, 7 -> offset - 2
                6 -> if (commas == 1) 5 else 4
                5 -> if (commas == 1) 4 else if (commas == 2) 3 else offset
                4, 3 -> if (commas >= 1) offset - 1 else offset
                2 -> if (commas == 2) 1 else offset
                else -> offset
            }
        }

    }
    return TransformedText(AnnotatedString(out), priceOffSetTranslator)
}