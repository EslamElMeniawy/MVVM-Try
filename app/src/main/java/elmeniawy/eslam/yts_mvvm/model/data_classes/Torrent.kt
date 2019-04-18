package elmeniawy.eslam.yts_mvvm.model.data_classes

import com.squareup.moshi.Json

/**
 * Torrent
 *
 * Created by Eslam El-Meniawy on 18-Apr-2019.
 * Roqay
 */
data class Torrent(
    @Json(name = "url") val url: String = "",
    @Json(name = "quality") val quality: String = "",
    @Json(name = "type") val type: String = "",
    @Json(name = "size") val size: String = ""
)