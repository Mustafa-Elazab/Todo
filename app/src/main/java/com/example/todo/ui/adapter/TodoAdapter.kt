package com.example.todo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.data.model.NoteModel


import java.util.*

class TodoAdapter:RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){

    var todoList:ArrayList<NoteModel> = ArrayList()


    fun setList(todoList :ArrayList<NoteModel>){
        this.todoList=todoList
        notifyDataSetChanged()
    }
    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var title:TextView=itemView.findViewById(R.id.rv_tv_title)
        var description:TextView=itemView.findViewById(R.id.rv_tv_description)




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        var view :View=LayoutInflater.from(parent.context).inflate(R.layout.todo_view,parent,false)
        return  TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        var todo:NoteModel=todoList.get(position)

    }

    override fun getItemCount(): Int {
        return   todoList.size
    }
}