package jp.yama07.qiitaviewer.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import jp.yama07.qiitaviewer.R
import jp.yama07.qiitaviewer.databinding.ArticleItemBinding
import jp.yama07.qiitaviewer.vo.Article

class ArticleAdapter(
  val callback: ArticleItemClickCallback
) : PagedListAdapter<Article, ArticleAdapter.ArticleViewHolder>(DIFF_ITEM_CALLBACK) {

  companion object {
    private val DIFF_ITEM_CALLBACK = object : DiffUtil.ItemCallback<Article>() {
      override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean = oldItem.id == newItem.id
      override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean = oldItem == newItem
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder =
    DataBindingUtil.inflate<ArticleItemBinding>(
      LayoutInflater.from(parent.context),
      R.layout.article_item,
      parent,
      false
    ).let {
      ArticleViewHolder(it)
    }

  override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
    holder.binding.also {
      it.article = getItem(position)
      it.callback = callback
      it.executePendingBindings()
    }
  }

  class ArticleViewHolder(val binding: ArticleItemBinding) : RecyclerView.ViewHolder(binding.root)
}