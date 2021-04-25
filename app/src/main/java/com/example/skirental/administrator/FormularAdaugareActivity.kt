package com.example.skirental.administrator

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.skirental.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_formular_adaugare.*

class FormularAdaugareActivity : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()

    private val TAG = "vlad"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formular_adaugare)

        seteazaVizibilitate()

        butoane()

    }

    private fun butoane() {
        buttonAdaugaProdus.setOnClickListener {
            if(intent.getStringExtra("produs").equals("schiuri"))
                adaugaSchiuri()
        }
    }

    private fun adaugaSchiuri(){
        val ref = db.collection("schiuri")

        var adult : Boolean = true
        if(chipCopil.isChecked)
            adult = false

        val data = hashMapOf(
            "firma" to editTextFirma.text.toString(),
            "descriere" to editTextDescriereAdaugareEchipament.text.toString(),
            "lungime" to editTextLungime.text.toString().toInt(),
            "inchiriat" to false

        )
        Toast.makeText(this, "Skis registrated succesfully.", Toast.LENGTH_SHORT).show()
        ref.document().set(data)
    }

    private fun seteazaVizibilitate() { // 1 - schiuri + bete
        // 2 - ochelari + casca
        // 3 - clapari
        val clasificare = intent.getStringExtra("clasificare")

        if(clasificare.equals("1")){
            editTextLungime.visibility = View.VISIBLE
            chipGroupCascaOchelari.visibility = View.INVISIBLE
            editTextMarimeClapar.visibility = View.INVISIBLE
        }
        if(clasificare.equals("2")){
            chipGroupCascaOchelari.visibility = View.VISIBLE
            editTextMarimeClapar.visibility = View.INVISIBLE
            editTextLungime.visibility = View.INVISIBLE
        }
        if(clasificare.equals("3")){
            editTextMarimeClapar.visibility = View.VISIBLE
            editTextLungime.visibility = View.INVISIBLE
            chipGroupCascaOchelari.visibility = View.INVISIBLE
        }
    }
}


