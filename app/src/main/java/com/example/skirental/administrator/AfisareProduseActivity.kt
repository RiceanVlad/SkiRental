package com.example.skirental.administrator

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skirental.R
import com.example.skirental.databinding.ActivityAfisareProduseBinding
import com.example.skirental.miscellaneous.TopSpacingItemDecoration
import com.example.skirental.models.ProdusFinal
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_afisare_produse.*
import kotlinx.android.synthetic.main.confirmare_dialog.view.*

class AfisareProduseActivity : AppCompatActivity(),DeleteRecyclerAdapter.OnItemClickListener {

    private lateinit var binding: ActivityAfisareProduseBinding
    val db = FirebaseFirestore.getInstance()
    private lateinit var deleteAdapter: DeleteRecyclerAdapter
    private var listaProduse = ArrayList<ProdusFinal>()
    private val TAG = "vlad"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAfisareProduseBinding.inflate(layoutInflater)
        setContentView(binding.root)

         preiaProduseDinBD()
    }

    private fun preiaProduseDinBD(){
        db.collection(intent.getStringExtra("tip").toString())
                .get()
                .addOnSuccessListener { documents ->
                    if(documents.size() != 0){
                        for(document in documents){
                            val produs = ProdusFinal(document.get("firma").toString(),
                                    document.get("descriere").toString(),
                                    document.id,
                                    intent.getStringExtra("tip").toString())
                            listaProduse.add(produs)
                        }
                        initRecyclerView()
                        addDataSet(listaProduse)
                    } else{
                        Toast.makeText(this, "There are no products available.", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener{ exception ->
                    Log.w(TAG,"Error getting documents: ", exception)
                }
    }

    private fun addDataSet(data: ArrayList<ProdusFinal>){
        deleteAdapter.submitList(data)
    }

    private fun initRecyclerView(){
        val recycler_view: RecyclerView = findViewById(R.id.recycler_view_delete_products)
        recycler_view.layoutManager = LinearLayoutManager(this@AfisareProduseActivity)
        val topSpacingDecoration = TopSpacingItemDecoration(30)
        recycler_view.addItemDecoration(topSpacingDecoration)
        deleteAdapter = DeleteRecyclerAdapter(this)
        recycler_view.adapter = deleteAdapter

    }

    override fun onItemClick(position: Int) {
        val clickedItem: ProdusFinal = listaProduse[position]
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.confirmare_dialog,null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
            .setTitle("Are you sure you want to delete this product?")
        val mAlertDialog = mBuilder.show()
        mDialogView.textViewFirmaSterge.setText(clickedItem.firma)
        mDialogView.textViewDescriereSterge.setText(clickedItem.descriere)
        mDialogView.buttonConfirmareDa.setOnClickListener {
            db.collection(intent.getStringExtra("tip").toString()).document(clickedItem.imagine) // nu sterg imaginea, doar ca imagine e id pe care trebuie sa-l sterg din firestore
                .delete()
                .addOnSuccessListener {
                    Log.d(TAG, "DocumentSnapshot successfully deleted!")
                    Toast.makeText(this, "Product successfully deleted from the system.", Toast.LENGTH_SHORT).show()
                    listaProduse.removeAt(position)
                    deleteAdapter.notifyItemRemoved(position)
                }
                .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }

            val storage = FirebaseStorage.getInstance()
            val storageRef = storage.reference
            val produsRef = storageRef.child("${intent.getStringExtra("tip")}/${clickedItem.imagine}.jpg")

            produsRef.delete()
                .addOnSuccessListener { Log.d(TAG,"Image successfully deleted.") }
                .addOnFailureListener { Log.d(TAG,"Failed to delete image.") }

            mAlertDialog.dismiss()
        }
        mDialogView.buttonConfirmareNu.setOnClickListener {
            mAlertDialog.dismiss()
        }

    }

}