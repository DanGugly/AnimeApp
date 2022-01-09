package com.example.aniapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aniapp.R
import com.example.aniapp.adapter.NekosAdapter
import com.example.aniapp.databinding.FragmentNekosBinding
import com.example.aniapp.utils.UIState
import com.example.aniapp.viewmodel.NekoBestViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NekosFragment : Fragment() {

    private val binding by lazy {
        FragmentNekosBinding.inflate(layoutInflater)
    }

    private val nekosViewModel : NekoBestViewModel by viewModel()

    private lateinit var nekosAdapter : NekosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nekosAdapter = NekosAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.nekosRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = nekosAdapter
        }
        binding.loading.setOnRefreshListener {
            nekosViewModel.subscribeToNekos()
        }
        nekosViewModel.allNekosObserver.observe(viewLifecycleOwner, ::handleNekos)
        nekosViewModel.subscribeToNekos()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun handleNekos(uiState: UIState) {
        when(uiState){
            is UIState.LOADING -> {
                binding.loading.isRefreshing = true
            }
            is UIState.SUCCESSNEKOS -> {
                binding.loading.isRefreshing = false
                nekosAdapter.setNekos(uiState.nekos)
            }
            is UIState.ERROR -> {
                binding.loading.isRefreshing = false
                Toast.makeText(requireContext(), "Please try again..", Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = NekosFragment()
    }
}