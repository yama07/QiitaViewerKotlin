package jp.yama07.qiitaviewer.di

import jp.yama07.qiitaviewer.viewmodel.BrowseArticleViewModel
import jp.yama07.qiitaviewer.viewmodel.SearchArticleViewModel
import jp.yama07.qiitaviewer.vo.Article
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
  viewModel { SearchArticleViewModel(get()) }
  viewModel { (article: Article) -> BrowseArticleViewModel(article) }
}