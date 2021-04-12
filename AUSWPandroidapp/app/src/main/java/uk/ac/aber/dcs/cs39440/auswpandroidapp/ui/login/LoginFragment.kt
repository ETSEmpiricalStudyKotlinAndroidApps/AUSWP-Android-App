package uk.ac.aber.dcs.cs39440.auswpandroidapp.ui.login

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.navigation.fragment.findNavController

import uk.ac.aber.dcs.cs39440.auswpandroidapp.R
import uk.ac.aber.dcs.cs39440.auswpandroidapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

private lateinit var LoginFragmentBinding: FragmentLoginBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        LoginFragmentBinding = FragmentLoginBinding.inflate(inflater, container, false)


        val register = LoginFragmentBinding.SignUp

        register.isClickable

        register.setOnClickListener {
         val navController = findNavController()
            navController.navigate(R.id.action_loginFragment_to_registerFragment)
        }

        return LoginFragmentBinding.root
    }


}