package com.example.tictactoeapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.tictactoeapp.R
import com.example.tictactoeapp.model.History

class HistoryAdapter(private val context: Context) :
    RecyclerView.Adapter<HistoryAdapter.ItemViewHolder>() {
    private var historyList = emptyList<History>()

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val winningplayer_txt: TextView = itemView.findViewById<TextView>(R.id.win_txt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = historyList[position]

        holder.winningplayer_txt.text = currentItem.winningplayer
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    fun setData(history: List<History>) {
        this.historyList = history
        notifyDataSetChanged()
    }
}