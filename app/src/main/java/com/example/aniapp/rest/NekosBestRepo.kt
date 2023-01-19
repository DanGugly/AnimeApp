package com.example.aniapp.rest

import android.util.Log
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
) : NekosBestRepo {
    private val _allNekos: MutableStateFlow<UIState> = MutableStateFlow(UIState.LOADING())
    private val _allNekoGifs: MutableStateFlow<UIState> = MutableStateFlow(UIState.LOADING())
    override val nekos: StateFlow<UIState>
        get() = _allNekos
    override val nekosGifs: StateFlow<UIState>
        get() = _allNekoGifs

    override suspend fun getNekoGifs() {
        try {
            val response = nekosBestAPI.getGifs()
            if (response.isSuccessful) {
                response.body()?.let {
                    _allNekoGifs.value = UIState.SUCCESSNEKOSGIF(it.url)
                    Log.d("Noah", "Response success" + it.url.toString())
                } ?: run { _allNekoGifs.value = UIState.ERROR(Throwable("Response is null")) }
            } else {
                Log.d("Noah", "Response Fail 1: " + response.errorBody()?.string())
                _allNekoGifs.value = UIState.ERROR(Exception(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Log.d("Noah", "Response Fail 2: " + e.localizedMessage)
            _allNekoGifs.value = UIState.ERROR(e)
        }
    }

    override suspend fun getNekos() {
        try {
            val response = nekosBestAPI.getNekos()
            if (response.isSuccessful) {
                response.body()?.let {
                    Log.d("Noah", "Response Pic success: " + it.url.toString())
                    _allNekos.value = UIState.SUCCESSNEKOS(it.url)
                } ?: run { _allNekos.value = UIState.ERROR(Throwable("Response is null")) }
            } else {
                Log.d("Noah", "Response Pic Fail 1: " + response.errorBody()?.string())
                _allNekos.value = UIState.ERROR(Exception(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Log.d("Noah", "Response Pic Fail 2: " + e.localizedMessage)
            _allNekos.value = UIState.ERROR(e)
        }
    }
}