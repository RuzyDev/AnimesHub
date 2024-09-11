package com.ruzy.animeshub.domain.observers

import com.ruzy.animeshub.domain.SubjectInteractor
import com.ruzy.animeshub.domain.repository.AnimesHubRepository
import com.ruzy.animeshub.model.anime.AnimeDetails
import kotlinx.coroutines.flow.Flow

class ObserveAnimes(
    private val animesHubRepository: AnimesHubRepository
) : SubjectInteractor<Unit, List<AnimeDetails>>() {

    override fun createObservable(params: Unit): Flow<List<AnimeDetails>> {
        return animesHubRepository.observeAllAnimes()
    }
}