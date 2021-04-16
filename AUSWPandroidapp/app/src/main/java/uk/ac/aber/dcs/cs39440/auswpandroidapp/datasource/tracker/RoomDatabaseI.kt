package uk.ac.aber.dcs.cs39440.auswpandroidapp.datasource.tracker

import uk.ac.aber.dcs.cs39440.auswpandroidapp.model.tracker.WorkoutDAO

interface RoomDatabaseI {
    fun workoutDAO():WorkoutDAO
    fun closeDb()
}