package uk.ac.aber.dcs.cs39440.auswpandroidapp.ui.tracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uk.ac.aber.dcs.cs39440.auswpandroidapp.R
import uk.ac.aber.dcs.cs39440.auswpandroidapp.databinding.FragmentTrackerBinding



class TrackerFragment : Fragment() {


    private lateinit var trackerFragmentBinding: FragmentTrackerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       trackerFragmentBinding = FragmentTrackerBinding.inflate(inflater,container,false)

        addButtons()

        return trackerFragmentBinding.root
    }

    private fun addButtons(){

        val sets = trackerFragmentBinding.setsView
        val workout = trackerFragmentBinding.trackerView

        sets.setOnClickListener{
            val navController = findNavController()
            navController.navigate(R.id.action_navigation_Tracker_to_setFragment)
        }

        workout.setOnClickListener {
            val navController = findNavController()
            navController.navigate((R.id.action_navigation_Tracker_to_workoutFragment))
        }
    }




}