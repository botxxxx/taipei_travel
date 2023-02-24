package com.example.travel.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.travel.R
import com.example.travel.api.model.LangType

/**
 * Author: Tom
 * usage: adapter for select transfer branch
 */
class ChooseLanguageAdapter(private var langList: List<LangType>, private val onItemClick: (LangType) -> Unit) :
    RecyclerView.Adapter<ChooseLanguageAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.tv_name)
    }

    fun setData(bankInfoList: List<LangType>) {
        this.langList = bankInfoList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_language, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val langInfo = langList[position]
        viewHolder.textView.text = langInfo.name
        viewHolder.textView.setOnClickListener {
            onItemClick.invoke(langInfo)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = langList.size

}