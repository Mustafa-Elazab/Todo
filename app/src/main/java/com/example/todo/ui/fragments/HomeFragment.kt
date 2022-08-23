package com.example.todo.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.todo.R
import com.example.todo.data.model.NoteModel
import com.example.todo.databinding.FragmentHomeBinding
import com.example.todo.ui.adapter.NoteAdapter
import com.example.todo.viewmodel.NoteViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel: NoteViewModel by viewModels()
    var isSearchShow: Boolean = false
    lateinit var adapter: NoteAdapter
    var oldNotes = arrayListOf<NoteModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


// staggeredGridLayoutManager with 3 columns and vertical orientation


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel.getAllNotes().observe(viewLifecycleOwner, { noteList ->
            oldNotes = noteList as ArrayList<NoteModel>
            binding.todoList.layoutManager =
                StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            adapter=NoteAdapter(requireContext(), noteList)
            binding.todoList.adapter = adapter

        })
        binding.imgFilter.setOnClickListener {
            viewModel.getAllNotes().observe(viewLifecycleOwner, { noteList ->

                binding.todoList.layoutManager =
                    StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                adapter=NoteAdapter(requireContext(), noteList)
                binding.todoList.adapter = adapter

            })
        }
        binding.tvHighFilter.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner, { noteList ->

                binding.todoList.layoutManager =
                    StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL)
                adapter=NoteAdapter(requireContext(), noteList)
                binding.todoList.adapter = adapter

            })
        }
        binding.tvMeduimFilter.setOnClickListener {
            viewModel.getMeduimNotes().observe(viewLifecycleOwner, { noteList ->

                binding.todoList.layoutManager =
                    StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL)
                adapter=NoteAdapter(requireContext(), noteList)
                binding.todoList.adapter = adapter

            })
        }
        binding.tvLowFilter.setOnClickListener {
            viewModel.getLowNotes().observe(viewLifecycleOwner, { noteList ->

                binding.todoList.layoutManager =
                    StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL)
                adapter=NoteAdapter(requireContext(), noteList)
                binding.todoList.adapter = adapter

            })
        }
        binding.floatingActionButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_addFragment)
        }
        binding.imgSearch.setOnClickListener {
            isSearchShow = !isSearchShow

            if (isSearchShow != false) {
                binding.btnSearch.visibility = View.VISIBLE
            } else {
                binding.btnSearch.visibility = View.GONE
            }
        }
        binding.btnSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                noteFilter(newText)
                return true
            }
        })

    }

    private fun noteFilter(newText: String?) {

        var newFilterList = arrayListOf<NoteModel>()
        for (i in oldNotes) {
            if (i.title.contains(newText!!) || i.description.contains(newText)) {
                newFilterList.add(i)
            }
        }

        adapter.noteFiltering(newFilterList)
    }

}
