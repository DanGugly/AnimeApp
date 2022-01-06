package com.example.aniapp.utils

import com.example.aniapp.model.NekoBestModels.NekoGif
import com.example.aniapp.model.NekoBestModels.Nekos

sealed class UIState{
    class LOADING(val isLoading : Boolean = true) : UIState()
    class SUCCESSNEKOS(val nekos: List<Nekos>) : UIState()
    class SUCCESSNEKOSGIF(val nekosGif: List<NekoGif>) : UIState()
    class ERROR(val error: Throwable) : UIState()
}