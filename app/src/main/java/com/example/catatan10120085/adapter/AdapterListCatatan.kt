package com.example.catatan10120085.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catatan10120085.OnClickItemListener
import com.example.catatan10120085.database.Note
import com.example.catatan10120085.databinding.AdapterNoteBinding
//10120085 - Dion Cahyan IF-2
class AdapterListCatatan(val context: Context, val listener: OnClickItemListener) : RecyclerView.Adapter<AdapterListCatatan.ViewHolder>() {
    private var  list = arrayListOf<Note>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return  ViewHolder(
            AdapterNoteBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.judul.text = item.judul
        holder.category.text = item.kategori
        holder.date.text = item.date
        holder.info.setOnClickListener {
            listener.onEditNote(item)
        }
        holder.delete.setOnClickListener {
            listener.onDeleteNote(item)
        }
    }

    override fun getItemCount(): Int = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: ArrayList<Note>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }


    class ViewHolder(view: AdapterNoteBinding) : RecyclerView.ViewHolder(view.root){
        val judul = view.lblTitle
        val category = view.lblCategory
        val date = view.lblDate
        val delete = view.btnDelete
        val info = view.llInfo
    }

}