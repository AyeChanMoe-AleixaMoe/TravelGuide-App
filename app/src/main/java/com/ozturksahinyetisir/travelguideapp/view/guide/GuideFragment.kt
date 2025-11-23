package com.ozturksahinyetisir.travelguideapp.view.guide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.ozturksahinyetisir.travelguideapp.view.wallet.WalletScreen
import com.ozturksahinyetisir.travelguideapp.view.wallet.WalletViewModel

class GuideFragment : Fragment() {

    private val walletViewModel: WalletViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                WalletScreen(walletViewModel)
            }
        }
    }
}
