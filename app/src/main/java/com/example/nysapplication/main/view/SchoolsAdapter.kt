package com.example.nysapplication.main.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nysapplication.R
import com.example.nysapplication.main.model.NYCDatabase
import com.example.nysapplication.main.model.SchoolsModel


class SchoolsAdapter(private val mList: List<SchoolsModel>): RecyclerView.Adapter<SchoolsAdapter.ViewHolder>() {

    var db = NYCDatabase
    var onItemClick: ((SchoolsModel) -> Unit)? = null
    var itemsViewModel: SchoolsModel = db.schoolsModel



    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.school_item, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        itemsViewModel = mList[position]

        // sets the text to the textview from our itemHolder class
        holder.schoolTitle.text = itemsViewModel.school_name
        holder.schoolOpp.text = itemsViewModel.academicopportunities1
        holder.schoolBoro.text = itemsViewModel.borough




    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val schoolTitle: TextView = itemView.findViewById(R.id.school_title)
        val schoolBoro: TextView = itemView.findViewById(R.id.school_boro)
        val schoolOpp: TextView = itemView.findViewById(R.id.school_opp_desc)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(mList[adapterPosition])
            }
        }

    }
}