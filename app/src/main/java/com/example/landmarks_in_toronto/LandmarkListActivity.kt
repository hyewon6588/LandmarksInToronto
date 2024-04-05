package com.example.landmarks_in_toronto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LandmarkListActivity : AppCompatActivity(),LandmarkAdapter.OnItemClickListener {
    private lateinit var landmarkAdapter: LandmarkAdapter
    private lateinit var landmarkType: String

    private val landmarks = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landmark_list)

        landmarkType = intent.getStringExtra("landmarkType")?:""

        title = landmarkType
//        Toast.makeText(this,"selected land mark:"+landmarkType,Toast.LENGTH_SHORT).show();
        val recyclerView:RecyclerView=findViewById(R.id.recyclerViewLandmarks)
        landmarkAdapter = LandmarkAdapter(landmarks,this)

        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = landmarkAdapter
        populateLandmarks(landmarkType)
    }


    override fun onItemClick(selectedLandmark:String){
        var latitude:Double=0.0
        var longitude:Double=0.0

        when (selectedLandmark) {
            "Royal Ontario Museum"->{
                latitude=43.667713
                longitude=-79.394913
            }
            "Bata Shoe Museum"->{
                latitude=43.6672859
                longitude=-79.4001825
            }
            "Toronto Railway Museum"->{
                latitude=43.640694
                longitude=-79.385889
            }
            "Casa Loma"->{
                latitude=43.678055
                longitude=-79.409538
            }
            "Distillery District"->{
                latitude=43.650238
                longitude=-79.359222
            }
            "Rogers Center"->{
                latitude=43.641796
                longitude=-79.390083
            }
            "Scotiabank Arena"->{
                latitude=43.643475
                longitude=-79.379379
            }
            "Coca-cola Coliseum"->{
                latitude=43.636100
                longitude=-79.415300
            }
            "CN Tower"->{
                latitude=43.642567
                longitude=-79.387054
            }
            "Toronto City Hall"->{
                latitude=43.653908
                longitude=-79.384293
            }
            "Exhibition Place"->{
                latitude=43.635100
                longitude=-79.412400
            }
            "Korea Town"->{
                latitude=43.663900
                longitude=-79.416600
            }
            "China Town"->{
                latitude=43.652900
                longitude=-79.398100
            }
            "Greek Town"->{
                latitude=43.678892
                longitude=-79.3499443
            }
            "Little Italy"->{
                latitude=43.655200
                longitude=-79.413900
            }
            "Kensington Market"->{
                latitude=43.654500
                longitude=-79.401500
            }
            "St.Lawrence Market"->{
                latitude=43.648700
                longitude=-79.371500
            }
            "CF Eaton Center"->{
                latitude=43.654400
                longitude=-79.380700
            }
            "Yorkdale Mall"->{
                latitude=43.725414
                longitude=-79.452293
            }
            "Vaughan Mills"->{
                latitude=43.825900
                longitude=-79.539100
            }
        }

//        Toast.makeText(this,"selected landmark:"+selectedLandmark+"\nlatitude:"+latitude+"\nlongitude:"+longitude,Toast.LENGTH_LONG).show()

        // Handle item click event
        val intent = Intent(this, MapActivity::class.java).apply {
            putExtra("selectedLandmark", selectedLandmark)
            putExtra("latitude",latitude)
            putExtra("longitude",longitude)
        }
        startActivity(intent)
    }

    private fun populateLandmarks(landmarkType: String) {
        val fetchedLandmarks = fetchLandmarksByType(landmarkType)

        landmarks.clear()
        landmarks.addAll(fetchedLandmarks)

        landmarkAdapter.notifyDataSetChanged()
    }

    private fun fetchLandmarksByType(landmarkType: String): Collection<String> {
        val landmarks = mutableListOf<String>()

        when (landmarkType) {
            "Museums" -> {
                landmarks.add("Royal Ontario Museum")
                landmarks.add("Bata Shoe Museum")
                landmarks.add("Toronto Railway Museum")
            }
            "Historic Sites" -> {
                landmarks.add("Casa Loma")
                landmarks.add("Distillery District")
            }
            "Stadiums" -> {
                landmarks.add("Rogers Center")
                landmarks.add("Scotiabank Arena")
                landmarks.add("Coca-cola Coliseum")
            }
            "Attractions" -> {
                landmarks.add("CN Tower")
                landmarks.add("Toronto City Hall")
                landmarks.add("Exhibition Place")
            }
            "Neighbourhood" -> {
                landmarks.add("Korea Town")
                landmarks.add("China Town")
                landmarks.add("Greek Town")
                landmarks.add("Little Italy")
            }
            "Markets" -> {
                landmarks.add("Kensington Market")
                landmarks.add("St.Lawrence Market")
            }
            "Mall" -> {
                landmarks.add("CF Eaton Center")
                landmarks.add("Yorkdale Mall")
                landmarks.add("Vaughan Mills")
            }
        }

        return landmarks
    }
}

