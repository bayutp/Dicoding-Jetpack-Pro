package com.bayuspace.academy.ui.home

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.bayuspace.academy.R
import com.bayuspace.academy.ui.academy.AcademyFragment
import com.bayuspace.academy.ui.bookmark.BookmarkFragment

class SectionPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = arrayOf(R.string.home, R.string.bookmark)
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> AcademyFragment()
            else -> BookmarkFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.getString(TAB_TITLES[position])
    }

}