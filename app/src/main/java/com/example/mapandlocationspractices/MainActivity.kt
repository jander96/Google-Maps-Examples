package com.example.mapandlocationspractices

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions

// Se implementa la interfaz OnMapReadyCallBack
class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mapFragment: SupportMapFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Se obtiene la referencia del mapFragment
        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        // Se obtiene el manejador del fragment
        mapFragment.getMapAsync(this)
    }

    // Se obtiene el manejador del googe map
    override fun onMapReady(googleMap: GoogleMap) {

    }
}