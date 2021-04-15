/**
 * This is the main activity for the app.
 * It activates the Toolbar, NavigationDrawer, BottomNavigationView
 * and allows for the NavigationDrawer elements to be clickable.
 * @author Callum Robert Binner
 * @version 1
 */


package uk.ac.aber.dcs.cs39440.auswpandroidapp.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import uk.ac.aber.dcs.cs39440.auswpandroidapp.ui.events.CalendarFragment

import uk.ac.aber.dcs.cs39440.auswpandroidapp.R
import uk.ac.aber.dcs.cs39440.auswpandroidapp.databinding.ActivityMainBinding
import uk.ac.aber.dcs.cs39440.auswpandroidapp.ui.events.EventsFragment
import uk.ac.aber.dcs.cs39440.auswpandroidapp.ui.login.LoginFragment
import uk.ac.aber.dcs.cs39440.auswpandroidapp.ui.tracker.ToggleState
import java.lang.Exception


class MainActivity : AppCompatActivity(), ToggleState {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var navigationView: NavigationView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




checkUser()

        navMenuItems()

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        val drawer = binding.drawerLayout
        toggle = ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.nav_open_drawer,
                R.string.nav_close_drawer
        )

        drawer.addDrawerListener(toggle)
        toggle.syncState()

        drawer.addDrawerListener(toggle)
        toggle.syncState()

        toggle.toolbarNavigationClickListener = View.OnClickListener {
            onBackPressed()
        }

        val navView = binding.bottomNavView
      val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_Committee, R.id.navigation_Events, R.id.navigation_Tracker))
      setupActionBarWithNavController(navController,appBarConfiguration)
        navView.setupWithNavController(navController)



    }

    override fun onStart() {
        super.onStart()
        checkUser()
    }

    override fun onPause() {
        super.onPause()
        checkUser()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        checkUser()
    }


    override fun setNavigationDrawer(isEnabled: Boolean){
        if (isEnabled){
            toggle.isDrawerIndicatorEnabled = true
        } else {
            toggle.isDrawerIndicatorEnabled = false

            toggle.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }
        toggle.syncState()

    }

    private fun navMenuItems(){
        navigationView = binding.navView
        navigationView.setNavigationItemSelectedListener{item ->
            when(item.itemId){
                R.id.nav_help ->{
                    val myIntent = Intent(Intent.ACTION_SEND)
                    myIntent.data = Uri.parse("mailto:")
                    myIntent.type = "text/html"
                    myIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("callumbinner22@gmail.com"))
                    myIntent.putExtra(Intent.EXTRA_SUBJECT,"Help")
                    try {
                        startActivity(Intent.createChooser(myIntent,"Choose Email Client"))
                    }
                    catch (e: Exception){
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    }
                }
                R.id.nav_feedback ->{
                    val myIntent = Intent(Intent.ACTION_SEND)
                    myIntent.data = Uri.parse("mailto:")
                    myIntent.type = "text/html"
                    myIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("callumbinner22@gmail.com"))
                    myIntent.putExtra(Intent.EXTRA_SUBJECT,"Feedback")


                    try {
                        startActivity(Intent.createChooser(myIntent,"Choose Email Client"))
                    }
                    catch (e: Exception){
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    }





                }
                R.id.nav_login ->{
                    findNavController(R.id.nav_host_fragment).navigate(R.id.loginFragment)

                    val drawer = binding.drawerLayout
                    drawer.closeDrawer(GravityCompat.START)
                }
                R.id.nav_logOut ->{
                    FirebaseAuth.getInstance().signOut()
                    Toast.makeText(this, "User signed out", Toast.LENGTH_SHORT).show()
                    findNavController(R.id.nav_host_fragment).navigate(R.id.navigation_home)

                }
                R.id.admin ->{
                    findNavController(R.id.nav_host_fragment).navigate(R.id.adminFragment)
                    val drawer = binding.drawerLayout
                    drawer.closeDrawer(GravityCompat.START)
                }
            }
            false
        }
    }



    fun checkUser() {

        navigationView = binding.navView


        var logOut = navigationView.menu.findItem(R.id.nav_logOut)
        var logIn = navigationView.menu.findItem(R.id.nav_login)
        var admin = navigationView.menu.findItem(R.id.admin)


        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
           // Toast.makeText(this, "User signed in", Toast.LENGTH_SHORT).show()
            logOut.isVisible = true
            logIn.isVisible = false
            admin.isVisible = true
        } else {
          //  Toast.makeText(this, "User not signed in", Toast.LENGTH_SHORT).show()
            logIn.isVisible = true
            logOut.isVisible = false
            admin.isVisible = false


        }
    }

}



