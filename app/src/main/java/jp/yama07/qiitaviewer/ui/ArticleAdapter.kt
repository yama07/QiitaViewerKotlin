package jp.yama07.qiitaviewer.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import jp.yama07.qiitaviewer.R
import jp.yama07.qiitaviewer.databinding.ArticleItemBinding
import jp.yama07.qiitaviewer.vo.Article

class ArticleAdapter(
  val callback: ArticleItemClickCallback
) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {
  init {
    setHasStableIds(true)
  }

  var articleList: List<Article> = emptyList()
    set(value) {
      if (field.isEmpty()) {
        field = value
        notifyItemRangeInserted(0, value.size)
      } else {
        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
          override fun getOldListSize(): Int = field.size
          override fun getNewListSize(): Int = value.size
          override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            field[oldItemPosition].id == value[newItemPosition].id

          override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            field[oldItemPosition] == value[newItemPosition]
        })
        field = value
        diffResult.dispatchUpdatesTo(this)
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

  override fun getItemCount(): Int = articleList.size

  override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
    holder.binding.also {
      it.article = articleList[position]
      it.callback = callback
      it.executePendingBindings()
    }
  }

  class ArticleViewHolder(val binding: ArticleItemBinding) : RecyclerView.ViewHolder(binding.root)
}