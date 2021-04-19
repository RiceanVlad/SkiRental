package com.example.skirental

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skirental.models.Clapari

class ClapariActivity : AppCompatActivity(), ClapariRecyclerAdapter.OnItemClickListener {

    private lateinit var clapariAdapter: ClapariRecyclerAdapter
    private var listaClapari = DataSource.createDataSetClapari()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clapari)

        initRecyclerView()
        addDataSet(listaClapari)
    }

    private fun addDataSet(data: ArrayList<Clapari>){
        clapariAdapter.submitList(data)
    }

    private fun initRecyclerView(){
        val recycler_view: RecyclerView = findViewById(R.id.recycler_view_clapari)
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
//        val intent = Intent(this, InfoClapariActivity::class.java)
//        intent.putExtra("titlu",clickedItem.title)
//        intent.putExtra("username",clickedItem.username)
//        startActivity(intent)
    }
}