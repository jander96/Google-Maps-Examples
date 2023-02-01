package com.example.mapandlocationspractices

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

// Se implementa la interfaz OnMapReadyCallBack
class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mapFragment: SupportMapFragment
    private val COLOR_BLACK_ARGB = -0x1000000
    private val POLYLINE_STROKE_WIDTH_PX = 12
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
        addMarker(googleMap)
        addPolyline(googleMap)

        // Click listeners de la Polyline, la guia los hace implementando la intefaz
        googleMap.setOnPolylineClickListener {
            doSomethingOnCLick()
        }
        googleMap.setOnPolygonClickListener {
            doSomethingOnCLick()
        }
        // setting type other way
        googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID

        // Habilitar el modo 3d
        googleMap.isBuildingsEnabled = true
        googleMap.isIndoorEnabled = true

    }

    private fun doSomethingOnCLick() {

    }

    private fun addPolyline(googleMap: GoogleMap) {
        val polylineOptions = PolylineOptions()
            .clickable(true)
            .add(
                LatLng(-35.016, 143.321),
                LatLng(-34.747, 145.592),
                LatLng(-34.364, 147.891),
                LatLng(-33.501, 150.217),
                LatLng(-32.306, 149.248),
                LatLng(-32.491, 147.309)
            )

        val polyline = googleMap.addPolyline(polylineOptions)
        polyline.tag = "A"// Se le puede pasar un valor usando el tag
        stylePolyline( polyline)
    }

    private fun addMarker(map: GoogleMap) {
        val sydney = LatLng(-33.852, 151.211)
        val markerOptions = MarkerOptions()
            .position(sydney)
            .title("Marcador de Sydney")
        map.addMarker(markerOptions)?.tag = 0 // Se le puede pasar un valor usando el tag
        // Efecto de movimiento hacia el marker
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney))
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
    //Podemos recuperar los valores q se le han pasado para ejecutar una logica de personalización
    private fun stylePolyline(polyline: Polyline) {
        // Get the data object stored with the polyline.
        val type = polyline.tag?.toString() ?: ""
        when (type) {
            "A" -> {
                // Use a custom bitmap as the cap at the start of the line.
                polyline.startCap = CustomCap(
                    BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher_foreground), 10f)
            }
            "B" -> {
                // Use a round cap at the start of the line.
                polyline.startCap = RoundCap()
            }
        }
        polyline.endCap = RoundCap()
        polyline.width = POLYLINE_STROKE_WIDTH_PX.toFloat()
        polyline.color = COLOR_BLACK_ARGB
        polyline.jointType = JointType.ROUND
    }
}