package jp.yama07.qiitaviewer.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import jp.yama07.qiitaviewer.vo.Article
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ArticleDataSource(
  val query: String,
  private val qiitaService: QiitaService,
  private val coroutineContext: CoroutineContext
) : PageKeyedDataSource<Int, Article>() {

  val isLoading: LiveData<Boolean>
    get() = _isLoading
  private val _isLoading = MutableLiveData<Boolean>()

  val occurredException: LiveData<Throwable>
    get() = _occurredException
  private val _occurredException = MutableLiveData<Throwable>()

  override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Article>) {
    CoroutineScope(coroutineContext).launch {
      val pageSize = params.requestedLoadSize
      val page = 1
      _isLoading.postValue(true)
      kotlin.runCatching {
        qiitaService.getItemsAsync(query, page = page, per_page = pageSize).await()
      }.fold(
        onSuccess = { callback.onResult(it, null, 2) },
        onFailure = { _occurredException.postValue(it) }
      )
      _isLoading.postValue(false)
    }
  }

  override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
    CoroutineScope(coroutineContext).launch {
      val pageSize = params.requestedLoadSize
      val page = params.key
      _isLoading.postValue(true)
      kotlin.runCatching {
        qiitaService.getItemsAsync(query, page = page, per_page = pageSize).await()
      }.fold(
        onSuccess = { callback.onResult(it, page + 1) },
        onFailure = { _occurredException.postValue(it) }
      )
      _isLoading.postValue(false)
    }
  }

  override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
    CoroutineScope(coroutineContext).launch {
      val pageSize = params.requestedLoadSize
      val page = params.key
      _isLoading.postValue(true)
      kotlin.runCatching {
        qiitaService.getItemsAsync(query, page = page, per_page = pageSize).await()
      }.fold(
        onSuccess = { callback.onResult(it, page - 1) },
        onFailure = { _occurredException.postValue(it) }
      )
      _isLoading.postValue(false)
    }
  }

  class Factory(
    private val query: String,
    private val qiitaService: QiitaService,
    private val coroutineContext: CoroutineContext
  ) : DataSource.Factory<Int, Article>() {

    val sourceLiveData: LiveData<ArticleDataSource>
      get() = _sourceLiveData
    private val _sourceLiveData = MutableLiveData<ArticleDataSource>()

    override fun create(): DataSource<Int, Article> =
      ArticleDataSource(query, qiitaService, coroutineContext).also {
        _sourceLiveData.postValue(it)
      }
  }
}