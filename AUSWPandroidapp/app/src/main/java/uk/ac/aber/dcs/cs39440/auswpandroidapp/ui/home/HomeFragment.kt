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

        val texter = homeFragmentBinding.testTextView
        
       database = Firebase.database.reference.child("Events")



        val postListener = object: ValueEventListener{
            override fun onDataChange(datasnapshot: DataSnapshot) {
                val post = datasnapshot.getValue()
                texter.text = post.toString()
                Log.d(TAG, "Value is: $post")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        }
        database.addValueEventListener(postListener)



        return homeFragmentBinding.root






    }





}