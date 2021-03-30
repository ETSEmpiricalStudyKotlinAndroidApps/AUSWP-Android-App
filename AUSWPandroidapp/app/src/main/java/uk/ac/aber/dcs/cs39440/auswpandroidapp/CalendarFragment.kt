package uk.ac.aber.dcs.cs39440.auswpandroidapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.ac.aber.dcs.cs39440.auswpandroidapp.databinding.FragmentCalendarBinding
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






        return calendarBinding.root
    }


}