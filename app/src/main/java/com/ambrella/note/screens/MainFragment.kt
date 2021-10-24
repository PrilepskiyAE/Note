package com.ambrella.note.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.ambrella.note.R
import com.ambrella.note.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val mBinding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController= Navigation.findNavController(view)
       /*
        mBinding.btaddnote.setOnClickListener {
            findNavController().navigate(R.id.addNoteFragment)
            val bundle=Bundle()
            bundle.putString("title","title")
            navController.navigate(R.id.addNoteFragment, bundle)
        }

        mBinding.btdetail.setOnClickListener {
            findNavController().navigate(R.id.detailFragment)
            val bundle=Bundle()
            bundle.putString("title","title")
            navController.navigate(R.id.detailFragment, bundle)
        }


        */
    }

}