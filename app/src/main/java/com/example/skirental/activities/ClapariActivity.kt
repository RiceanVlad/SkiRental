package com.example.skirental.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skirental.R
import com.example.skirental.adapters.ClapariRecyclerAdapter
import com.example.skirental.infoactivities.InfoClapariActivity
import com.example.skirental.miscellaneous.TopSpacingItemDecoration
import com.example.skirental.models.Produs
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_clapari.*

/**
 * Recycler view for all the boots
 */
class ClapariActivity : AppCompatActivity(), ClapariRecyclerAdapter.OnItemClickListener {

    private lateinit var clapariAdapter: ClapariRecyclerAdapter
    private var listaClapari = ArrayList<Produs>()
    val db = FirebaseFirestore.getInstance()
    private val TAG = "vlad"
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clapari)

        preiaClapariDinBD()
        butoane()
    }

    /**
     * Buttons in this activity
     * @param buttonSkipClapari user skips choosing boots
     */
    private fun butoane() {
        buttonSkipClapari.setOnClickListener {
            val intent1 = Intent(this, BeteActivity::class.java)
            intent1.putExtra("inaltime",intent.getStringExtra("inaltime"))
            intent1.putExtra("marimepicior",intent.getStringExtra("marimepicior"))
            intent1.putExtra("sex",intent.getStringExtra("sex"))
            intent1.putExtra("varsta",intent.getStringExtra("varsta"))
            intent1.putExtra("schiuri",intent.getSerializableExtra("schiuri") as? Produs)
            intent1.putExtra("clapari",Produs("null","",""))

            startActivity(intent1)
        }
    }

    /**
     * Extract boots from database
     */
    private fun preiaClapariDinBD(){
        var sem : Boolean = false
        db.collection("clapari")
                .whereEqualTo("marime",intent.getStringExtra("marimepicior").toString().toInt())
                .whereEqualTo("inchiriat", sem)
                .get()
                .addOnSuccessListener { documents ->
                    if(documents.size() != 0){
                        for(document in documents){
                            val clapari = Produs(document.get("firma").toString(),
                                    document.get("descriere").toString(),
                                    document.id)
                            listaClapari.add(clapari)
                        }
                        initRecyclerView()
                        addDataSet(listaClapari)
                    } else{
                        Toast.makeText(this, "There are no boots available for your foot size.", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener{ exception ->
                    Log.w(TAG,"Error getting documents: ", exception)
                }
    }

    private fun addDataSet(data: ArrayList<Produs>){
        clapariAdapter.submitList(data)
    }

    private fun initRecyclerView(){
        val recycler_view: RecyclerView = findViewById(R.id.recycler_view_clapari)
        recycler_view.layoutManager = LinearLayoutManager(this@ClapariActivity)
        val topSpacingDecoration = TopSpacingItemDecoration(30)
        recycler_view.addItemDecoration(topSpacingDecoration)
        clapariAdapter = ClapariRecyclerAdapter(this)
        recycler_view.adapter = clapariAdapter

    }

    /**
     * If item is clicked then go to boots info activity and pass required data
     */
    override fun onItemClick(position: Int) {
        val clickedItem: Produs = listaClapari[position]
        clapariAdapter.notifyItemChanged(position)
        val intent1 = Intent(this, InfoClapariActivity::class.java)
        intent1.putExtra("inaltime",intent.getStringExtra("inaltime"))
        intent1.putExtra("marimepicior",intent.getStringExtra("marimepicior"))
        intent1.putExtra("sex",intent.getStringExtra("sex"))
        intent1.putExtra("varsta",intent.getStringExtra("varsta"))
        intent1.putExtra("schiuri",intent.getSerializableExtra("schiuri") as? Produs)
        intent1.putExtra("clapari",clickedItem)
        //trimit pt afisare info produs
        intent1.putExtra("firma",clickedItem.firma)
        intent1.putExtra("descriere",clickedItem.descriere)
        startActivity(intent1)
    }
}