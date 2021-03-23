package uk.ac.aber.dcs.cs39440.auswpandroidapp.ui.tracker

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import uk.ac.aber.dcs.cs39440.auswpandroidapp.R
import uk.ac.aber.dcs.cs39440.auswpandroidapp.databinding.FragmentSetBinding
import uk.ac.aber.dcs.cs39440.auswpandroidapp.ui.events.Event

private const val TAG = "My Activity"
class setFragment : Fragment() {


    private lateinit var setFragmentBinding: FragmentSetBinding
    private lateinit var databaseSetRef: DatabaseReference
    private lateinit var databaseTipRef: DatabaseReference

    lateinit var tip1: TextView
    lateinit var tip2: TextView
    lateinit var tip3: TextView
    lateinit var warmup: TextView
    lateinit var mainset: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        backbuttonpress()

        setFragmentBinding = FragmentSetBinding.inflate(inflater,container, false)

        val botNav: BottomNavigationView = requireActivity().findViewById(R.id.bottom_nav_view)
        botNav.isVisible = false



        databaseSetRef = Firebase.database.reference.child("Swimset")
        databaseTipRef = Firebase.database.reference.child("PoloTips")

        tip1 = setFragmentBinding.tip1
        tip2 = setFragmentBinding.tip2
        tip3 = setFragmentBinding.tip3
        warmup = setFragmentBinding.warmup
        mainset = setFragmentBinding.mainSet

        addSets()
        addTips()


        return setFragmentBinding.root
    }

    private fun backbuttonpress(){
        val callback: OnBackPressedCallback =
                object: OnBackPressedCallback(true){
                    override fun handleOnBackPressed() {
                        findNavController().navigateUp()
                    }
                }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        val parent = requireActivity() as ToggleState
        parent.setNavigationDrawer(false)
    }

    override fun onDestroyView() {
        val botNav: BottomNavigationView = requireActivity().findViewById(R.id.bottom_nav_view)
        botNav.isVisible = true

        val parent = requireActivity() as ToggleState
        parent.setNavigationDrawer(true)

        super.onDestroyView()
    }

    private fun addTips(){
        val postListener = object: ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                val post = datasnapshot.getValue(tips::class.java)
                tip1.text = post?.Tip1
                tip2.text = post?.Tip2
                tip3.text= post?.Tip3

                Log.d(TAG, "Value is: $post")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        }
        databaseTipRef.addValueEventListener(postListener)
    }

    private fun addSets(){
        val postListener = object: ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                val post = datasnapshot.getValue(sets::class.java)
                warmup.text = post?.Warmup
                mainset.text = post?.MainSet

                Log.d(TAG, "Value is: $post")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        }
        databaseSetRef.addValueEventListener(postListener)

    }
}
