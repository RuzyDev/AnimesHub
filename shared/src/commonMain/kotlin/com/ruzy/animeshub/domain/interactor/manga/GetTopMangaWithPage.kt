package com.ruzy.animeshub.domain.interactor.manga

import com.ruzy.animeshub.domain.Interactor
import com.ruzy.animeshub.domain.repository.TopRepository
import com.ruzy.animeshub.model.ranking.TypeRakingAnime
import com.ruzy.animeshub.model.ranking.TypeRakingManga
import com.ruzy.animeshub.util.AppCoroutineDispatchers
import kotlinx.coroutines.withContext

class GetTopMangaWithPage(
    private val topRepository: TopRepository,
    private val dispatchers: AppCoroutineDispatchers
) {
    suspend fun invoke(
        typeRakingAnime: TypeRakingManga,
        page: Long
    ) = withContext(dispatchers.io) { topRepository.getTopMangas(typeRakingAnime, page) }
}