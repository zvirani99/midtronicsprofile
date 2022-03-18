package com.zeeshanvirani.midtronicsprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class CountrySelection : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_selection)

        // Initialize Views on Screen
        val backButton: MaterialButton = findViewById(R.id.countryselection_back_button)
        val searchBar: EditText = findViewById(R.id.countryselection_search)
        val countryListRecyclerView: RecyclerView = findViewById(R.id.countryselection_list)

        // Go to last Activity (MainActivity)
        backButton.setOnClickListener{
            onBackPressed()
        }

        // Load all countries from xml file
        val listOfCountries: Array<String> = resources.getStringArray(R.array.countries_array)

        // Set Adapter for RecyclerView to Custom Adapter (CountryListAdapter)
        val adapter = CountryListAdapter( this, listOfCountries )
        countryListRecyclerView.adapter = adapter

        // Set Layout Manager to a new LinearLayoutManager
        countryListRecyclerView.layoutManager = LinearLayoutManager(this)

        // Update List of Countries based on what is entered into the search bar dynamically
        searchBar.addTextChangedListener {
            val tempListOfCountries = mutableListOf<String>()
            for ( i in listOfCountries ) {
                if ( i.uppercase().contains(searchBar.text.toString().uppercase()) ) {
                    tempListOfCountries.add( i )
                }
            }
            adapter.updateDataset( tempListOfCountries.toTypedArray() )
        }

    }
}