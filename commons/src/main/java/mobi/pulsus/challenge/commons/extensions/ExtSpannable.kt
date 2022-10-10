package mobi.pulsus.challenge.commons.extensions

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import java.util.Locale

fun String.toSpannableStringBuilder() = SpannableStringBuilder(this)

fun SpannableStringBuilder.sectionTextBold(vararg args: String?): SpannableStringBuilder {
    return this.sectionText(*args) { spannable, startingIndex, endingIndex ->
        spannable.setSpan(StyleSpan(Typeface.BOLD), startingIndex, endingIndex, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    }
}

private fun SpannableStringBuilder.sectionText(vararg args: String?, apply: (SpannableStringBuilder, Int, Int) -> Unit): SpannableStringBuilder {
    args.forEach { textToBold ->
        if (textToBold != null) {
            if (textToBold.isNotEmpty() && textToBold.trim { it <= ' ' } != "") {
                val testText = this.toString().lowercase(Locale.ROOT)
                val testTextToBold = textToBold.lowercase(Locale.ROOT)
                val startingIndex = testText.indexOf(testTextToBold)
                val endingIndex = startingIndex + testTextToBold.length

                if (startingIndex >= 0 && endingIndex >= 0) {
                    apply(this, startingIndex, endingIndex)
                }
            }
        }
    }
    return this
}