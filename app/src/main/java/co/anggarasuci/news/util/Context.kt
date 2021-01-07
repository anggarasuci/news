package co.anggarasuci.news.util

import android.content.Context
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun Context.getColorCompat(@ColorRes color: Int) = ContextCompat.getColor(this, color)
fun Context.toastShort(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_SHORT)
fun Context.toastLong(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_LONG)
