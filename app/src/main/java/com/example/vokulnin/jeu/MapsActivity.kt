package com.example.vokulnin.jeu

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*
import android.location.LocationManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat





class MapsActivity : AppCompatActivity(), OnMapReadyCallback , LocationListener{

    private lateinit var mMap: GoogleMap
    private var locationManager: LocationManager? = null
    private var locationProvider: String? = null
    private val MINIMUM_DISTANCECHANGE_FOR_UPDATE: Float = 10f // in Meters
    private val MINIMUM_TIME_BETWEEN_UPDATE: Long = 6000 // in Milliseconds
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderEnabled(p0: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderDisabled(p0: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override  fun onLocationChanged(location: Location) {
        // TODO Auto-generated method stub



    }


    override fun onMapReady(googleMap: GoogleMap) {

        try {
            locationManager?.requestLocationUpdates(
                    locationProvider,
                    MINIMUM_TIME_BETWEEN_UPDATE,
                    MINIMUM_DISTANCECHANGE_FOR_UPDATE,
                    this)
        } catch (e: SecurityException) {
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
        }

        val location = locationManager?.getLastKnownLocation(locationProvider)

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        println(location!!.latitude.toString() + "  " + location!!.longitude.toString())
        var test = LatLng(location!!.latitude, location!!.longitude)
        mMap = googleMap

        mMap.moveCamera(CameraUpdateFactory.newLatLng(test))
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))

        mMap = googleMap

        // Add a marker in Sydney and move the camera
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
   }
}
