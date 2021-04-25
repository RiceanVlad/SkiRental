package com.example.skirental.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skirental.R
import com.example.skirental.adapters.BeteRecyclerAdapter
import com.example.skirental.data.DataSource
import com.example.skirental.infoactivities.InfoBeteActivity
import com.example.skirental.miscellaneous.TopSpacingItemDecoration
import com.example.skirental.models.Bete

class BeteActivity : AppCompatActivity(), BeteRecyclerAdapter.OnItemClickListener {

    private lateinit var beteAdapter: BeteRecyclerAdapter
    private var listaBete = DataSource.createDataSetBete()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bete)

        initRecyclerView()
        addDataSet(listaBete)
    }

    private fun addDataSet(data: ArrayList<Bete>){
        beteAdapter.submitList(data)
    }

    private fun initRecyclerView(){
        val recycler_view: RecyclerView = findViewById(R.id.recycler_view_casca)
        recycler_view.layoutManager = LinearLayoutManager(this@BeteActivity)
        val topSpacingDecoration = TopSpacingItemDecoration(30)
        recycler_view.addItemDecoration(topSpacingDecoration)
        beteAdapter = BeteRecyclerAdapter(this)
        recycler_view.adapter = beteAdapter

    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position clicked!", Toast.LENGTH_SHORT).show()
        val clickedItem: Bete = listaBete[position]
        beteAdapter.notifyItemChanged(position)
        val intent = Intent(this, InfoBeteActivity::class.java)
        intent.putExtra("titlu",clickedItem.title)
        intent.putExtra("username",clickedItem.username)
        startActivity(intent)
    }
}