package mobi.pulsus.challenge.commons.customviews.toolbars

import android.app.Activity
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import mobi.pulsus.challenge.commons.R
import mobi.pulsus.challenge.commons.extensions.configureBackButtonAction
import mobi.pulsus.challenge.commons.extensions.onSingleClick
import mobi.pulsus.challenge.commons.extensions.setAccessibilityAsButton
import mobi.pulsus.challenge.commons.extensions.setAccessibilityAsHeader
import mobi.pulsus.challenge.commons.extensions.setToolbarAccessibleBackButton

class GenericToolbar @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val btLeftButton by lazy { findViewById<TextView>(R.id.tv_generic_toolbar_left_button) }
    private val tvToolbarTitle by lazy { findViewById<TextView>(R.id.tv_generic_toolbar_title) }
    private val btRightButton by lazy { findViewById<TextView>(R.id.tv_generic_toolbar_right_button) }

    private var showLeftButton: Boolean
    private var showTitle: Boolean = true
    private var showRightButton: Boolean

    private var attributesTypedArray: TypedArray

    var titleToolbar: String? = null

    init {
        inflate(context, R.layout.view_generic_toolbar, this)
        attributesTypedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.GenericToolbar, 0, 0).apply {
            tvToolbarTitle.apply {
                text = getString(R.styleable.GenericToolbar_toolbarText) ?: ""
                setTextColor(getColor(R.styleable.GenericToolbar_toolbarTextColor, ContextCompat.getColor(context, R.color.black)))
                setAccessibilityAsHeader()
            }
            btLeftButton.apply {
                setTextColor(getColor(R.styleable.GenericToolbar_buttonLeftTextColor, ContextCompat.getColor(context, R.color.black)))
                setAccessibilityAsButton()
            }
            btRightButton.apply {
                setTextColor(getColor(R.styleable.GenericToolbar_buttonRightTextColor, ContextCompat.getColor(context, R.color.black)))
                setAccessibilityAsButton()
            }
            showLeftButton = getBoolean(R.styleable.GenericToolbar_showLeftButton, true)
            showTitle = getBoolean(R.styleable.GenericToolbar_showTitle, true)
            showRightButton = getBoolean(R.styleable.GenericToolbar_showRightButton, false)

            initView()

            recycle()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        btLeftButton.setToolbarAccessibleBackButton()
    }

    private fun initView() {
        if (context is Activity) {
            btLeftButton.run { configureBackButtonAction(context as Activity) }
        }
        btLeftButton.visibility = if (showLeftButton) View.VISIBLE else View.GONE
        tvToolbarTitle.visibility = if (showTitle) View.VISIBLE else View.GONE
        btRightButton.visibility = if (showRightButton) View.VISIBLE else View.GONE
    }

    fun setLeftButtonAction(action: () -> Unit) {
        btLeftButton.onSingleClick { action() }
    }

    fun setRightButtonAction(action: () -> Unit) {
        btRightButton.onSingleClick { action() }
    }

    fun updateTitleToolbar() {
        tvToolbarTitle.run {
            text = titleToolbar
            contentDescription = resources.getString(R.string.generic_toolbar_title_acs, titleToolbar)
        }
    }

    fun setFocusOnLeftButton() {
        btLeftButton.requestFocus()
        btLeftButton.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED)
    }

    fun setLeftButtonText(text: String) {
        btLeftButton.text = text
        btLeftButton.contentDescription = text
    }

    fun setLeftButtonColor(color: Int) {
        btLeftButton.setTextColor(color)
    }

    fun setRightButtonText(text: String) {
        btRightButton.text = text
        btRightButton.contentDescription = text
    }

    fun setRightButtonColor(color: Int) {
        btRightButton.setTextColor(color)
    }
}