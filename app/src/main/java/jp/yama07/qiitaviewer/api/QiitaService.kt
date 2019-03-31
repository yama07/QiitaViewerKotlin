package jp.yama07.qiitaviewer.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import jp.yama07.qiitaviewer.AppJsonAdapterFactory
import jp.yama07.qiitaviewer.vo.Article
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface QiitaService {

  companion object {
    private const val SERVICE_ENDPOINT = "https://qiita.com"
    fun create(endpoint: String = SERVICE_ENDPOINT): QiitaService = Retrofit.Builder()
      .baseUrl(endpoint)
      .addConverterFactory(
        MoshiConverterFactory.create(
          Moshi.Builder()
            .add(AppJsonAdapterFactory.INSTANCE)
            .build()
        )
      )
      .addCallAdapterFactory(CoroutineCallAdapterFactory())
      .build()
      .create(QiitaService::class.java)
  }

  @GET("/api/v2/items")
  fun getItemsAsync(
    @Query("query") query: String,
    @Query("page") page: Int,
    @Query("per_page") per_page: Int
  ): Deferred<List<Article>>
}