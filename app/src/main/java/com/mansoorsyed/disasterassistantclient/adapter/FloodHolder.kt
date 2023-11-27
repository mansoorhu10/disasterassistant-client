package com.mansoorsyed.disasterassistantclient.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.disasterassistantclient.R;

class FloodHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var name : TextView? = null
    private var location : TextView? = null
    private var magnitude: TextView? = null

    public fun getName() : TextView? {
        return name
    }

    public fun getLocation() : TextView? {
        return location
    }

    public fun getMagnitude() : TextView? {
        return magnitude
    }

    init {
        name = itemView.findViewById(R.id.floodListItem_name)
        location = itemView.findViewById(R.id.floodListItem_location)
        magnitude = itemView.findViewById(R.id.floodListItem_magnitude)
    }
}