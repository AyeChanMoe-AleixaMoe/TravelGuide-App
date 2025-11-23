package com.ozturksahinyetisir.travelguideapp.view.discover

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.ozturksahinyetisir.travelguideapp.components.ItineraHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoverScreen(onItineraryClick: () -> Unit = {}) {
    Scaffold(
        topBar = {
            Column(modifier = Modifier.padding(16.dp)) {
                ItineraHeader()
            }
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            item { SearchBarSection() }
            item { TripIdeasSection(onItineraryClick) }
            item { PopularDestinationsSection() }
            item { NearbyAttractionsSection() }
            item { FoodAndCafesSection() }
            item { GettingAroundSection() }
            item { BottomNoteSection() }
        }
    }
}

@Composable
fun SearchBarSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Where are you travelling next?") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp)
        )
        Text(
            text = "Search cities, countries or saved itineraries.",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
            modifier = Modifier.padding(top = 4.dp, start = 16.dp)
        )
    }
}

@Composable
fun TripIdeasSection(onItineraryClick: () -> Unit) {
    val tripIdeas = listOf(
        Triple("3-Day Budget Trip in Tokyo", "Itinerary · Solo / Friends", "https://picsum.photos/seed/1/400/300"),
        Triple("Family Weekend in Sydney", "Itinerary · Family friendly", "https://picsum.photos/seed/2/400/300")
    )

    Section("Trip ideas from other travellers", "Browse ready-made itineraries and travel guides shared by the community.") {
        LazyRow(contentPadding = PaddingValues(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(tripIdeas) { (title, subtitle, imageUrl) ->
                Card(modifier = Modifier
                    .width(250.dp)
                    .clickable { onItineraryClick() }) {
                    Box {
                        Image(painter = rememberAsyncImagePainter(imageUrl), contentDescription = null, modifier = Modifier.height(150.dp), contentScale = ContentScale.Crop)
                        Text("ITINERARY", style = MaterialTheme.typography.labelSmall, modifier = Modifier.padding(8.dp).background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(4.dp)).padding(4.dp), color = Color.White)
                    }
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(title, fontWeight = FontWeight.Bold)
                        Text(subtitle, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                    }
                }
            }
        }
    }
}

@Composable
fun PopularDestinationsSection() {
    val destinations = listOf(
        Triple("London", "United Kingdom · 12 guides", "https://picsum.photos/seed/3/400/300"),
        Triple("Seoul", "South Korea · 8 itineraries", "https://picsum.photos/seed/4/400/300")
    )

    Section("Popular destinations", "Cities that are often added to Itinera trips.") {
        LazyRow(contentPadding = PaddingValues(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(destinations) { (title, subtitle, imageUrl) ->
                Card(modifier = Modifier.width(200.dp)) {
                    Image(painter = rememberAsyncImagePainter(imageUrl), contentDescription = null, modifier = Modifier.height(120.dp), contentScale = ContentScale.Crop)
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(title, fontWeight = FontWeight.Bold)
                        Text(subtitle, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                    }
                }
            }
        }
    }
}

@Composable
fun NearbyAttractionsSection() {
    val attractions = listOf(
        Triple("Cairo, Egypt", "Landmarks · Museums · Markets", "https://picsum.photos/seed/5/400/300")
    )

    Section("Nearby attractions", "Must-see places close to your current or selected city.") {
        LazyRow(contentPadding = PaddingValues(horizontal = 16.dp)) {
            items(attractions) { (title, subtitle, imageUrl) ->
                Card(modifier = Modifier.width(300.dp)) {
                    Box {
                        Image(painter = rememberAsyncImagePainter(imageUrl), contentDescription = null, modifier = Modifier.height(180.dp), contentScale = ContentScale.Crop)
                        Text("NEARBY", style = MaterialTheme.typography.labelSmall, color = Color.White, modifier = Modifier.padding(8.dp).background(Color.Red, RoundedCornerShape(4.dp)).padding(4.dp))
                    }
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(title, fontWeight = FontWeight.Bold)
                        Text(subtitle, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                    }
                }
            }
        }
    }
}

@Composable
fun FoodAndCafesSection() {
    val foodSpots = listOf(
        Triple("Best Street Food in Bangkok", "Food guide · Under $15/day", "https://picsum.photos/seed/6/400/300")
    )

    Section("Food & cafés to try", "Local food recommendations from other travellers.") {
        LazyRow(contentPadding = PaddingValues(horizontal = 16.dp)) {
            items(foodSpots) { (title, subtitle, imageUrl) ->
                Card(modifier = Modifier.width(250.dp)) {
                    Image(painter = rememberAsyncImagePainter(imageUrl), contentDescription = null, modifier = Modifier.height(150.dp), contentScale = ContentScale.Crop)
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(title, fontWeight = FontWeight.Bold)
                        Text(subtitle, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                    }
                }
            }
        }
    }
}

@Composable
fun GettingAroundSection() {
    val transportGuides = listOf(
        Triple("Tokyo Metro Basics", "How to buy IC cards & tickets", "https://picsum.photos/seed/7/400/300")
    )

    Section("Getting around", "Quick guides for metro, buses and local transport.") {
        LazyRow(contentPadding = PaddingValues(horizontal = 16.dp)) {
            items(transportGuides) { (title, subtitle, imageUrl) ->
                Card(modifier = Modifier.width(250.dp)) {
                    Image(painter = rememberAsyncImagePainter(imageUrl), contentDescription = null, modifier = Modifier.height(150.dp), contentScale = ContentScale.Crop)
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(title, fontWeight = FontWeight.Bold)
                        Text(subtitle, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNoteSection() {
    Text(
        text = "Save any place to your trip and Itinera will add it to your itinerary and budget automatically.",
        style = MaterialTheme.typography.bodySmall,
        color = Color.Gray,
        modifier = Modifier.padding(16.dp)
    )
}


@Composable
fun Section(title: String, subtitle: String, content: @Composable () -> Unit) {
    Column(modifier = Modifier.padding(vertical = 16.dp)) {
        Text(title, fontWeight = FontWeight.Bold, fontSize = 20.sp, modifier = Modifier.padding(horizontal = 16.dp))
        Text(subtitle, style = MaterialTheme.typography.bodySmall, color = Color.Gray, modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp))
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun DiscoverScreenPreview() {
    DiscoverScreen()
}
