package jp.yama07.qiitaviewer.ui

import jp.yama07.qiitaviewer.vo.Article

interface ArticleItemClickCallback {
  fun onClick(article: Article)
}