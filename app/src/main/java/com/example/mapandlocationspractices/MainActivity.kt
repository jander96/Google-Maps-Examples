package com.example.mapandlocationspractices

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

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
        setInitialConfig()

    }

    private fun setInitialConfig() {
        /*Con el google map options podemos establecer algunas configuraciones iniciales*/
        // se establecen los limites de la camara
        val southweast = LatLng(20.19137110047066, -75.16585249254445)
        val northeast = LatLng(20.1955633650374, -75.12561274395493)
        //------------------------------------------
        val target = LatLng(20.193317989644015, -75.14420603680837)
        val camaraPosition = CameraPosition(
            target, 10f, 10f, 10f
        )
        val latLngBounds = LatLngBounds(southweast, northeast)
        val googleMapOptions = GoogleMapOptions()
            .mapType(GoogleMap.MAP_TYPE_NORMAL)
            .compassEnabled(false)
            .rotateGesturesEnabled(false)
            .tiltGesturesEnabled(false)
            .latLngBoundsForCameraTarget(latLngBounds)
            .camera(camaraPosition)
        // Le pasamos las opciones creadas al mapFragment
        SupportMapFragment.newInstance(googleMapOptions)

        // En caso de q estemos usando un MapView
        // MapView(this,googleMapOptions)
    }
}