package com.bayuspace.mynotesroom.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.bayuspace.mynotesroom.R
import com.bayuspace.mynotesroom.database.NoteEntity
import com.bayuspace.mynotesroom.databinding.ActivityMainBinding
import com.bayuspace.mynotesroom.helper.SortUtil
import com.bayuspace.mynotesroom.helper.ViewModelFactory
import com.bayuspace.mynotesroom.ui.NoteAdapter
import com.bayuspace.mynotesroom.ui.insert.NoteAddUpdateActivity
import com.bayuspace.mynotesroom.ui.insert.NoteAddUpdateActivity.Companion.EXTRA_NOTE
import com.bayuspace.mynotesroom.ui.insert.NoteAddUpdateActivity.Companion.EXTRA_POSITION
import com.bayuspace.mynotesroom.ui.insert.NoteAddUpdateActivity.Companion.REQUEST_ADD
import com.bayuspace.mynotesroom.ui.insert.NoteAddUpdateActivity.Companion.REQUEST_UPDATE
import com.bayuspace.mynotesroom.ui.insert.NoteAddUpdateActivity.Companion.RESULT_ADD
import com.bayuspace.mynotesroom.ui.insert.NoteAddUpdateActivity.Companion.RESULT_DELETE
import com.bayuspace.mynotesroom.ui.insert.NoteAddUpdateActivity.Companion.RESULT_UPDATE
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private var _activityMainBinding: ActivityMainBinding? = null
    private lateinit var viewModel: MainViewModel
    private val binding get() = _activityMainBinding
    private lateinit var noteAdapter: NotePagedListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        noteAdapter = NotePagedListAdapter() { note, position ->
            val intent = Intent(this, NoteAddUpdateActivity::class.java)
            intent.putExtra(EXTRA_POSITION, position)
            intent.putExtra(EXTRA_NOTE, note)
            startActivityForResult(intent, REQUEST_UPDATE)
        }

        binding?.rvNotes?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = noteAdapter
        }

        viewModel = obtainViewModel(this@MainActivity)
        viewModel.getAllNotes(SortUtil.NEWEST).observe(this, observer)

        binding?.fabAdd?.setOnClickListener {
            if (it.id == R.id.fab_add) {
                val intent = Intent(this@MainActivity, NoteAddUpdateActivity::class.java)
                startActivityForResult(intent, REQUEST_ADD)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            if (requestCode == REQUEST_ADD && resultCode == RESULT_ADD) showMessage(getString(R.string.added))
            else if (requestCode == REQUEST_UPDATE) {
                if (resultCode == RESULT_UPDATE) showMessage(getString(R.string.changed))
                else if (resultCode == RESULT_DELETE) showMessage(getString(R.string.deleted))
            }
        }
    }

    private fun showMessage(msg: String) {
        Snackbar.make(binding?.root as View, msg, Snackbar.LENGTH_SHORT).show()
    }

    private fun obtainViewModel(activity: AppCompatActivity): MainViewModel {
        val factory = ViewModelFactory.newInstance(activity.application)
        return ViewModelProvider(activity, factory).get(MainViewModel::class.java)
    }

    private val observer = Observer<List<NoteEntity>> {
        if (it != null) {
            noteAdapter.submitList(it as PagedList<NoteEntity>)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var sort = ""
        when (item.itemId) {
            R.id.action_newest -> sort = SortUtil.NEWEST
            R.id.action_random -> sort = SortUtil.RANDOM
            R.id.action_oldest -> sort = SortUtil.OLDEST
        }
        viewModel.getAllNotes(sort).observe(this, observer)
        item.isChecked = true
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityMainBinding = null
    }
}