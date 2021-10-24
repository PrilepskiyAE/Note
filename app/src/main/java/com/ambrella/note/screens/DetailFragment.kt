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
import com.ambrella.note.databinding.FragmentDetailBinding
import com.ambrella.note.viewmodel.NoteListViewModel


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentDetailBinding.inflate(layoutInflater,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController= Navigation.findNavController(view)
        val title = requireArguments().getString("title")
        val text = requireArguments().getString("text")
        mBinding.tvTitle.text=title
        mBinding.tvText.text=text


        mBinding.btdown.setOnClickListener {
            findNavController().navigate(R.id.mainFragment)
            val bundle=Bundle()
            bundle.putString("title","title")
            navController.navigate(R.id.mainFragment, bundle)
        }


    }
}