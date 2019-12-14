/*
 * Copyright (c) 2019 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.where2go

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.location.Location
import android.os.Bundle
//import android.support.v7.widget.DrawerLayout
//import android.support.v7.app.ActionBarDrawerToggle

//import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.material.navigation.NavigationView
import com.mapbox.android.core.location.LocationEngine
import com.mapbox.android.core.location.LocationEngineListener
import com.mapbox.android.core.location.LocationEnginePriority
import com.mapbox.android.core.location.LocationEngineProvider
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.api.directions.v5.DirectionsCriteria
import com.mapbox.api.directions.v5.models.DirectionsResponse
import com.mapbox.api.directions.v5.models.DirectionsRoute
import com.mapbox.geojson.Point
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.location.LocationComponent
import com.mapbox.mapboxsdk.location.modes.CameraMode
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncher
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncherOptions

import com.mapbox.services.android.navigation.ui.v5.route.NavigationMapRoute
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.mapbox.turf.TurfMeasurement
import com.mapbox.turf.TurfConstants.UNIT_KILOMETERS

//import androidx.appcompat.widget.Toolbar
//import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
//import android.support.v7.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout


class MainActivity : AppCompatActivity(),
    PermissionsListener, LocationEngineListener, OnMapReadyCallback, MapboxMap.OnMapClickListener, NavigationView.OnNavigationItemSelectedListener  {

  //1
  val REQUEST_CHECK_SETTINGS = 1

   var totalLineDistance = 0.0
  var settingsClient: SettingsClient? = null

    var DISTANCE_UNIT:String = UNIT_KILOMETERS

  //2
  lateinit var map: MapboxMap
  lateinit var lineLengthTextView: TextView
  lateinit var permissionManager: PermissionsManager
  var originLocation: Location? = null

  var locationEngine: LocationEngine? = null
  var locationComponent: LocationComponent? = null

  var navigationMapRoute: NavigationMapRoute? = null
  var currentRoute: DirectionsRoute? = null

  //3

  lateinit var toolbar: Toolbar
  lateinit var drawerLayout: DrawerLayout
  lateinit var navView: NavigationView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Mapbox.getInstance(this, "pk.eyJ1IjoiY2hnMDA3IiwiYSI6ImNrM3czOGs5azB1bzAzbG8xZWRiODFlMHEifQ.6LO_DbU5PQzBCI-U7XTuyQ")

    setContentView(R.layout.activity_main)

    toolbar = findViewById(R.id.toolbar)
    setSupportActionBar(toolbar)

    drawerLayout = findViewById(R.id.drawer_layout)
    navView = findViewById(R.id.nav_view)

    val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
    )
    drawerLayout.addDrawerListener(toggle)
    toggle.syncState()
    navView.setNavigationItemSelectedListener(this)

    mapbox.onCreate(savedInstanceState)
    mapbox.getMapAsync(this)
    settingsClient = LocationServices.getSettingsClient(this)
    btnNavigate.isEnabled = false

    btnNavigate.setOnClickListener {
      val navigationLauncherOptions = NavigationLauncherOptions.builder() //1
          .directionsRoute(currentRoute) //2
          .shouldSimulateRoute(true) //3
          .build()

      NavigationLauncher.startNavigation(this, navigationLauncherOptions) //4
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    if (requestCode == REQUEST_CHECK_SETTINGS) {
      if (resultCode == Activity.RESULT_OK) {
        enableLocation()
      } else
        if (resultCode == Activity.RESULT_CANCELED) {
          finish()
        }
    }
  }

  @SuppressWarnings("MissingPermission")
  override fun onStart() {
    super.onStart()
    if (PermissionsManager.areLocationPermissionsGranted(this)) {
      locationEngine?.requestLocationUpdates()
      locationComponent?.onStart()
    }

    mapbox.onStart()
  }

  override fun onResume() {
    super.onResume()
    mapbox.onResume()
  }

  override fun onPause() {
    super.onPause()
    mapbox.onPause()
  }

  override fun onStop() {
    super.onStop()
    locationEngine?.removeLocationUpdates()
    locationComponent?.onStop()
    mapbox.onStop()
  }

  override fun onDestroy() {
    super.onDestroy()
    locationEngine?.deactivate()
    mapbox.onDestroy()
  }

  override fun onLowMemory() {
    super.onLowMemory()
    mapbox.onLowMemory()
  }

  override fun onSaveInstanceState(outState: Bundle?) {
    super.onSaveInstanceState(outState)
    if (outState != null) {
      mapbox.onSaveInstanceState(outState)
    }
  }

  override fun onExplanationNeeded(permissionsToExplain: MutableList<String>?) {
    Toast.makeText(this, "This app needs location permission to be able to show your location on the map", Toast.LENGTH_LONG).show()
  }

  override fun onPermissionResult(granted: Boolean) {
    if (granted) {
      enableLocation()
    } else {
      Toast.makeText(this, "User location was not granted", Toast.LENGTH_LONG).show()
      finish()
    }
  }

  override fun onLocationChanged(location: Location?) {
    location?.run {
      originLocation = this
      setCameraPosition(this)
    }
  }

  override fun onConnected() {
    locationEngine?.requestLocationUpdates()
  }

  override fun onMapReady(mapboxMap: MapboxMap?) {
    //1
    map = mapboxMap ?: return
    //2
    val locationRequestBuilder = LocationSettingsRequest.Builder().addLocationRequest(LocationRequest()
        .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
    )
    //3
    val locationRequest = locationRequestBuilder?.build()

    settingsClient?.checkLocationSettings(locationRequest)?.run {
      addOnSuccessListener {
        enableLocation()
      }

      lineLengthTextView = findViewById(R.id.line_length_textView);

// DISTANCE_UNITS must be equal to a String found in the TurfConstants class
      lineLengthTextView.setText(String.format(getString(R.string.line_distance_textview),
              DISTANCE_UNIT, (totalLineDistance).toString()));

      addOnFailureListener {
        val statusCode = (it as ApiException).statusCode

        if (statusCode == LocationSettingsStatusCodes.RESOLUTION_REQUIRED) {
          val resolvableException = it as? ResolvableApiException
          resolvableException?.startResolutionForResult(this@MainActivity, REQUEST_CHECK_SETTINGS)
        }
      }
    }
  }

  //1
  fun enableLocation() {
    if (PermissionsManager.areLocationPermissionsGranted(this)) {
      initializeLocationComponent()
      initializeLocationEngine()
      map.addOnMapClickListener(this)
    } else {
      permissionManager = PermissionsManager(this)
      permissionManager.requestLocationPermissions(this)
    }
  }

  //2
  @SuppressWarnings("MissingPermission")
  fun initializeLocationEngine() {
    locationEngine = LocationEngineProvider(this).obtainBestLocationEngineAvailable()
    locationEngine?.priority = LocationEnginePriority.HIGH_ACCURACY
    locationEngine?.activate()
    locationEngine?.addLocationEngineListener(this)

    val lastLocation = locationEngine?.lastLocation
    if (lastLocation != null) {
      originLocation = lastLocation
      setCameraPosition(lastLocation)
    } else {
      locationEngine?.addLocationEngineListener(this)
    }
  }

  @SuppressWarnings("MissingPermission")
  fun initializeLocationComponent() {
    locationComponent = map.locationComponent
    locationComponent?.activateLocationComponent(this)
    locationComponent?.isLocationComponentEnabled = true
    locationComponent?.cameraMode = CameraMode.TRACKING
  }

  //3
  fun setCameraPosition(location: Location) {
    map.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude,
        location.longitude), 15.0))
  }

  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    permissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
  }

  override fun onMapClick(point: LatLng) {
    if (!map.markers.isEmpty()) {
      map.clear()
    }

    map.addMarker(MarkerOptions().setTitle("I'm a marker :]").setSnippet("This is a snippet about this marker that will show up here").position(point))

    checkLocation()
    originLocation?.run {
      val startPoint = Point.fromLngLat(longitude, latitude)
      val endPoint = Point.fromLngLat(point.longitude, point.latitude)

      totalLineDistance = dist(startPoint,endPoint)
      lineLengthTextView?.text = (totalLineDistance*100).toString()

      getRoute(startPoint, endPoint)
    }
  }

   fun  dist( p1:Point,  p2:Point):Double {
     return Math.sqrt(Math.pow(p2.latitude() - p1.latitude(), 2.0)
            + Math.pow(p2.longitude() - p1.longitude(), 2.0));

  }

  @SuppressLint("MissingPermission")
  private fun checkLocation() {
    if (originLocation == null) {
      map.locationComponent.lastKnownLocation?.run {
        originLocation = this
      }
    }
  }

  private fun getRoute(originPoint: Point, endPoint: Point) {
    NavigationRoute.builder(this) //1
        .accessToken(Mapbox.getAccessToken()!!) //2
            .profile(DirectionsCriteria.PROFILE_WALKING)//2
        .origin(originPoint) //3
        .destination(endPoint) //4
        .build() //5
        .getRoute(object : Callback<DirectionsResponse> { //6
          override fun onFailure(call: Call<DirectionsResponse>, t: Throwable) {
            Log.d("MainActivity", t.localizedMessage)
          }

          override fun onResponse(call: Call<DirectionsResponse>,
                                  response: Response<DirectionsResponse>) {
            if (navigationMapRoute != null) {
              navigationMapRoute?.updateRouteVisibilityTo(false)
            } else {
              navigationMapRoute = NavigationMapRoute(null, mapbox, map)
            }

            currentRoute = response.body()?.routes()?.first()
            if (currentRoute != null) {
              navigationMapRoute?.addRoute(currentRoute)
            }

            btnNavigate.isEnabled = true
          }
        })
  }

  override fun onNavigationItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.nav_profile -> {

        Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show()
      }
      R.id.nav_messages -> {
        Toast.makeText(this, "Messages clicked", Toast.LENGTH_SHORT).show()
      }
      R.id.nav_friends -> {
        Toast.makeText(this, "Friends clicked", Toast.LENGTH_SHORT).show()
      }
      R.id.nav_update -> {
        Toast.makeText(this, "Update clicked", Toast.LENGTH_SHORT).show()
      }
      R.id.nav_logout -> {
        Toast.makeText(this, "Sign out clicked", Toast.LENGTH_SHORT).show()
      }
    }
    drawerLayout.closeDrawer(GravityCompat.START)
    return true
  }
}
