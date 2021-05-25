package com.bayuspace.mynotesroom.database;

import android.database.Cursor;
import androidx.paging.DataSource;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.paging.LimitOffsetDataSource;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class NoteDao_Impl implements NoteDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<NoteEntity> __insertionAdapterOfNoteEntity;

  private final EntityDeletionOrUpdateAdapter<NoteEntity> __deletionAdapterOfNoteEntity;

  private final EntityDeletionOrUpdateAdapter<NoteEntity> __updateAdapterOfNoteEntity;

  public NoteDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfNoteEntity = new EntityInsertionAdapter<NoteEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `tbl_note` (`id`,`title`,`description`,`date`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, NoteEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        if (value.getDate() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDate());
        }
      }
    };
    this.__deletionAdapterOfNoteEntity = new EntityDeletionOrUpdateAdapter<NoteEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `tbl_note` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, NoteEntity value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfNoteEntity = new EntityDeletionOrUpdateAdapter<NoteEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `tbl_note` SET `id` = ?,`title` = ?,`description` = ?,`date` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, NoteEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        if (value.getDate() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDate());
        }
        stmt.bindLong(5, value.getId());
      }
    };
  }

  @Override
  public void insertNote(final NoteEntity note) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfNoteEntity.insert(note);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(final List<NoteEntity> list) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfNoteEntity.insert(list);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteNote(final NoteEntity note) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfNoteEntity.handle(note);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateNote(final NoteEntity note) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfNoteEntity.handle(note);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public DataSource.Factory<Integer, NoteEntity> getAllNotes(final SupportSQLiteQuery query) {
    final SupportSQLiteQuery _internalQuery = query;
    return new DataSource.Factory<Integer, NoteEntity>() {
      @Override
      public LimitOffsetDataSource<NoteEntity> create() {
        return new LimitOffsetDataSource<NoteEntity>(__db, _internalQuery, false, true , "tbl_note") {
          @Override
          protected List<NoteEntity> convertRows(Cursor cursor) {
            final List<NoteEntity> _res = new ArrayList<NoteEntity>(cursor.getCount());
            while(cursor.moveToNext()) {
              final NoteEntity _item;
              _item = __entityCursorConverter_comBayuspaceMynotesroomDatabaseNoteEntity(cursor);
              _res.add(_item);
            }
            return _res;
          }
        };
      }
    };
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private NoteEntity __entityCursorConverter_comBayuspaceMynotesroomDatabaseNoteEntity(
      Cursor cursor) {
    final NoteEntity _entity;
    final int _cursorIndexOfId = cursor.getColumnIndex("id");
    final int _cursorIndexOfTitle = cursor.getColumnIndex("title");
    final int _cursorIndexOfDescription = cursor.getColumnIndex("description");
    final int _cursorIndexOfDate = cursor.getColumnIndex("date");
    final int _tmpId;
    if (_cursorIndexOfId == -1) {
      _tmpId = 0;
    } else {
      _tmpId = cursor.getInt(_cursorIndexOfId);
    }
    final String _tmpTitle;
    if (_cursorIndexOfTitle == -1) {
      _tmpTitle = null;
    } else {
      if (cursor.isNull(_cursorIndexOfTitle)) {
        _tmpTitle = null;
      } else {
        _tmpTitle = cursor.getString(_cursorIndexOfTitle);
      }
    }
    final String _tmpDescription;
    if (_cursorIndexOfDescription == -1) {
      _tmpDescription = null;
    } else {
      if (cursor.isNull(_cursorIndexOfDescription)) {
        _tmpDescription = null;
      } else {
        _tmpDescription = cursor.getString(_cursorIndexOfDescription);
      }
    }
    final String _tmpDate;
    if (_cursorIndexOfDate == -1) {
      _tmpDate = null;
    } else {
      if (cursor.isNull(_cursorIndexOfDate)) {
        _tmpDate = null;
      } else {
        _tmpDate = cursor.getString(_cursorIndexOfDate);
      }
    }
    _entity = new NoteEntity(_tmpId,_tmpTitle,_tmpDescription,_tmpDate);
    return _entity;
  }
}
