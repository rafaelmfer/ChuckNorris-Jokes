package mobi.pulsus.challenge.data.local

import android.content.Context
import androidx.room.Room

/**
 * Singleton class that is used to create a reminder db
 */
object LocalDB {

    /**
     * static method that creates a reminder class and returns the DAO of the reminder
     */
    fun createJokeDao(context: Context): JokeDao {
        return Room.databaseBuilder(
            context.applicationContext,
            JokesDatabase::class.java, "jokes.db"
        )
            .build()
            .jokeDao()
    }

}