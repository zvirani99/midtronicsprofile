package com.zeeshanvirani.midtronicsprofile

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class CountryDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_details)

        // Initialize Views on Screen
        val heading: TextView = findViewById(R.id.countrydetails_heading)
        val capitalText: TextView = findViewById(R.id.countrydetails_capital)
        val populationText: TextView = findViewById(R.id.countrydetails_population)
        val areaText: TextView = findViewById(R.id.countrydetails_area)
        val regionText: TextView = findViewById(R.id.countrydetails_region)
        val subregionText: TextView = findViewById(R.id.countrydetails_subregion)
        val backButton: MaterialButton = findViewById(R.id.countrydetails_back_button)

        // Return to last activity (CountrySelection)
        backButton.setOnClickListener{
            onBackPressed()
        }

        // Get country name from extras in intent. Modify string for api call.
        val extras = intent.extras
        val countryName = extras?.getString("countryname")
        val apiCountry = countryName?.replace(" ", "%20")

        // Set "countrydetails_heading" to Country Name
        heading.text = countryName

        // Create new thread to run api call on
        // Create an HTTP Connection and send GET request
        // API will return one line as a JSONArray that contains one JSONObject
        // Get first JSONObject inside JSONArray and use keys to get values
        // Text on UI is then manipulated using the "runOnUIThread" call
        Thread {
            try {
                val url = URL("https://restcountries.com/v3.1/name/$apiCountry?fields=capital,population,area,region,subregion")
                val connection = url.openConnection()
                BufferedReader(InputStreamReader(connection.getInputStream())).use { bf ->
                    val jsonArr = JSONArray(bf.readLine())
                    val jsonObject = jsonArr.getJSONObject(0)
                    this@CountryDetails.runOnUiThread{
                        var rawStringOfCapitals: String = jsonObject.getString("capital")
                        rawStringOfCapitals = rawStringOfCapitals.substring(1, rawStringOfCapitals.length - 1)
                        rawStringOfCapitals = rawStringOfCapitals.replace("\"", "")

                        val capitalString = "Capital: $rawStringOfCapitals"
                        capitalText.text = capitalString

                        val populationString = "Population: " + jsonObject.getString("population")
                        populationText.text = populationString

                        val areaString = "Area: " + jsonObject.getString("area")
                        areaText.text = areaString

                        val regionString = "Region: " + jsonObject.getString("region")
                        regionText.text = regionString

                        val subregionString = "Subregion: " + jsonObject.getString("subregion")
                        subregionText.text = subregionString
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()

    }
}