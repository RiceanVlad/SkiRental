package com.example.skirental.administrator

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.skirental.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_formular_adaugare.*

class FormularAdaugareActivity : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()
    private val TAG = "vlad"
    lateinit var filepath : Uri

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
            if(intent.getStringExtra("produs").equals("clapari"))
                adaugaClapari()
            if(intent.getStringExtra("produs").equals("bete"))
                adaugaBete()
            if(intent.getStringExtra("produs").equals("casca"))
                adaugaCasca()
            if(intent.getStringExtra("produs").equals("ochelari"))
                adaugaOchelari()

        }
        buttonAlegeImagineProdus.setOnClickListener {
            startFileChooser()
        }
    }

    private fun adaugaOchelari() {
        val ref = db.collection("ochelari")

        var adult = true;
        if(chipCopil.isChecked)
            adult = false

        val data = hashMapOf(
                "firma" to editTextFirma.text.toString(),
                "descriere" to editTextDescriereAdaugareEchipament.text.toString(),
                "inchiriat" to false,
                "adulti" to adult

        )
        Toast.makeText(this, "Goggles registered succesfully.", Toast.LENGTH_SHORT).show()
        val key : String = ref.document().id
        ref.document(key).set(data)
        uploadFile(key,"ochelari")
    }

    private fun adaugaCasca() {
        val ref = db.collection("casti")

        var adult = true;
        if(chipCopil.isChecked)
            adult = false

        val data = hashMapOf(
                "firma" to editTextFirma.text.toString(),
                "descriere" to editTextDescriereAdaugareEchipament.text.toString(),
                "inchiriat" to false,
                "adulti" to adult

        )
        Toast.makeText(this, "Helmet registered succesfully.", Toast.LENGTH_SHORT).show()
        val key : String = ref.document().id
        ref.document(key).set(data)
        uploadFile(key,"casti")
    }

    private fun adaugaBete() {
        val ref = db.collection("bete")

        val data = hashMapOf(
                "firma" to editTextFirma.text.toString(),
                "descriere" to editTextDescriereAdaugareEchipament.text.toString(),
                "lungime" to editTextLungime.text.toString().toInt(),
                "inchiriat" to false

        )
        Toast.makeText(this, "Poles registered succesfully.", Toast.LENGTH_SHORT).show()
        val key : String = ref.document().id
        ref.document(key).set(data)
        uploadFile(key,"bete")
    }

    private fun adaugaClapari() {
        val ref = db.collection("clapari")

        val data = hashMapOf(
                "firma" to editTextFirma.text.toString(),
                "descriere" to editTextDescriereAdaugareEchipament.text.toString(),
                "inchiriat" to false,
                "marime" to editTextMarimeClapar.text.toString().toInt()

        )
        Toast.makeText(this, "Boots registered succesfully.", Toast.LENGTH_SHORT).show()
        val key : String = ref.document().id
        ref.document(key).set(data)
        uploadFile(key,"clapari")
    }

    private fun adaugaSchiuri(){
        val ref = db.collection("schiuri")

        val data = hashMapOf(
            "firma" to editTextFirma.text.toString(),
            "descriere" to editTextDescriereAdaugareEchipament.text.toString(),
            "lungime" to editTextLungime.text.toString().toInt(),
            "inchiriat" to false

        )
        Toast.makeText(this, "Skis registered succesfully.", Toast.LENGTH_SHORT).show()
        val key : String = ref.document().id
        ref.document(key).set(data)
        uploadFile(key,"schiuri")
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

        if(intent.getStringExtra("produs").equals("schiuri")){
            textViewTitluAdaugaProdus.setText("Add Ski's")
            buttonAdaugaProdus.setText("Add Ski's")
        }
        if(intent.getStringExtra("produs").equals("clapari")){
            textViewTitluAdaugaProdus.setText("Add Boots")
            buttonAdaugaProdus.setText("Add Boots")
        }

        if(intent.getStringExtra("produs").equals("bete")){
            textViewTitluAdaugaProdus.setText("Add Poles")
            buttonAdaugaProdus.setText("Add Poles")
        }

        if(intent.getStringExtra("produs").equals("casca")){
            textViewTitluAdaugaProdus.setText("Add Helmet")
            buttonAdaugaProdus.setText("Add Helmet")
        }

        if(intent.getStringExtra("produs").equals("ochelari")){
            textViewTitluAdaugaProdus.setText("Add Goggles")
            buttonAdaugaProdus.setText("Add Goggles")
        }

    }

    private fun uploadFile(key : String, dir : String) {
        if(filepath!=null){
            var pd = ProgressDialog(this)
            pd.setTitle("Uploading")
            pd.show()

            var imageRef = FirebaseStorage.getInstance().reference.child("$dir/$key.jpg")
            imageRef.putFile(filepath)
                .addOnSuccessListener { p0 ->
                    pd.dismiss()
                    Toast.makeText(applicationContext, "File Uploaded", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener{ p0 ->
                    pd.dismiss()
                    Toast.makeText(applicationContext, p0.message, Toast.LENGTH_SHORT).show()
                }
                .addOnProgressListener { p0 ->
                    var progress = (100.0 * p0.bytesTransferred / p0.totalByteCount)
                    pd.setMessage("Uploaded ${progress.toInt()}%")
                }
        }
    }

    private fun startFileChooser() {
        var intent = Intent()
        intent.setType("image/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(intent,"Choose Picture"),111)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==111 && resultCode== Activity.RESULT_OK && data != null){
            filepath = data.data!!
            var bitmap = MediaStore.Images.Media.getBitmap(contentResolver,filepath)
            imageViewImagineProdus.setImageBitmap(bitmap)
        }
    }
}


