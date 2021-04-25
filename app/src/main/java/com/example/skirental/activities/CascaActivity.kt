package com.example.skirental.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skirental.R
import com.example.skirental.adapters.CascaRecyclerAdapter
import com.example.skirental.data.DataSource
import com.example.skirental.infoactivities.InfoCascaActivity
import com.example.skirental.miscellaneous.TopSpacingItemDecoration
import com.example.skirental.models.Casca

class CascaActivity : AppCompatActivity(), CascaRecyclerAdapter.OnItemClickListener {

    private lateinit var cascaAdapter: CascaRecyclerAdapter
    private var listaCasca = DataSource.createDataSetCasca()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_casca)

        initRecyclerView()
        addDataSet(listaCasca)
    }

    private fun addDataSet(data: ArrayList<Casca>){
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
        val clickedItem: Casca = listaCasca[position]
        cascaAdapter.notifyItemChanged(position)
        val intent = Intent(this, InfoCascaActivity::class.java)
        intent.putExtra("titlu",clickedItem.title)
        intent.putExtra("username",clickedItem.username)
        startActivity(intent)
    }
}