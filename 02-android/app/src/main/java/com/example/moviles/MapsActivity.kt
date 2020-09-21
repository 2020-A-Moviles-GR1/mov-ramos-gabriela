package com.example.moviles

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapsActivity : AppCompatActivity(),
    OnMapReadyCallback,
GoogleMap.OnCameraMoveStartedListener,
GoogleMap.OnCameraMoveListener,
GoogleMap.OnCameraIdleListener,
    GoogleMap.OnPolylineClickListener,
        GoogleMap.OnPolygonClickListener
{

    private lateinit var mMap: GoogleMap
    var tienePermisos = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        solicitarPermisos()

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap
        // 1) Permisos
        establecerConfiguracionMapa(mMap)
        Log.i("map", "Empezando")
        establecerListeners(mMap)

        val cci = LatLng(-0.178067, -78.485594)
        val puntoUsuario = LatLng(-0.179204, -78.484189)

        val titulo = "CCI"
        val zoom = 17f
        anadirMarcador(cci, titulo)
        moverCamaraConZoom(puntoUsuario, zoom)







        val poligonoUno = googleMap.addPolygon(
            PolygonOptions().clickable(true).add(
                LatLng(        -0.180259, -78.484826
                ),

                LatLng(-0.180015, -78.485826),
                LatLng(-0.179607, -78.485679),
                LatLng(-0.179425, -78.484544)
            )
        )
poligonoUno.fillColor = -0xc771c54

        val poliLineaUno = googleMap.addPolyline(

            PolylineOptions().clickable(true).add(
                LatLng(-0.179175, -78.484280),
                LatLng(-0.177399, -78.483655),
                LatLng(-0.176397, -78.485485),
                LatLng(-0.177947, -78.485695)
            )
        )

        // Add a marker in Sydney and move the camera
     //   val sydney = LatLng(-34.0, 151.0)
       // mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    fun moverCamaraConZoom(latLng: LatLng, zoom: Float = 10f) {
        mMap.moveCamera(
            CameraUpdateFactory
                .newLatLngZoom(latLng, zoom)
        )
    }

    fun anadirMarcador(latLng: LatLng, title: String) {
        mMap.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(title)
        )
    }

    fun establecerConfiguracionMapa(mapa: GoogleMap) {
        val contexto = this.applicationContext
        with(mapa) {
            val permisosFineLocation = ContextCompat
                .checkSelfPermission(
                    contexto,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
            if (tienePermisos) {
                mapa.isMyLocationEnabled = true
            }
            // this.uiSettings.isZoomControlsEnabled = true
            uiSettings.isZoomControlsEnabled = true
            uiSettings.isMyLocationButtonEnabled = true
        }

    }


    fun solicitarPermisos() {
        val permisosFineLocation = ContextCompat.checkSelfPermission(
            this.applicationContext,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )

        val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
        if (tienePermisos) {
            Log.i("mapa", "Tiene permisos FINE LOCATION")
            this.tienePermisos = true
        }else{

            ActivityCompat.requestPermissions(
                this, // Contexto
                arrayOf( // Arreglo Permisos
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),
                1 //  Codigo que esperamos
            )
        }

    }

    fun establecerListeners(map: GoogleMap){
        with(map){
            setOnCameraIdleListener(this@MapsActivity)
            setOnCameraMoveStartedListener(this@MapsActivity)
            setOnCameraMoveListener(this@MapsActivity)
            setOnPolylineClickListener(this@MapsActivity)
            setOnPolygonClickListener(this@MapsActivity)

        }

    }

    override fun onCameraMoveStarted(p0: Int) {
Log.i("map", "Empezando a mover onCameraMoveStarted")    }

    override fun onCameraMove() {
        Log.i("map", "Moviendo onCameraMove")    }


    override fun onCameraIdle() {
        Log.i("map", "Quieto onCameraIdle")    }

    override fun onPolylineClick(p0: Polyline?) {
        Log.i("maa", "Polylinea ${p0.toString()}")    }


    override fun onPolygonClick(p0: Polygon?) {
        Log.i("map", "Polygono ${p0.toString()}")    }

}