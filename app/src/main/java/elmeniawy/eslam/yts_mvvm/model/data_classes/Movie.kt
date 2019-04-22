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
    @field:Json(name = "id") @PrimaryKey @ColumnInfo(name = "id") val movieId: Long = Long.MIN_VALUE,
    @field:Json(name = "imdb_code") @ColumnInfo(name = "imdb_code") val imdbCode: String = "",
    @field:Json(name = "title") @ColumnInfo(name = "title") val title: String = "",
    @field:Json(name = "year") @ColumnInfo(name = "year") val year: Long = Long.MIN_VALUE,
    @field:Json(name = "rating") @ColumnInfo(name = "rating") val rating: Double = Double.MIN_VALUE,
    @field:Json(name = "runtime") @ColumnInfo(name = "runtime") val runtime: Long = Long.MIN_VALUE,
    @field:Json(name = "genres") @ColumnInfo(name = "genres") val genres: List<String> = listOf(),
    @field:Json(name = "synopsis") @ColumnInfo(name = "synopsis") val synopsis: String = "",
    @field:Json(name = "background_image") @ColumnInfo(name = "background_image") val backgroundImage: String = "",
    @field:Json(name = "medium_cover_image") @ColumnInfo(name = "medium_cover_image") val mediumCoverImage: String = "",
    @field:Json(name = "torrents") @ColumnInfo(name = "torrents") val torrents: List<Torrent> = listOf()
)