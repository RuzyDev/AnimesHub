package com.ruzy.animeshub.domain.observers

import com.ruzy.animeshub.domain.SubjectInteractor
import com.ruzy.animeshub.domain.repository.TopRepository
import com.ruzy.animeshub.model.anime.AnimeDetails
import com.ruzy.animeshub.model.ranking.TypeRakingAnime
import kotlinx.coroutines.flow.Flow

class ObserveTopAnimes(
    private val topRepository: TopRepository
) : SubjectInteractor<ObserveTopAnimes.Params, List<AnimeDetails>>() {

    data class Params(
        val typeRakingAnime: TypeRakingAnime,
        val page: Long = 0
    )

    override fun createObservable(params: Params): Flow<List<AnimeDetails>> {
        return topRepository.observeTopAnimes(params.typeRakingAnime, params.page)
    }
}
