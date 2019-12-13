package com.example.hw3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration

import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var navigation_Control: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);

        navigation_Control = findNavController(R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(
            this, navigation_Control, drawer_layout
        )
        NavigationUI.setupWithNavController(nav_view, navigation_Control);
        nav_view.setNavigationItemSelectedListener(this);
    }
/*
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

 */
    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        val id = menuItem.itemId
        menuItem.isChecked = true
        drawer_layout.closeDrawers()
        when (id) {
            R.id.RANDOM_NUMBER -> navigation_Control.navigate(R.id.randomNumber)
            R.id.DICE_ROLL -> navigation_Control.navigate(R.id.diceRoller)
            R.id.TIP_CALCULATOR -> navigation_Control.navigate(R.id.tipCalculator)
        }
        return true
    }
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navigation_Control,
            drawer_layout)
    }
}