package com.sibeldogan.notebookproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sibeldogan.mynotebookproject.Notes
import com.sibeldogan.mynotebookproject.databinding.RecyclerRowBinding

class NotebookAdapter(val itemlist: ArrayList<Notes>) :
    RecyclerView.Adapter<NotebookAdapter.NotebookHolder>() {
    class NotebookHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotebookHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotebookHolder(binding)
    }

    override fun onBindViewHolder(holder: NotebookHolder, position: Int) {
        holder.binding.noteLabel.text = itemlist.get(position).note
        holder.binding.notedetailLabel.text = itemlist.get(position).noteDetail

    }

    override fun getItemCount(): Int {
        return itemlist.size
    }

}