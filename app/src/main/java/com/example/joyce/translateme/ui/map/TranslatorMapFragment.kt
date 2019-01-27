package com.example.joyce.translateme.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.joyce.translateme.R
import com.mapbox.core.utils.ColorUtils
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.plugins.annotation.LineManager
import com.mapbox.mapboxsdk.plugins.annotation.LineOptions
import kotlinx.android.synthetic.main.fragment_translator_map.*


class TranslatorMapFragment : Fragment() {

    private val latLngs = listOf<LatLng>(
            LatLng(
                    45.49655619089009,
                    -73.58173727989197),
            LatLng(
                    45.49610496505068,
                    -73.58029961585997
            ),
            LatLng(
                    45.49598839778783,
                    -73.57995629310608
            ),
            LatLng(
                    45.495702618961175,
                    -73.57931256294249
            ),
            LatLng(
                    45.495277708287695,
                    -73.57834696769713
            ),
            LatLng(
                    45.494965603715265,
                    -73.57872247695923
            ))

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
        mapView.getMapAsync {map ->
            map.setStyle(Style.LIGHT) {
                val lineManager = LineManager(mapView, map, it)
                val lineOptions = LineOptions()
                        .withLatLngs(latLngs)
                        .withLineColor(ColorUtils.toHexString(0, 0, 255))
                        .withLineWidth(5f)

                lineManager.create(lineOptions)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
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
