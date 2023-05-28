package com.example.cutecat.view.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.cutecat.model.categories.CategoriesItem

class CategoriesSpinnerAdapter(private val context: Context, private val dataSource: MutableList<CategoriesItem>) : ArrayAdapter<CategoriesItem>(context, android.R.layout.simple_spinner_item, dataSource) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)
        val item = getItem(position)
        if (item != null) {
            (view as TextView).text = item.name
        }
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getDropDownView(position, convertView, parent)
        val item = getItem(position)
        if (item != null) {
            (view as TextView).text = item.name
        }
        return view
    }
}