package uk.ac.aber.dcs.cs39440.auswpandroidapp.model.tracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import uk.ac.aber.dcs.cs39440.auswpandroidapp.datasource.tracker.WorkoutRepository

class AddWorkoutViewModel(application: Application):AndroidViewModel(application) {
    private val repository: WorkoutRepository = WorkoutRepository(application)

    fun insertWorkout(workout:Workout){
        repository.insert(workout)
    }

}