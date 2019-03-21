package jp.yama07.qiitaviewer.ext

import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter

@BindingAdapter("onQueryText")
fun SearchView.onQueryText(listener: SearchView.OnQueryTextListener) {
  setOnQueryTextListener(listener)
}