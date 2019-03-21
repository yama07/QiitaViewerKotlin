package jp.yama07.qiitaviewer.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import jp.yama07.qiitaviewer.AppJsonAdapterFactory
import jp.yama07.qiitaviewer.api.QiitaService
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val SERVICE_ENDPOINT = "https://qiita.com"

val qiitaServiceModule = module {
  single<QiitaService> {
    Retrofit.Builder()
      .baseUrl(SERVICE_ENDPOINT)
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
}
