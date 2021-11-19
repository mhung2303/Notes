package com.carter.takenotes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.carter.takenotes.R
import com.carter.takenotes.entities.Notes
import kotlinx.android.synthetic.main.item_rv_notes.view.*

class NotesAdapter(val arrayList: List<Notes>) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_notes,parent,false)
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.itemView.tvTitle.text = arrayList[position].title
        holder.itemView.tvDesc.text = arrayList[position].noteText
        holder.itemView.tvDateTime.text = arrayList[position].dateTime
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class NotesViewHolder(view: View) : RecyclerView.ViewHolder(view){

    }
}