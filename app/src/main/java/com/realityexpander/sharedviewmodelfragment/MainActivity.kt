package com.realityexpander.sharedviewmodelfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.realityexpander.sharedviewmodelfragment.databinding.ActivityMainBinding
import com.realityexpander.sharedviewmodelfragment.ui.main.FirstFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // this is the old way (doesn't work with FragmentContainerView, works with simple <fragment>)
//        navController = findNavController(R.id.navHostFragment)

        // kind of messy, but works
//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
//        navController = navHostFragment.navController

        // Google recommended way using viewBinding
        navController = binding.navHostFragment.getFragment<NavHostFragment>().navController

        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}