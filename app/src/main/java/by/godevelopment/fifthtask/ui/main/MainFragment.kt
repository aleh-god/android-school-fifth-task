package by.godevelopment.fifthtask.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.godevelopment.fifthtask.R
import by.godevelopment.fifthtask.commons.START_POINT_LAT
import by.godevelopment.fifthtask.commons.START_POINT_LNG
import by.godevelopment.fifthtask.commons.ZOOM_LEVEL
import by.godevelopment.fifthtask.databinding.MainFragmentBinding
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var googleMap: GoogleMap
    lateinit var mMapView: MapView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        mMapView = binding.mapView.apply {
            onCreate(savedInstanceState)
            onResume()
            try {
                MapsInitializer.initialize(requireContext())
            } catch (e: Exception) {
                e.printStackTrace()
            }
            getMapAsync {
                googleMap = it
                // googleMap.isMyLocationEnabled
                setupUi()
            }
        }
        setupEvent()
        return binding.root
    }

    private fun setupUi() {
        lifecycleScope.launchWhenStarted {
            mainViewModel.uiState.collect { uiState ->
                uiState?.let {
                    if (uiState.isFetchingData) binding.progressBar.visibility = View.VISIBLE
                    else binding.progressBar.visibility = View.GONE
                    val zoomLevel = ZOOM_LEVEL
                    val homeLatLng = LatLng(START_POINT_LAT, START_POINT_LNG)
                    uiState.data.forEach { model ->
                        googleMap.addMarker(
                            MarkerOptions()
                                .position(
                                    LatLng(model.latitude, model.longitude)
                                )
                                .title(model.tittle_text)
                                .snippet(model.snippet_text)
                        )
                    }
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(homeLatLng, zoomLevel))
                }
            }
        }
    }

    private fun setupEvent() {
        lifecycleScope.launchWhenStarted {
            mainViewModel.uiEvent.collect {
                Snackbar
                    .make(binding.root, it, Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.snackbar_btn_reload))
                    { mainViewModel.fetchGeographicData() }
                    .show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mMapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMapView.onLowMemory()
    }
}