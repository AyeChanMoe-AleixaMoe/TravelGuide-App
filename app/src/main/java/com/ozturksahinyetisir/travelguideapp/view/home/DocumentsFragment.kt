package com.ozturksahinyetisir.travelguideapp.view.home

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ozturksahinyetisir.travelguideapp.databinding.FragmentDocumentsBinding
import java.io.IOException

class DocumentsFragment : Fragment() {

    private lateinit var binding: FragmentDocumentsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDocumentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try {
            // Load the image from the assets folder with the corrected filename
            val inputStream = requireContext().assets.open("jamescook_student_card.jpg")
            val bitmap = BitmapFactory.decodeStream(inputStream)
            binding.documentImage.setImageBitmap(bitmap)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
