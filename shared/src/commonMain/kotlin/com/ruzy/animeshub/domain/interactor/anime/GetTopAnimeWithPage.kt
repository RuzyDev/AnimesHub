package com.ruzy.animeshub.domain.interactor.anime

import com.ruzy.animeshub.domain.Interactor
import com.ruzy.animeshub.domain.repository.TopRepository
import com.ruzy.animeshub.model.ranking.TypeRakingAnime
import com.ruzy.animeshub.util.AppCoroutineDispatchers
import kotlinx.coroutines.withContext

class GetTopAnimeWithPage(
    private val topRepository: TopRepository,
    private val dispatchers: AppCoroutineDispatchers
) {
    suspend fun invoke(
        typeRakingAnime: TypeRakingAnime,
        page: Long
    ) = withContext(dispatchers.io) { topRepository.getTopAnimes(typeRakingAnime, page) }
}