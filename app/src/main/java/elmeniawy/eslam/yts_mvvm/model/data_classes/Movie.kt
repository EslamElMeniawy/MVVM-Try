package elmeniawy.eslam.yts_mvvm.model.data_classes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import elmeniawy.eslam.yts_mvvm.utils.TABLE_MOVIES

/**
 * Movie
 *
 * Created by Eslam El-Meniawy on 18-Apr-2019.
 * Roqay
 */
@Entity(tableName = TABLE_MOVIES)
data class Movie(
    @Json(name = "id") @PrimaryKey @ColumnInfo(name = "id") val movieId: Long = Long.MIN_VALUE,
    @Json(name = "imdb_code") @ColumnInfo(name = "imdb_code") val imdbCode: String = "",
    @Json(name = "title") @ColumnInfo(name = "title") val title: String = "",
    @Json(name = "year") @ColumnInfo(name = "year") val year: Long = Long.MIN_VALUE,
    @Json(name = "rating") @ColumnInfo(name = "rating") val rating: Double = Double.MIN_VALUE,
    @Json(name = "runtime") @ColumnInfo(name = "runtime") val runtime: Long = Long.MIN_VALUE,
    @Json(name = "genres") @ColumnInfo(name = "genres") val genres: List<String> = listOf(),
    @Json(name = "synopsis") @ColumnInfo(name = "synopsis") val synopsis: String = "",
    @Json(name = "background_image") @ColumnInfo(name = "background_image") val backgroundImage: String = "",
    @Json(name = "medium_cover_image") @ColumnInfo(name = "medium_cover_image") val mediumCoverImage: String = "",
    @Json(name = "torrents") @ColumnInfo(name = "torrents") val torrents: List<Torrent> = listOf()
)