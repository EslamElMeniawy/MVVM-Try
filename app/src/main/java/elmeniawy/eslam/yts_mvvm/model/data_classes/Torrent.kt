package elmeniawy.eslam.yts_mvvm.model.data_classes

import com.squareup.moshi.Json

/**
 * Torrent
 *
 * Created by Eslam El-Meniawy on 18-Apr-2019.
 * Roqay
 */
data class Torrent(
    @field:Json(name = "url") val url: String = "",
    @field:Json(name = "quality") val quality: String = "",
    @field:Json(name = "type") val type: String = "",
    @field:Json(name = "size") val size: String = ""
)