package com.example.aniapp.utils

import com.example.aniapp.model.NekoBestModels.Url
import com.example.aniapp.model.NekoBestModels.UrlGif

sealed class UIState{
    class LOADING(val isLoading: Boolean = true) : UIState()
    class SUCCESSNEKOS(val nekos: List<Url>?) : UIState()
    class SUCCESSNEKOSGIF(val nekosGif: List<UrlGif>?) : UIState()
    class ERROR(val error: Throwable) : UIState()
}