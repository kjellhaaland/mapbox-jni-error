package io.kjellhaaland.sampleapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.location.OnCameraTrackingChangedListener
import com.mapbox.mapboxsdk.location.modes.CameraMode
import com.mapbox.mapboxsdk.location.modes.RenderMode
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnMapReadyCallback, MapboxMap.OnMapClickListener, MapboxMap.OnCameraMoveListener,
    OnCameraTrackingChangedListener {

    // Reference to the MapView
    internal lateinit var mapView : MapView

    // Reference to the MapboxMap
    internal var map : MapboxMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Mapbox.getInstance(this, "SOME VALID KEY")

        setContentView(R.layout.activity_main)

        // Create and initialize the map view
        this.mapView = map_view
        this.mapView.onCreate(savedInstanceState)
        this.mapView.getMapAsync(this)

    }

    override fun onMapReady(mapboxMap: MapboxMap?) {
        map = mapboxMap
        map?.addOnCameraMoveListener(this)
        map?.addOnMapClickListener(this)
        map?.uiSettings?.isCompassEnabled = false

        val locationComp = map?.locationComponent!!

        try {
            locationComp.activateLocationComponent(this)
            locationComp.isLocationComponentEnabled = true
            locationComp.renderMode = RenderMode.NORMAL
            locationComp.cameraMode = CameraMode.TRACKING_GPS
            locationComp.locationComponentOptions?.trackingGesturesManagement()
            locationComp.locationComponentOptions?.trackingInitialMoveThreshold()
            locationComp.locationComponentOptions?.trackingMultiFingerMoveThreshold()
            locationComp.addOnCameraTrackingChangedListener(this)
        }catch (e : SecurityException){
            e.printStackTrace()
        }
    }


    override fun onMapClick(point: LatLng) {

    }

    override fun onCameraMove() {

    }

    override fun onCameraTrackingChanged(currentMode: Int) {

    }

    override fun onCameraTrackingDismissed() {

    }

}
