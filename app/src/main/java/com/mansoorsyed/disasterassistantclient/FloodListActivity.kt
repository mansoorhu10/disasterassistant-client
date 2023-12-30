package com.mansoorsyed.disasterassistantclient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.disasterassistantclient.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mansoorsyed.disasterassistantclient.adapter.FloodAdapter
import com.mansoorsyed.disasterassistantclient.model.Flood
import com.mansoorsyed.disasterassistantclient.retrofit.FloodApi
import com.mansoorsyed.disasterassistantclient.retrofit.RetroFitService
import retrofit2.Call
import retrofit2.Response

class FloodListActivity : AppCompatActivity() {

    private var recyclerView : RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flood_list)

        recyclerView = findViewById(R.id.floodList_recyclerView);
        recyclerView?.layoutManager = LinearLayoutManager(this);

        var floatingActionButton : FloatingActionButton = findViewById(R.id.floodList_fab);
        floatingActionButton.setOnClickListener { view ->
            var intent : Intent = Intent(this, FloodForm::class.java);
            startActivity(intent)
        }

        loadFloods();
    }

    private fun loadFloods() {
        var retrofitService : RetroFitService = RetroFitService(this@FloodListActivity);
        var floodApi = retrofitService.retroFit?.create<FloodApi>(FloodApi::class.java);
        floodApi?.allFloods
            ?.enqueue(object : retrofit2.Callback<List<Flood?>?> {
                override fun onResponse(call: Call<List<Flood?>?>, response: Response<List<Flood?>?>) {
                    populateListView(response.body());
                }

                override fun onFailure(call: Call<List<Flood?>?>, t: Throwable) {
                    Toast.makeText(this@FloodListActivity, "Failed to load floods", Toast.LENGTH_SHORT)
                        .show()
                }
            });
    }

    private fun populateListView(floodList: List<Flood?>?) {
        var floodAdapter : FloodAdapter = FloodAdapter(floodList);
        recyclerView?.adapter = floodAdapter;
    }

}