package com.example.skirental.administrator

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.skirental.R
import kotlinx.android.synthetic.main.activity_adauga_echipament.*

/**
 * Choose what type of equipment to add in the database
 */
class AdaugaEchipamentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adauga_echipament)

        butoane()
    }

    /**
     * Buttons in this activity
     * @param buttonAdaugaSchiuri add skis
     * @param buttonAdaugaBete add poles
     * @param buttonAdaugaCasti add helmet
     * @param buttonAdaugaOchelari add goggles
     * @param buttonAdaugaClapari add boots
     */
    private fun butoane() { // 1 - schiuri + bete
        // 2 - ochelari + casca
        // 3 - clapari
        buttonAdaugaSchiuri.setOnClickListener{
            val intent = Intent(this,FormularAdaugareActivity::class.java)
            intent.putExtra("clasificare","1")
            intent.putExtra("produs", "schiuri")
            startActivity(intent)
        }
        buttonAdaugaBete.setOnClickListener {
            val intent = Intent(this,FormularAdaugareActivity::class.java)
            intent.putExtra("clasificare","1")
            intent.putExtra("produs", "bete")
            startActivity(intent)
        }
        buttonAdaugaCasti.setOnClickListener {
            val intent = Intent(this,FormularAdaugareActivity::class.java)
            intent.putExtra("clasificare","2")
            intent.putExtra("produs", "casca")
            startActivity(intent)
        }
        buttonAdaugaOchelari.setOnClickListener {
            val intent = Intent(this,FormularAdaugareActivity::class.java)
            intent.putExtra("clasificare","2")
            intent.putExtra("produs", "ochelari")
            startActivity(intent)
        }
        buttonAdaugaClapari.setOnClickListener {
            val intent = Intent(this,FormularAdaugareActivity::class.java)
            intent.putExtra("clasificare","3")
            intent.putExtra("produs", "clapari")
            startActivity(intent)
        }
    }
}