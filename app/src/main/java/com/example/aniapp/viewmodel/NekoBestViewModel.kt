package com.example.aniapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aniapp.rest.NekosBestAPI
import com.example.aniapp.utils.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NekoBestViewModel(
    private val nekosBestAPI: NekosBestAPI,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val coroutineScope : CoroutineScope = CoroutineScope(ioDispatcher)
) : ViewModel() {
    private var _allNekos : MutableLiveData<UIState> = MutableLiveData(UIState.LOADING())
    val allNekosObserver: LiveData<UIState> get() = _allNekos

    private var _allNekoGifs : MutableLiveData<UIState> = MutableLiveData(UIState.LOADING())
    val allNekoGifsObserver : LiveData<UIState> get() = _allNekoGifs

    fun getNekos(){
        coroutineScope.launch {
            try {
                val response = nekosBestAPI.getNekos()
                response.body()?.let {
                    _allNekos.postValue(UIState.SUCCESSNEKOS(listOf(it)))
                } ?: _allNekos.postValue(UIState.ERROR(Throwable("Response is null")))

            } catch (e : Exception){
                _allNekos.postValue(UIState.ERROR(e))
            }
        }
    }

    fun getNekoGifs(){
        coroutineScope.launch {
            try {
                val response = nekosBestAPI.getGifs()
                response.body()?.let {
                    _allNekoGifs.postValue(UIState.SUCCESSNEKOSGIF(listOf(it)))
                } ?: _allNekoGifs.postValue(UIState.ERROR(Throwable("Response is null")))
            } catch (e : Exception){
                _allNekoGifs.postValue(UIState.ERROR(e))
            }
        }
    }
}