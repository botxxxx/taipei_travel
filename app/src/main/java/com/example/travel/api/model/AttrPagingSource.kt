package com.example.travel.api.model

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Page
import androidx.paging.PagingState
import com.example.travel.api.ApiService

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class AttrPagingSource(
    private val service: ApiService,
    private val langType: LangType,
) : PagingSource<Int, ATTR002_Rs>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ATTR002_Rs> {
        val page = params.key ?: UNSPLASH_STARTING_PAGE_INDEX
        return try {
            val response = service.getAttractions(langType.lang, page = page)
            val attr = response.data
            Page(
                data = attr,
                prevKey = if (page == UNSPLASH_STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (page == response.total) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ATTR002_Rs>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}
