package com.ambrella.note.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
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
        _binding = FragmentAddNoteBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = Navigation.findNavController(view)
        viewModelFactory = NoteListViewModelFactory(
            NoteRepositoryImpl(requireContext(), Dispatchers.IO)
        )
        val isUpd = requireArguments().getInt("update")
        if (isUpd == 1) {
            mBinding.editTextTextPersonName2.setText(requireArguments().getString("title"))
            mBinding.editTextTextPersonName3.setText(requireArguments().getString("text"))
        }

        cityListViewModel =
            ViewModelProvider(this, viewModelFactory).get(NoteListViewModel::class.java)
        mBinding.btadd.setOnClickListener {
            if (isUpd == 0) {

                findNavController().navigate(R.id.mainFragment)
                navController.navigate(R.id.mainFragment)
                if (mBinding.editTextTextPersonName2.text.toString() != "") {
                    cityListViewModel.insertNote(
                        Note(
                            title = mBinding.editTextTextPersonName2.text.toString(),
                            text = mBinding.editTextTextPersonName3.text.toString()
                        )
                    )
                } else {
                    Toast.makeText(getActivity(), getString(R.string.isTitle), Toast.LENGTH_LONG)
                        .show()
                }
            } else if (isUpd == 1) {
                findNavController().navigate(R.id.mainFragment)
                navController.navigate(R.id.mainFragment)
                if (mBinding.editTextTextPersonName2.text.toString() != "") {
                    cityListViewModel.updateNote(
                        Note(
                            id = requireArguments().getInt("id"),
                            title = mBinding.editTextTextPersonName2.text.toString(),
                            text = mBinding.editTextTextPersonName3.text.toString()
                        )
                    )
                } else {
                    Toast.makeText(getActivity(), getString(R.string.isTitle), Toast.LENGTH_LONG)
                        .show()
                }
            }
        }


    }


}