/**
 * Represents the committee page. Shows each committee
 * member with their picture and contact information
 * contained within a RecyclerView.
 * Each grid can be clicked to launch an intent opening
 * an email client to email each committee member.
 * @author Callum Robert Binner
 * @Version 2
 *
 */


package uk.ac.aber.dcs.cs39440.auswpandroidapp.ui.committee

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.GridLayoutManager
import uk.ac.aber.dcs.cs39440.auswpandroidapp.R
import uk.ac.aber.dcs.cs39440.auswpandroidapp.databinding.FragmentCommitteeBinding
import uk.ac.aber.dcs.cs39440.auswpandroidapp.model.committee.Committee
import uk.ac.aber.dcs.cs39440.auswpandroidapp.model.committee.CommitteeViewModel
import java.lang.Exception

private const val COLUMN_COUNT = 2

class committeeFragment : Fragment() {

private lateinit var committeeBinding: FragmentCommitteeBinding
private lateinit var committeeRecyclerAdapter: CommitteeAdapter
private var oldCommitteeList: LiveData<List<Committee>>? = null
    private  val committeeViewModel: CommitteeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       committeeBinding = FragmentCommitteeBinding.inflate(inflater,container,false)

        addCommitteeRecyclerView()

        return committeeBinding.root
    }

    private fun addCommitteeRecyclerView(){
        val listCommittee = committeeBinding.committeeList
        listCommittee.setHasFixedSize(true)

        val gridLayoutManager = GridLayoutManager(context, COLUMN_COUNT)
        listCommittee.layoutManager = gridLayoutManager

        committeeRecyclerAdapter = CommitteeAdapter(context)
        listCommittee.adapter = committeeRecyclerAdapter

        val committeeList = searchForCommittee()

        if (oldCommitteeList != committeeList){
            oldCommitteeList?.removeObservers(viewLifecycleOwner)
            oldCommitteeList = committeeList
        }

        if (!committeeList.hasObservers()){
            committeeList.observe(viewLifecycleOwner) {Committee ->
                committeeRecyclerAdapter.changeDataSet(Committee.toMutableList())
            }
        }

        committeeRecyclerAdapter.clickListener = View.OnClickListener { v ->
            val emailView: TextView = v.findViewById(R.id.committeeEmail)
            val myIntent = Intent(Intent.ACTION_SEND)
            myIntent.data = Uri.parse("mailto:")
            myIntent.type = "text/html"
            myIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("${emailView.text}"))
            try{
                startActivity(Intent.createChooser(myIntent,"Choose Email Client"))
            }
            catch (e: Exception){
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

private fun searchForCommittee():LiveData<List<Committee>>{
    return committeeViewModel.getCommittee()
}

    }
