package com.bayuspace.academy.ui.bookmark

import com.bayuspace.academy.data.source.AcademyRepository
import com.bayuspace.academy.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BookmarkViewModelTest {

    private lateinit var viewModel: BookmarkViewModel

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Before
    fun setUp() {
        viewModel = BookmarkViewModel(academyRepository)
    }

    @Test
    fun getBookmarks() {
        `when`(academyRepository.getBookmarkCourses()).thenReturn(DataDummy.generateDummyCourse())
        val courseEntities = viewModel.getBookmarks()
        verify(academyRepository).getBookmarkCourses()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities.size)
    }
}