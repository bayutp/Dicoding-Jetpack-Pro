package com.bayuspace.academy.ui.bookmark

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bayuspace.academy.R
import com.bayuspace.academy.databinding.FragmentBookmarkBinding
import com.bayuspace.academy.ui.detail.DetailActivity
import com.bayuspace.academy.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class BookmarkFragment : Fragment() {

    private lateinit var bookmarkAdapter: BookmarkAdapter
    private lateinit var _binding: FragmentBookmarkBinding
    private lateinit var viewModel: BookmarkViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentBookmarkBinding.inflate(layoutInflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(_binding.rvBookmark)
        viewModel = ViewModelProvider(
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

        _binding.progressBar.visibility = View.VISIBLE
        viewModel.getBookmarks().observe(requireActivity(), {
            _binding.progressBar.visibility = View.GONE
            bookmarkAdapter.submitList(it)
        })
        if (activity != null) {
            with(_binding.rvBookmark) {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = bookmarkAdapter
            }
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int {
            return makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        }

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val position = viewHolder.adapterPosition
                val data = bookmarkAdapter.getSwipeData(position)
                data?.let { viewModel.setBookmark(it) }
                Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                    .setAction(R.string.message_ok) {
                        data?.let { courseEntity -> viewModel.setBookmark(courseEntity) }
                    }
                    .show()
            }
        }

    })
}