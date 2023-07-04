package com.example.noteapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.theartofdev.edmodo.cropper.CropImage.activity


class HomeFragment : BaseFragmet() {
    // TODO: Rename and change types of parameters




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bt=view.findViewById<FloatingActionButton>(R.id.fabBtnCreateNote)

        bt.setOnClickListener {
            replaceFragment(CreateNoteFragment.newInstance(),true)
        }
    }

    fun replaceFragment(fragment: Fragment, isTransition:Boolean){

        val fragmentTransection=activity!!.supportFragmentManager.beginTransaction()

        if (isTransition){
            fragmentTransection.setCustomAnimations(android.R.anim.slide_out_right,android.R.anim.slide_in_left)
        }
        fragmentTransection.replace(R.id.frame_layout,fragment)
                .addToBackStack(fragment.javaClass.simpleName)
                .commit()
    }

    companion object {

        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

}