package com.example.hw3.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox

import androidx.navigation.fragment.findNavController

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng

import com.google.android.gms.maps.model.MarkerOptions

import com.example.hw3.R
import kotlinx.android.synthetic.main.random_number.view.*

class RandomNumber : Fragment() {



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

        var contentLib= "Csula Library"



        mMap.addMarker(MarkerOptions().position(ecst).title("ECST"))
        mMap.addMarker(MarkerOptions().position(library).title("Library"))
        mMap.addMarker(MarkerOptions().position(student_union).title("University Student Union"))

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(csula,16.0f))
        mMap.animateCamera(point);
    }


    /*
    val generatedList = hashSetOf<Int>()
    lateinit var checked: CheckBox
    private lateinit var f_View: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        f_View = inflater.inflate(R.layout.random_number, container, false)
        f_View.GetNumber.setOnClickListener {

                val startingNumber = f_View.min_number.text.toString().trim().toInt()
                val endingNumber = f_View.max_number.text.toString().trim().toInt()

                val randomNumber =getRandomNumber(startingNumber, endingNumber)
                f_View.GeneratedNumber.setText(randomNumber)

        }
    /*
        f_View.NavigateToDiceRoll.setOnClickListener {
            findNavController().navigate(R.id
                .action_randomNumberFragment_to_diceRollerFragment)
        }

     */
        return f_View
    }



    private fun getRandomNumber(min_number: Int, max_number: Int):String {
        var low: Int
        var temp: Int=0
        low = min_number
        var max: Int
        max = max_number
        var randomNumber = (low..max).random()
        val lengthn = (max - low) + 1

        if(f_View.check_box.isChecked) {
            while (generatedList.contains(randomNumber)) {
                randomNumber = (low..max).random()
                temp++
                if (temp > lengthn) {
                    break
                }
            }
            if (temp <= lengthn) {
                generatedList.add(randomNumber)

            }
            else {
                return "List Empty"
            }
        }
        return randomNumber.toString()

    }


     */

}
