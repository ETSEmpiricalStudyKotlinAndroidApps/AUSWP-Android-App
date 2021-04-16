package uk.ac.aber.dcs.cs39440.auswpandroidapp.datasource

import android.content.Context
import uk.ac.aber.dcs.cs39440.auswpandroidapp.datasource.tracker.RoomDatabaseI

object Injection {
    fun getDatabase(context: Context): RoomDatabaseI =
        WokroutInMemoryRoomDatabase.getDatabase(context)!!
}