package jp.yama07.qiitaviewer.ext

import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService
import androidx.databinding.BindingAdapter
import com.google.android.material.snackbar.Snackbar

@BindingAdapter("visibleGone")
fun View.showHide(show: Boolean) {
  visibility = if (show) View.VISIBLE else View.GONE
}

fun View.hideKeyboard() {
  context.getSystemService<InputMethodManager>()
    ?.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}

fun View.showSnackbar(text: CharSequence, duration: Int) {
  Snackbar.make(this, text, duration).show()
}