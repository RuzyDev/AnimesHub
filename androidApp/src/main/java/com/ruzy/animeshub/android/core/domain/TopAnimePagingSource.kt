package com.ruzy.animeshub.android.core.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ruzy.animeshub.domain.interactor.anime.GetTopAnimeWithPage
import com.ruzy.animeshub.model.anime.AnimeDetails
import com.ruzy.animeshub.model.ranking.TypeRakingAnime

class TopAnimePagingSource(
    private val getTopAnimeWithPage: GetTopAnimeWithPage,
    private val typeRakingAnime: TypeRakingAnime
) : PagingSource<Int, AnimeDetails>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimeDetails> {
        val page = params.key ?: 0
        return try {
            val items = getTopAnimeWithPage.invoke(typeRakingAnime, page.toLong())

            LoadResult.Page(
                data = items,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (items.size < 20) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, AnimeDetails>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }
}