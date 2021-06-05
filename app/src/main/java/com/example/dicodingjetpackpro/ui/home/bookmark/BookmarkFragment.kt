package com.example.dicodingjetpackpro.ui.home.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dicodingjetpackpro.base.BaseFragment
import com.example.dicodingjetpackpro.databinding.FragmentBookmarkBinding
import com.example.dicodingjetpackpro.ui.home.MainActivity
import com.google.android.material.tabs.TabLayoutMediator

class BookmarkFragment : BaseFragment() {

    private lateinit var _binding: FragmentBookmarkBinding
    private lateinit var vpAdapter: ScreenPagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        vpAdapter = ScreenPagerAdapter(this)
        with(_binding) {
            vpBookmark.adapter = vpAdapter
            TabLayoutMediator(tabBookmark, vpBookmark) { tab, position ->
                tab.text = when (position) {
                    0 -> "Movies"
                    else -> "Tv Shows"
                }
            }.attach()
        }

        (activity as MainActivity).hideBottomNavigation(false)
    }

    override fun observeData() {}
}