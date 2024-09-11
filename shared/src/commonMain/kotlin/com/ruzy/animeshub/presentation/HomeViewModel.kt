package com.ruzy.animeshub.presentation

import com.ruzy.animeshub.domain.interactor.InsertRandomAnime
import com.ruzy.animeshub.domain.observers.ObserveAnimes
import com.ruzy.animeshub.model.anime.AnimeDetails
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel : CoroutineViewModel(), KoinComponent {

    private val observeAnimes: ObserveAnimes by inject()
    private val insertRandomAnime: InsertRandomAnime by inject()

    val uiState: StateFlow<HomeUiState> =
        combine(observeAnimes.flow, insertRandomAnime.inProgress, ::HomeUiState).stateIn(
            coroutineScope,
            SharingStarted.WhileSubscribed(5000),
            HomeUiState.Empty
        )

    fun randomAnime() {
        coroutineScope.launch {
            insertRandomAnime.invoke(Unit)
        }
    }

    init {
        observeAnimes(Unit)
    }
}

data class HomeUiState(
    val animes: List<AnimeDetails> = emptyList(),
    val loadingAnimes: Boolean = false
) {
    companion object {
        val Empty = HomeUiState()
    }
}