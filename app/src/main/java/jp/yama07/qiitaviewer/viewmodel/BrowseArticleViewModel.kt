package jp.yama07.qiitaviewer.viewmodel

import androidx.lifecycle.ViewModel
import jp.yama07.qiitaviewer.vo.Article

class BrowseArticleViewModel(
  val article: Article
) : ViewModel() {
  val url: String = article.url
}