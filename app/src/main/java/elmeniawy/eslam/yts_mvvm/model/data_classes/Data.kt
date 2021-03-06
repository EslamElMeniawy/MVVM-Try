package elmeniawy.eslam.yts_mvvm.model.data_classes

import com.squareup.moshi.Json

/**
 * Data
 *
 * Created by Eslam El-Meniawy on 18-Apr-2019.
 * Roqay
 */
data class Data(
    @field:Json(name = "movie_count") val movieCount: Long = Long.MIN_VALUE,
    @field:Json(name = "limit") val limit: Long = Long.MIN_VALUE,
    @field:Json(name = "page_number") val pageNumber: Long = Long.MIN_VALUE,
    @field:Json(name = "movies") val movies: List<Movie> = listOf()
)