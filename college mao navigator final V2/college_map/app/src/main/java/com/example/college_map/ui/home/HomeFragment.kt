package com.example.college_map.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.college_map.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(this, Observer {
            textView.text = it
        })
         lateinit var mMap: GoogleMap

         fun onMapReady(googleMap: GoogleMap) {
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

        return root
    }
}