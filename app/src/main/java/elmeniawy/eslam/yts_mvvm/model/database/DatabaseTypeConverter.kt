package elmeniawy.eslam.yts_mvvm.model.database

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import elmeniawy.eslam.yts_mvvm.model.data_classes.Torrent

/**
 * DatabaseTypeConverter
 *
 * Created by Eslam El-Meniawy on 18-Apr-2019.
 * Roqay
 */
class DatabaseTypeConverter {
    private val moshi = Moshi.Builder().build()
    private val stringListType = Types.newParameterizedType(List::class.java, String::class.java)
    private val torrentListType = Types.newParameterizedType(List::class.java, Torrent::class.java)
    private val adapterStringList: JsonAdapter<List<String>> = moshi.adapter(stringListType)
    private val adapterTorrentList: JsonAdapter<List<Torrent>> = moshi.adapter(torrentListType)

    @TypeConverter
    fun stringToStringList(value: String): List<String> = adapterStringList.fromJson(value) ?: listOf()

    @TypeConverter
    fun stringListToString(list: List<String>): String = adapterStringList.toJson(list) ?: ""

    @TypeConverter
    fun stringToTorrentList(value: String): List<Torrent> = adapterTorrentList.fromJson(value) ?: listOf()

    @TypeConverter
    fun torrentListToString(list: List<Torrent>): String = adapterTorrentList.toJson(list) ?: ""
}