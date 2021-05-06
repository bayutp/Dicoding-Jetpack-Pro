package com.bayuspace.academy.di

import android.content.Context
import com.bayuspace.academy.data.source.AcademyRepository
import com.bayuspace.academy.data.source.remote.RemoteDataSource
import com.bayuspace.academy.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): AcademyRepository {
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        return AcademyRepository.getInstance(remoteDataSource)
    }
}