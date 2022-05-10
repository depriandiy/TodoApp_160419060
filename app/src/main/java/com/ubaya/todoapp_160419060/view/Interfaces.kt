package com.ubaya.todoapp_160419060.view

import android.view.View
import android.widget.CompoundButton
import com.ubaya.todoapp_160419060.model.Todo

interface TodoCheckedChangeListener {
    fun onCheckedChange(cb: CompoundButton, isChecked: Boolean, obj: Todo)
}

interface TodoEditClickListener {
    fun onEditClick(view: View)
}

interface TodoPriorityClickListener {
    fun onRadioPriorityClick(view: View, priority: Int, obj: Todo)
}

interface TodoSaveChangeListener {
    fun onSaveChanges(view: View, obj: Todo)
}