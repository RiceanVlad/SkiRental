package com.example.skirental.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skirental.R
import com.example.skirental.adapters.SkiuriRecyclerAdapter
import com.example.skirental.databinding.ActivitySkiuriBinding
import com.example.skirental.infoactivities.InfoSkiuriActivity
import com.example.skirental.miscellaneous.TopSpacingItemDecoration
import com.example.skirental.models.Produs
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class SkiuriActivity : AppCompatActivity(), SkiuriRecyclerAdapter.OnItemClickListener {

    private lateinit var binding: ActivitySkiuriBinding
    private lateinit var skiuriAdapter: SkiuriRecyclerAdapter
    private var listaSkiuri = ArrayList<Produs>()
    val db = FirebaseFirestore.getInstance()
    private val TAG = "vlad"
    val storage = FirebaseStorage.getInstance()
    val storageRef = storage.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySkiuriBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preiaSchiuriDinBD()


    }

    private fun preiaSchiuriDinBD(){
        var sem : Boolean = false
        db.collection("schiuri")
                .whereLessThanOrEqualTo("lungime",intent.getStringExtra("inaltime").toString().toInt()+5)
                .whereGreaterThanOrEqualTo("lungime",intent.getStringExtra("inaltime").toString().toInt()-25)
                .whereEqualTo("inchiriat", sem)
                .get()
                .addOnSuccessListener { documents ->
                    if(documents.size() != 0){
                        for(document in documents){
                            val produs = Produs(document.get("firma").toString(),
                                    document.get("descriere").toString(),
                                    document.id)
                            listaSkiuri.add(produs)
                        }
                        initRecyclerView()
                        addDataSet(listaSkiuri)
                    } else{
                        Toast.makeText(this, "There are no skis available for your height.", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener{ exception ->
                    Log.w(TAG,"Error getting documents: ", exception)
                }
    }

    private fun addDataSet(data: ArrayList<Produs>){
        skiuriAdapter.submitList(data)
    }

    private fun initRecyclerView(){
        val recycler_view: RecyclerView = findViewById(R.id.recycler_view)
        recycler_view.layoutManager = LinearLayoutManager(this@SkiuriActivity)
        val topSpacingDecoration = TopSpacingItemDecoration(30)
        recycler_view.addItemDecoration(topSpacingDecoration)
        skiuriAdapter = SkiuriRecyclerAdapter(this)
        recycler_view.adapter = skiuriAdapter

    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position clicked!", Toast.LENGTH_SHORT).show()
        val clickedItem: Produs = listaSkiuri[position]
        skiuriAdapter.notifyItemChanged(position)
        val intent1 = Intent(this, InfoSkiuriActivity::class.java)
        intent1.putExtra("inaltime",intent.getStringExtra("inaltime"))
        intent1.putExtra("marimepicior",intent.getStringExtra("marimepicior"))
        intent1.putExtra("sex",intent.getStringExtra("sex"))
        intent1.putExtra("varsta",intent.getStringExtra("varsta"))
        intent1.putExtra("schiuri",clickedItem)
        startActivity(intent1)
    }
}

