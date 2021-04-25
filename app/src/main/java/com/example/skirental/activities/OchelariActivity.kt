package com.example.skirental.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skirental.R
import com.example.skirental.adapters.OchelariRecyclerAdapter
import com.example.skirental.infoactivities.InfoOchelariActivity
import com.example.skirental.miscellaneous.TopSpacingItemDecoration
import com.example.skirental.models.Ochelari
import com.google.firebase.firestore.FirebaseFirestore

class OchelariActivity : AppCompatActivity(), OchelariRecyclerAdapter.OnItemClickListener {

    private lateinit var ochelariAdapter: OchelariRecyclerAdapter
    private var listaOchelari = ArrayList<Ochelari>()
    val db = FirebaseFirestore.getInstance()
    private val TAG = "vlad"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ochelari)

        preiaOchelariDinBD()
    }

    private fun preiaOchelariDinBD(){
        val sem : Boolean = false
        var adult : Boolean
        val varsta = intent.getStringExtra("varsta").toString().toInt()

        if(varsta<18)
            adult = false
        else
            adult = true

        db.collection("ochelari")
                .whereEqualTo("inchiriat", sem)
                .whereEqualTo("adulti",adult)
                .get()
                .addOnSuccessListener { documents ->
                    if(documents.size() != 0){
                        for(document in documents){
                            val ochelari = Ochelari(document.get("firma").toString(),
                                    document.get("descriere").toString(),
                                    document.id)
                            listaOchelari.add(ochelari)
                        }
                        initRecyclerView()
                        addDataSet(listaOchelari)
                    } else{
                        Toast.makeText(this, "There are no goggles available.", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener{ exception ->
                    Log.w(TAG,"Error getting documents: ", exception)
                }
    }


    private fun addDataSet(data: ArrayList<Ochelari>){
        ochelariAdapter.submitList(data)
    }

    private fun initRecyclerView(){
        val recycler_view: RecyclerView = findViewById(R.id.recycler_view_ochelari)
        recycler_view.layoutManager = LinearLayoutManager(this@OchelariActivity)
        val topSpacingDecoration = TopSpacingItemDecoration(30)
        recycler_view.addItemDecoration(topSpacingDecoration)
        ochelariAdapter = OchelariRecyclerAdapter(this)
        recycler_view.adapter = ochelariAdapter

    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position clicked!", Toast.LENGTH_SHORT).show()
        val clickedItem: Ochelari = listaOchelari[position]
        ochelariAdapter.notifyItemChanged(position)
        val intent1 = Intent(this, InfoOchelariActivity::class.java)
        intent1.putExtra("inaltime",intent.getStringExtra("inaltime"))
        intent1.putExtra("marimepicior",intent.getStringExtra("marimepicior"))
        intent1.putExtra("sex",intent.getStringExtra("sex"))
        intent1.putExtra("varsta",intent.getStringExtra("varsta"))
        startActivity(intent1)
    }
}