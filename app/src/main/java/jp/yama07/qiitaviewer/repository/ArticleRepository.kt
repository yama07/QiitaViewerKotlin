package jp.yama07.qiitaviewer.repository

import jp.yama07.qiitaviewer.api.QiitaService
import jp.yama07.qiitaviewer.vo.Article
import jp.yama07.qiitaviewer.vo.Response

class ArticleRepository(private val qiitaService: QiitaService) {
  suspend fun searchArticles(query: String): Response<List<Article>> = runCatching {
    qiitaService.getItems(query).await()
  }.fold(
    onSuccess = {
      Response.Success(it)
    },
    onFailure = {
      Response.Error(it)
    }
  )
}