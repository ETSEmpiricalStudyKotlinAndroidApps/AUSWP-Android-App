package uk.ac.aber.dcs.cs39440.auswpandroidapp.ui.tracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import uk.ac.aber.dcs.cs39440.auswpandroidapp.R
import uk.ac.aber.dcs.cs39440.auswpandroidapp.databinding.FragmentWorkoutBinding
import uk.ac.aber.dcs.cs39440.auswpandroidapp.model.tracker.Workout
import uk.ac.aber.dcs.cs39440.auswpandroidapp.model.tracker.WorkoutViewModel



private const val GRID_COUNT = 1

class workoutFragment : Fragment() {

    private var oldWorkoutList: LiveData<List<Workout>>? = null
    private lateinit var workoutRecyclerAdapter: WorkoutAdapter
    private val workoutViewModel: WorkoutViewModel by viewModels()
    private lateinit var workoutFragmentBinding: FragmentWorkoutBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        workoutFragmentBinding = FragmentWorkoutBinding.inflate(inflater, container, false)

        addWorkoutRecyclerView()
        backbuttonpress()


        addFab()

        return workoutFragmentBinding.root
    }

    private fun backbuttonpress(){
        val callback: OnBackPressedCallback =
                object: OnBackPressedCallback(true){
                    override fun handleOnBackPressed() {
                        findNavController().navigateUp()
                    }
                }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        val parent = requireActivity() as ToggleState
        parent.setNavigationDrawer(false)
    }

    private fun addFab(){

        val fab = workoutFragmentBinding.addWords

        fab.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_workoutFragment_to_addWorkoutFragment)
        }
    }

    private fun addWorkoutRecyclerView(){
        val listWorkout = workoutFragmentBinding.workoutList
        listWorkout.setHasFixedSize(true)

        val gridLayoutManager = GridLayoutManager(context, GRID_COUNT)
        listWorkout.layoutManager = gridLayoutManager

        workoutRecyclerAdapter = WorkoutAdapter(context)
        listWorkout.adapter = workoutRecyclerAdapter

        val workoutList = searchForWorkouts()

        if (oldWorkoutList != workoutList){
            oldWorkoutList?.removeObservers(viewLifecycleOwner)
            oldWorkoutList = workoutList
        }
        if (!workoutList.hasObservers()){
            workoutList.observe(viewLifecycleOwner) { workouts ->
                workoutRecyclerAdapter.changeDataSet(workouts.toMutableList())
            }
        }


    }

    override fun onDestroyView() {


        val parent = requireActivity() as ToggleState
        parent.setNavigationDrawer(true)

        super.onDestroyView()
    }

private fun searchForWorkouts():LiveData<List<Workout>>{
    return workoutViewModel.getWorkouts()
}

}