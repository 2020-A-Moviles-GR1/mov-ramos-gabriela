package com.example.afinal

import android.content.Intent
import android.content.pm.PackageManager

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.squareup.picasso.Picasso

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private lateinit var mMap: GoogleMap
    var tienePermisos = false
    var listas = BddService.listaPokemones
    var indice:Int =1

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
     //   BddService.getAcordes()

        solicitarPermisos()
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
        establecerConfiguracionMapa(mMap)

            listas.forEach {

                agregarPokemonesMapa(it.latitud,it.longitud,it.nombre,it.url,it.imagen)
                Log.i("mapaFor","url: ${it.url}")
                Log.i("mapaFor","url: ${it.url}")

            }







    }

    fun anadirMarcador(latLng: LatLng, title: String) {
        mMap.addMarker(
            MarkerOptions()
                .position(latLng)
               // .title(title)
                //.snippet(url_redirect)
                .title(title)

        )

    }
    fun moverCamaraConZoom(latLng: LatLng, zoom: Float = 10f) {
        mMap.moveCamera(
            CameraUpdateFactory
                .newLatLngZoom(latLng, zoom)
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
        val permisosFineLocation = ContextCompat
            .checkSelfPermission(
                this.applicationContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
        val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED

        if (tienePermisos) {
            Log.i("mapa", "Tiene permisos FINE LOCATION")
            this.tienePermisos = true
        } else {
            ActivityCompat.requestPermissions(
                this, // Contexto
                arrayOf( // Arreglo Permisos
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),
                1 //  Codigo que esperamos
            )
        }
    }



    override fun onInfoWindowClick(p0: Marker?) {
        // Log.i("MapActivity","Clicked")
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(p0?.snippet)) //p0?.snippet
        startActivity(browserIntent)
    }


    fun agregarPokemonesMapa(
        lat:String, lon:String, titulo: String, urlsalgo:String, imagen:String
    ){


            val puntoUsuario = LatLng(lat.toDouble(), lon.toDouble())

            val melbourne = mMap.addMarker(
                MarkerOptions()
                    .position(puntoUsuario)
                    .title(titulo))

            val marker = PicassoMarker(melbourne);
            Picasso.get().load(imagen).resize(150, 150).into(marker);




            mMap.setOnMarkerClickListener(GoogleMap.OnMarkerClickListener { melbourne ->
                val uri: Uri =
                    Uri.parse("${urlsalgo}")
                Log.i("mapaFor", "url salgo: ${urlsalgo}")

                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
                true
            })


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(puntoUsuario, 18F))
    }


}