package jp.yama07.qiitaviewer.ui

import android.graphics.Bitmap
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

class WebViewProgressAdapter() {

  val loadProgress = MediatorLiveData<Int>().also { it.value = 0 }
  val isLoading = MutableLiveData<Boolean>().also { it.value = false }

  val webChromeClient = object : WebChromeClient() {
    override fun onProgressChanged(view: WebView?, newProgress: Int) {
      super.onProgressChanged(view, newProgress)
      loadProgress.value = newProgress
    }
  }

  val webViewClient = object : WebViewClient() {
    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
      super.onPageStarted(view, url, favicon)
      isLoading.value = true
    }

    override fun onPageFinished(view: WebView?, url: String?) {
      super.onPageFinished(view, url)
      isLoading.value = false
    }
  }
}