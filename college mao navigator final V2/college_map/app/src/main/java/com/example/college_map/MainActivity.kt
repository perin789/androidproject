package com.example.college_map


import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.ui.AppBarConfiguration as AppBarConfiguration1
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng

import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.CameraUpdate
class MainActivity : AppCompatActivity(),OnMapReadyCallback {
    private lateinit var mMap: GoogleMap

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Thought of the day", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration1(
            topLevelDestinationIds = setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send
            ), drawerLayout = drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val point = CameraUpdateFactory.newLatLng(LatLng(34.0668, -118.1684));

        // Add a marker in CSULA and move the camera
        val csula = LatLng(34.0668, -118.1684)
        val ecst = LatLng(34.066619, -118.166567)
        val library= LatLng(34.067698, -118.167442)
        val student_union= LatLng(34.068196, -118.168998)
        val kings_hall = LatLng(34.068165, -118.165829)
        val bookstore = LatLng(34.067798, -118.168552)
        val cdc = LatLng( 34.066343, -118.168310)
        val s_affairs = LatLng(34.066665, -118.169224)
        val fine_arts = LatLng(34.067370, -118.166524)
        val SH = LatLng(34.064208, -118.169623)
        val kertz_hall = LatLng(34.064979, -118.168613)
        val health_center = LatLng(34.065930, -118.168277)
        var contentLib= "Csula Library"

        mMap.addMarker(MarkerOptions().position(kings_hall).title("Kings Hall"))
        mMap.addMarker(MarkerOptions().position(bookstore).title("Golden Eagle Bookstore"))
        mMap.addMarker(MarkerOptions().position(cdc).title("Career Development Center"))
        mMap.addMarker(MarkerOptions().position(s_affairs).title("Student Affairs"))
        mMap.addMarker(MarkerOptions().position(fine_arts).title("Fine Arts"))
        mMap.addMarker(MarkerOptions().position(SH).title("Salazar Hall"))
        mMap.addMarker(MarkerOptions().position(kertz_hall).title("La Kertz Hall"))
        mMap.addMarker(MarkerOptions().position(health_center).title("Health Center"))


        mMap.addMarker(MarkerOptions().position(ecst).title("ECST"))
        mMap.addMarker(MarkerOptions().position(library).title("Library"))
        mMap.addMarker(MarkerOptions().position(student_union).title("University Student Union"))

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(csula,16.0f))
        mMap.animateCamera(point);
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
