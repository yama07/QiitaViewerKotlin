package jp.yama07.qiitaviewer

import android.app.Application
import jp.yama07.qiitaviewer.di.qiitaServiceModule
import jp.yama07.qiitaviewer.di.repositoryModule
import jp.yama07.qiitaviewer.di.viewModelModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber

@Suppress("unused")
class QiitaViewerApp : Application() {
  override fun onCreate() {
    super.onCreate()

    Timber.plant(Timber.DebugTree())
    startKoin(this, listOf(qiitaServiceModule, repositoryModule, viewModelModule))
  }
}