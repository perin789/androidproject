package com.example.college_map

import androidx.appcompat.app.AppCompatActivity
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



class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
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
}
