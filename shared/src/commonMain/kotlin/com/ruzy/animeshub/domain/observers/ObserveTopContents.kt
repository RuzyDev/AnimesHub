package com.ruzy.animeshub.domain.observers

import com.ruzy.animeshub.domain.SubjectInteractor
import com.ruzy.animeshub.domain.observers.ObserveTopContents.Params
import com.ruzy.animeshub.domain.repository.TopRepository
import com.ruzy.animeshub.model.anime.AnimeDetails
import com.ruzy.animeshub.model.manga.MangaDetails
import com.ruzy.animeshub.model.ranking.TypeRakingAnime
import com.ruzy.animeshub.model.ranking.TypeRakingManga
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class ObserveTopContents(
    private val topRepository: TopRepository
) : SubjectInteractor<Params, TopContentUiState>() {

    data class Params(
        val typeRakingAnime: TypeRakingAnime,
        val typeRakingManga: TypeRakingManga
    )

    override fun createObservable(params: Params): Flow<TopContentUiState> {
        return combine(
            topRepository.observeTopAnimes(params.typeRakingAnime),
            topRepository.observeTopMangas(params.typeRakingManga),
            ::TopContentUiState
        )
    }
}

data class TopContentUiState(
    val animes: List<AnimeDetails> = emptyList(),
    val mangas: List<MangaDetails> = emptyList()
){
    companion object{
        val Empty = TopContentUiState()
    }
}