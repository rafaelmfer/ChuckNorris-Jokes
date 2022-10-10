package mobi.pulsus.challenge.commons.extensions

import android.accessibilityservice.AccessibilityServiceInfo
import android.content.Context
import android.os.Build
import android.view.MotionEvent
import android.view.View
import android.view.accessibility.AccessibilityManager
import android.widget.Button
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import androidx.fragment.app.Fragment
import kotlin.reflect.KClass

val Context.accessibilityManager: AccessibilityManager
    get() = getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager

/**
 * Extension para validação se a Accesibilidade está ligada, incluindo aparelhos SAMSUNG que possuem um sistema diferente
 */

fun Context.isScreenReaderEnabled(): Boolean {
    if (!accessibilityManager.isEnabled) {
        return false
    }

    val serviceInfoList = accessibilityManager.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_SPOKEN)
    if (serviceInfoList.isNullOrEmpty()) {
        return false
    }

    return true
}

fun Fragment.isScreenReaderEnabled(): Boolean = requireContext().isScreenReaderEnabled()

fun View.setToolbarAccessibleBackButton() {
    setOnTouchListener { view, event ->
        when (event.action) {
            MotionEvent.ACTION_UP -> {
                view.performClick()
                true
            }
            else -> false
        }
    }
}

fun View.setAccessibilityAsHeader(value: Boolean = true) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        isAccessibilityHeading = value
    } else {
        ViewCompat.setAccessibilityDelegate(this, object : AccessibilityDelegateCompat() {
            override fun onInitializeAccessibilityNodeInfo(host: View, info: AccessibilityNodeInfoCompat) {
                super.onInitializeAccessibilityNodeInfo(host, info);
                info.isHeading = value
            }
        })
    }
}

private infix fun View.viewAs(className: KClass<*>) {
    ViewCompat.setAccessibilityDelegate(this, object : AccessibilityDelegateCompat() {
        override fun onInitializeAccessibilityNodeInfo(host: View, info: AccessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(host, info)
            info.className = className.java.name
        }
    })
}

fun View.setAccessibilityAsButton() {
    viewAs(Button::class)
}