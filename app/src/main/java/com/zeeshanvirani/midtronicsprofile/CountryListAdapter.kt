package com.zeeshanvirani.midtronicsprofile

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class CountryListAdapter(private val context: Context, private var listOfCountries: Array<String>) : RecyclerView.Adapter<CountryListAdapter.ViewHolder>() {

    // Define objects within layout
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val button: MaterialButton = view.findViewById(R.id.countryitem_button)
    }

    // Create new view for each item from layout
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.country_button, viewGroup, false)
        return ViewHolder(view)
    }

    // Replace the text of country_button and set OnClickListener
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.button.text = listOfCountries[position]
        viewHolder.button.setOnClickListener{
            // Open country details activity
            val i = Intent( context, CountryDetails::class.java)
            i.putExtra("countryname", viewHolder.button.text)
            context.startActivity(i)
        }
    }

    // Return the size of listOfCountries
    override fun getItemCount() = listOfCountries.size

    // Function takes in an array to use as the new dataset for the recyclerview
    @SuppressLint("NotifyDataSetChanged")
    fun updateDataset(newData: Array<String> ) {
        listOfCountries = newData
        this.notifyDataSetChanged()
    }

}
