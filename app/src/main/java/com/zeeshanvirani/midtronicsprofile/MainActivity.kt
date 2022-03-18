package com.zeeshanvirani.midtronicsprofile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val goToCountries: MaterialButton = findViewById(R.id.countries_button)
        goToCountries.setOnClickListener {
            // Go to Country Selection Activity
            startActivity( Intent(this, CountrySelection::class.java) )
        }
    }
}