package uk.ac.aber.dcs.cs39440.auswpandroidapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uk.ac.aber.dcs.cs39440.auswpandroidapp.datasource.Injection
import uk.ac.aber.dcs.cs39440.auswpandroidapp.datasource.tracker.RoomDatabaseI
import uk.ac.aber.dcs.cs39440.auswpandroidapp.model.tracker.Workout
import uk.ac.aber.dcs.cs39440.auswpandroidapp.model.tracker.WorkoutDAO
import uk.ac.aber.dcs.cs39440.auswpandroidapp.util.TestLiveDataUtil
import kotlin.jvm.Throws
import androidx.arch.core.executor.testing.InstantTaskExecutorRule



@RunWith(AndroidJUnit4::class)
class WorkoutInsertTest {

    @JvmField @Rule

    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var workoutDao: WorkoutDAO
    private lateinit var db: RoomDatabaseI

    @Before
    @Throws(Exception::class)
    fun createDb(){
        val context = androidx.test.InstrumentationRegistry.getTargetContext()
        db = Injection.getDatabase(context)
        workoutDao = db.workoutDAO()
    }

    @After
    fun closeDb(){
        db.closeDb()
    }

    @Test
    fun insertAWorkout_withCheck(){
        val workout = Workout(
            0,
            "Running",
        "09/04/2021",
            "5k PB - 22:34"
        )

        workoutDao.insertSingleWorkout(workout)
        val workoutFound = workoutDao.getAllWorkouts()
        assertEquals(1,TestLiveDataUtil.getValue(workoutFound).size)
    }

    @Test
    fun insertMultipleWorkouts_checkInsert(){
        val workout = Workout(
        0,
        "Run",
        "09/05/2021",
            "Half Marathon")

        val workout1 = Workout(
            0,
            "Swim",
            "09/04/2021",
        "50m IM, 500M Free"
        )

        var workouts = mutableListOf<Workout>()
        workouts.add(workout)
        workouts.add(workout1)
        workoutDao.insertMultipleWorkouts(workouts)

        val workoutsFound = workoutDao.getAllWorkouts()
        assertEquals(2,TestLiveDataUtil.getValue(workoutsFound).size)
    }

    @Test
    fun insert10Workouts_checkInsert(){
        val workout = Workout(
                0,
                "Run",
                "09/05/2021",
                "Half Marathon")

        val workout1 = Workout(
                0,
                "Swim",
                "09/04/2021",
                "50m IM, 500M Free"
        )
        val workout2 = Workout(
                0,
                "Swim",
                "09/04/2021",
                "50m IM, 500M Free"
        )
        val workout3 = Workout(
                0,
                "Swim",
                "09/04/2021",
                "50m IM, 500M Free"
        )
        val workout4 = Workout(
                0,
                "Swim",
                "09/04/2021",
                "50m IM, 500M Free"
        )
        val workout5 = Workout(
                0,
                "Swim",
                "09/04/2021",
                "50m IM, 500M Free"
        )
        val workout6 = Workout(
                0,
                "Swim",
                "09/04/2021",
                "50m IM, 500M Free"
        )
        val workout7 = Workout(
                0,
                "Swim",
                "09/04/2021",
                "50m IM, 500M Free"
        )
        val workout8 = Workout(
                0,
                "Swim",
                "09/04/2021",
                "50m IM, 500M Free"
        )
        val workout9 = Workout(
                0,
                "Swim",
                "09/04/2021",
                "50m IM, 500M Free"
        )
        val workout10 = Workout(
                0,
                "Swim",
                "09/04/2021",
                "50m IM, 500M Free"
        )

        var workouts = mutableListOf<Workout>()
        workouts.add(workout)
        workouts.add(workout1)
        workoutDao.insertMultipleWorkouts(workouts)

        val workoutsFound = workoutDao.getAllWorkouts()
        assertEquals(10,TestLiveDataUtil.getValue(workoutsFound).size)
    }

    @Test fun deleteWorkout_AfterInsertion(){
        val workout = Workout(
            0,
            "Water Polo",
        "16/04/2021",
            "Match against Bangor. 39-0 win"
        )
        workoutDao.insertSingleWorkout(workout)
        workoutDao.deleteAllWorkouts()

        val workoutsFound = workoutDao.getAllWorkouts()
        assertEquals(0,TestLiveDataUtil.getValue(workoutsFound).size)
    }

}