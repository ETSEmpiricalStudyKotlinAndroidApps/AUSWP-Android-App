package uk.ac.aber.dcs.cs39440.auswpandroidapp.ui.login

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import uk.ac.aber.dcs.cs39440.auswpandroidapp.R
import uk.ac.aber.dcs.cs39440.auswpandroidapp.databinding.FragmentLoginBinding
import uk.ac.aber.dcs.cs39440.auswpandroidapp.ui.tracker.ToggleState

class LoginFragment : Fragment() {

    private lateinit var LoginFragmentBinding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var login: Button
    private lateinit var email: EditText
    private lateinit var password: EditText


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        LoginFragmentBinding = FragmentLoginBinding.inflate(inflater, container, false)

        auth = FirebaseAuth.getInstance()
        val firebaseUser: FirebaseUser? = auth.currentUser
        val register = LoginFragmentBinding.SignUp

        email = LoginFragmentBinding.username
        password = LoginFragmentBinding.password
        login = LoginFragmentBinding.Login


        backButtonPress()

        register.isClickable
        login.isClickable


        register.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_loginFragment_to_registerFragment)
        }


        signin()

        return LoginFragmentBinding.root
    }

    private fun signin() {
        login.setOnClickListener {
            signinUser(email.text.toString().trim(), password.text.toString().trim())
            hideKeyboard()

        }
    }

    private fun signinUser(email: String, password: String) {


        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { signIn ->
                    if (signIn.isSuccessful) {
                        // Toast.makeText(context,"Welcome $email", Toast.LENGTH_SHORT).show()
                        Log.d(TAG, "Sign in successful")
                        val navController = findNavController()
                        navController.navigate(R.id.navigation_home)

                    } else {
                        Toast.makeText(context, "Something went wrong! Please try again", Toast.LENGTH_SHORT)
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

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }
}

