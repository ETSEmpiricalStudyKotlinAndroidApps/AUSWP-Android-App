package uk.ac.aber.dcs.cs39440.auswpandroidapp.ui.login

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import uk.ac.aber.dcs.cs39440.auswpandroidapp.R
import uk.ac.aber.dcs.cs39440.auswpandroidapp.databinding.FragmentRegisterBinding
import uk.ac.aber.dcs.cs39440.auswpandroidapp.ui.tracker.ToggleState


class RegisterFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var registerFragmentBinding: FragmentRegisterBinding
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var name: EditText
    private lateinit var register: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        registerFragmentBinding = FragmentRegisterBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()
        val firebaseUser: FirebaseUser? = auth.currentUser

        email = registerFragmentBinding.editTextTextEmailAddress
        password = registerFragmentBinding.editTextTextPassword
        name = registerFragmentBinding.editTextTextPersonName
        register = registerFragmentBinding.button2

        Register()
        backButtonPress()

        return registerFragmentBinding.root
    }


    private fun Register() {
        register.setOnClickListener {
            createAccount(email.text.toString().trim(), password.text.toString().trim())
        }
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Account Created Successfully")
                    Toast.makeText(context, "Create account successfully", Toast.LENGTH_SHORT)
                        .show()
                    val navController = findNavController()
                    navController.navigate(R.id.loginFragment)
                } else {
                    Log.w(TAG, "Authorisation Failed")
                    Toast.makeText(context, "Authorisation failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun backButtonPress() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigateUp()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        val parent = requireActivity() as ToggleState
        parent.setNavigationDrawer(false)
    }
}


