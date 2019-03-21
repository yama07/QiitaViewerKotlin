package jp.yama07.qiitaviewer.api

import jp.yama07.qiitaviewer.vo.Article
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface QiitaService {

  @GET("/api/v2/items")
  fun getItemsAsync(
    @Query("query") query: String,
    @Query("page") page: Int = 1,
    @Query("per_page") per_page: Int = 20
  ): Deferred<List<Article>>
}