package uk.ac.aber.dcs.cs39440.auswpandroidapp.ui.events

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import uk.ac.aber.dcs.cs39440.auswpandroidapp.R
import uk.ac.aber.dcs.cs39440.auswpandroidapp.databinding.FragmentEventsBinding

class EventsFragment : Fragment() {


    private lateinit var eventAdapter: FirebaseRecyclerAdapter<Event, EventViewHolder>
    private var dataQuery = FirebaseDatabase.getInstance().reference.child("Events")
    private lateinit var eventsFragmentBinding: FragmentEventsBinding


override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        eventsFragmentBinding = FragmentEventsBinding.inflate(inflater, container, false)

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

    private fun getAdapter(): FirebaseRecyclerAdapter<Event, EventViewHolder>{
        val options = FirebaseRecyclerOptions.Builder<Event>()
            .setLifecycleOwner(this)
            .setQuery(dataQuery, Event::class.java)
            .build()

        return object: FirebaseRecyclerAdapter<Event, EventViewHolder>(options){
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
                return EventViewHolder(
                    layoutInflater.inflate(R.layout.event_item, parent, false)
                )
            }

            override fun onBindViewHolder(holder: EventViewHolder, position: Int, model: Event) {
                holder.bind(model)
            }
        }
    }
}







