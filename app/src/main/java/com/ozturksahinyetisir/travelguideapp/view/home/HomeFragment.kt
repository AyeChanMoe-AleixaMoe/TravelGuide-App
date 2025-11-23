package com.ozturksahinyetisir.travelguideapp.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ozturksahinyetisir.travelguideapp.R
import com.ozturksahinyetisir.travelguideapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupQuickActions()
        setupTodaySchedule()
        setupTripBudget()
        setupTravelDocuments()
    }

    private fun setupQuickActions() {
        // Flights
        binding.flightsAction.actionIcon.setImageResource(R.drawable.plane)
        binding.flightsAction.actionText.text = "Flights"
        binding.flightsAction.root.setOnClickListener {
            Toast.makeText(requireContext(), "Flights clicked", Toast.LENGTH_SHORT).show()
        }

        // Stay
        binding.stayAction.actionIcon.setImageResource(R.drawable.suite)
        binding.stayAction.actionText.text = "Stay"
        binding.stayAction.root.setOnClickListener {
            Toast.makeText(requireContext(), "Stay clicked", Toast.LENGTH_SHORT).show()
        }

        // Activities
        binding.activitiesAction.actionIcon.setImageResource(R.drawable.checklist)
        binding.activitiesAction.actionText.text = "Activities"
        binding.activitiesAction.root.setOnClickListener {
            Toast.makeText(requireContext(), "Activities clicked", Toast.LENGTH_SHORT).show()
        }

        // Expenses
        binding.expensesAction.actionIcon.setImageResource(R.drawable.spending)
        binding.expensesAction.actionText.text = "Expenses"
        binding.expensesAction.root.setOnClickListener {
            Toast.makeText(requireContext(), "Expenses clicked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupTodaySchedule() {
        // Hotel Check-In
        binding.hotelCheckIn.scheduleIcon.setImageResource(R.drawable.check_in)
        binding.hotelCheckIn.scheduleTitle.text = "Hotel Check-In • MBS"
        binding.hotelCheckIn.scheduleSubtitle.text = "3:00 PM • Confirmation #SJX439"
        binding.hotelCheckIn.actionButton.text = "Map"

        // Flower Dome Ticket
        binding.flowerDomeTicket.scheduleIcon.setImageResource(R.drawable.ticket)
        binding.flowerDomeTicket.scheduleTitle.text = "Tickets For Flower Dome"
        binding.flowerDomeTicket.scheduleSubtitle.text = "7:00 PM • 2 Tickets"
        binding.flowerDomeTicket.actionButton.text = "Tickets"
    }

    private fun setupTripBudget() {
        // Flights
        binding.budgetFlights.budgetIcon.setImageResource(R.drawable.plane_overview)
        binding.budgetFlights.budgetAmount.text = "$242"

        // Hotel
        binding.budgetHotel.budgetIcon.setImageResource(R.drawable.hotel)
        binding.budgetHotel.budgetAmount.text = "$123"

        // Food
        binding.budgetFood.budgetIcon.setImageResource(R.drawable.ramen)
        binding.budgetFood.budgetAmount.text = "$42"

        // Transport
        binding.budgetTransport.budgetIcon.setImageResource(R.drawable.public_transport)
        binding.budgetTransport.budgetAmount.text = "$77"
    }

    private fun setupTravelDocuments() {
        binding.passportVisaDoc.documentIcon.setImageResource(R.drawable.google_docs)
        binding.passportVisaDoc.documentTitle.text = "Passport & Visas"
        binding.passportVisaDoc.documentSubtitle.text = "Secure • PIN Enabled"
        binding.passportVisaDoc.openButton.setOnClickListener {
            Toast.makeText(requireContext(), "Opening Passport & Visas...", Toast.LENGTH_SHORT).show()
        }

        binding.flightPdfDoc.documentIcon.setImageResource(R.drawable.pdf)
        binding.flightPdfDoc.documentTitle.text = "Flight PDFs"
        binding.flightPdfDoc.documentSubtitle.text = "NH 802 • 12 Oct • Gate 54"
        binding.flightPdfDoc.openButton.setOnClickListener {
            Toast.makeText(requireContext(), "Opening Flight PDFs...", Toast.LENGTH_SHORT).show()
        }
    }
}
