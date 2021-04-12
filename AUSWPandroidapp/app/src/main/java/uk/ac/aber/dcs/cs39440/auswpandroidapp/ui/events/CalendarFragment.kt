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
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import uk.ac.aber.dcs.cs39440.auswpandroidapp.databinding.FragmentCalendarBinding
import uk.ac.aber.dcs.cs39440.auswpandroidapp.model.SharedViewModel
import uk.ac.aber.dcs.cs39440.auswpandroidapp.ui.events.EventViewHolder
import uk.ac.aber.dcs.cs39440.auswpandroidapp.ui.events.EventsFragment


private lateinit var calendarBinding: FragmentCalendarBinding


class CalendarFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        calendarBinding = FragmentCalendarBinding.inflate(inflater,container, false)


        val word1 = calendarBinding.titleText
        val word2 = calendarBinding.dateText
        val word3 = calendarBinding.locationText
        val word4 = calendarBinding.timeText
        val button = calendarBinding.button

        val model = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        model.title.observe(viewLifecycleOwner,Observer{
            word1.text = it

        })
        model.location.observe(viewLifecycleOwner, Observer {
            word3.text = it
        })
        model.date.observe(viewLifecycleOwner, Observer {
            word2.text = it
        })
        model.time.observe(viewLifecycleOwner,Observer{
            word4.text = it
        })


        button.setOnClickListener {
            val insertCalendarIntent = Intent(Intent.ACTION_INSERT)
                    .setData(CalendarContract.Events.CONTENT_URI)
                    .putExtra(CalendarContract.Events.TITLE, word1.text.toString())
                    .putExtra(CalendarContract.Events.EVENT_LOCATION, word3.text.toString())

              startActivity(insertCalendarIntent)
        }



        return calendarBinding.root
    }


}