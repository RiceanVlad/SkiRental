package com.example.skirental.administrator

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.skirental.R
import kotlinx.android.synthetic.main.activity_administrator.*

/**
 * Administrator can add equipment, delete equipment or scan equipment
 */
class AdministratorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administrator)

        butoane()
    }

    /**
     * Buttons in this activity
     * @param buttonAdaugaEchipament add equipment
     * @param buttonStergeEchipament delete equipment
     * @param buttonReturnareProdus return equipment
     */
    private fun butoane() {
        buttonAdaugaEchipament.setOnClickListener{
            val intent = Intent(this,AdaugaEchipamentActivity::class.java)
            startActivity(intent)
        }
        buttonStergeEchipament.setOnClickListener {
            val intent = Intent(this,DeleteProductsActivity::class.java)
            startActivity(intent)
        }
        buttonReturnareProdus.setOnClickListener {
            val intent = Intent(this,CodeScanerActivity::class.java)
            startActivity(intent)
        }
    }
}