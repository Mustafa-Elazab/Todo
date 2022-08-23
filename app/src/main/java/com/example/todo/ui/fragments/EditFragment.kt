package com.example.todo.ui.fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todo.R
import com.example.todo.data.model.NoteModel
import com.example.todo.databinding.FragmentEditBinding
import com.example.todo.viewmodel.NoteViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


class EditFragment : Fragment() {

    val oldNote by navArgs<EditFragmentArgs>()
    lateinit var binding: FragmentEditBinding
    var proietry: String = "1"
    val viewModel: NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.edtTitle.setText(oldNote.data.title)
        binding.edtDescription.setText(oldNote.data.description)
        binding.icDelete.setOnClickListener {
            var bottomSheet: BottomSheetDialog = BottomSheetDialog(requireContext())
            bottomSheet.setContentView(R.layout.custom_dialog)

            var textViewYes = bottomSheet.findViewById<TextView>(R.id.tv_yes_dialog)
            var textViewNo = bottomSheet.findViewById<TextView>(R.id.tv_no_dialog)


            textViewYes?.setOnClickListener {

                GlobalScope.launch(Dispatchers.IO ) {
                    viewModel.deleteNote(oldNote.data.id!!)
                    bottomSheet.dismiss()

                }

               findNavController().navigate(R.id.action_editFragment_to_homeFragment)
            }
            textViewNo?.setOnClickListener {
                bottomSheet.dismiss()
            }
            bottomSheet.show()
        }
        binding.icBack.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_editFragment_to_homeFragment)
        }

        when (oldNote.data.proietry) {
            "1" -> {
                binding.dgreen.setImageResource(R.drawable.ic_baseline_check_24)
                binding.dred.setImageResource(0)
                binding.dyellow.setImageResource(0)
            }
            "2" -> {
                binding.dred.setImageResource(R.drawable.ic_baseline_check_24)
                binding.dgreen.setImageResource(0)
                binding.dyellow.setImageResource(0)
            }
            "3" -> {
                binding.dyellow.setImageResource(R.drawable.ic_baseline_check_24)
                binding.dred.setImageResource(0)
                binding.dgreen.setImageResource(0)
            }
        }

        binding.btnEdit.setOnClickListener {
            if (!binding.edtTitle.text.toString()
                    .isNullOrEmpty() && !binding.edtDescription.text.toString().isNullOrEmpty()
            ) {
                GlobalScope.launch(Dispatchers.IO ){
                    updateNote(it!!)
                }
//                        Navigation.findNavController(it!!).navigate(
//            R.id.action_addFragment_to_homeFragment
//        )
            }

        }
    }

    private fun updateNote(it: View?) {
        val d = Date()
        val noteDate: CharSequence = DateFormat.format("MMMM d, yyyy", d.time)

        var title = binding.edtTitle.text.toString()
        var description = binding.edtDescription.text.toString()

        var noteData = NoteModel(
            id = oldNote.data.id,
            title = title,
            description = description,
            date = noteDate.toString(),
            proietry = proietry
        )
        Log.e("@@@@", oldNote.data.id.toString())
        Log.e("@@@@", title)
        Log.e("@@@@", description)


        GlobalScope.launch(Dispatchers.IO){
            viewModel.updateNote(noteData)

        }






    }
}
