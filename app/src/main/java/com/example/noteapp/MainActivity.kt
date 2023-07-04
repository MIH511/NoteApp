package com.example.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(HomeFragment.newInstance(),true)
    }

    fun replaceFragment(fragment: Fragment, isTransition:Boolean){

        val fragmentTransection=supportFragmentManager.beginTransaction()

        if (isTransition){
            fragmentTransection.setCustomAnimations(android.R.anim.slide_out_right,android.R.anim.slide_in_left)
        }
        fragmentTransection.replace(R.id.frame_layout,fragment)
                .addToBackStack(fragment.javaClass.simpleName).commit()
    }
}