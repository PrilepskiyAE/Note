package com.ambrella.note.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.ambrella.note.R
import com.ambrella.note.adapters.NoteAdapter
import com.ambrella.note.databinding.FragmentMainBinding
import com.ambrella.note.model.Note
import com.ambrella.note.repository.NoteRepositoryImpl
import com.ambrella.note.viewmodel.NoteListViewModel
import com.ambrella.note.viewmodel.NoteListViewModelFactory
import kotlinx.coroutines.Dispatchers


class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var cityListViewModel: NoteListViewModel
    private lateinit var viewModelFactory: ViewModelProvider.Factory
    private val noteadapter = NoteAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)
        val swapHelperRight = getSwapRight()
        swapHelperRight.attachToRecyclerView(mBinding.recyclerView)

        val swapHelperLeft = getSwapLeft(navController)
        swapHelperLeft.attachToRecyclerView(mBinding.recyclerView)

        noteadapter.onNoteClicLisener = object : NoteAdapter.OnNoteClicLisener {
            override fun onNoteClick(note: Note) {
                findNavController().navigate(R.id.detailFragment)
                val bundle = Bundle()
                bundle.putString("title", note.title)
                bundle.putString("text", note.text)
                navController.navigate(R.id.detailFragment, bundle)
            }

        }

        mBinding.recyclerView.adapter = noteadapter
        viewModelFactory = NoteListViewModelFactory(
            NoteRepositoryImpl(requireContext(), Dispatchers.IO)
        )

        cityListViewModel =
            ViewModelProvider(this, viewModelFactory).get(NoteListViewModel::class.java)
        cityListViewModel.getAllNote().observe(viewLifecycleOwner, Observer<List<Note>> {
            updateResults(it)
        })

        mBinding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.addNoteFragment)
            val bundle = Bundle()
            //bundle.putString("title","title")
            //bundle.putString("text","title")
            bundle.putInt("update", 0)
            navController.navigate(R.id.addNoteFragment, bundle)
        }

        /*
         mBinding.btaddnote.setOnClickListener {

         }

         mBinding.btdetail.setOnClickListener {
             findNavController().navigate(R.id.detailFragment)
             val bundle=Bundle()
             bundle.putString("title","title")
             navController.navigate(R.id.detailFragment, bundle)
         }


         */
    }

    private fun getSwapRight(): ItemTouchHelper {
        return ItemTouchHelper(
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    cityListViewModel.deleteNote(noteadapter.getNote(viewHolder.adapterPosition))
                }
            }
        )
    }

    private fun getSwapLeft(navController: NavController): ItemTouchHelper {
        return ItemTouchHelper(
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    findNavController().navigate(R.id.addNoteFragment)
                    val bundle = Bundle()

                    bundle.putString("title", noteadapter.getNote(viewHolder.adapterPosition).title)
                    bundle.putString("text", noteadapter.getNote(viewHolder.adapterPosition).text)
                    bundle.putInt("id", noteadapter.getNote(viewHolder.adapterPosition).id)

                    bundle.putInt("update", 1)
                    navController.navigate(R.id.addNoteFragment, bundle)
                    //cityListViewModel.updateNote(Note(viewHolder.adapterPosition))
                }
            }
        )
    }

    private fun updateResults(notes: List<Note>) {
        noteadapter.setNote(notes)
        mBinding.recyclerView.apply {
            adapter = noteadapter
        }

    }
}