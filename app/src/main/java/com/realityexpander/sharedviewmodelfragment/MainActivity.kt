package com.realityexpander.sharedviewmodelfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.realityexpander.sharedviewmodelfragment.databinding.ActivityMainBinding
import com.realityexpander.sharedviewmodelfragment.ui.main.SharedViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Testing MainViewModelFactory
        // To be able to Pass in `myVariable` to a viewModel, we need to create a custom factory
        val mainViewModelFactory = MainViewModelFactory("the Best country")
        val viewModel = ViewModelProvider(this, mainViewModelFactory)
            .get(MainViewModel::class.java)


        // this is the old way (doesn't work with <FragmentContainerView>, works with simple <fragment>)
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


class MainViewModel(myVariable: String): ViewModel() {

    val name = myVariable

    init {
        Log.d("MainViewModel", "MainViewModel created, name: $name")
    }
}


class MainViewModelFactory(private var myVariable: String) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(myVariable) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}