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
import com.example.skirental.models.Clapari
import com.google.firebase.firestore.FirebaseFirestore

class ClapariActivity : AppCompatActivity(), ClapariRecyclerAdapter.OnItemClickListener {

    private lateinit var clapariAdapter: ClapariRecyclerAdapter
    private var listaClapari = ArrayList<Clapari>()
    val db = FirebaseFirestore.getInstance()
    private val TAG = "vlad"
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clapari)

        preiaClapariDinBD()

    }

    private fun preiaClapariDinBD(){
        var sem : Boolean = false
        db.collection("clapari")
                .whereEqualTo("marime",intent.getStringExtra("marimepicior").toString().toInt())
                .whereEqualTo("inchiriat", sem)
                .get()
                .addOnSuccessListener { documents ->
                    if(documents.size() != 0){
                        for(document in documents){
                            val clapari = Clapari(document.get("firma").toString(),
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

    private fun addDataSet(data: ArrayList<Clapari>){
        clapariAdapter.submitList(data)
    }

    private fun initRecyclerView(){
        val recycler_view: RecyclerView = findViewById(R.id.recycler_view_casca)
        recycler_view.layoutManager = LinearLayoutManager(this@ClapariActivity)
        val topSpacingDecoration = TopSpacingItemDecoration(30)
        recycler_view.addItemDecoration(topSpacingDecoration)
        clapariAdapter = ClapariRecyclerAdapter(this)
        recycler_view.adapter = clapariAdapter

    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position clicked!", Toast.LENGTH_SHORT).show()
        val clickedItem: Clapari = listaClapari[position]
        clapariAdapter.notifyItemChanged(position)
        val intent1 = Intent(this, InfoClapariActivity::class.java)
        intent1.putExtra("inaltime",intent.getStringExtra("inaltime"))
        intent1.putExtra("marimepicior",intent.getStringExtra("marimepicior"))
        intent1.putExtra("sex",intent.getStringExtra("sex"))
        intent1.putExtra("varsta",intent.getStringExtra("varsta"))
        startActivity(intent1)
    }
}