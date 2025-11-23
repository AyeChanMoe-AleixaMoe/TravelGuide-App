package com.ozturksahinyetisir.travelguideapp.view

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.ozturksahinyetisir.travelguideapp.R
import com.ozturksahinyetisir.travelguideapp.data.service.TravelApi
import com.ozturksahinyetisir.travelguideapp.databinding.ActivityMainBinding
import com.ozturksahinyetisir.travelguideapp.domain.model.TravelModel
import com.ozturksahinyetisir.travelguideapp.room.TravelDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var preferences: SharedPreferences
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferences = getSharedPreferences("tripdata", MODE_PRIVATE)
        getData()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)
    }

    private val travelApi = TravelApi()

    /**
     * [getData] function get data onCreate MainActivity
     */
    private fun getData() {
        Log.e(TAG, "getData: ")
        val call = travelApi.getTravelData()
        call.enqueue(object : Callback<List<TravelModel>> {
            override fun onResponse(
                call: Call<List<TravelModel>>,
                response: Response<List<TravelModel>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { travelList ->
                        try {
                            val data = TravelDatabase.getDatabase(this@MainActivity)
                            data.roomDao().insertTravel(travelList)
                            Log.e(TAG, "got travel data")
                        } catch (e: Exception) {
                            Log.e(TAG, "onResponse: exception message ${e.message}")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<TravelModel>>, t: Throwable) {
                Log.v("API Failure", t.message.toString())
            }
        })
    }
}