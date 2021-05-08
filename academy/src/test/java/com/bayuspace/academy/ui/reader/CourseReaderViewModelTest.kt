package com.bayuspace.academy.ui.reader

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bayuspace.academy.data.ContentEntity
import com.bayuspace.academy.data.CourseEntity
import com.bayuspace.academy.data.ModuleEntity
import com.bayuspace.academy.data.source.AcademyRepository
import com.bayuspace.academy.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class CourseReaderViewModelTest {

    private lateinit var viewModel: CourseReaderViewModel

    private val dummyCourse = DataDummy.generateDummyCourse()[0]
    private val courseId = dummyCourse.courseId
    private val dummyModules = DataDummy.generateDummyModules(courseId)
    private val moduleId = dummyModules[0].moduleId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Mock
    private lateinit var observer: Observer<List<ModuleEntity>>

    @Mock
    private lateinit var moduleObserver: Observer<ModuleEntity>

    @Before
    fun setUp() {
        viewModel = CourseReaderViewModel(academyRepository)
        viewModel.selectedCourse(courseId)
        viewModel.selectedModule(moduleId)

        val dummyModule = dummyModules[0]
        dummyModule.contentEntity =
            ContentEntity("<h3 class=\\\"fr-text-bordered\\\">" + dummyModule.title + "</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>")

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

        viewModel.getModules().observeForever(observer)
        verify(observer).onChanged(dummyModules)
    }

    @Test
    fun getSelectedModule() {
        val module = MutableLiveData<ModuleEntity>()
        module.value = dummyModules[0]

        `when`(academyRepository.getContent(courseId, moduleId)).thenReturn(module)
        val moduleEntity = viewModel.getSelectedModule().value
        verify(academyRepository).getContent(courseId, moduleId)

        val contentEntity = moduleEntity?.contentEntity
        val content = contentEntity?.content
        assertNotNull(moduleEntity)
        assertNotNull(contentEntity)
        assertNotNull(content)
        assertEquals(content, dummyModules[0].contentEntity?.content)

        viewModel.getSelectedModule().observeForever(moduleObserver)
        verify(moduleObserver).onChanged(dummyModules[0])
    }
}