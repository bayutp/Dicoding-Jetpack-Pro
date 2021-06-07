package com.example.dicodingjetpackpro.ui.home.bookmark

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.example.dicodingjetpackpro.DataDummy
import com.example.dicodingjetpackpro.PagedListUtils
import com.example.dicodingjetpackpro.di.modules.DepsModuleProvider
import com.example.dicodingjetpackpro.model.entity.MovieEntity
import com.example.dicodingjetpackpro.model.entity.TvEntity
import com.example.dicodingjetpackpro.repository.DataRepository
import com.nhaarman.mockitokotlin2.given
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class BookmarkViewModelTest : KoinTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val bookmarkViewModel: BookmarkViewModel by inject()

    @ExperimentalCoroutinesApi
    @get:Rule
    val koinRule = KoinTestRule.create {
        modules(DepsModuleProvider.modules)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @get:Rule
    val mockProvider = MockProviderRule.create {
        Mockito.mock(it.java)
    }

    @Test
    fun getMovies() {
        val data = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        runBlocking {
            val service = declareMock<DataRepository> {
                given(getMovieBookmarked()).willReturn(data)
            }
            bookmarkViewModel.getMovies()
            val result = PagedListUtils.mockPagedList(DataDummy.getMovieEntities())
            verify(service).getMovieBookmarked()
            assertNotNull(result)
        }
    }

    @Test
    fun getTvs() {
        val data = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvEntity>
        runBlocking {
            val service = declareMock<DataRepository> {
                given(getTvBookmarked()).willReturn(data)
            }
            bookmarkViewModel.getTvs()
            val result = PagedListUtils.mockPagedList(DataDummy.getTvEntities())
            verify(service).getTvBookmarked()
            assertNotNull(result)
        }
    }
}