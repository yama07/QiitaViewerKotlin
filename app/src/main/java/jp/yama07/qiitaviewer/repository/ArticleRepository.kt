package jp.yama07.qiitaviewer.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import jp.yama07.qiitaviewer.api.QiitaService
import jp.yama07.qiitaviewer.vo.Article
import jp.yama07.qiitaviewer.vo.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class ArticleRepository(private val qiitaService: QiitaService) {
  fun searchArticles(query: String): LiveData<Resource> {
    val data = MutableLiveData<Resource>().also { it.postValue(Resource.Loading) }
    qiitaService.getItems(query).enqueue(object : Callback<List<Article>> {
      override fun onResponse(call: Call<List<Article>>, response: Response<List<Article>>) {
        if (response.isSuccessful) {
          Timber.d("Response: $response")
          val articles = response.body() ?: listOf()
          data.postValue(Resource.Completed(articles))
        } else {
          Timber.e("An error occurred: $response")
          val msg = response.errorBody()?.string() ?: ""
          data.postValue(Resource.Completed(msg))
        }
      }

      override fun onFailure(call: Call<List<Article>>, t: Throwable) {
        Timber.e(t)
        data.postValue(Resource.Completed(t))
      }
    })
    return data
  }
}