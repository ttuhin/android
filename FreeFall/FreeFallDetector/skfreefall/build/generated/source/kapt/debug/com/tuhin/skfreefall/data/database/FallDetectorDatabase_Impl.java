package com.tuhin.skfreefall.data.database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unchecked")
public final class FallDetectorDatabase_Impl extends FallDetectorDatabase {
  private volatile FallEventDao _fallEventDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `FreeFallData` (`timeStamp` TEXT NOT NULL, `duration` TEXT NOT NULL, PRIMARY KEY(`timeStamp`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"1306bc8d80872c5d1ae27dcbed31d623\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `FreeFallData`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsFreeFallData = new HashMap<String, TableInfo.Column>(2);
        _columnsFreeFallData.put("timeStamp", new TableInfo.Column("timeStamp", "TEXT", true, 1));
        _columnsFreeFallData.put("duration", new TableInfo.Column("duration", "TEXT", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFreeFallData = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFreeFallData = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFreeFallData = new TableInfo("FreeFallData", _columnsFreeFallData, _foreignKeysFreeFallData, _indicesFreeFallData);
        final TableInfo _existingFreeFallData = TableInfo.read(_db, "FreeFallData");
        if (! _infoFreeFallData.equals(_existingFreeFallData)) {
          throw new IllegalStateException("Migration didn't properly handle FreeFallData(com.tuhin.skfreefall.data.model.FreeFallData).\n"
                  + " Expected:\n" + _infoFreeFallData + "\n"
                  + " Found:\n" + _existingFreeFallData);
        }
      }
    }, "1306bc8d80872c5d1ae27dcbed31d623", "e49cbac8a2432079cdcd3783f256a0a4");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "FreeFallData");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `FreeFallData`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public FallEventDao fallEventDao() {
    if (_fallEventDao != null) {
      return _fallEventDao;
    } else {
      synchronized(this) {
        if(_fallEventDao == null) {
          _fallEventDao = new FallEventDao_Impl(this);
        }
        return _fallEventDao;
      }
    }
  }
}
