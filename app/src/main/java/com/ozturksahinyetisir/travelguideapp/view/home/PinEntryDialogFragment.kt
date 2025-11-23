package com.ozturksahinyetisir.travelguideapp.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.ozturksahinyetisir.travelguideapp.R
import com.ozturksahinyetisir.travelguideapp.databinding.FragmentPinEntryBinding

class PinEntryDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentPinEntryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPinEntryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pinInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (binding.pinInput.text.toString() == "1234") { // Example PIN
                    Toast.makeText(context, "PIN Correct! Opening document...", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_pinEntryDialogFragment_to_documentsFragment)
                } else {
                    Toast.makeText(context, "Incorrect PIN", Toast.LENGTH_SHORT).show()
                    binding.pinInput.text.clear()
                }
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }
}
