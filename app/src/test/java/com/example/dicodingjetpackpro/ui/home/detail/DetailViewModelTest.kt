package com.example.dicodingjetpackpro.ui.home.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.dicodingjetpackpro.DataDummy
import com.example.dicodingjetpackpro.di.modules.DepsModuleProvider
import com.example.dicodingjetpackpro.repository.DataRepository
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.Mockito

class DetailViewModelTest : KoinTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val detailViewModel: DetailViewModel by inject()
    private val movieId = 337404
    private val tvId = 88396

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
    fun getMovieDetail() {
        runBlocking {
            val service = declareMock<DataRepository> {
                runBlocking {
                    given(getMovieDetail(movieId)).willReturn(
                        DataDummy.getMovieDetail(
                            movieId
                        )
                    )
                }
            }
            detailViewModel.getMovieDetail(movieId)
            val result = detailViewModel.observeGetDMovieDetailSuccess().value
            Mockito.verify(service).getMovieDetail(movieId)
            assertNotNull(result)
        }
    }

    @Test
    fun getSimilarMovies() {
        runBlocking {
            val service = declareMock<DataRepository> {
                runBlocking {
                    given(getSimilarMovies(movieId)).willReturn(DataDummy.getSimilarMovie())
                }
            }
            detailViewModel.getSimilarMovies(movieId)
            val result = detailViewModel.observeGetSimilarMovieSuccess().value
            Mockito.verify(service).getSimilarMovies(movieId)
            assertNotNull(result)
        }
    }

    @Test
    fun getTvDetail() {
        runBlocking {
            val service = declareMock<DataRepository> {
                runBlocking {
                    given(getTvDetail(tvId)).willReturn(
                        DataDummy.getTvDetail(
                            tvId
                        )
                    )
                }
            }
            detailViewModel.getTvDetail(tvId)
            val result = detailViewModel.observeGetTvDetailSuccess().value
            Mockito.verify(service).getTvDetail(tvId)
            assertNotNull(result)
        }
    }

    @Test
    fun getSimilarTvs() {
        runBlocking {
            val service = declareMock<DataRepository> {
                runBlocking {
                    given(getSimilarTvs(tvId)).willReturn(DataDummy.getSimilarTv())
                }
            }
            detailViewModel.getSimilarTvs(tvId)
            val result = detailViewModel.observeGetSimilarTvSuccess().value
            Mockito.verify(service).getSimilarTvs(tvId)
            assertNotNull(result)
        }
    }

    @Test
    fun addMovieToBookmark() {
        val data = DataDummy.getMovieEntity(movieId, true)
        runBlocking {
            val service = declareMock<DataRepository> {
                runBlocking { given(saveBookmark(listOf(data))).willReturn(DataDummy.addToMovieBookmark()) }
            }
            detailViewModel.addMovieToBookmark(data, true)
            Mockito.verify(service).saveBookmark(listOf(data))
        }
    }

    @Test
    fun checkMovieBookmark() {
        runBlocking {
            val service = declareMock<DataRepository> {
                runBlocking {
                    given(checkMovieBookmarked(movieId)).willReturn(
                        DataDummy.checkMovieBookmark(
                            movieId
                        )
                    )
                }
            }
            detailViewModel.checkMovieBookmark(movieId)
            val result = detailViewModel.observeCheckBookmarkSuccess().value
            Mockito.verify(service).checkMovieBookmarked(movieId)
            assertEquals(true, result)
        }
    }

    @Test
    fun addTvToBookmark() {
        val data = DataDummy.getTvEntity(tvId, true)
        runBlocking {
            val service = declareMock<DataRepository> {
                runBlocking { given(saveTvBookmark(listOf(data))).willReturn(DataDummy.addToMovieBookmark()) }
            }
            detailViewModel.addTvToBookmark(data, true)
            Mockito.verify(service).saveTvBookmark(listOf(data))
        }
    }

    @Test
    fun checkTvBookmark() {
        runBlocking {
            val service = declareMock<DataRepository> {
                runBlocking {
                    given(checkTvBookmarked(tvId)).willReturn(
                        DataDummy.checkTvBookmark(
                            tvId
                        )
                    )
                }
            }
            detailViewModel.checkTvBookmark(tvId)
            val result = detailViewModel.observeCheckBookmarkSuccess().value
            Mockito.verify(service).checkTvBookmarked(tvId)
            assertEquals(true, result)
        }
    }
}