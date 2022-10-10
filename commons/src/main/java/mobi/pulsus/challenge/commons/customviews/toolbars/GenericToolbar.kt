package mobi.pulsus.challenge.commons.customviews.toolbars

import android.app.Activity
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import mobi.pulsus.challenge.commons.R
import mobi.pulsus.challenge.commons.extensions.configureBackButtonAction
import mobi.pulsus.challenge.commons.extensions.setAccessibilityAsButton
import mobi.pulsus.challenge.commons.extensions.setAccessibilityAsHeader
import mobi.pulsus.challenge.commons.extensions.setToolbarAccessibleBackButton

class GenericToolbar @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val btLeftButton by lazy { findViewById<MaterialButton>(R.id.mbt_generic_toolbar_left_button) }
    private val tvToolbarTitle by lazy { findViewById<TextView>(R.id.tv_generic_toolbar_title) }
    private val btRightButton by lazy { findViewById<MaterialButton>(R.id.mbt_generic_toolbar_right_button) }

    private var showLeftButton: Boolean
    private var showTitle: Boolean = true
    private var showRightButton: Boolean

    private var attributesTypedArray: TypedArray

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
}