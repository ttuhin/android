package com.tuhin.skfreefall.data.database;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.RxRoom;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.tuhin.skfreefall.data.model.FreeFallData;
import io.reactivex.Completable;
import io.reactivex.Observable;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.lang.Void;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings("unchecked")
public final class FallEventDao_Impl implements FallEventDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfFreeFallData;

  public FallEventDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFreeFallData = new EntityInsertionAdapter<FreeFallData>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `FreeFallData`(`timeStamp`,`duration`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FreeFallData value) {
        if (value.getTimeStamp() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getTimeStamp());
        }
        if (value.getDuration() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getDuration());
        }
      }
    };
  }

  @Override
  public Completable save(final FreeFallData event) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfFreeFallData.insert(event);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Observable<List<FreeFallData>> getAll() {
    final String _sql = "SELECT * FROM FreeFallData";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createObservable(__db, new String[]{"FreeFallData"}, new Callable<List<FreeFallData>>() {
      @Override
      public List<FreeFallData> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false);
        try {
          final int _cursorIndexOfTimeStamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timeStamp");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final List<FreeFallData> _result = new ArrayList<FreeFallData>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final FreeFallData _item;
            final String _tmpTimeStamp;
            _tmpTimeStamp = _cursor.getString(_cursorIndexOfTimeStamp);
            final String _tmpDuration;
            _tmpDuration = _cursor.getString(_cursorIndexOfDuration);
            _item = new FreeFallData(_tmpTimeStamp,_tmpDuration);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
