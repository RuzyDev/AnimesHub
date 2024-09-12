package com.ruzy.animeshub.presentation

import com.ruzy.animeshub.domain.interactor.anime.GetTopAnimeWithPage
import com.ruzy.animeshub.domain.interactor.anime.UpdateTopAnimes
import com.ruzy.animeshub.domain.interactor.manga.GetTopMangaWithPage
import com.ruzy.animeshub.domain.interactor.manga.UpdateTopMangas
import com.ruzy.animeshub.domain.observers.ObserveTopContents
import com.ruzy.animeshub.domain.observers.TopContentUiState
import com.ruzy.animeshub.model.ranking.TypeRakingAnime
import com.ruzy.animeshub.model.ranking.TypeRakingManga
import com.ruzy.animeshub.util.ResultState
import com.ruzy.animeshub.util.asResultState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel : CoroutineViewModel(), KoinComponent {

    private val updateTopAnimes: UpdateTopAnimes by inject()
    private val updateTopMangas: UpdateTopMangas by inject()
    val getTopAnimeWithPage: GetTopAnimeWithPage by inject()
    val getTopMangaWithPage: GetTopMangaWithPage by inject()

    val uiState: StateFlow<HomeUiState> =
        combine(
            updateTopAnimes.inProgress,
            updateTopMangas.inProgress,
            ::HomeUiState
        ).stateIn(
            coroutineScope,
            SharingStarted.WhileSubscribed(5000),
            HomeUiState.Empty
        )

    fun refresh() {
        coroutineScope.launch {
            updateTopAnimes.invoke(UpdateTopAnimes.Params(TypeRakingAnime.BY_POPULARITY))
            updateTopMangas.invoke(UpdateTopMangas.Params(TypeRakingManga.BY_POPULARITY))
        }
    }

    init {
        refresh()
    }
}

data class HomeUiState(
    val loadingTopAnimes: Boolean = false,
    val loadingTopMangas: Boolean = false
) {
    companion object {
        val Empty = HomeUiState()
    }
}