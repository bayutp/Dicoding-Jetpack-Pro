package com.example.dicodingjetpackpro.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.example.dicodingjetpackpro.DataDummy
import com.example.dicodingjetpackpro.di.modules.DepsModuleProvider
import com.example.dicodingjetpackpro.model.entity.MovieEntity
import com.example.dicodingjetpackpro.model.entity.TvEntity
import com.nhaarman.mockitokotlin2.given
import junit.framework.Assert.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.Test
import org.koin.core.inject
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.Mockito
import org.mockito.Mockito.mock

class DataRepositoryTest : KoinTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val dataRepository: DataRepository by inject()
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
        mock(it.java)
    }

    @Test
    fun getDiscoverMovies() {
        runBlocking {
            val service = declareMock<DataRepository> {
                runBlocking { given(getDiscoverMovies()).willReturn(DataDummy.discoverMovies()) }
            }
            val data = dataRepository.getDiscoverMovies()
            Mockito.verify(service).getDiscoverMovies()
            assertEquals(DataDummy.discoverMovies(), data)
        }
    }

    @Test
    fun getDiscoverTvs() {
        runBlocking {
            val service = declareMock<DataRepository> {
                runBlocking { given(getDiscoverTvs()).willReturn(DataDummy.discoverTv()) }
            }
            val data = dataRepository.getDiscoverTvs()
            Mockito.verify(service).getDiscoverTvs()
            assertEquals(DataDummy.discoverTv(), data)
        }
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
            val data = dataRepository.getMovieDetail(movieId)
            Mockito.verify(service).getMovieDetail(movieId)
            assertEquals(DataDummy.getMovieDetail(movieId), data)
        }
    }

    @Test
    fun getSimilarMovies() {
        runBlocking {
            val service = declareMock<DataRepository> {
                runBlocking { given(getSimilarMovies(movieId)).willReturn(DataDummy.getSimilarMovie()) }
            }
            val data = dataRepository.getSimilarMovies(movieId)
            Mockito.verify(service).getSimilarMovies(movieId)
            assertEquals(DataDummy.getSimilarMovie(), data)
        }
    }

    @Test
    fun getTvDetail() {
        runBlocking {
            val service = declareMock<DataRepository> {
                runBlocking { given(getTvDetail(tvId)).willReturn(DataDummy.getTvDetail(tvId)) }
            }
            val data = dataRepository.getTvDetail(tvId)
            Mockito.verify(service).getTvDetail(tvId)
            assertEquals(DataDummy.getTvDetail(tvId), data)
        }
    }

    @Test
    fun getSimilarTvs() {
        runBlocking {
            val service = declareMock<DataRepository> {
                runBlocking { given(getSimilarTvs(tvId)).willReturn(DataDummy.getSimilarTv()) }
            }
            val data = dataRepository.getSimilarTvs(tvId)
            Mockito.verify(service).getSimilarTvs(tvId)
            assertEquals(DataDummy.getSimilarTv(), data)
        }
    }

    @Test
    fun searchMovies() {
        val query = "spiderman"
        runBlocking {
            val service = declareMock<DataRepository> {
                runBlocking { given(searchMovies(query)).willReturn(DataDummy.searchMovies()) }
            }
            val data = dataRepository.searchMovies(query)
            Mockito.verify(service).searchMovies(query)
            assertEquals(DataDummy.searchMovies(), data)
        }
    }

    @Test
    fun searchTvs() {
        val query = "falcon"
        runBlocking {
            val service = declareMock<DataRepository> {
                runBlocking { given(searchTvs(query)).willReturn(DataDummy.searchTv()) }
            }
            val data = dataRepository.searchTvs(query)
            Mockito.verify(service).searchTvs(query)
            assertEquals(DataDummy.searchTv(), data)
        }
    }

    @Test
    fun getMovieBookmarked() {
        val data = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        runBlocking {
            val service = declareMock<DataRepository> {
                runBlocking { given(getMovieBookmarked()).willReturn(data) }
            }
            val result = dataRepository.getMovieBookmarked()
            Mockito.verify(service).getMovieBookmarked()
            assertEquals(data, result)
        }
    }

    @Test
    fun checkMovieBookmarked() {
        runBlocking {
            val service = declareMock<DataRepository> {
                runBlocking {
                    given(checkMovieBookmarked(movieId)).willReturn(null)
                }
            }
            val result = dataRepository.checkMovieBookmarked(movieId)
            Mockito.verify(service).checkMovieBookmarked(movieId)
            assertNull(result)
        }
    }

    @Test
    fun checkTvBookmarked() {
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
            val result = dataRepository.checkTvBookmarked(tvId)
            Mockito.verify(service).checkTvBookmarked(tvId)
            assertNotNull(result)
        }
    }

    @Test
    fun saveBookmark() {
        runBlocking {
            val data = DataDummy.getMovieEntity(movieId, true)
            val service = declareMock<DataRepository> {
                runBlocking {
                    given(saveBookmark(listOf(data))).willReturn(
                        DataDummy.addToMovieBookmark()
                    )
                }
            }
            val result = dataRepository.saveBookmark(listOf(data))
            Mockito.verify(service).saveBookmark(listOf(data))
            assertEquals(DataDummy.addToMovieBookmark(), result)
        }
    }

    @Test
    fun getTvBookmarked() {
        val data = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvEntity>
        runBlocking {
            val service = declareMock<DataRepository> {
                runBlocking { given(getTvBookmarked()).willReturn(data) }
            }
            val result = dataRepository.getTvBookmarked()
            Mockito.verify(service).getTvBookmarked()
            assertEquals(data, result)
        }
    }

    @Test
    fun saveTvBookmark() {
        runBlocking {
            val data = DataDummy.getTvEntity(tvId, true)
            val service = declareMock<DataRepository> {
                runBlocking {
                    given(saveTvBookmark(listOf(data))).willReturn(
                        DataDummy.addToMovieBookmark()
                    )
                }
            }
            val result = dataRepository.saveTvBookmark(listOf(data))
            Mockito.verify(service).saveTvBookmark(listOf(data))
            assertEquals(DataDummy.addToMovieBookmark(), result)
        }
    }
}