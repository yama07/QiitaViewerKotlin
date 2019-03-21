package jp.yama07.qiitaviewer.vo

sealed class Response<out T : Any> {
  data class Success<out T : Any>(val data: T) : Response<T>()
  data class Error(val throwable: Throwable) : Response<Nothing>()
}