/**
 * Represents the Events page. Contains a firebase UI
 * recyclerView to display the data passed in from the
 * firebase database.
 * @author Callum Robert Binner
 * @version 2
 */


package uk.ac.aber.dcs.cs39440.auswpandroidapp.ui.events


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import uk.ac.aber.dcs.cs39440.auswpandroidapp.R
import uk.ac.aber.dcs.cs39440.auswpandroidapp.databinding.FragmentEventsBinding
import uk.ac.aber.dcs.cs39440.auswpandroidapp.model.SharedViewModel
import uk.ac.aber.dcs.cs39440.auswpandroidapp.ui.MainActivity

class EventsFragment : Fragment() {


    private lateinit var eventAdapter: FirebaseRecyclerAdapter<Event, EventViewHolder>
    private var dataQuery = FirebaseDatabase.getInstance().reference.child("Events").orderByChild("date")
    private lateinit var eventsFragmentBinding: FragmentEventsBinding
    private lateinit var Smodel: SharedViewModel


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        eventsFragmentBinding = FragmentEventsBinding.inflate(inflater, container, false)
        Smodel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        (activity as MainActivity?)!!.navBar()


        eventAdapter = getAdapter()
        val recyclerView = eventsFragmentBinding.recyclerView
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = eventAdapter

        }

        return eventsFragmentBinding.root
    }

    override fun onStart() {
        super.onStart()
        eventAdapter?.startListening()
    }

    override fun onDestroy() {
        super.onDestroy()
        eventAdapter?.stopListening()
    }

    private fun getAdapter(): FirebaseRecyclerAdapter<Event, EventViewHolder> {
        var clickListener: View.OnClickListener? = null
        val options = FirebaseRecyclerOptions.Builder<Event>()
                .setLifecycleOwner(this)
                .setQuery(dataQuery, Event::class.java)
                .build()

        return object : FirebaseRecyclerAdapter<Event, EventViewHolder>(options) {

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {

                return EventViewHolder(
                        layoutInflater.inflate(R.layout.event_item, parent, false)
                )
            }

            override fun onBindViewHolder(holder: EventViewHolder, position: Int, model: Event) {
                holder.itemView.setOnClickListener {
                    Log.i("TAG", "Logged ${holder.title.text} + ${holder.time.text} + ${holder.date.text} + ${holder.location.text}")


                    val title = holder.title.text
                    val date = holder.date.text
                    val location = holder.location.text
                    val time = holder.time.text

                    Smodel.sendTitle(title.toString())
                    Smodel.sendDate(date.toString())
                    Smodel.sendLocation(location.toString())
                    Smodel.sendTime(time.toString())


                    val navController = findNavController()
                    navController.navigate(R.id.action_navigation_Events_to_calendarFragment)
                }

                holder.bind(model)
            }
        }


    }


}







