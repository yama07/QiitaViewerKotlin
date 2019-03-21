package jp.yama07.qiitaviewer.api

import jp.yama07.qiitaviewer.vo.Article
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface QiitaService {

  @GET("/api/v2/items")
  fun getItems(@Query("query") query: String): Deferred<List<Article>>
}