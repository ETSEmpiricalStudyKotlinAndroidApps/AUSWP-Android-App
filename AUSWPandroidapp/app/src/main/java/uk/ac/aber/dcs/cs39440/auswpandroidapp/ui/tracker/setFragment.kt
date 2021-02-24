package uk.ac.aber.dcs.cs39440.auswpandroidapp.ui.tracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import uk.ac.aber.dcs.cs39440.auswpandroidapp.R
import uk.ac.aber.dcs.cs39440.auswpandroidapp.databinding.FragmentSetBinding

private lateinit var setFragmentBinding: FragmentSetBinding

class setFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        backbuttonpress()

        setFragmentBinding = FragmentSetBinding.inflate(inflater,container, false)

        val botNav: BottomNavigationView = requireActivity().findViewById(R.id.bottom_nav_view)
        botNav.isVisible = false

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

    }
