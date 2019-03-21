package jp.yama07.qiitaviewer.ext

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeOrNull(owner: LifecycleOwner, observer: (T?) -> Unit) = observe(owner, Observer {
  observer.invoke(it)
})

fun <T> LiveData<T>.observeNonNull(owner: LifecycleOwner, observer: (T) -> Unit) = observe(owner, Observer {
  if (it != null) observer.invoke(it)
})
