package uk.ac.aber.dcs.cs39440.auswpandroidapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import uk.ac.aber.dcs.cs39440.auswpandroidapp.databinding.FragmentAdminBinding
import uk.ac.aber.dcs.cs39440.auswpandroidapp.ui.events.Event


class adminFragment : Fragment() {

    private lateinit var adminFragmentBinding: FragmentAdminBinding
    private lateinit var dbRef: DatabaseReference


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        adminFragmentBinding = FragmentAdminBinding.inflate(inflater, container, false)
      val addEventBut = adminFragmentBinding.addEvent
        addEventBut.isClickable

        addEventBut.setOnClickListener {
            addEvent()
            Log.d(TAG, "button pressed")
        }



        return adminFragmentBinding.root
    }



    private fun addEvent(){
        dbRef = Firebase.database.reference.child("Events")
        val title = adminFragmentBinding.eventTitle.text.toString().trim()
        val date = adminFragmentBinding.eventDate.text.toString().trim()
        val time = adminFragmentBinding.eventTime.text.toString().trim()
        val location = adminFragmentBinding.eventLocation.text.toString().trim()

        val event = Event(title,date,time,location)

        dbRef.push().setValue(event)



    }
}

