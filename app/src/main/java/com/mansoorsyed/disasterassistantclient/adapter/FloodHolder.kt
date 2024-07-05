package com.mansoorsyed.disasterassistantclient.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.disasterassistantclient.R;

class FloodHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var name : TextView? = null
    private var longitude : TextView? = null
    private var latitude : TextView? = null
    private var magnitude: TextView? = null

    public fun getName() : TextView? {
        return name
    }

    public fun getLatitude() : TextView? {
        return latitude
    }

    public fun getLongitude() : TextView? {
        return longitude
    }
    public fun getMagnitude() : TextView? {
        return magnitude
    }

    init {
        name = itemView.findViewById(R.id.floodListItem_name)
        latitude = itemView.findViewById(R.id.floodListItem_latitude)
        longitude = itemView.findViewById(R.id.floodListItem_longitude)
        magnitude = itemView.findViewById(R.id.floodListItem_magnitude)
    }
}