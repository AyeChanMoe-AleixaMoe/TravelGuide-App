package com.ozturksahinyetisir.travelguideapp.view.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.ozturksahinyetisir.travelguideapp.R

class DiscoverFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    DiscoverScreen(onItineraryClick = {
                        findNavController().navigate(R.id.action_discoverFragment_to_itineraryFragment)
                    })
                }
            }
        }
    }
}