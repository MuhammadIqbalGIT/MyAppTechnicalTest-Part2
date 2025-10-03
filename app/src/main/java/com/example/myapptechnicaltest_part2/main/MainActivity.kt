package com.example.myapptechnicaltest_part2.main

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.myapptechnicaltest_part2.R
import com.example.myapptechnicaltest_part2.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val host: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainNavHostFragment) as NavHostFragment
        navController = host.navController

        val navGraph =
            navController.navInflater.inflate(R.navigation.nav_main)
        navController.graph = navGraph

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.chuckNorrisListFragment
            ) {
                binding.navView.visibility = View.VISIBLE
            } else {
                binding.navView.visibility = View.GONE
            }
        }
        binding.navView.setupWithNavController(navController)
    }
}