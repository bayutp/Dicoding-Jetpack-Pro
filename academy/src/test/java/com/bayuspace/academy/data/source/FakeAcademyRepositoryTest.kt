package com.bayuspace.academy.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.bayuspace.academy.data.CourseEntity
import com.bayuspace.academy.data.source.remote.RemoteDataSource
import com.bayuspace.academy.data.source.remote.response.CourseResponse
import com.bayuspace.academy.ui.academy.AcademyViewModel
import com.bayuspace.academy.utils.DataDummy
import com.bayuspace.academy.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito

class FakeAcademyRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val academyRepository = FakeAcademyRepository(remote)

    private val courseResponse = DataDummy.generateDummyCourse()
    private val courseId = courseResponse[0].courseId
    private val moduleResponse = DataDummy.generateDummyModules(courseId)
    private val moduleId = moduleResponse[0].moduleId
    //private val content = DataDummy.generateRemoteDummyContent(moduleId)

    @Test
    fun getAllCourses() {
        doAnswer {
            (it.arguments as RemoteDataSource.LoadCourseCallback).onAllCoursesReceived(
                courseResponse.map { data -> CourseResponse.mapToCourseResponse(data) }
            )
            null
        }.`when`(remote).getAllCourses(any())

        val courseEntities = LiveDataTestUtil.getValue(academyRepository.getAllCourses())
        verify(remote).getAllCourses(any())

        assertNotNull(courseEntities)
        assertEquals(courseEntities.size.toLong(), courseEntities.size.toLong())
    }

    @Test
    fun getBookmarkCourses() {
    }

    @Test
    fun getCourseWithModules() {
    }

    @Test
    fun getAllModulesByCourse() {
    }

    @Test
    fun getContent() {
    }
}