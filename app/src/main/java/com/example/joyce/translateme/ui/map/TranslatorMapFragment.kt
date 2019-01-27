package com.example.joyce.translateme.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.joyce.translateme.R
import com.example.joyce.translateme.ui.MainViewModel
import com.mapbox.core.utils.ColorUtils
import com.mapbox.geojson.utils.PolylineUtils
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.geometry.LatLngBounds
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.plugins.annotation.LineManager
import com.mapbox.mapboxsdk.plugins.annotation.LineOptions
import kotlinx.android.synthetic.main.fragment_translator_map.*
import org.koin.android.viewmodel.ext.android.sharedViewModel


class TranslatorMapFragment : Fragment() {

    private lateinit var map: MapboxMap
    private val vm: MainViewModel by sharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(requireContext(), "pk.eyJ1IjoiZGVsbGlzZCIsImEiOiJjam9obzZpMDQwMGQ0M2tsY280OTh2M2o5In0.XtnbkAMU7nIMkq7amsiYdw")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_translator_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync { m ->
            map = m
            map.setStyle(Style.LIGHT)

            vm.plan.observe(this, Observer { path ->
                val latLngBounds = LatLngBounds.Builder()

                if (vm.currentPosition.value != null) {
                    latLngBounds.include(vm.currentPosition.value!!)
                }
                if (vm.otherUser.value != null && vm.otherUser.value!!.getLatLng() != null) {
                    latLngBounds.include(vm.otherUser.value!!.getLatLng()!!)
                }

                if (path != null) {
                    val points = PolylineUtils.decode(path, 5).map { LatLng(it.latitude(), it.longitude()) }

                    latLngBounds.includes(points)
                    val lineManager = LineManager(mapView, map, map.style!!)
                    val lineOptions = LineOptions()
                            .withLatLngs(points)
                            .withLineColor("#048CF2FF")
                            .withLineWidth(5f)

                    lineManager.create(lineOptions)
                }


                map.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds.build(), 24))
            })

            vm.otherUser.observe(this, Observer {
                map.addMarker(MarkerOptions()
                        .position(it.getLatLng())
                        .setTitle(it.name))
            })
        }
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
        vm.getLocationUpdates()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
        vm.stopLocationUpdates()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroyView() {
        mapView.onDestroy()
        super.onDestroyView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

}
