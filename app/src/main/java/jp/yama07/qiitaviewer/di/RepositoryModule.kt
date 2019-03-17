package jp.yama07.qiitaviewer.di

import jp.yama07.qiitaviewer.repository.ArticleRepository
import org.koin.dsl.module.module

val repositoryModule = module {
  single { ArticleRepository(get()) }
}