package com.example.skirental.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.skirental.databinding.ActivityFirstBinding

/**
 * Use can choose to rent a ski equipemnt or to modify his personal data
 */
class FirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstBinding
    private val TAG = "vlad"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        butoane()
    }

    /**
     * Function to store all the buttons
     * @param buttonRentSkiEquipment User rents ski equipment
     * @param buttonModificareDatePersonale User modifies his personal data
     */
    private fun butoane(){
        binding.buttonRentSkiEquipment.setOnClickListener(){
            val intent1 = Intent(this, SkiuriActivity::class.java)
            intent1.putExtra("inaltime",intent.getStringExtra("inaltime"))
            intent1.putExtra("marimepicior",intent.getStringExtra("marimepicior"))
            intent1.putExtra("sex",intent.getStringExtra("sex"))
            intent1.putExtra("varsta",intent.getStringExtra("varsta"))
            startActivity(intent1);
        }
        binding.buttonModificareDatePersonale.setOnClickListener {
            val intent1 = Intent(this, ModificareDateActivity::class.java)
            intent1.putExtra("email",intent.getStringExtra("email"))
            intent1.putExtra("parola",intent.getStringExtra("parola"))
            startActivity(intent1)
        }
    }
}