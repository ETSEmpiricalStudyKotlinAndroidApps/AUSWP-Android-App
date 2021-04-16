package uk.ac.aber.dcs.cs39440.auswpandroidapp.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import uk.ac.aber.dcs.cs39440.auswpandroidapp.datasource.tracker.RoomDatabaseI
import uk.ac.aber.dcs.cs39440.auswpandroidapp.model.tracker.Workout
import uk.ac.aber.dcs.cs39440.auswpandroidapp.model.tracker.WorkoutDAO

@Database(entities = [Workout::class], version = 1)
abstract class WokroutInMemoryRoomDatabase: RoomDatabase(), RoomDatabaseI {

    abstract override fun workoutDAO(): WorkoutDAO


    override fun closeDb() {
        instance?.close()
        instance = null
    }

    companion object{
        private var instance: WokroutInMemoryRoomDatabase? = null
        private val coroutineScope = CoroutineScope(Dispatchers.Main)

        fun getDatabase(context: Context):WokroutInMemoryRoomDatabase?{
            synchronized(this){
                if(instance == null){
                    instance =
                        Room.inMemoryDatabaseBuilder(
                            context.applicationContext,
                            WokroutInMemoryRoomDatabase::class.java
                        )
                            .allowMainThreadQueries()

                            .build()
                }
                return instance!!
            }
        }
    }
}