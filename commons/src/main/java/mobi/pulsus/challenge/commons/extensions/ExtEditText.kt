package mobi.pulsus.challenge.commons.extensions

import android.text.InputFilter
import android.widget.EditText

fun EditText.notAllowWhitespace() {
    val filter = InputFilter { source, start, end, _, _, _ ->
        for (i in start until end) {
            if (Character.isWhitespace(source[i])) {
                return@InputFilter ""
            }
        }
        null
    }
    this.filters = arrayOf(filter)
}