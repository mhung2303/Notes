package com.carter.takenotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.carter.takenotes.adapter.NotesAdapter
import com.carter.takenotes.database.NotesDatabase
import com.carter.takenotes.entities.Notes
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : BaseFragment() {

    var arrNotes = ArrayList<Notes>()
    private val notesAdapter: NotesAdapter = NotesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view.setHasFixedSize(true)

        recycler_view.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

        launch {
            context?.let {
                val notes = NotesDatabase.getDatabase(it).noteDao().getAllNotes()
                notesAdapter.setData(notes)
                arrNotes = notes as ArrayList<Notes>
                recycler_view.adapter = notesAdapter
            }
        }

         notesAdapter.setOnClickListener(onClicked)

        // Click create Note
        fabBtnCreateNote.setOnClickListener {
            replaceFragment(CreateNoteFragment.newInstance(),false)
        }

        search_view.setOnQueryTextListener(object  : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                var tempArr = ArrayList<Notes>()
                for(arr in arrNotes)
                {
                    if(arr.title!!.lowercase(Locale.getDefault()).contains(newText.toString())){
                        tempArr.add(arr)
                    }
                }

                notesAdapter.setData(tempArr)
                notesAdapter.notifyDataSetChanged()
                return true
            }

        })


    }

    private val onClicked = object : NotesAdapter.OnItemClickListener{
        override fun onClicked(noteID: Int) {
            val fragment : Fragment
            val bundle = Bundle()
            bundle.putInt("noteID",noteID)
            fragment = CreateNoteFragment.newInstance()
            fragment.arguments = bundle

            replaceFragment(fragment,false)
        }



    }

    private fun replaceFragment(fragment: Fragment, isTransition: Boolean)
    {
        val fragmentTransition = requireActivity().supportFragmentManager.beginTransaction()
        if (isTransition){
            fragmentTransition.setCustomAnimations(android.R.anim.slide_out_right,android.R.anim.slide_in_left)
        }
        fragmentTransition.replace(R.id.frame_layout,fragment).addToBackStack(fragment.javaClass.simpleName).commit()
    }
}