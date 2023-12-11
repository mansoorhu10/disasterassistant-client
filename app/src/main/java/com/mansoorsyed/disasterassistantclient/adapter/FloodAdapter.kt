package com.mansoorsyed.disasterassistantclient.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.mansoorsyed.disasterassistantclient.model.Flood
import com.example.disasterassistantclient.R

class FloodAdapter(floodList: List<Flood?>?) : RecyclerView.Adapter<FloodHolder>() {

    private var floodList : List<Flood?>? = floodList

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : FloodHolder {
        var view : View = LayoutInflater.from(parent.context).inflate(R.layout.list_flood_item, parent, false);

        return FloodHolder(view);
    }

    override fun onBindViewHolder(holder: FloodHolder, position: Int) {
        var flood : Flood? = floodList?.get(position);
        holder.getName()?.text = flood?.name;
        holder.getLocation()?.text = flood?.location;
        holder.getMagnitude()?.text = flood?.magnitude.toString();
    }

    override fun getItemCount(): Int {
        return floodList!!.size;
    }
}