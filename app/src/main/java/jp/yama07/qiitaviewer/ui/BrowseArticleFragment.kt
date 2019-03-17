package jp.yama07.qiitaviewer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import jp.yama07.qiitaviewer.R
import jp.yama07.qiitaviewer.databinding.BrowseArticleFragmentBinding
import jp.yama07.qiitaviewer.viewmodel.BrowseArticleViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class BrowseArticleFragment : Fragment() {

  private val vm: BrowseArticleViewModel by viewModel {
    parametersOf(BrowseArticleFragmentArgs.fromBundle(arguments!!).article)
  }
  private lateinit var binding: BrowseArticleFragmentBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = DataBindingUtil.inflate<BrowseArticleFragmentBinding>(
      inflater,
      R.layout.browse_article_fragment,
      container,
      false
    ).also {
      it.lifecycleOwner = this
      it.viewModel = vm
      it.progressAdapter = WebViewProgressAdapter()
    }

    return binding.root
  }
}