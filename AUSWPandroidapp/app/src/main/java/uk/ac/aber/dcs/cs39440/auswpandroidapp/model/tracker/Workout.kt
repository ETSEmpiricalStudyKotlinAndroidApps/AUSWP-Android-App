package uk.ac.aber.dcs.cs39440.auswpandroidapp.model.tracker

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "workouts")
data class Workout (
        @PrimaryKey(autoGenerate = true)
        @NonNull
        var id:Int =0,
        var activity: String ="",
        var date:String ="",
        var set: String =""
        )
