package com.example.noteapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.noteapp.database.NoteDatabase
import com.example.noteapp.entities.Notes
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.*


class CreateNoteFragment : BaseFragmet() {
    // TODO: Rename and change types of parameters


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_note, container, false)
    }

    companion object {

        fun newInstance() =
            CreateNoteFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sdf=SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate=sdf.format(Date())

        val txDate=view.findViewById<TextView>(R.id.tvDateTime)
        val imgDone: ImageView=view.findViewById(R.id.ImDone)
        val title:EditText=view.findViewById(R.id.etNoteTitle)
        val subTitle:EditText=view.findViewById(R.id.etNoteSubTitle)
        val imgBack: ImageView=view.findViewById(R.id.imBack)
        val noteDetails :EditText=view.findViewById(R.id.etNoteDes)

        txDate.text=currentDate

        imgDone.setOnClickListener {
            saveNote(title,subTitle,noteDetails,txDate)
        }

        imgBack.setOnClickListener {
            replaceFragment(HomeFragment.newInstance(),false)
        }


    }

    fun replaceFragment(fragment: Fragment, isTransition:Boolean){

        val fragmentTransection=activity!!.supportFragmentManager.beginTransaction()

        if (isTransition){
            fragmentTransection.setCustomAnimations(android.R.anim.slide_out_right,android.R.anim.slide_in_left)
        }
        fragmentTransection.replace(R.id.frame_layout,fragment).commit()
    }

    private fun saveNote(title: EditText, subTitle: EditText, noteDetails: EditText, txDate: TextView) {
        if (title.text.isNullOrEmpty()){
            title.setError("title Requested")
            title.requestFocus()
            return
        }
        if(subTitle.text.isNullOrEmpty()){
            subTitle.setError("Note sub Title is required")
            subTitle.requestFocus()
            return
        }

        if(noteDetails.text.isNullOrEmpty()){
            noteDetails.setError("Note sub Title is required")
            noteDetails.requestFocus()
            return
        }

        runBlocking {
            val notes= Notes()
            notes.title = title.text.toString()
            notes.dateTime = txDate.toString()
            notes.subTitle = subTitle.text.toString()
            notes.noteText = noteDetails.text.toString()

            context?.let {
                NoteDatabase.getDatabase(it).noteDao().insertNotes(notes)

            }
            title.text.clear()
            subTitle.text.clear()
            noteDetails.text.clear()
        }
    }
}