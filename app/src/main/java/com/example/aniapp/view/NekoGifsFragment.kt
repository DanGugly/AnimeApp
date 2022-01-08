package com.example.aniapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aniapp.R
import com.example.aniapp.databinding.FragmentNekoGifsBinding

class NekoGifsFragment : Fragment() {

    private val binding: FragmentNekoGifsBinding by lazy {
        FragmentNekoGifsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = NekoGifsFragment()
    }
}