package mobi.pulsus.challenge.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mobi.pulsus.challenge.data.dto.JokeDTO

/**
 * Data Access Object for the jokes table.
 */
@Dao
interface JokeDao {
    /**
     * @return all jokes.
     */
    @Query("SELECT * FROM jokes_table_name")
    suspend fun getJokes(): List<JokeDTO>

    /**
     * @return a joke by the id.
     *
     * @param id the id of the joke.
     */
    @Query("SELECT * FROM jokes_table_name WHERE id = :id")
    fun getJokeById(id: String): JokeDTO?

    /**
     * Insert a joke in the database. If the joke already exists, replace it.
     *
     * @param joke the joke to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveJoke(joke: JokeDTO)

    /**
     * Delete joke.
     *
     * @param joke the joke to be deleted.
     */
    @Delete
    suspend fun deleteJoke(joke: JokeDTO)
}