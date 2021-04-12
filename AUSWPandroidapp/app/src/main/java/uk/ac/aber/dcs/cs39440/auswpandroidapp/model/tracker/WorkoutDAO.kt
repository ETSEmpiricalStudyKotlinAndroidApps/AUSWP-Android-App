/**
 * Interface to allow access to underlying Room database for workouts
 * @author Callum Robert Binner
 * @version 1
 */

package uk.ac.aber.dcs.cs39440.auswpandroidapp.model.tracker

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface WorkoutDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSingleWorkout(workout:Workout)

    @Insert
    fun insertMultipleWorkouts(workoutList:List<Workout>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateWorkout(workout: Workout)

    @Delete
    fun DeleteWorkout(workout:Workout)

    @Query("DELETE FROM workouts")
    fun deleteAllWorkouts()

    @Query("SELECT * FROM workouts")
    fun getAllWorkouts(): LiveData<List<Workout>>
}