package com.example.todo.ui.fragments

import android.icu.text.MessageFormat.format
import android.os.Bundle
import android.text.format.DateFormat
import android.text.format.DateFormat.format
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.todo.R
import com.example.todo.data.model.NoteModel
import com.example.todo.databinding.FragmentAddBinding
import com.example.todo.viewmodel.NoteViewModel

import java.util.*


class AddFragment : Fragment() {

    lateinit var binding: FragmentAddBinding
    var proietry :String ="1"
    val viewModel :NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dgreen.setImageResource(R.drawable.ic_baseline_check_24)
        binding.dgreen.setOnClickListener {
            proietry="1"
            binding.dgreen.setImageResource(R.drawable.ic_baseline_check_24)
            binding.dred.setImageResource(0)
            binding.dyellow.setImageResource(0)
        }
        binding.dred.setOnClickListener {
            proietry="3"
            binding.dred.setImageResource(R.drawable.ic_baseline_check_24)
            binding.dgreen.setImageResource(0)
            binding.dyellow.setImageResource(0)
        }
        binding.dyellow.setOnClickListener {
            proietry="2"
            binding.dyellow.setImageResource(R.drawable.ic_baseline_check_24)
            binding.dred.setImageResource(0)
            binding.dgreen.setImageResource(0)
        }
        binding.icBack.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_addFragment_to_homeFragment)
        }
        binding.btnSave.setOnClickListener {
            if (!binding.edtTitle.text.toString()
                    .isNullOrEmpty() && !binding.edtDescription.text.toString().isNullOrEmpty()
            ) {

                addNote(it)

            }
        }
    }

    private fun addNote(it: View?) {
        val d=Date()
        val noteDate:CharSequence = DateFormat.format("MMMM d, yyyy",d.time)
        var title=binding.edtTitle.text.toString()
        var description=binding.edtDescription.text.toString()

        var noteData=NoteModel(
            id = null,
            title = title,
            description = description,
            date = noteDate.toString(),
            proietry = proietry
        )

        viewModel.addNote(noteData)

        Toast.makeText(context,"Note Add Sucessfully",Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_addFragment_to_homeFragment
        )


    }


}