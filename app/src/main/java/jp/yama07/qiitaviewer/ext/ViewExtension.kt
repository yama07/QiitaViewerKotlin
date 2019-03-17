package jp.yama07.qiitaviewer.ext

import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService
import com.google.android.material.snackbar.Snackbar

fun View.hideKeyboard() {
  context.getSystemService<InputMethodManager>()
    ?.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}

fun View.showSnackbar(text: CharSequence, duration: Int) {
  Snackbar.make(this, text, duration).show()
}