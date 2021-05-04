package com.bayuspace.academy.ui.detail

import com.bayuspace.academy.utils.DataDummy
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailCourseViewModelTest {

    private lateinit var viewModel: DetailCourseViewModel
    private val dummyCourse = DataDummy.generateDummyCourse()[0]
    private val courseId = dummyCourse.courseId

    @Before
    fun setUp() {
        viewModel = DetailCourseViewModel()
        viewModel.selectedCourse(courseId)
    }

    @Test
    fun getCourse() {
        val courseEntity = viewModel.getCourse()
        assertNotNull(courseEntity)
        assertEquals(dummyCourse.courseId, courseEntity.courseId)
        assertEquals(dummyCourse.bookmark, courseEntity.bookmark)
        assertEquals(dummyCourse.deadline, courseEntity.deadline)
        assertEquals(dummyCourse.description, courseEntity.description)
        assertEquals(dummyCourse.imagePath, courseEntity.imagePath)
        assertEquals(dummyCourse.title, courseEntity.title)
    }

    @Test
    fun getModules() {
        val moduleEntities = viewModel.getModules()
        assertNotNull(moduleEntities)
        assertEquals(7, moduleEntities.size)
    }
}