package com.bayuspace.mynotesroom.ui.insert

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bayuspace.mynotesroom.R
import com.bayuspace.mynotesroom.database.NoteEntity
import com.bayuspace.mynotesroom.databinding.ActivityNoteAddUpdateBinding
import com.bayuspace.mynotesroom.helper.DateHelper
import com.bayuspace.mynotesroom.helper.ViewModelFactory

class NoteAddUpdateActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_NOTE = "extra_note"
        const val EXTRA_POSITION = "extra_position"
        const val REQUEST_ADD = 100
        const val RESULT_ADD = 101
        const val REQUEST_UPDATE = 200
        const val RESULT_UPDATE = 201
        const val RESULT_DELETE = 301
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20
    }

    private var _activityNoteAddUpdateBinding: ActivityNoteAddUpdateBinding? = null
    private val _binding get() = _activityNoteAddUpdateBinding
    private lateinit var viewModel: NoteAddUpdateViewModel
    private var isEdit = false
    private var note: NoteEntity? = null
    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityNoteAddUpdateBinding = ActivityNoteAddUpdateBinding.inflate(layoutInflater)
        setContentView(_binding?.root)

        viewModel = obtainViewModel(this@NoteAddUpdateActivity)

        note = intent.getParcelableExtra(EXTRA_NOTE)
        if (note != null) {
            position = intent.getIntExtra(EXTRA_POSITION, 0)
            isEdit = true
        } else note = NoteEntity()

        val actionTitle: String
        val btnTitle: String

        if (isEdit) {
            actionTitle = getString(R.string.change)
            btnTitle = getString(R.string.update)
            if (note != null) {
                note?.let { noteEntity ->
                    _binding?.apply {
                        edtTitle.setText(noteEntity.title)
                        edtDescription.setText(noteEntity.description)
                    }
                }
            }
        } else {
            actionTitle = getString(R.string.add)
            btnTitle = getString(R.string.save)
        }

        supportActionBar?.apply {
            title = actionTitle
            setDisplayHomeAsUpEnabled(true)
        }
        _binding?.apply {
            btnSubmit.text = btnTitle
            btnSubmit.setOnClickListener {
                val title = edtTitle.text.trim().toString()
                val desc = edtDescription.text.trim().toString()
                when {
                    title.isEmpty() -> edtTitle.error = getString(R.string.empty)
                    desc.isEmpty() -> edtDescription.error = getString(R.string.empty)
                    else -> {
                        note?.let {
                            it.title = title
                            it.description = desc
                        }

                        val intent = Intent().apply {
                            putExtra(EXTRA_NOTE, note)
                            putExtra(EXTRA_POSITION, position)
                        }

                        if (isEdit) {
                            note?.let { viewModel.updateNote(it) }
                            setResult(RESULT_UPDATE, intent)
                            finish()
                        } else {
                            note?.let {
                                it.date = DateHelper.getCurrentDate()
                                viewModel.insertNote(it)
                            }
                            setResult(RESULT_ADD, intent)
                            finish()
                        }
                    }
                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (isEdit)
            menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete -> showAlert(ALERT_DIALOG_DELETE)
            else -> showAlert(ALERT_DIALOG_CLOSE)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        showAlert(ALERT_DIALOG_CLOSE)
    }

    private fun showAlert(type: Int) {
        val dialogClose = type == ALERT_DIALOG_CLOSE
        val title: String
        val msg: String
        when (type) {
            ALERT_DIALOG_CLOSE -> {
                title = getString(R.string.cancel)
                msg = getString(R.string.message_cancel)
            }
            else -> {
                title = getString(R.string.delete)
                msg = getString(R.string.message_delete)
            }
        }

        val alert = AlertDialog.Builder(this)
        alert.apply {
            setTitle(title)
            setMessage(msg)
            setCancelable(false)
            setPositiveButton(getString(R.string.yes)) { _, _ ->
                if (!dialogClose) {
                    note?.let { viewModel.deleteNote(it) }
                    val intent = Intent()
                    intent.putExtra(EXTRA_POSITION, position)
                    setResult(RESULT_DELETE, intent)
                }
                finish()
            }
            setNegativeButton(getString(R.string.no)) { dialog, _ ->
                dialog.cancel()
            }
            val alertDialog = alert.create()
            alertDialog.show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityNoteAddUpdateBinding = null
    }

    private fun obtainViewModel(activity: AppCompatActivity): NoteAddUpdateViewModel {
        val factory = ViewModelFactory.newInstance(activity.application)
        return ViewModelProvider(activity, factory).get(NoteAddUpdateViewModel::class.java)
    }
}