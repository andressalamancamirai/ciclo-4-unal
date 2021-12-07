package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.InputStream
import java.net.URL
import java.net.URLConnection


class MainActivity : AppCompatActivity(), OnMapReadyCallback {


    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val url:URL=URL("https://wikipedia.org")
        val urlConnection: URLConnection = url.openConnection()
        val inputStream:InputStream = urlConnection.getInputStream()
        print(inputStream.toString())


    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val latitudLongitud = LatLng(4.75269,-74.06354)
        mMap.addMarker(MarkerOptions().position(latitudLongitud).title("HOla a tdos!!"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latitudLongitud))
        mMap.setMinZoomPreference(15F)
    }
}