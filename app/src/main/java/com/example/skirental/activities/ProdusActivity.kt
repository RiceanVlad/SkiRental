package com.example.skirental.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skirental.R
import com.example.skirental.adapters.ProdusRecyclerAdapter
import com.example.skirental.miscellaneous.TopSpacingItemDecoration
import com.example.skirental.models.Produs
import com.example.skirental.models.ProdusFinal
import kotlinx.android.synthetic.main.activity_produs.*

/**
 * Recycler view to store the shopping basket
 */
class ProdusActivity : AppCompatActivity(), ProdusRecyclerAdapter.OnItemClickListener {

    private lateinit var produsAdapter: ProdusRecyclerAdapter
    private var listaProdus = ArrayList<ProdusFinal>()
    private lateinit var p : Produs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produs)

        adaugaProduseInCos()
        initRecyclerView()
        addDataSet(listaProdus)
        butoane()
    }

    /**
     * Buttons in this activity
     * @param buttonPlata payment
     */
    private fun butoane() {
        buttonPlata.setOnClickListener {
            val intent1 = Intent(this@ProdusActivity,PlataActivity::class.java)
            startActivity(intent1)
        }
    }

    /**
     * Add products chosen in a list
     */
    private fun adaugaProduseInCos() {
        p = intent.getSerializableExtra("schiuri") as Produs
        if(!p.firma.equals("null"))
            listaProdus.add(ProdusFinal(p.firma,p.descriere,p.imagine,"schiuri"))

        p = intent.getSerializableExtra("clapari") as Produs
        if(!p.firma.equals("null"))
            listaProdus.add(ProdusFinal(p.firma,p.descriere,p.imagine,"clapari"))

        p = intent.getSerializableExtra("bete") as Produs
        if(!p.firma.equals("null"))
            listaProdus.add(ProdusFinal(p.firma,p.descriere,p.imagine,"bete"))

        p = intent.getSerializableExtra("casca") as Produs
        if(!p.firma.equals("null"))
            listaProdus.add(ProdusFinal(p.firma,p.descriere,p.imagine,"casti"))

        p = intent.getSerializableExtra("ochelari") as Produs
        if(!p.firma.equals("null"))
            listaProdus.add(ProdusFinal(p.firma,p.descriere,p.imagine,"ochelari"))


    }

    private fun addDataSet(data: ArrayList<ProdusFinal>){
        produsAdapter.submitList(data)
    }

    private fun initRecyclerView(){
        val recycler_view: RecyclerView = findViewById(R.id.recycler_view_produs)
        recycler_view.layoutManager = LinearLayoutManager(this@ProdusActivity)
        val topSpacingDecoration = TopSpacingItemDecoration(30)
        recycler_view.addItemDecoration(topSpacingDecoration)
        produsAdapter = ProdusRecyclerAdapter(this)
        recycler_view.adapter = produsAdapter

    }

    override fun onItemClick(position: Int) {
        val clickedItem: ProdusFinal = listaProdus[position]
        produsAdapter.notifyItemChanged(position)

    }
}