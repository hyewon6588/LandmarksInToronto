package com.example.landmarks_in_toronto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(),LandmarkAdapter.OnItemClickListener {
    private val itemsList = ArrayList<String>()
    private lateinit var landmarkAdapter: LandmarkAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        landmarkAdapter = LandmarkAdapter(itemsList,this)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = landmarkAdapter
        prepareItems()
    }

    override fun onItemClick(landmarkType:String){
        Toast.makeText(this,"selected type:"+landmarkType,Toast.LENGTH_LONG)
        // Handle item click event
        val intent = Intent(this, LandmarkListActivity::class.java).apply {
            putExtra("landmarkType", landmarkType)
        }
        startActivity(intent)
    }
    private fun prepareItems() {
        itemsList.add("Museums")
        itemsList.add("Historic Sites")
        itemsList.add("Stadiums")
        itemsList.add("Attractions")
        itemsList.add("Neighbourhood")
        itemsList.add("Markets")
        itemsList.add("Mall")
        landmarkAdapter.notifyDataSetChanged()
    }

}