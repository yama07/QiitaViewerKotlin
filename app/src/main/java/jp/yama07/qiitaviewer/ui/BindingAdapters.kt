package jp.yama07.qiitaviewer.ui

import android.view.View
import android.webkit.WebView
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("visibleGone")
fun View.showHide(show: Boolean) {
  visibility = if (show) View.VISIBLE else View.GONE
}

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String) {
  Glide.with(context).load(url).into(this)
}

@BindingAdapter("onQueryText")
fun SearchView.onQueryText(listener: SearchView.OnQueryTextListener) {
  setOnQueryTextListener(listener)
}

@BindingAdapter("progressAdapter")
fun WebView.setProgressAdapter(adapter: WebViewProgressAdapter) {
  adapter.also {
    webViewClient = it.webViewClient
    webChromeClient = it.webChromeClient
  }
}