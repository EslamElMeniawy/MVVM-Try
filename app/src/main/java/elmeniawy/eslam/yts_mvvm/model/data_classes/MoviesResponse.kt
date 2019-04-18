package elmeniawy.eslam.yts_mvvm.model.data_classes

import com.squareup.moshi.Json

/**
 * MoviesResponse
 *
 * Created by Eslam El-Meniawy on 18-Apr-2019.
 * Roqay
 */
data class MoviesResponse(
    @Json(name = "status") val status: String = "",
    @Json(name = "status_message") val statusMessage: String = "",
    @Json(name = "data") val data: Data = Data()
)