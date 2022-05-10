package com.ubaya.todoapp_160419060.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.todoapp_160419060.R
import com.ubaya.todoapp_160419060.databinding.FragmentEditTodoBinding
import com.ubaya.todoapp_160419060.model.Todo
import com.ubaya.todoapp_160419060.viewmodel.DetailTodoViewModel
import kotlinx.android.synthetic.main.fragment_create_todo.*

class EditTodoFragment : Fragment(), TodoPriorityClickListener, TodoSaveChangeListener {
    private lateinit var viewModel: DetailTodoViewModel
    private lateinit var dataBinding: FragmentEditTodoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = FragmentEditTodoBinding.inflate(inflater, container, false)
        // return inflater.inflate(R.layout.fragment_edit_todo, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)

        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)
        observeViewModel()

        dataBinding.priorityListener = this
        dataBinding.saveListener = this

        // txtJudulTodo.text = "Edit Todo// "
        // btnAdd.text = "Save Chang// es"

        /* btnAdd.setOnClickListener {
            val radio = view.findViewById<RadioButton>(radioGrupPriority.checkedRadioButtonId)
            viewModel.update(txtTitle.text.toString(), txtNote.text.toString(), radio.tag.toString().toInt(), uuid)
            Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        } */
    }

    fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            /* txtTitle.setText(it.title)
            txtNote.setText(it.notes)

            when (it.priority) {
                1 -> radioLow.isChecked = true
                2 -> radioMedium.isChecked = true
                else -> radioHigh.isChecked = true
            } */
            dataBinding.todo = it
        })
    }

    override fun onRadioPriorityClick(view: View, priority: Int, obj: Todo) {
        obj.priority = priority
    }

    override fun onSaveChanges(view: View, obj: Todo) {
        viewModel.update(obj.title, obj.notes, obj.priority, obj.uuid)
        Toast.makeText(view.context, "Todo Updated", Toast.LENGTH_SHORT).show()
    }
}