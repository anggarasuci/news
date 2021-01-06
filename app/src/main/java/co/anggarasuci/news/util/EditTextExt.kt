package co.anggarasuci.news.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.text.NumberFormat
import java.text.ParseException
import java.util.*


private var nf = NumberFormat.getInstance(Locale("id", "ID"))

fun EditText.afterTextChanged(action: (String) -> Unit) {
    val textWatcher = object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            action(editable.toString())
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

    }

    addTextChangedListener(textWatcher)
}

fun EditText.onTextChanged(action: (CharSequence, Int, Int, Int) -> Unit) {

    val textWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onTextChanged(char: CharSequence, start: Int, before: Int, count: Int) {
            action(char, start, before, count)
        }

    }

    addTextChangedListener(textWatcher)

}

fun EditText.MoneyTextChanged(action: (String) -> Unit) {
    val textWatcher = object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            if (text.isNotEmpty() && text.length > 3) {
                removeTextChangedListener(this)
                    val initLen: Int = text.length

                    val editTextContent = editable.toString()
                    val value = editTextContent.trimStart('0').replace(".", "")
                val textNumber = try {
                     nf.parse(value)
                } catch (nfe: NumberFormatException) {
                    nf.parse("0")
                } catch (e: ParseException) {
                    nf.parse("0")
                }
                    val cursorPosition = selectionStart

                    setText(nf.format(textNumber))

                    val endLen: Int = text.length

                    val sel = cursorPosition + (endLen - initLen)

                    if (sel > 0 && sel <= text.length) {
                        setSelection(sel)
                    } else {
                        // place cursor at the end?
                        setSelection(text.length - 1)
                    }
                addTextChangedListener(this)
            }

            action(editable.toString())
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
    }

    addTextChangedListener(textWatcher)
}