package com.bayuspace.academy.ui.bookmark

import androidx.lifecycle.ViewModel
import com.bayuspace.academy.utils.DataDummy

class BookmarkViewModel : ViewModel(){
    fun getBookmarks() = DataDummy.generateDummyCourse()
}