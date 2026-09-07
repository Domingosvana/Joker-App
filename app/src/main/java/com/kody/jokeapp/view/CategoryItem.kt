package com.kody.jokeapp.view

import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.kody.jokeapp.R
import com.kody.jokeapp.model.Category
import com.google.android.material.card.MaterialCardView
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class CategoryItem(val category: Category) : Item<CategoryItem.CategoryViewHolder>() {

    class CategoryViewHolder(view: View) : GroupieViewHolder(view)

    override fun createViewHolder(itemView: View) = CategoryViewHolder(itemView)

    override fun bind(viewHolder: CategoryViewHolder, position: Int) {
        viewHolder.itemView.findViewById<TextView>(R.id.txt_category).text = category.name
        val cardView = viewHolder.itemView.findViewById<MaterialCardView>(R.id.container_category)
        cardView.setCardBackgroundColor(category.bgcolor.toInt())
    }

    override fun getLayout(): Int {
        return R.layout.item_category
    }
}
