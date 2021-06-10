package com.example.dicodingjetpackpro

import com.example.dicodingjetpackpro.base.ResourceState
import com.example.dicodingjetpackpro.base.ResponseWrapper
import com.example.dicodingjetpackpro.model.entity.MovieEntity
import com.example.dicodingjetpackpro.model.entity.TvEntity
import com.example.dicodingjetpackpro.model.response.movie.*
import com.example.dicodingjetpackpro.model.response.tv.*
import com.nhaarman.mockitokotlin2.any
import java.text.SimpleDateFormat
import java.util.*

object DataDummy {
    fun discoverMovies(): ResourceState<ResponseWrapper<MovieResponse>> {
        return ResourceState.Success(
            ResponseWrapper(null, null, MovieResponse(1, getMovieList(), 500, 1000), null)
        )
    }

    fun discoverTv(): ResourceState<ResponseWrapper<TvResponse>> {
        return ResourceState.Success(
            ResponseWrapper(null, null, TvResponse(1, getTvList(), 500, 1000), null)
        )
    }

    fun searchMovies(): ResourceState<ResponseWrapper<MovieResponse>> {
        return ResourceState.Success(
            ResponseWrapper(null, null, MovieResponse(1, getMovieList(), 10, 10), null)
        )
    }

    fun searchTv(): ResourceState<ResponseWrapper<TvResponse>> {
        return ResourceState.Success(
            ResponseWrapper(null, null, TvResponse(1, getTvList(), 10, 10), null)
        )
    }

    fun getMovieDetail(movieId: Int): ResourceState<ResponseWrapper<MovieDetailResponse>> {
        return ResourceState.Success(
            ResponseWrapper(null, null, getMovieDetailResponse(movieId), null)
        )
    }

    fun getTvDetail(tvId: Int): ResourceState<ResponseWrapper<TvDetailResponse>> {
        return ResourceState.Success(
            ResponseWrapper(null, null, getTvDetailResponse(tvId), null)
        )
    }

    private fun getTvDetailResponse(tvId: Int): TvDetailResponse {
        return TvDetailResponse(
            "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
            listOf(
                CreatedBy(
                    "605508e2960cde00721fc5e8",
                    2,
                    1868712,
                    "Malcolm Spellman",
                    "/cNqxh76tysL9D8RcCKR6XqcoyW0.jpg"
                )
            ),
            listOf(50),
            "2021-03-19",
            listOf(Genre(10765, "Sci-Fi & Fantasy")),
            "https://www.disneyplus.com/series/the-falcon-and-the-winter-soldier/4gglDBMx8icA",
            tvId,
            false,
            listOf("en", "de"),
            "2021-04-23",
            LastEpisodeToAir(
                "2021-04-23",
                6,
                2558743,
                "One World, One People",
                "As The Flag Smashers escalate their efforts, Sam and Bucky take action.",
                "",
                1,
                "/qXxCqMP7aj3rGndhVfGUyyU6hyq.jpg",
                6.8,
                11
            ),
            "The Falcon and the Winter Soldier",
            listOf(Network(2739, "/gJ8VX6JSu3ciXHuC2dDGAo2lvwM.png", "Disney+", "US")),
            null,
            6,
            1,
            listOf("US"),
            "en",
            "he Falcon and the Winter Soldier",
            "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
            909.726,
            "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
            listOf(
                ProductionCompany(
                    420,
                    "/hUzeosd33nzE5MCNsZxCGEKTXaQ.png",
                    "Marvel Studios",
                    "US"
                )
            ),
            listOf(ProductionCountry("US", "United States of America")),
            listOf(
                Season(
                    "2021-03-19",
                    6,
                    156676,
                    "Season 1",
                    "",
                    "/fIT6Y6O3cUX1X8qY8pZgzEvxUDQ.jpg",
                    1
                )
            ),
            listOf(SpokenLanguage("English", "en", "English")),
            "Ended",
            "Honor the shield.",
            "Miniseries",
            7.9,
            5773
        )
    }

    fun getSimilarMovie(): ResourceState<ResponseWrapper<MovieResponse>> {
        return ResourceState.Success(
            ResponseWrapper(null, null, MovieResponse(1, getMovieList(), 10, 10), null)
        )
    }

    fun getSimilarTv(): ResourceState<ResponseWrapper<TvResponse>> {
        return ResourceState.Success(
            ResponseWrapper(null, null, TvResponse(1, getTvList(), 10, 10), null)
        )
    }

    fun addToMovieBookmark(): ResourceState<ResponseWrapper<Unit>> {
        return ResourceState.Success(
            ResponseWrapper(null, null, Unit, null)
        )
    }

    fun checkMovieBookmark(movieId: Int): ResourceState<ResponseWrapper<MovieEntity>> {
        return ResourceState.Success(
            ResponseWrapper(null, null, getMovieEntity(movieId, true), null)
        )
    }

    fun checkTvBookmark(tvId: Int): ResourceState<ResponseWrapper<TvEntity>> {
        return ResourceState.Success(
            ResponseWrapper(null, null, getTvEntity(tvId, true), null)
        )
    }

    fun getMovieEntity(movieId: Int, isBookmark: Boolean) = MovieEntity(
        movieId,
        "Cruella",
        "/A0knvX7rlwTyZSKj8H5NiARb45.jpg",
        "2021-05-26",
        isBookmark,
        getCurrentDate()
    )

    fun getTvEntity(tvId: Int, isBookmark: Boolean) = TvEntity(
        tvId,
        "The Falcon and the Winter Soldier",
        "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
        "2021-03-19",
        isBookmark,
        getCurrentDate()
    )

    private fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        return sdf.format(Date())
    }

    private fun getMovieDetailResponse(movieId: Int): MovieDetailResponse {
        return MovieDetailResponse(
            false,
            "/6MKr3KgOLmzOP6MSuZERO41Lpkt.jpg",
            200000000,
            listOf(Genre(35, "Comedy")),
            "https://movies.disney.com/cruella",
            movieId,
            "tt3228774",
            "en",
            "Cruella",
            "In 1970s London amidst the punk rock revolution...",
            9320.775,
            "/A0knvX7rlwTyZSKj8H5NiARb45.jpg",
            listOf(
                ProductionCompany(
                    2,
                    "/wdrCwmRnLFJhEoH8GSfymY85KHT.png",
                    "Walt Disney Pictures",
                    "US"
                )
            ),
            listOf(ProductionCountry("US", "United States of America")),
            "2021-05-26",
            87084673,
            134,
            listOf(SpokenLanguage("English", "en", "English")),
            "Released",
            "Hello Cruel World",
            "Cruella",
            false,
            8.7,
            2034
        )

    }

    private fun getMovieList(): List<Result> {
        val result = mutableListOf<Result>()
        for (i in 0..10) {
            val data =
                Result(id = i, title = "title $i", posterPath = "/bT3c4TSOP8vBmMoXZRDPTII6eDa.jpg")
            result.add(data)
        }
        return result
    }

    fun getMovieEntities(): List<MovieEntity> {
        val result = mutableListOf<MovieEntity>()
        for (i in 0..10) {
            val data = MovieEntity(
                i,
                "title $i",
                "/bT3c4TSOP8vBmMoXZRDPTII6eDa.jpg",
                "2021-05-26",
                createdAt = getCurrentDate()
            )
            result.add(data)
        }
        return result
    }

    fun getTvEntities(): List<TvEntity> {
        val result = mutableListOf<TvEntity>()
        for (i in 0..10) {
            val data = TvEntity(
                i,
                "title $i",
                "/bT3c4TSOP8vBmMoXZRDPTII6eDa.jpg",
                "2021-05-26",
                createdAt = getCurrentDate()
            )
            result.add(data)
        }
        return result
    }

    private fun getTvList(): List<TvResult> {
        val result = mutableListOf<TvResult>()
        for (i in 0..10) {
            val data =
                TvResult(id = i, name = "title $i", posterPath = "/bT3c4TSOP8vBmMoXZRDPTII6eDa.jpg")
            result.add(data)
        }
        return result
    }


}