package com.example.skirental

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skirental.models.PerecheSki

class SkiuriActivity : AppCompatActivity(), SkiuriRecyclerAdapter.OnItemClickListener {

    private lateinit var skiuriAdapter: SkiuriRecyclerAdapter
    private var listaSkiuri = DataSource.createDataSetSkiuri()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skiuri)

        initRecyclerView()
        addDataSet(listaSkiuri)
    }

    private fun addDataSet(data: ArrayList<PerecheSki>){
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
        val clickedItem: PerecheSki = listaSkiuri[position]
        skiuriAdapter.notifyItemChanged(position)
        clickedItem.title = "Emaaaa"
//        val intent = Intent(this, FirstActivity::class.java)
//        startActivity(intent)
    }
}