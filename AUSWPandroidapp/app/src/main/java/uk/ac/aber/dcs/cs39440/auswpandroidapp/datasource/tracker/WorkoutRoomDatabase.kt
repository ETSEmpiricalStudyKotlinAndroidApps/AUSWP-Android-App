package uk.ac.aber.dcs.cs39440.auswpandroidapp.datasource.tracker

import android.content.Context
import android.telecom.Call
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uk.ac.aber.dcs.cs39440.auswpandroidapp.model.tracker.Workout
import uk.ac.aber.dcs.cs39440.auswpandroidapp.model.tracker.WorkoutDAO


@Database(entities = [Workout::class], version = 1)

abstract class WorkoutRoomDatabase: RoomDatabase() {

    abstract fun workoutDAO(): WorkoutDAO

    companion object {
        private var instance: WorkoutRoomDatabase? = null
        private val coroutineScope = CoroutineScope(Dispatchers.Main)

        fun getDatabase(context: Context): WorkoutRoomDatabase?{
            synchronized(this){
                if(instance == null){
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



