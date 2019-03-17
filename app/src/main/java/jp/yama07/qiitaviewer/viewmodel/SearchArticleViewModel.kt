package jp.yama07.qiitaviewer.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jp.yama07.qiitaviewer.repository.ArticleRepository
import jp.yama07.qiitaviewer.vo.Article
import jp.yama07.qiitaviewer.vo.Resource

class SearchArticleViewModel(
  private val articleRepository: ArticleRepository
) : ViewModel() {
  val articles = MediatorLiveData<List<Article>>()
  val isLoading = MutableLiveData<Boolean>()//.also { it.value = false }
  val errorMessage = MutableLiveData<String>()
  val occurredException = MutableLiveData<Throwable>()

  fun searchArticles(query: String) {
    val result = articleRepository.searchArticles(query)
    articles.addSource(result) {
      when (it) {
        is Resource.Loading -> {
          isLoading.postValue(true)
        }
        is Resource.Completed<*> -> {
          isLoading.postValue(false)
          val data = it.data
          when (data) {
            is String -> {
              errorMessage.postValue(data)
            }
            is Throwable -> {
              occurredException.postValue(data)
            }
            is List<*> -> {
              articles.postValue(data as List<Article>)
            }
          }
        }
      }
    }
  }
}