package uk.ac.aber.dcs.cs39440.auswpandroidapp.ui.committee

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.ac.aber.dcs.cs39440.auswpandroidapp.R
import uk.ac.aber.dcs.cs39440.auswpandroidapp.databinding.FragmentCommitteeBinding


class committeeFragment : Fragment() {

private lateinit var committeeBinding: FragmentCommitteeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       committeeBinding = FragmentCommitteeBinding.inflate(inflater,container,false)
        return committeeBinding.root
    }


    }
