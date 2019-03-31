package jp.yama07.qiitaviewer.di

import jp.yama07.qiitaviewer.api.QiitaService
import org.koin.dsl.module.module

val qiitaServiceModule = module {
  single { QiitaService.create() }
}
