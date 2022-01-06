package com.example.aniapp.rest

import com.example.aniapp.utils.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface NekosBestRepo {
    val nekos : StateFlow<UIState>
    val nekosGifs : StateFlow<UIState>
    suspend fun getNekoGifs()
    suspend fun getNekos()
}

class NekosBestRepoImpl(
    private val nekosBestAPI: NekosBestAPI
) : NekosBestRepo{
    private val _allNekos : MutableStateFlow<UIState> = MutableStateFlow(UIState.LOADING())
    private val _allNekoGifs : MutableStateFlow<UIState> = MutableStateFlow(UIState.LOADING())
    override val nekos: StateFlow<UIState>
        get() = _allNekos
    override val nekosGifs: StateFlow<UIState>
        get() = _allNekoGifs

    override suspend fun getNekoGifs() {
        try {
            val response = nekosBestAPI.getGifs()
            if (response.isSuccessful){
                response.body()?.let {
                    _allNekoGifs.value = UIState.SUCCESSNEKOSGIF(listOf(it))
                } ?: run {_allNekoGifs.value = UIState.ERROR(Throwable("Response is null")) }
            }else{
                _allNekoGifs.value = UIState.ERROR(Exception(response.errorBody()?.string()))
            }
        } catch (e : Exception){
            _allNekoGifs.value = UIState.ERROR(e)
        }
    }

    override suspend fun getNekos() {
        try {
            val response = nekosBestAPI.getNekos()
            if (response.isSuccessful){
                response.body()?.let {
                    _allNekos.value = UIState.SUCCESSNEKOS(listOf(it))
                } ?: run {_allNekos.value = UIState.ERROR(Throwable("Response is null")) }
            }else{
                _allNekos.value = UIState.ERROR(Exception(response.errorBody()?.string()))
            }
        } catch (e : Exception){
            _allNekos.value = UIState.ERROR(e)
        }
    }
}