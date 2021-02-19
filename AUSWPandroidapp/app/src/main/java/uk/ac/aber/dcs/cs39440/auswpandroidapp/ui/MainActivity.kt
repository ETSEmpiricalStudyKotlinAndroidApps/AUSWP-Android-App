package uk.ac.aber.dcs.cs39440.auswpandroidapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import uk.ac.aber.dcs.cs39440.auswpandroidapp.R
import uk.ac.aber.dcs.cs39440.auswpandroidapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        val drawer = binding.drawerLayout
        val toggle = ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.nav_open_drawer,
                R.string.nav_close_drawer
        )

        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navView = binding.bottomNavView
      val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_Committee, R.id.navigation_Events, R.id.navigation_Tracker))
      setupActionBarWithNavController(navController,appBarConfiguration)
        navView.setupWithNavController(navController)

    }
    
    }

