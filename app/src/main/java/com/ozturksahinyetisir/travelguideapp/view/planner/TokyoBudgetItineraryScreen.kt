package com.ozturksahinyetisir.travelguideapp.view.planner

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TokyoBudgetItineraryScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tokyo itinerary") },
                navigationIcon = {
                    IconButton(onClick = { /* Navigation handled by caller */ }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            // Header
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "3-Day Budget Trip in Tokyo",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("Itinerary · Solo / Friends", style = MaterialTheme.typography.bodyMedium)
                Text("Base area: Asakusa / Ueno", style = MaterialTheme.typography.bodyMedium)
                Text("Length: 3 days, 2 nights", style = MaterialTheme.typography.bodyMedium)
                Text(
                    "Approx. budget: ¥25,000–¥30,000 per person (excluding flights & accommodation)",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(24.dp))
            }

            // Trip overview
            item {
                SectionTitle("Trip overview")
                Text(
                    text = "This 3-day budget itinerary is for first-time visitors who want to see the classic sights of Tokyo without spending too much money. I stayed around Asakusa, used an IC card (Suica/PASMO) for trains and focused on free or low-cost attractions. Most meals are from cheap local restaurants or convenience stores, so it’s easy to keep daily expenses low while still enjoying good Japanese food.",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(24.dp))
            }

            // Budget summary
            item {
                SectionTitle("Budget summary")
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            "Food: ¥2,500–¥3,000 per day – convenience-store breakfast, cheap ramen/gyudon, casual dinners.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "Transport: ¥1,000–¥1,500 per day – metro rides with IC card, no taxis.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "Attractions: ¥1,000–¥2,000 per day – mostly free shrines/parks, optional paid viewpoints.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "Extras & snacks: ¥500–¥1,000 per day.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Divider(modifier = Modifier.padding(vertical = 12.dp))
                        Text(
                            text = "Estimated daily spending: around ¥5,000–¥7,000.",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            // Day 1
            item {
                SectionTitle("Day 1 – Asakusa, Ueno & Akihabara")
                DayItineraryItem(
                    time = "Morning",
                    description = "Check in or drop luggage at your hostel/hotel in Asakusa/Ueno. Walk to Sensō-ji Temple (free) and explore Nakamise Shopping Street for snacks like taiyaki or senbei."
                )
                DayItineraryItem(
                    time = "Lunch",
                    description = "Eat tendon (tempura rice bowl) at a local shop near Sensō-ji. Estimated cost: ~¥1,000–¥1,200."
                )
                DayItineraryItem(
                    time = "Afternoon",
                    description = "Take the metro to Ueno Station. Stroll around Ueno Park and the pond area (free). Optional: visit one paid museum such as Tokyo National Museum (~¥600–¥1,000)."
                )
                DayItineraryItem(
                    time = "Evening",
                    description = "Train to Akihabara. Explore game centres, anime shops and electronic stores. Have a cheap dinner such as curry rice or gyudon (~¥600–¥900) and then head back to your accommodation."
                )
                Spacer(modifier = Modifier.height(24.dp))
            }

            // Day 2
            item {
                SectionTitle("Day 2 – Shibuya, Harajuku & Shinjuku")
                DayItineraryItem(
                    time = "Morning",
                    description = "Train to Harajuku Station. Visit Meiji Shrine (free), then walk along Takeshita Street for shops and youth fashion. Try a crepe or snack (~¥500–¥800)."
                )
                DayItineraryItem(
                    time = "Lunch",
                    description = "Go to Shibuya Station. See Shibuya Crossing and Hachikō statue. Have lunch at a chain restaurant (gyudon, ramen or set meal, ~¥800–¥1,000)."
                )
                DayItineraryItem(
                    time = "Afternoon",
                    description = "Explore Shibuya’s shopping streets or visit Shibuya PARCO. Optional: visit Shibuya Sky or another viewpoint if budget allows (~¥2,000)."
                )
                DayItineraryItem(
                    time = "Evening",
                    description = "Train to Shinjuku. Walk through Omoide Yokocho and Kabukichō to see the neon lights. Eat dinner at a small izakaya or cheap curry/ramen shop (~¥1,000–¥1,200) before heading back."
                )
                Spacer(modifier = Modifier.height(24.dp))
            }

            // Day 3
            item {
                SectionTitle("Day 3 – Markets, River & Shopping")
                DayItineraryItem(
                    time = "Morning",
                    description = "Go to Tsukiji Outer Market. Have breakfast or early lunch with affordable sushi bowls or grilled seafood (~¥1,200–¥1,800)."
                )
                DayItineraryItem(
                    time = "Midday",
                    description = "Return towards Asakusa. Optional: take a Sumida River cruise towards Odaiba (~¥1,000–¥1,500) or just enjoy the free riverside views."
                )
                DayItineraryItem(
                    time = "Afternoon",
                    description = "Visit Ameyoko Shopping Street near Ueno for last-minute snacks, souvenirs and gifts. Souvenir budget: ~¥1,000–¥2,000."
                )
                DayItineraryItem(
                    time = "Evening",
                    description = "Pick up stored luggage and use metro/train to return to the airport. Avoid taxis to keep the trip budget-friendly."
                )
                Spacer(modifier = Modifier.height(24.dp))
            }

            // Practical Tips
            item {
                SectionTitle("Practical tips")
                BulletListItem("Get a Suica or PASMO IC card and top it up for all trains and metro rides.")
                BulletListItem("Avoid peak rush hours (7–9am and 5–7pm) when trains are very crowded.")
                BulletListItem("Use convenience stores (FamilyMart, 7-Eleven, Lawson) for cheap, decent meals.")
                BulletListItem("Stay near a JR or metro station to reduce travel time.")
                BulletListItem("Bring a small bag for water, snacks and a compact umbrella.")
                Spacer(modifier = Modifier.height(32.dp))
            }

            // Bottom Button
            item {
                Button(
                    onClick = { /* No-op */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Text(
                        text = "Add this itinerary to my trip",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 12.dp)
    )
}

@Composable
private fun DayItineraryItem(time: String, description: String) {
    Column(modifier = Modifier.padding(bottom = 12.dp)) {
        Text(
            text = time,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun BulletListItem(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 6.dp)
    ) {
        Text("•  ", style = MaterialTheme.typography.bodyLarge)
        Text(text = text, style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun TokyoBudgetItineraryScreenPreview() {
    MaterialTheme {
        TokyoBudgetItineraryScreen()
    }
}
