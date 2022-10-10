package mobi.pulsus.challenge.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import mobi.pulsus.challenge.data.dto.JokeDTO

/**
 * The Room Database that contains the joke table.
 */
@Database(entities = [JokeDTO::class], version = 1, exportSchema = false)
abstract class JokesDatabase : RoomDatabase() {

    abstract fun jokeDao(): JokeDao
}