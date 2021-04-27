package com.example.skirental.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skirental.R
import com.example.skirental.adapters.BeteRecyclerAdapter
import com.example.skirental.infoactivities.InfoBeteActivity
import com.example.skirental.miscellaneous.TopSpacingItemDecoration
import com.example.skirental.models.Produs
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_bete.*

class BeteActivity : AppCompatActivity(), BeteRecyclerAdapter.OnItemClickListener {

    private lateinit var beteAdapter: BeteRecyclerAdapter
    private var listaBete = ArrayList<Produs>()
    val db = FirebaseFirestore.getInstance()
    private val TAG = "vlad"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bete)

        preiaBeteDinBD()
    }

    private fun preiaBeteDinBD(){
        var sem : Boolean = false
        db.collection("bete")
                .whereLessThanOrEqualTo("lungime",intent.getStringExtra("inaltime").toString().toInt()-25)
                .whereGreaterThanOrEqualTo("lungime",intent.getStringExtra("inaltime").toString().toInt()-45)
                .whereEqualTo("inchiriat", sem)
                .get()
                .addOnSuccessListener { documents ->
                    if(documents.size() != 0){
                        for(document in documents){
                            val bete = Produs(document.get("firma").toString(),
                                    document.get("descriere").toString(),
                                    document.id)
                            listaBete.add(bete)
                        }
                        initRecyclerView()
                        addDataSet(listaBete)
                    } else{
                        Toast.makeText(this, "There are no poles available for your height.", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener{ exception ->
                    Log.w(TAG,"Error getting documents: ", exception)
                }
    }

    private fun addDataSet(data: ArrayList<Produs>){
        beteAdapter.submitList(data)
    }

    private fun initRecyclerView(){
        val recycler_view: RecyclerView = findViewById(R.id.recycler_view_bete)
        recycler_view.layoutManager = LinearLayoutManager(this@BeteActivity)
        val topSpacingDecoration = TopSpacingItemDecoration(30)
        recycler_view.addItemDecoration(topSpacingDecoration)
        beteAdapter = BeteRecyclerAdapter(this)
        recycler_view.adapter = beteAdapter

    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position clicked!", Toast.LENGTH_SHORT).show()
        val clickedItem: Produs = listaBete[position]
        beteAdapter.notifyItemChanged(position)
        val intent1 = Intent(this, InfoBeteActivity::class.java)
        intent1.putExtra("inaltime",intent.getStringExtra("inaltime"))
        intent1.putExtra("marimepicior",intent.getStringExtra("marimepicior"))
        intent1.putExtra("sex",intent.getStringExtra("sex"))
        intent1.putExtra("varsta",intent.getStringExtra("varsta"))
        intent1.putExtra("schiuri",intent.getSerializableExtra("schiuri") as? Produs)
        intent1.putExtra("clapari",intent.getSerializableExtra("clapari") as? Produs)
        intent1.putExtra("bete",clickedItem)
        //trimit pt afisare info produs
        intent1.putExtra("firma",clickedItem.firma)
        intent1.putExtra("descriere",clickedItem.descriere)
        startActivity(intent1)
    }
}