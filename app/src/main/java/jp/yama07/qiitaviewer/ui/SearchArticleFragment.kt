package jp.yama07.qiitaviewer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import jp.yama07.qiitaviewer.R
import jp.yama07.qiitaviewer.databinding.SearchArticleFragmentBinding
import jp.yama07.qiitaviewer.ext.hideKeyboard
import jp.yama07.qiitaviewer.ext.observeNonNull
import jp.yama07.qiitaviewer.ext.observeOrNull
import jp.yama07.qiitaviewer.ext.showSnackbar
import jp.yama07.qiitaviewer.viewmodel.SearchArticleViewModel
import jp.yama07.qiitaviewer.vo.Article
import org.koin.android.viewmodel.ext.android.viewModel

class SearchArticleFragment : Fragment() {
  private val vm: SearchArticleViewModel by viewModel()
  private lateinit var binding: SearchArticleFragmentBinding
  private lateinit var articleAdapter: ArticleAdapter

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = DataBindingUtil.inflate(
      inflater,
      R.layout.search_article_fragment,
      container,
      false
    )
    articleAdapter = ArticleAdapter(object : ArticleItemClickCallback {
      override fun onClick(article: Article) {
        Navigation
          .findNavController(binding.root)
          .navigate(SearchArticleFragmentDirections.actionSearchArticleFragmentToBrowseArticleFragment(article))
      }
    })
    binding.also {
      it.lifecycleOwner = this
      it.viewModel = vm
      it.articleAdapter = articleAdapter
      it.onQueryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
          binding.root.hideKeyboard()
          vm.searchArticles(query)
          return true
        }

        override fun onQueryTextChange(query: String): Boolean = false
      }
    }
    subscribe()
    return binding.root
  }

  private fun subscribe() {
    vm.articles.observeOrNull(this) { articles ->
      articleAdapter.articleList = articles ?: emptyList()
      if (articles?.isEmpty() == true) {
        binding.root.showSnackbar("Not Found.", Snackbar.LENGTH_LONG)
      }
    }
    vm.occurredException.observeNonNull(this) { t ->
      binding.root.showSnackbar("An error occurred: ${t.message}", Snackbar.LENGTH_LONG)
    }
  }
}