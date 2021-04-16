/**
 * The Repository class acting as a face to the underlying Room database
 * @author Callum Robert Binner
 * @version 1
 *
 */


package uk.ac.aber.dcs.cs39440.auswpandroidapp.datasource.tracker

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uk.ac.aber.dcs.cs39440.auswpandroidapp.datasource.Injection
import uk.ac.aber.dcs.cs39440.auswpandroidapp.model.tracker.Workout




class WorkoutRepository(application: Application) {
    private val workoutDao = Injection.getDatabase(application).workoutDAO()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insert(workout: Workout) {
        coroutineScope.launch(Dispatchers.IO) {
            workoutDao.insertSingleWorkout(workout)
        }
    }

    fun insertMultipleWorkouts(workouts: List<Workout>) {
        coroutineScope.launch(Dispatchers.IO) {
            workoutDao.insertMultipleWorkouts(workouts)
        }
    }

    fun getAllWorkouts() = workoutDao.getAllWorkouts()

    fun deleteAllWorkouts() = workoutDao.deleteAllWorkouts()
}
