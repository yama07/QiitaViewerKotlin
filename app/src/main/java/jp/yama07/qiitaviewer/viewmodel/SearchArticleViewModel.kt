package jp.yama07.qiitaviewer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.toLiveData
import jp.yama07.qiitaviewer.api.ArticleDataSource
import jp.yama07.qiitaviewer.repository.ArticleRepository
import jp.yama07.qiitaviewer.vo.Article
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class SearchArticleViewModel(
  private val articleRepository: ArticleRepository
) : ViewModel(), CoroutineScope {

  companion object {
    private val PAGED_LIST_CONFIG = PagedList.Config.Builder()
      .setPageSize(20)
      .setEnablePlaceholders(false)
      .build()
  }

  private val job = Job()
  override val coroutineContext: CoroutineContext = Dispatchers.Main + job

  val query = MutableLiveData<String>()

  private val dataSourceFactory: LiveData<ArticleDataSource.Factory> = map(query) {
    articleRepository.searchArticles(it, coroutineContext = coroutineContext)
  }
  private val dataSource: LiveData<ArticleDataSource> = switchMap(dataSourceFactory) { it.sourceLiveData }

  val articles: LiveData<PagedList<Article>> = switchMap(dataSourceFactory) { it.toLiveData(PAGED_LIST_CONFIG) }
  val isLoading: LiveData<Boolean> = switchMap(dataSource) { it.isLoading }
  val occurredException: LiveData<Throwable> = switchMap(dataSource) { it.occurredException }

  override fun onCleared() {
    super.onCleared()
    job.cancel()
  }
}