package com.bayuspace.academy.ui.bookmark

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bayuspace.academy.R
import com.bayuspace.academy.databinding.FragmentBookmarkBinding
import com.bayuspace.academy.ui.detail.DetailActivity
import com.bayuspace.academy.viewmodel.ViewModelFactory

class BookmarkFragment : Fragment() {

    private lateinit var bookmarkAdapter: BookmarkAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(requireActivity())
        )[BookmarkViewModel::class.java]
        bookmarkAdapter = BookmarkAdapter({ course ->
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_COURSE, course.courseId)
            requireContext().startActivity(intent)
        }) { course ->
            if (activity != null) {
                val mimeType = "text/plain"
                ShareCompat.IntentBuilder
                    .from(requireActivity())
                    .setType(mimeType)
                    .setChooserTitle("Bagikan aplikasi ini sekarang")
                    .setText(resources.getString(R.string.share_text, course.title))
                    .startChooser()
            }
        }

        val courses = viewModel.getBookmarks()
        bookmarkAdapter.setCourse(courses)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentBookmarkBinding.inflate(layoutInflater, container, false)
        if (activity != null) {
            with(binding.rvBookmark) {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = bookmarkAdapter
            }
        }
        return binding.root
    }
}