package com.carter.takenotes.adapter

import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.carter.takenotes.R
import com.carter.takenotes.entities.Notes
import kotlinx.android.synthetic.main.item_rv_notes.view.*

class NotesAdapter(private val arrList: List<Notes>) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_notes,parent,false)
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int)
    {
        holder.itemView.tvTitle.text = arrList[position].title
        holder.itemView.tvDesc.text = arrList[position].noteText
        holder.itemView.tvDateTime.text = arrList[position].dateTime

        if(arrList[position].color != null)
        {
            holder.itemView.cardView.setCardBackgroundColor(Color.parseColor(arrList[position].color))
        }

        if(arrList[position].imgPath != null)
        {
            holder.itemView.imgNote.setImageBitmap(BitmapFactory.decodeFile(arrList[position].imgPath))
            holder.itemView.imgNote.visibility = View.VISIBLE
        }
        else
        {
            holder.itemView.imgNote.visibility = View.GONE
        }

        if(arrList[position].webLink != null)
        {
            holder.itemView.tvWebLink.text = arrList[position].webLink
            holder.itemView.tvWebLink.visibility = View.VISIBLE
        }
        else
        {
            holder.itemView.tvWebLink.visibility = View.GONE
        }

    }

    override fun getItemCount(): Int {
        return arrList.size
    }

    class NotesViewHolder(view: View) : RecyclerView.ViewHolder(view)

}