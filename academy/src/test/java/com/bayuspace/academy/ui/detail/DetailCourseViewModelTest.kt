package com.bayuspace.academy.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bayuspace.academy.data.CourseEntity
import com.bayuspace.academy.data.ModuleEntity
import com.bayuspace.academy.data.source.AcademyRepository
import com.bayuspace.academy.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailCourseViewModelTest {

    private lateinit var viewModel: DetailCourseViewModel
    private val dummyCourse = DataDummy.generateDummyCourse()[0]
    private val courseId = dummyCourse.courseId
    private val dummyModules = DataDummy.generateDummyModules(courseId)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Mock
    private lateinit var observer: Observer<CourseEntity>

    @Mock
    private lateinit var moduleObserver: Observer<List<ModuleEntity>>


    @Before
    fun setUp() {
        viewModel = DetailCourseViewModel(academyRepository)
        viewModel.selectedCourse(courseId)
    }

    @Test
    fun getCourse() {
        val course = MutableLiveData<CourseEntity>()
        course.value = dummyCourse

        `when`(academyRepository.getCourseWithModules(courseId)).thenReturn(course)
        val courseEntity = viewModel.getCourse().value
        verify(academyRepository).getCourseWithModules(courseId)

        assertNotNull(courseEntity)
        assertEquals(dummyCourse.courseId, courseEntity?.courseId)
        assertEquals(dummyCourse.bookmark, courseEntity?.bookmark)
        assertEquals(dummyCourse.deadline, courseEntity?.deadline)
        assertEquals(dummyCourse.description, courseEntity?.description)
        assertEquals(dummyCourse.imagePath, courseEntity?.imagePath)
        assertEquals(dummyCourse.title, courseEntity?.title)

        viewModel.getCourse().observeForever(observer)
        verify(observer).onChanged(dummyCourse)
    }

    @Test
    fun getModules() {
        val modules = MutableLiveData<List<ModuleEntity>>()
        modules.value = dummyModules

        `when`(academyRepository.getAllModulesByCourse(courseId)).thenReturn(modules)
        val moduleEntities = viewModel.getModules().value
        verify(academyRepository).getAllModulesByCourse(courseId)

        assertNotNull(moduleEntities)
        assertEquals(7, moduleEntities?.size)

        viewModel.getModules().observeForever(moduleObserver)
        verify(moduleObserver).onChanged(dummyModules)
    }
}