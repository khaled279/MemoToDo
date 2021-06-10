package com.sablab.memotodolist.tasksfrag

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sablab.memotodolist.R
import com.sablab.memotodolist.dataclasses.ToDoTask

class TasksAdapter:ListAdapter<ToDoTask , TasksAdapter.TaskViewHolder>(TasksCompartor()){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
            holder.bind(getItem(position))
        }

    class TaskViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(task: ToDoTask){
            val textDescription: TextView = itemView!!.findViewById(R.id.task_description_txt)!!
            val importantMark:ImageView = itemView!!.findViewById(R.id.important_label)!!
            textDescription.text = task.description
            textDescription.paint.isStrikeThruText = task.done
            importantMark.isVisible = task.important


        }
        companion object {
            fun create(parent: ViewGroup): TaskViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.task_item,  parent, false)
                return TaskViewHolder(view)
            }
        }
    }

    class TasksCompartor: DiffUtil.ItemCallback<ToDoTask>(){
        override fun areItemsTheSame(oldItem: ToDoTask, newItem: ToDoTask): Boolean {
            return oldItem === newItem

        }

        override fun areContentsTheSame(oldItem: ToDoTask, newItem: ToDoTask): Boolean {
          return  oldItem.description == newItem.description
        }

    }




}