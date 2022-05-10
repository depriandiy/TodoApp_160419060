package com.ubaya.todoapp_160419060.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.todoapp_160419060.R
import com.ubaya.todoapp_160419060.databinding.TodoItemLayoutBinding
import com.ubaya.todoapp_160419060.model.Todo
import kotlinx.android.synthetic.main.todo_item_layout.view.*

class TodoListAdapter(val todolist: ArrayList<Todo>, val adapterOnClick : (Todo) -> Unit)
    :RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>(), TodoCheckedChangeListener, TodoEditClickListener {
    class TodoViewHolder(var view: TodoItemLayoutBinding): RecyclerView.ViewHolder(view.root) // using data binding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.todo_item_layout, parent, false)
        val view = TodoItemLayoutBinding.inflate(inflater, parent, false) // using data binding class

        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        /*
        holder.view.checkTask.setText(todolist[position].title.toString())

        holder.view.imgButtonEdit.setOnClickListener {
            val action = TodoListFragmentDirections.actionEditTodoFragment(todolist[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }

        holder.view.checkTask.setOnCheckedChangeListener { compoundButton, b ->
            if(b == true) {
                adapterOnClick(todolist[position])
            }
        }*/

        // using data binding
        val todo = todolist[position]
        holder.view.todo = todo
        holder.view.checkBoxListener = this
        holder.view.editListener = this
    }

    fun updateTodoList(newTodoList: List<Todo>) {
        todolist.clear()
        todolist.addAll(newTodoList)
        notifyDataSetChanged()
    }

    override fun getItemCount() = todolist.size

    override fun onCheckedChange(cb: CompoundButton, isChecked: Boolean, obj: Todo) {
        if (isChecked) adapterOnClick(obj)
    }

    override fun onEditClick(view: View) {
        val action = TodoListFragmentDirections.actionEditTodoFragment(view.tag.toString().toInt())
        Navigation.findNavController(view).navigate(action)
    }
}