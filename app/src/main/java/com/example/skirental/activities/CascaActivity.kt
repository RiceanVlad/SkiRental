package com.example.skirental.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skirental.R
import com.example.skirental.adapters.CascaRecyclerAdapter
import com.example.skirental.infoactivities.InfoCascaActivity
import com.example.skirental.miscellaneous.TopSpacingItemDecoration
import com.example.skirental.models.Produs
import com.google.firebase.firestore.FirebaseFirestore

class CascaActivity : AppCompatActivity(), CascaRecyclerAdapter.OnItemClickListener {

    private lateinit var cascaAdapter: CascaRecyclerAdapter
    private var listaCasti = ArrayList<Produs>()
    val db = FirebaseFirestore.getInstance()
    private val TAG = "vlad"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_casca)

        preiaCastiDinBD()
    }

    private fun preiaCastiDinBD(){
        val sem : Boolean = false
        var adult : Boolean
        val varsta = intent.getStringExtra("varsta").toString().toInt()

        if(varsta<18)
            adult = false
        else
            adult = true

        db.collection("casti")
                .whereEqualTo("inchiriat", sem)
                .whereEqualTo("adulti",adult)
                .get()
                .addOnSuccessListener { documents ->
                    if(documents.size() != 0){
                        for(document in documents){
                            val casca = Produs(document.get("firma").toString(),
                                    document.get("descriere").toString(),
                                    document.id)
                            listaCasti.add(casca)
                        }
                        initRecyclerView()
                        addDataSet(listaCasti)
                    } else{
                        Toast.makeText(this, "There are no helmets available.", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener{ exception ->
                    Log.w(TAG,"Error getting documents: ", exception)
                }
    }

    private fun addDataSet(data: ArrayList<Produs>){
        cascaAdapter.submitList(data)
    }

    private fun initRecyclerView(){
        val recycler_view: RecyclerView = findViewById(R.id.recycler_view_casca)
        recycler_view.layoutManager = LinearLayoutManager(this@CascaActivity)
        val topSpacingDecoration = TopSpacingItemDecoration(30)
        recycler_view.addItemDecoration(topSpacingDecoration)
        cascaAdapter = CascaRecyclerAdapter(this)
        recycler_view.adapter = cascaAdapter

    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position clicked!", Toast.LENGTH_SHORT).show()
        val clickedItem: Produs = listaCasti[position]
        cascaAdapter.notifyItemChanged(position)
        val intent1 = Intent(this, InfoCascaActivity::class.java)
        intent1.putExtra("inaltime",intent.getStringExtra("inaltime"))
        intent1.putExtra("marimepicior",intent.getStringExtra("marimepicior"))
        intent1.putExtra("sex",intent.getStringExtra("sex"))
        intent1.putExtra("varsta",intent.getStringExtra("varsta"))
        intent1.putExtra("schiuri",intent.getSerializableExtra("schiuri") as? Produs)
        intent1.putExtra("clapari",intent.getSerializableExtra("clapari") as? Produs)
        intent1.putExtra("bete",intent.getSerializableExtra("bete") as? Produs)
        intent1.putExtra("casca",clickedItem)
        startActivity(intent1)
    }
}