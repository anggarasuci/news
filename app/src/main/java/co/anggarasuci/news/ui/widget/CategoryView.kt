package co.anggarasuci.news.ui.widget

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import co.anggarasuci.news.R
import co.anggarasuci.news.util.getColorCompat
import kotlinx.android.synthetic.main.widget_category.view.*

class CategoryView : ConstraintLayout {

    var title: String = ""
    set(value) {
        field = value
        label_txt?.text = field
    }

    var isActive: Boolean = false
        @SuppressLint("NewApi")
        set(value) {
            field = value
            label_txt?.apply {
                if (field) {
                    background = context?.getDrawable(R.drawable.bg_active)
                    context?.getColorCompat(R.color.white)?.let { setTextColor(it) }
                } else {
                    background = context?.getDrawable(R.drawable.bg_disabled)
                    context?.getColorCompat(R.color.black)?.let { setTextColor(it) }
                }
            }
        }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.widget_category, this, true)
    }

    @SuppressLint("CustomViewStyleable")
    private fun init(attrs: AttributeSet? = null) {
        lateinit var typedArray: TypedArray
        try {
            attrs?.let {
                typedArray =
                    context.obtainStyledAttributes(it, R.styleable.category_attributes, 0, 0)
                val title = typedArray.getString(R.styleable.category_attributes_android_text) ?: ""
                label_txt?.text = title
            }
        } finally {
            typedArray.recycle()
        }
    }
}
