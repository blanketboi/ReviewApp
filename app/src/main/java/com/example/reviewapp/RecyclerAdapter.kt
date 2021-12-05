package com.example.reviewapp

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items : List<RestaurantModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val info = ArrayList<RestaurantModel>()[position]

    }

    override fun getItemCount(): Int {
        return items.size
    }

//    class RestaurantViewHolder constructor(itemView : View) : RecyclerView.ViewHolder(itemView) {
//
//        val restImage = itemView.restImage
//        val restTitle = itemView.restTitle
//        val restRating = itemView.restRating
//        val cuisine = itemView.cuisine
//
//        fun bind(restaurantModel: RestaurantModel) {
//
//            restTitle.setText()
//        }
//    }
}