package uk.ac.aber.dcs.cs39440.auswpandroidapp.ui.tracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uk.ac.aber.dcs.cs39440.auswpandroidapp.R
import uk.ac.aber.dcs.cs39440.auswpandroidapp.databinding.FragmentWorkoutBinding


private lateinit var workoutFragmentBinding: FragmentWorkoutBinding

class workoutFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        workoutFragmentBinding = FragmentWorkoutBinding.inflate(inflater, container, false)


        addFab()

        return workoutFragmentBinding.root
    }

    private fun addFab(){

        val fab = workoutFragmentBinding.addWords

        fab.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_workoutFragment_to_addWorkoutFragment)
        }
    }


}