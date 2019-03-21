package jp.yama07.qiitaviewer.ext

import android.webkit.WebView
import androidx.databinding.BindingAdapter
import jp.yama07.qiitaviewer.ui.WebViewProgressAdapter

@BindingAdapter("progressAdapter")
fun WebView.setProgressAdapter(adapter: WebViewProgressAdapter) {
  adapter.also {
    webViewClient = it.webViewClient
    webChromeClient = it.webChromeClient
  }
}