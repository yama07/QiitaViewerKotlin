package jp.yama07.qiitaviewer.vo

sealed class Resource {
  data class Completed<out T>(val data: T) : Resource()
  object Loading : Resource()
}