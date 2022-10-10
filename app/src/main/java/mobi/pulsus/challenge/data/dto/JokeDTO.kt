package mobi.pulsus.challenge.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import mobi.pulsus.challenge.domain.model.JokeModel

/**
 * Immutable model class for a Joke. In order to compile with Room
 *
 * @param id             jokes' id
 * @param categories     jokes' categories
 * @param createdAt      when the joke was created
 * @param iconUrl        url icon of the joke
 * @param url            jokes' url
 * @param value          jokes' text
 * @param isFavorite     joke is saved as favorite
 */

@Entity(tableName = "jokes_table_name")
data class JokeDTO(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "createdAt") val createdAt: String,
    @ColumnInfo(name = "iconUrl") val iconUrl: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "value") val value: String,
    @ColumnInfo(name = "isFavorite") val isFavorite: Boolean = false
) {
    fun dtoToModel(): JokeModel {
        return JokeModel(
            categories = listOf(),
            createdAt = createdAt,
            iconUrl = iconUrl,
            id = id,
            url = url,
            value = value,
            isFavorite = isFavorite
        )
    }
}
