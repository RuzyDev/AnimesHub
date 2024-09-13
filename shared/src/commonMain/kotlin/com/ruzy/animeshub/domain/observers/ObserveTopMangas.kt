package com.ruzy.animeshub.domain.observers

import com.ruzy.animeshub.domain.SubjectInteractor
import com.ruzy.animeshub.domain.repository.TopRepository
import com.ruzy.animeshub.model.manga.MangaDetails
import com.ruzy.animeshub.model.ranking.TypeRakingManga
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class ObserveTopMangas(
    private val topRepository: TopRepository
) : SubjectInteractor<ObserveTopMangas.Params, List<MangaDetails>>() {

    data class Params(
        val typeRakingManga: TypeRakingManga,
        val page: Long = 0
    )

    override fun createObservable(params: Params): Flow<List<MangaDetails>> {
        return topRepository.observeTopMangas(params.typeRakingManga, params.page)
    }
}
