package com.bayuspace.academy.di

import android.content.Context
import com.bayuspace.academy.data.source.AcademyRepository
import com.bayuspace.academy.data.source.local.LocaleDataSource
import com.bayuspace.academy.data.source.local.room.AcademyDatabase
import com.bayuspace.academy.data.source.remote.RemoteDataSource
import com.bayuspace.academy.utils.AppExecutor
import com.bayuspace.academy.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): AcademyRepository {
        val database = AcademyDatabase.getInstance(context)
        val localDataSource = LocaleDataSource.getInstance(database.academyDao())
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val executor = AppExecutor()
        return AcademyRepository.getInstance(remoteDataSource, localDataSource, executor)
    }
}