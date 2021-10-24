package com.ambrella.note.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.ambrella.note.R
import com.ambrella.note.databinding.FragmentAddNoteBinding
import com.ambrella.note.model.Note
import com.ambrella.note.repository.NoteRepositoryImpl
import com.ambrella.note.viewmodel.NoteListViewModel
import com.ambrella.note.viewmodel.NoteListViewModelFactory
import kotlinx.coroutines.Dispatchers


class AddNoteFragment : Fragment() {

    private var _binding: FragmentAddNoteBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var cityListViewModel: NoteListViewModel
    private lateinit var viewModelFactory: ViewModelProvider.Factory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentAddNoteBinding.inflate(layoutInflater,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController= Navigation.findNavController(view)
        viewModelFactory = NoteListViewModelFactory(
            NoteRepositoryImpl(requireContext(), Dispatchers.IO)
        )


        cityListViewModel =
            ViewModelProvider(this, viewModelFactory).get(NoteListViewModel::class.java)
        mBinding.btadd.setOnClickListener {

            findNavController().navigate(R.id.mainFragment)
           // val bundle=Bundle()
          //  bundle.putString("title","title")
            navController.navigate(R.id.mainFragment)
            cityListViewModel.insertNote(
                Note(title = mBinding.editTextTextPersonName2.text.toString(),
                text = mBinding.editTextTextPersonName3.text.toString())
            )
        }

            }
        }



