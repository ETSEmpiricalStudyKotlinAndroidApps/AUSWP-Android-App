/**
 * Room database implementation. Entity class used to define
 * table.
 * @author Callum Robert Binner
 * @version 1
 *
 */

package uk.ac.aber.dcs.cs39440.auswpandroidapp.datasource.tracker

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import uk.ac.aber.dcs.cs39440.auswpandroidapp.model.tracker.Workout
import uk.ac.aber.dcs.cs39440.auswpandroidapp.model.tracker.WorkoutDAO


@Database(entities = [Workout::class], version = 1)

abstract class WorkoutRoomDatabase : RoomDatabase() {

    abstract fun workoutDAO(): WorkoutDAO

    companion object {
        private var instance: WorkoutRoomDatabase? = null
        private val coroutineScope = CoroutineScope(Dispatchers.Main)

        fun getDatabase(context: Context): WorkoutRoomDatabase? {
            synchronized(this) {
                if (instance == null) {
                    instance =
                        Room.databaseBuilder<WorkoutRoomDatabase>(
                            context.applicationContext,
                            WorkoutRoomDatabase::class.java,
                            "Workout_Database"
                        )
                            .allowMainThreadQueries()

                            .build()
                }
                return instance!!
            }
        }
    }
}



