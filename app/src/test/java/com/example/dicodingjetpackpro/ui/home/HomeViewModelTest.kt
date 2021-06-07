package com.example.dicodingjetpackpro.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.dicodingjetpackpro.DataDummy
import com.example.dicodingjetpackpro.di.modules.DepsModuleProvider
import com.example.dicodingjetpackpro.repository.DataRepository
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.Mockito

class HomeViewModelTest : KoinTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val homeViewModel: HomeViewModel by inject()

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
    fun getDiscoverMovies() {
        runBlocking {
            val service = declareMock<DataRepository> {
                runBlocking { given(getDiscoverMovies()).willReturn(DataDummy.discoverMovies()) }
            }
            homeViewModel.getDiscoverMovies()
            val result = homeViewModel.observeDiscoverMovies().value
            Mockito.verify(service).getDiscoverMovies()
            assertNotNull(result)
        }

    }

    @Test
    fun getDiscoverTvs() {
        runBlocking {
            val service = declareMock<DataRepository> {
                runBlocking { given(getDiscoverTvs()).willReturn(DataDummy.discoverTv()) }
            }
            homeViewModel.getDiscoverTvs()
            val result = homeViewModel.observeDiscoverTvs().value
            Mockito.verify(service).getDiscoverTvs()
            assertNotNull(result)
        }
    }

    @Test
    fun searchTvs() {
        val query = "falcon"
        runBlocking {
            val service = declareMock<DataRepository> {
                runBlocking { given(searchTvs(query)).willReturn(DataDummy.searchTv()) }
            }
            homeViewModel.searchTvs(query)
            val result = homeViewModel.observeDiscoverTvs().value
            Mockito.verify(service).searchTvs(query)
            assertNotNull(result)
        }
    }

    @Test
    fun searchTvsEmpty() {
        val query = "warkop reborn"
        runBlocking {
            val service = declareMock<DataRepository> {
                runBlocking { given(searchTvs(query)).willReturn(null) }
            }
            homeViewModel.searchTvs(query)
            val result = homeViewModel.observeDiscoverTvs().value
            Mockito.verify(service).searchTvs(query)
            assertNull(result)
        }
    }

    @Test
    fun searchMovies() {
        val query = "spiderman"
        runBlocking {
            val service = declareMock<DataRepository> {
                runBlocking { given(searchMovies(query)).willReturn(DataDummy.searchMovies()) }
            }
            homeViewModel.searchMovies(query)
            val result = homeViewModel.observeDiscoverMovies().value
            Mockito.verify(service).searchMovies(query)
            assertNotNull(result)
        }
    }

    @Test
    fun searchMoviesEmpty() {
        val query = "mbak kunti"
        runBlocking {
            val service = declareMock<DataRepository> {
                runBlocking { given(searchMovies(query)).willReturn(null) }
            }
            homeViewModel.searchMovies(query)
            val result = homeViewModel.observeDiscoverMovies().value
            Mockito.verify(service).searchMovies(query)
            assertNull(result)
        }
    }


}