package com.example.landmarks_in_toronto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.landmarks_in_toronto.databinding.ActivityMapBinding

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapBinding
    private lateinit var selectedLandmark: String
    private var latitude: Double=0.0
    private var longitude: Double=0.0
    private var isSatelliteViewEnabled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        selectedLandmark = intent.getStringExtra("selectedLandmark")?:""
        latitude=intent.getDoubleExtra("latitude",0.0)
        longitude=intent.getDoubleExtra("longitude",0.0)

        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        binding.zoomInButton.setOnClickListener {
            mMap.animateCamera(CameraUpdateFactory.zoomIn())
        }

        binding.zoomOutButton.setOnClickListener {
            mMap.animateCamera(CameraUpdateFactory.zoomOut())
        }

        binding.toggleMapViewButton.setOnClickListener {
            toggleMapView()
        }
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


        // Add a marker in Sydney and move the camera
        val landmark = LatLng(latitude, longitude)


        mMap.addMarker(MarkerOptions().position(landmark).title(selectedLandmark))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(landmark))

        val zoomLevel = 12f // Adjust this value as needed
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(landmark, zoomLevel))

    }

    private fun toggleMapView() {
        if (isSatelliteViewEnabled) {
            mMap.mapType = GoogleMap.MAP_TYPE_NORMAL // Switch to map view
            isSatelliteViewEnabled = false
        } else {
            mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE // Switch to satellite view
            isSatelliteViewEnabled = true
        }
    }

}
