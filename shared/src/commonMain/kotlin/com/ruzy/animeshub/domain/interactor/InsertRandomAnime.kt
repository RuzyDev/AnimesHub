package com.ruzy.animeshub.domain.interactor

import com.ruzy.animeshub.domain.Interactor
import com.ruzy.animeshub.domain.repository.AnimesHubRepository
import com.ruzy.animeshub.util.AppCoroutineDispatchers
import kotlinx.coroutines.withContext

class InsertRandomAnime(
    private val animesHubRepository: AnimesHubRepository,
    private val dispatchers: AppCoroutineDispatchers
) : Interactor<Unit, Unit>() {

    override suspend fun doWork(params: Unit) {
        withContext(dispatchers.io) {
            animesHubRepository.insertRandomAnime()
        }
    }
}