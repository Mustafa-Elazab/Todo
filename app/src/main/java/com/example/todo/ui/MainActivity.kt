package com.example.todo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.todo.R

class MainActivity : AppCompatActivity() {

    lateinit var nav_controller:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
         nav_controller=findNavController(R.id.fragmentContainerView)

        setupActionBarWithNavController(nav_controller)
    }

    override fun onNavigateUp(): Boolean {
        return nav_controller.navigateUp()|| super.onNavigateUp()

    }
}