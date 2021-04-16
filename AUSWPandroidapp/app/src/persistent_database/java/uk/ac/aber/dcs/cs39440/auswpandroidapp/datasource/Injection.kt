package uk.ac.aber.dcs.cs39440.auswpandroidapp.datasource

import android.content.Context
import androidx.room.RoomDatabase
import uk.ac.aber.dcs.cs39440.auswpandroidapp.datasource.tracker.RoomDatabaseI

object Injection {

    fun getDatabase(context: Context): RoomDatabaseI =
        WorkoutPersistentRoomDatabase.getDatabase(context)!!
}