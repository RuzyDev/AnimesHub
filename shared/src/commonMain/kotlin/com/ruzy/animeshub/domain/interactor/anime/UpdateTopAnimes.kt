package com.ruzy.animeshub.domain.interactor.anime

import com.ruzy.animeshub.domain.Interactor
import com.ruzy.animeshub.domain.repository.TopRepository
import com.ruzy.animeshub.model.ranking.TypeRakingAnime
import com.ruzy.animeshub.util.AppCoroutineDispatchers
import kotlinx.coroutines.withContext

class UpdateTopAnimes(
    private val topRepository: TopRepository,
    private val dispatchers: AppCoroutineDispatchers
) : Interactor<UpdateTopAnimes.Params, Unit>() {

    data class Params(
        val typeRakingAnime: TypeRakingAnime
    )

    override suspend fun doWork(params: Params) {
        withContext(dispatchers.io) {
            topRepository.updateTopAnimes(params.typeRakingAnime)
        }
    }
}