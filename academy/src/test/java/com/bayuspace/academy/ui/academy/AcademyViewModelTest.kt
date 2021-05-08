package com.bayuspace.academy.ui.academy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bayuspace.academy.data.CourseEntity
import com.bayuspace.academy.data.source.AcademyRepository
import com.bayuspace.academy.data.source.remote.RemoteDataSource
import com.bayuspace.academy.utils.DataDummy
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class AcademyViewModelTest {

    private lateinit var viewModel: AcademyViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Mock
    private lateinit var observer: Observer<List<CourseEntity>>

    @Before
    fun setUp() {
        viewModel = AcademyViewModel(academyRepository)
    }

    @Test
    fun testGetCourses() {
        val dummyCourse = DataDummy.generateDummyCourse()
        val courses = MutableLiveData<List<CourseEntity>>()
        courses.value = dummyCourse

        `when`(academyRepository.getAllCourses()).thenReturn(courses)
        val courseEntities = viewModel.getCourses().value
        verify(academyRepository).getAllCourses()

        assertNotNull(courseEntities)
        assertEquals(5, courseEntities?.size)

        viewModel.getCourses().observeForever(observer)
        verify(observer).onChanged(dummyCourse)
    }
}