/**
 * ViewModel for the WorkoutFragment. Caches live data object
 * of list of workouts. Communicates with repository.
 * @author Callum Robert Binner
 * @version 1
 */




package uk.ac.aber.dcs.cs39440.auswpandroidapp.model.tracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import uk.ac.aber.dcs.cs39440.auswpandroidapp.datasource.tracker.WorkoutRepository

class WorkoutViewModel (application: Application): AndroidViewModel(application) {
    private var repository: WorkoutRepository = WorkoutRepository(application)
    var workoutList: LiveData<List<Workout>> = repository.getAllWorkouts()
    private set

    fun getWorkouts(): LiveData<List<Workout>>{
        workoutList = repository.getAllWorkouts()
        return workoutList
    }

    fun deleteAllWorkouts(){
        repository.deleteAllWorkouts()
    }
}