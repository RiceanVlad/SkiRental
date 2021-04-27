package com.example.skirental.administrator

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.skirental.R
import kotlinx.android.synthetic.main.activity_delete_products.*

class DeleteProductsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_products)

        butoane()
    }

    private fun butoane() {
        buttonStergeSchiuri.setOnClickListener {
            val intent = Intent(this,AfisareProduseActivity::class.java)
            intent.putExtra("tip","schiuri")
            startActivity(intent)
        }
         buttonStergeClapari.setOnClickListener {
            val intent = Intent(this,AfisareProduseActivity::class.java)
            intent.putExtra("tip","clapari")
            startActivity(intent)
        }
         buttonStergeBete.setOnClickListener {
            val intent = Intent(this,AfisareProduseActivity::class.java)
            intent.putExtra("tip","bete")
            startActivity(intent)
        }
         buttonStergeCasca.setOnClickListener {
            val intent = Intent(this,AfisareProduseActivity::class.java)
            intent.putExtra("tip","casti")
            startActivity(intent)
        }
         buttonStergeOchelari.setOnClickListener {
            val intent = Intent(this,AfisareProduseActivity::class.java)
            intent.putExtra("tip","ochelari")
            startActivity(intent)
        }

    }
}