package com.example.dicodingjetpackpro.ui.home.bookmark

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ScreenPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> BookmarkContentFragment.newInstance("movie")
            else -> BookmarkContentFragment.newInstance("tv")
        }
    }

}