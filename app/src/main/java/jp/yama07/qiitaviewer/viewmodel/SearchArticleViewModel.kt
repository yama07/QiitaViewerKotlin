package jp.yama07.qiitaviewer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jp.yama07.qiitaviewer.repository.ArticleRepository
import jp.yama07.qiitaviewer.vo.Article
import jp.yama07.qiitaviewer.vo.Response
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class SearchArticleViewModel(
  private val articleRepository: ArticleRepository
) : ViewModel(), CoroutineScope {

  private val job = Job()
  override val coroutineContext: CoroutineContext = Dispatchers.Main + job

  val articles = MutableLiveData<List<Article>>()
  val isLoading = MutableLiveData<Boolean>()
  val occurredException = MutableLiveData<Throwable>()

  fun searchArticles(query: String) {
    empty()
    launch(Dispatchers.IO) {
      isLoading.postValue(true)
      val response = articleRepository.searchArticles(query)
      isLoading.postValue(false)
      when (response) {
        is Response.Success -> {
          val data = response.data
          data.forEach { Timber.d("article: $it") }
          articles.postValue(data)
        }
        is Response.Error -> {
          val err = response.throwable
          Timber.e(err)
          occurredException.postValue(err)
        }
      }
    }
  }

  private fun empty() {
    isLoading.postValue(false)
    articles.postValue(null)
    occurredException.postValue(null)
  }

  override fun onCleared() {
    super.onCleared()
    job.cancel()
  }
}