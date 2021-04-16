/**
 * Represents the add workout page. Contains two edit text fields where
 * users can enter their information. The button then allows them to store
 * this data within the room database.
 * @author Callum Robert Binner
 * @version 2
 */


package uk.ac.aber.dcs.cs39440.auswpandroidapp.ui.tracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import uk.ac.aber.dcs.cs39440.auswpandroidapp.R
import uk.ac.aber.dcs.cs39440.auswpandroidapp.databinding.FragmentAddWorkoutBinding
import uk.ac.aber.dcs.cs39440.auswpandroidapp.model.tracker.AddWorkoutViewModel
import uk.ac.aber.dcs.cs39440.auswpandroidapp.model.tracker.Workout

private const val ACT_Key = "Activity Name"
private const val DATE_Key = "Date"
private const val DETS_KEY = "Activity Details"

class addWorkoutFragment : Fragment(), View.OnClickListener {


    private lateinit var addWorkoutFragmentBinding: FragmentAddWorkoutBinding
    private val addWorkoutViewModel: AddWorkoutViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val parent = requireActivity() as ToggleState
        parent.setNavigationDrawer(false)
        backbuttonpress()
        addWorkoutFragmentBinding = FragmentAddWorkoutBinding.inflate(inflater, container, false)


        val botNav: BottomNavigationView = requireActivity().findViewById(R.id.bottom_nav_view)
        botNav.isVisible = false

        addWorkoutFragmentBinding.add.setOnClickListener(this)



        return addWorkoutFragmentBinding.root
    }

    private fun backbuttonpress() {
        val callback: OnBackPressedCallback =
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        findNavController().navigateUp()
                    }
                }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        val parent = requireActivity() as ToggleState
        parent.setNavigationDrawer(false)
    }

    private fun restoreInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            val activityName = savedInstanceState.getString(ACT_Key, "")
            if (activityName.isNotEmpty()) addWorkoutFragmentBinding.nameEntry.setText(activityName)
            val activityDate = savedInstanceState.getString(DATE_Key, "")
            if (activityDate.isNotEmpty()) addWorkoutFragmentBinding.dateEntry.setText(activityDate)
            val activityStat = savedInstanceState.getString(DETS_KEY, "")
            if (activityStat.isNotEmpty()) addWorkoutFragmentBinding.userStats.setText(activityStat)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.add -> {
                insertWorkout()
            }
            else -> {

            }
        }
    }

    private fun insertWorkout() {
        if (   addWorkoutFragmentBinding.nameEntry.text.toString().isNotEmpty()
            &&  addWorkoutFragmentBinding.dateEntry.text.toString().isNotEmpty()
            &&addWorkoutFragmentBinding.userStats.text.toString().isNotEmpty()){

        val workout = Workout(
                0,
                addWorkoutFragmentBinding.nameEntry.text.toString(),
                addWorkoutFragmentBinding.dateEntry.text.toString(),
                addWorkoutFragmentBinding.userStats.text.toString()
        )
        addWorkoutViewModel.insertWorkout(workout)
            findNavController().navigateUp()
        }else{
            Toast.makeText(context, "You must enter data in all fields", Toast.LENGTH_SHORT).show()
        }


    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (addWorkoutFragmentBinding.nameEntry.text.isNotEmpty())
            outState.putString(ACT_Key, addWorkoutFragmentBinding.nameEntry.text.toString())
        if (addWorkoutFragmentBinding.dateEntry.text.isNotEmpty())
            outState.putString(DATE_Key, addWorkoutFragmentBinding.dateEntry.text.toString())
        if (addWorkoutFragmentBinding.userStats.text.isNotEmpty())
            outState.putString(DETS_KEY, addWorkoutFragmentBinding.userStats.text.toString())



        super.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        val botNav: BottomNavigationView = requireActivity().findViewById(R.id.bottom_nav_view)
        botNav.isVisible = true



        super.onDestroyView()
    }

}
