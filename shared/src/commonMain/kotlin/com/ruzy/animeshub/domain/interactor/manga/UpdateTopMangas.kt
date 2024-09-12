package com.ruzy.animeshub.domain.interactor.manga

import com.ruzy.animeshub.domain.Interactor
import com.ruzy.animeshub.domain.repository.TopRepository
import com.ruzy.animeshub.model.ranking.TypeRakingManga
import com.ruzy.animeshub.util.AppCoroutineDispatchers
import kotlinx.coroutines.withContext

class UpdateTopMangas(
    private val topRepository: TopRepository,
    private val dispatchers: AppCoroutineDispatchers
) : Interactor<UpdateTopMangas.Params, Unit>() {

    data class Params(
        val typeRakingAnime: TypeRakingManga
    )

    override suspend fun doWork(params: Params) {
        withContext(dispatchers.io) {
            topRepository.updateTopMangas(params.typeRakingAnime)
        }
    }
}