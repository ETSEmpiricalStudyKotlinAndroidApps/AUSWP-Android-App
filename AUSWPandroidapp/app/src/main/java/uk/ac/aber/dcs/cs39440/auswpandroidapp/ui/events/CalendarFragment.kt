/**
 * Represents the page where the user is able to view the
 * event details and add it to their personal calendar.
 * Adding to calendar is done via a button press launching a
 * calendar intent and the users calendar client.
 * @author Callum Robert Binner
 * @version 1
 */


package uk.ac.aber.dcs.cs39440.auswpandroidapp.ui.events

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uk.ac.aber.dcs.cs39440.auswpandroidapp.databinding.FragmentCalendarBinding
import uk.ac.aber.dcs.cs39440.auswpandroidapp.model.SharedViewModel
import uk.ac.aber.dcs.cs39440.auswpandroidapp.ui.tracker.ToggleState
import java.util.*


private lateinit var calendarBinding: FragmentCalendarBinding


class CalendarFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        calendarBinding = FragmentCalendarBinding.inflate(inflater, container, false)

        backButtonPress()

        var delimiter = "/"
        var delimiter2 =":"
        var delimeter3 ="-"
        val word1 = calendarBinding.titleText
        val word2 = calendarBinding.dateText
        val word3 = calendarBinding.locationText
        val word4 = calendarBinding.timeText
        val button = calendarBinding.button

        val model = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        model.title.observe(viewLifecycleOwner, Observer {
            word1.text = it

        })
        model.location.observe(viewLifecycleOwner, Observer {
            word3.text = it
        })
        model.date.observe(viewLifecycleOwner, Observer {
            word2.text = it

        })
        model.time.observe(viewLifecycleOwner, Observer {
            word4.text = it

        })


        val date = model.date.value.toString().split(delimiter)
        val year = date[0].toInt()
        val month = date[1].toInt()-1
        val day = date [2].toInt()




        val time = model.time.value.toString().split(delimiter2,delimeter3)
        val startHour = time[0].toInt()
        val startMin = time[1].toInt()
        val endHour = time[2].toInt()
        val endMinute = time[3].toInt()


   Log.i("TAG", "Logged ${date[0]} + ${date[1]} + ${date[2]} ")
       Log.i("TAG", "Logged ${time[0]} + ${time[1]} + ${time[2]} + ${time[3]} ")











        val startMillis: Long = Calendar.getInstance().run {
            set(year, month, day, startHour, startMin)
            timeInMillis
        }

        val endMillis: Long = Calendar.getInstance().run {
            set(year, month, day, endHour, endMinute)
            timeInMillis
        }

        button.setOnClickListener {
            val insertCalendarIntent = Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.TITLE, word1.text.toString())
                .putExtra(CalendarContract.Events.EVENT_LOCATION, word3.text.toString())
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startMillis)
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endMillis)



            startActivity(insertCalendarIntent)
        }



        return calendarBinding.root
    }

    private fun backButtonPress() {
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
}