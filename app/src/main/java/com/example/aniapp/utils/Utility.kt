package com.example.aniapp.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.aniapp.R

fun switchFragments(manager: FragmentManager, fragment: Fragment) {
    manager.beginTransaction()
        .replace(R.id.frag_container, fragment)
        .addToBackStack(null)
        .commit()
}