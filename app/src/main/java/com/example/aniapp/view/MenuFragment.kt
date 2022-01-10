package com.example.aniapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.aniapp.R
import com.example.aniapp.databinding.FragmentMenuBinding
import com.example.aniapp.rest.NekosBestAPI
import com.example.aniapp.utils.switchFragments

class MenuFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private val binding : FragmentMenuBinding by lazy {
        FragmentMenuBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.gifSpinner.apply {
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter.createFromResource(
                requireContext(),
                R.array.gifs_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                this.adapter = adapter
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.gifSpinner.onItemSelectedListener = this
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

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        NekosBestAPI.GIF_CATEGORY = parent?.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}