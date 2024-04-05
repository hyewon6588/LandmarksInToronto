package com.example.landmarks_in_toronto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

internal class LandmarkAdapter(private var itemsList:List<String>, private val listener: OnItemClickListener):
    RecyclerView.Adapter<LandmarkAdapter.MyViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(landmarkType: String)
    }

    //
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view),View.OnClickListener {
        var itemTextView: TextView = view.findViewById(R.id.itemTextView)
        private var currentItem:String=""

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(item: String) {
            currentItem=item
            itemTextView.text = item
        }

        override fun onClick(view: View) {
            listener.onItemClick(currentItem)
        }
    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.landmark_item, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList[position]
        holder.bind(item)
    }
    override fun getItemCount(): Int {
        return itemsList.size
    }
}