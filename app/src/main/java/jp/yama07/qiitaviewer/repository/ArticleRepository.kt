package jp.yama07.qiitaviewer.repository

import jp.yama07.qiitaviewer.api.ArticleDataSource
import jp.yama07.qiitaviewer.api.QiitaService
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class ArticleRepository(private val qiitaService: QiitaService) {

  fun searchArticles(
    query: String,
    coroutineContext: CoroutineContext = Dispatchers.IO
  ): ArticleDataSource.Factory = ArticleDataSource.Factory(query, qiitaService, coroutineContext)

}