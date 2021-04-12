/**
 * This represents the home page. It contains a view
 * which has text views that dynamically update to display
 * the next event happening within the database.
 * @author Callum Robert Binner
 * @version 4
 */


package uk.ac.aber.dcs.cs39440.auswpandroidapp.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import uk.ac.aber.dcs.cs39440.auswpandroidapp.R
import uk.ac.aber.dcs.cs39440.auswpandroidapp.databinding.FragmentHomeBinding
import uk.ac.aber.dcs.cs39440.auswpandroidapp.ui.events.Event
import kotlin.String as String

private const val TAG = "My Activity"

class HomeFragment : Fragment() {

    private lateinit var homeFragmentBinding: FragmentHomeBinding
    private lateinit var database: DatabaseReference



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        homeFragmentBinding = FragmentHomeBinding.inflate(inflater,container,false)

        val texter = homeFragmentBinding.textView2
        val time = homeFragmentBinding.textView3
        val location = homeFragmentBinding.textView4
        val date = homeFragmentBinding.textView5

       database = Firebase.database.reference.child("Events")

    val myquery = database.limitToFirst(1)


        val postListener = object: ValueEventListener{
            override fun onDataChange(datasnapshot: DataSnapshot) {
                for (ds:DataSnapshot in datasnapshot.children){
                    val post = ds.getValue(Event::class.java)
                    texter.text = post?.Title
                    time.text = post?.Time
                    location.text = post?.Location
                    date.text = post?.Date
                    Log.d(TAG, "Value is: $post")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        }
        myquery.addValueEventListener(postListener)



        return homeFragmentBinding.root






    }


}