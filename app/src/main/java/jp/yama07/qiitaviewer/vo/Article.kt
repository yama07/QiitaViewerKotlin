package jp.yama07.qiitaviewer.vo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import se.ansman.kotshi.JsonSerializable

@Parcelize
@JsonSerializable
data class Article(
  val id: String,
  val title: String,
  val url: String,
  val user: User
) : Parcelable