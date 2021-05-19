package com.bayuspace.academy.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.bayuspace.academy.data.source.local.LocaleDataSource
import com.bayuspace.academy.data.source.local.entity.CourseEntity
import com.bayuspace.academy.data.source.local.entity.CourseWithModule
import com.bayuspace.academy.data.source.local.entity.ModuleEntity
import com.bayuspace.academy.data.source.remote.RemoteDataSource
import com.bayuspace.academy.utils.AppExecutor
import com.bayuspace.academy.utils.DataDummy
import com.bayuspace.academy.utils.LiveDataTestUtil
import com.bayuspace.academy.utils.PagedListUtil
import com.bayuspace.academy.vo.Resource
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class FakeAcademyRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocaleDataSource::class.java)
    private val appExecutors = mock(AppExecutor::class.java)
    private val academyRepository = FakeAcademyRepository(remote, local, appExecutors)

    private val courseResponse = DataDummy.generateRemoteDummyCourses()
    private val courseId = courseResponse[0].id
    private val moduleResponse = DataDummy.generateRemoteDummyModules(courseId)
    private val moduleId = moduleResponse[0].moduleId
    private val content = DataDummy.generateRemoteDummyContent(moduleId)

    @Test
    fun getAllCourses() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, CourseEntity>
        `when`(local.getAllCourses()).thenReturn(dataSourceFactory)
        academyRepository.getAllCourses()

        val courseEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyCourse()))
        verify(local).getAllCourses()
        assertNotNull(courseEntities.data)
        assertEquals(courseResponse.size.toLong(), courseEntities.data?.size?.toLong())
    }

    @Test
    fun getBookmarkCourses() {

        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, CourseEntity>
        `when`(local.getBookmarkedCourses()).thenReturn(dataSourceFactory)
        academyRepository.getBookmarkCourses()

        val courseEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyCourse()))

        verify(local).getBookmarkedCourses()

        assertNotNull(courseEntities)
        assertEquals(courseResponse.size.toLong(), courseEntities.data?.size?.toLong())
    }

    @Test
    fun getCourseWithModules() {
        val dummyEntity = MutableLiveData<CourseWithModule>()
        dummyEntity.value = DataDummy.generateDummyCourseWithModules(DataDummy.generateDummyCourse()[0], false)
        `when`(local.getCourseWithModules(courseId)).thenReturn(dummyEntity)

        val courseEntities =
            LiveDataTestUtil.getValue(academyRepository.getCourseWithModules(courseId))

        verify(local).getCourseWithModules(courseId)

        assertNotNull(courseEntities.data)
        assertNotNull(courseEntities.data?.mCourse?.title)
        assertEquals(courseResponse[0].title, courseEntities.data?.mCourse?.title)
    }

    @Test
    fun getAllModulesByCourse() {
        val dummyModules = MutableLiveData<List<ModuleEntity>>()
        dummyModules.value = DataDummy.generateDummyModules(courseId)
        `when`(local.getAllModulesByCourse(courseId)).thenReturn(dummyModules)

        val courseEntities =
            LiveDataTestUtil.getValue(academyRepository.getAllModulesByCourse(courseId))

        verify(local).getAllModulesByCourse(eq(courseId))

        assertNotNull(courseEntities.data)
        assertEquals(moduleResponse.size.toLong(), courseEntities.data?.size?.toLong())
    }

    @Test
    fun getContent() {
        val dummyEntity = MutableLiveData<ModuleEntity>()
        dummyEntity.value = DataDummy.generateDummyModuleWithContent(moduleId)
        `when`(local.getModuleWithContent(courseId)).thenReturn(dummyEntity)

        val courseEntitiesContent =
            LiveDataTestUtil.getValue(academyRepository.getContent(courseId))

        verify(local)
            .getModuleWithContent(eq(courseId))

        assertNotNull(courseEntitiesContent)
        assertNotNull(courseEntitiesContent.data)
        assertNotNull(courseEntitiesContent.data?.contentEntity?.content)
        assertEquals(content.content, courseEntitiesContent.data?.contentEntity?.content)
    }
}