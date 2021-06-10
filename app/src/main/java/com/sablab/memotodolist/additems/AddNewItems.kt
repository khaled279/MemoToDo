package com.sablab.memotodolist.additems

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.sablab.memotodolist.MemoApplication
import com.sablab.memotodolist.R

class AddNewItems:Fragment() {
    var editText:EditText? = null
    var addButton:Button? = null
    var importantCheck:CheckBox? =null
    private var viewModel:AddItemsViewModel? = null

    @Override
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_items,container , false)

        return view
    }
    @Override
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       val viewModel: AddItemsViewModel by viewModels {
                AddItemsViewModel.AddItemsViewModelFactory((activity?.application as MemoApplication).repostory)
       }
        this.viewModel = viewModel
        editText = view.findViewById(R.id.add_describtion)
        addButton = view.findViewById(R.id.add_button)
        importantCheck = view.findViewById(R.id.add_importance)
        addButton?.setOnClickListener { onClickAddButton(view) }
    }

    fun onClickAddButton(view:View){
        viewModel?.makeTask(editText?.text.toString(),importantCheck?.isChecked!!)
        viewModel?.insertTask(viewModel?.task!!)
        NavHostFragment.findNavController(this).navigate(R.id.action_addNewItems_to_tasksFragment)
    }


}