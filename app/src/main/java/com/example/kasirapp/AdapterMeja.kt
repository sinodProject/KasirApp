package com.example.kasirapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kasirapp.databinding.RvCardMejaBinding

class AdapterMeja (private val context: Context, private val tables : ArrayList<Meja>,
                   private val mejaViewModel: MejaViewModel): RecyclerView.Adapter<AdapterMeja.ViewHolder>(){
    inner class ViewHolder(private val binding : RvCardMejaBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(meja: Meja){
            binding.cardTableTvTitle.text = "Meja "+meja.number.toString()
            binding.cardTableIvDelete.setOnClickListener {
                mejaViewModel.deleteTable(meja)
            }
        }
        fun onClick(context: Context, meja: Meja){
            binding.cardTableIvUpdate.setOnClickListener {
                val intent = Intent(context, UpdateMeja::class.java)
                intent.putExtra("id", meja.id)
                intent.putExtra("noMeja", meja.number)
                context.startActivity(intent)
            }
        }
    }

    fun updateTable(newList: List<Meja>){
        tables.clear()
        tables.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvCardMejaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tables[position])
        holder.onClick(context = context, tables[position])
    }

    override fun getItemCount(): Int {
        return tables.size
    }
}
