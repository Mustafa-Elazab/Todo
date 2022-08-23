package com.example.todo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.data.model.NoteModel
import com.example.todo.databinding.TodoViewBinding
import com.example.todo.ui.fragments.HomeFragmentDirections
import java.util.ArrayList

class NoteAdapter(val requireContext: Context,var noteList: List<NoteModel>) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    fun noteFiltering(newFilterList: ArrayList<NoteModel>) {
          noteList=newFilterList
        notifyDataSetChanged()
    }
    class NoteViewHolder(val binding: TodoViewBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
   return  NoteViewHolder(
       TodoViewBinding.inflate(
           LayoutInflater.from(parent.context),
           parent,false
       )
   )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val  data=noteList[position]
        holder.binding.rvTvTitle.text=data.title
        holder.binding.rvTvDescription.text=data.description
        holder.binding.rvTvTime.text=data.date


        when(data.proietry)
        {
            "1" -> {
                holder.binding.rvVDot.setImageResource(R.drawable.green_dot_shape)
            }
            "2" -> {
                holder.binding.rvVDot.setImageResource(R.drawable.yellow_dot_shape)
            }
            "3" -> {
                holder.binding.rvVDot.setImageResource(R.drawable.red_dot_shape)
            }
        }
        holder.binding.root.setOnClickListener {
         var action=HomeFragmentDirections.actionHomeFragmentToEditFragment(data)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount()=noteList.size


}