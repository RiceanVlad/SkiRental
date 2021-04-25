package com.example.skirental.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skirental.R
import com.example.skirental.adapters.ProdusRecyclerAdapter
import com.example.skirental.data.DataSource
import com.example.skirental.miscellaneous.TopSpacingItemDecoration
import com.example.skirental.models.Produs

class ProdusActivity : AppCompatActivity(), ProdusRecyclerAdapter.OnItemClickListener {

    private lateinit var produsAdapter: ProdusRecyclerAdapter
    private var listaProdus = DataSource.createDataSetProdus()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produs)

        initRecyclerView()
        addDataSet(listaProdus)
    }

    private fun addDataSet(data: ArrayList<Produs>){
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
        Toast.makeText(this, "Item $position clicked!", Toast.LENGTH_SHORT).show()
        val clickedItem: Produs = listaProdus[position]
        produsAdapter.notifyItemChanged(position)

    }
}