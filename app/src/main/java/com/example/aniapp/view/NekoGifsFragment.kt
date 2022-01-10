package com.example.aniapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aniapp.R
import com.example.aniapp.adapter.NekoGifsAdapter
import com.example.aniapp.databinding.FragmentNekoGifsBinding
import com.example.aniapp.utils.UIState
import com.example.aniapp.viewmodel.NekoBestViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NekoGifsFragment : Fragment() {

    private val binding: FragmentNekoGifsBinding by lazy {
        FragmentNekoGifsBinding.inflate(layoutInflater)
    }

    private val nekosViewModel : NekoBestViewModel by viewModel()

    private lateinit var nekogifAdapter : NekoGifsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nekogifAdapter = NekoGifsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.nekosgifRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = nekogifAdapter
        }
        binding.loading.setOnRefreshListener {
            nekosViewModel.subscribeToNekoGifs()
        }
        nekosViewModel.allNekoGifsObserver.observe(viewLifecycleOwner, ::handleNekoGifs)
        nekosViewModel.subscribeToNekoGifs()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun handleNekoGifs(uiState: UIState) {
        when(uiState){
            is UIState.LOADING -> {
                binding.loading.isRefreshing = true
            }
            is UIState.SUCCESSNEKOSGIF -> {
                binding.loading.isRefreshing = false
                nekogifAdapter.setNekosGif(uiState.nekosGif)
            }
            is UIState.ERROR -> {
                binding.loading.isRefreshing = false
                Toast.makeText(requireContext(), "Please try again..", Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = NekoGifsFragment()
    }
}