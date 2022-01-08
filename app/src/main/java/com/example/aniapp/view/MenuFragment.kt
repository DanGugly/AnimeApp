package com.example.aniapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aniapp.databinding.FragmentMenuBinding
import com.example.aniapp.utils.switchFragments

class MenuFragment : Fragment() {

    private val binding : FragmentMenuBinding by lazy {
        FragmentMenuBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.nekosButton.setOnClickListener {
            switchFragments(parentFragmentManager, NekosFragment.newInstance())
        }
        binding.nekosGifs.setOnClickListener {
            switchFragments(parentFragmentManager, NekoGifsFragment.newInstance())
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = MenuFragment()
    }
}