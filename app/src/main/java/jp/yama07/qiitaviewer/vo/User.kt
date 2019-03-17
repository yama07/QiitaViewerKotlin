package jp.yama07.qiitaviewer.vo

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import se.ansman.kotshi.JsonSerializable

@Parcelize
@JsonSerializable
data class User(
  val id: String,
  val name: String,
  @Json(name = "profile_image_url")
  val profileImageUrl: String
) : Parcelable