package com.example.aniapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aniapp.rest.NekosBestRepo
import com.example.aniapp.utils.UIState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class NekoBestViewModel(
    private val nekosBestRepo: NekosBestRepo,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val coroutineScope : CoroutineScope = CoroutineScope(SupervisorJob() + ioDispatcher)
) : ViewModel(), CoroutineScope by coroutineScope {
    private var _allNekos : MutableLiveData<UIState> = MutableLiveData(UIState.LOADING())
    val allNekosObserver: LiveData<UIState> get() = _allNekos

    private var _allNekoGifs : MutableLiveData<UIState> = MutableLiveData(UIState.LOADING())
    val allNekoGifsObserver : LiveData<UIState> get() = _allNekoGifs

    fun subscribeToNekos(){
        launch{
            nekosBestRepo.getNekos()
        }
    }

    fun subscribeToNekoGifs() {
        launch{
            nekosBestRepo.getNekoGifs()
        }
    }

    private fun collectNekosData(){
        launch {
            nekosBestRepo.nekos.collect{ uiState ->
                when(uiState){
                    is UIState.LOADING -> { _allNekos.postValue(uiState) }
                    is UIState.SUCCESSNEKOS -> { _allNekos.postValue(uiState) }
                    is UIState.ERROR -> { _allNekos.postValue(uiState) }
                }
            }
        }
    }

    private fun collectNekoGifsData(){
        launch {
            nekosBestRepo.nekosGifs.collect{ uiState ->
                when(uiState){
                    is UIState.LOADING -> { _allNekoGifs.postValue(uiState) }
                    is UIState.SUCCESSNEKOSGIF -> { _allNekoGifs.postValue(uiState) }
                    is UIState.ERROR -> { _allNekoGifs.postValue(uiState) }
                }
            }
        }
    }
}