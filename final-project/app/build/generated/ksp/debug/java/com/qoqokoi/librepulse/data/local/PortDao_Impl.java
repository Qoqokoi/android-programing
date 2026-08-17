package com.qoqokoi.librepulse.data.local;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomDatabaseKt;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class PortDao_Impl extends PortDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PortEntity> __insertionAdapterOfPortEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeletePortsByDeviceId;

  private final SharedSQLiteStatement __preparedStmtOfClearAllPorts;

  public PortDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPortEntity = new EntityInsertionAdapter<PortEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `ports` (`deviceId`,`ifName`,`ipAddress`,`ifDescr`,`ifAlias`,`ifOperStatus`,`ifAdminStatus`,`ifSpeed`,`disabled`,`deleted`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PortEntity entity) {
        statement.bindString(1, entity.getDeviceId());
        statement.bindString(2, entity.getIfName());
        if (entity.getIpAddress() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getIpAddress());
        }
        if (entity.getIfDescr() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getIfDescr());
        }
        if (entity.getIfAlias() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getIfAlias());
        }
        if (entity.getIfOperStatus() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getIfOperStatus());
        }
        if (entity.getIfAdminStatus() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getIfAdminStatus());
        }
        if (entity.getIfSpeed() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getIfSpeed());
        }
        if (entity.getDisabled() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getDisabled());
        }
        if (entity.getDeleted() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getDeleted());
        }
      }
    };
    this.__preparedStmtOfDeletePortsByDeviceId = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM ports WHERE deviceId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfClearAllPorts = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM ports";
        return _query;
      }
    };
  }

  @Override
  public Object insertPorts(final List<PortEntity> ports,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPortEntity.insert(ports);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateDevicePorts(final String deviceId, final List<PortEntity> ports,
      final Continuation<? super Unit> $completion) {
    return RoomDatabaseKt.withTransaction(__db, (__cont) -> PortDao_Impl.super.updateDevicePorts(deviceId, ports, __cont), $completion);
  }

  @Override
  public Object deletePortsByDeviceId(final String deviceId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeletePortsByDeviceId.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, deviceId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeletePortsByDeviceId.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object clearAllPorts(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearAllPorts.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfClearAllPorts.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<PortEntity>> getPortsByDeviceId(final String deviceId) {
    final String _sql = "SELECT * FROM ports WHERE deviceId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, deviceId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"ports"}, false, new Callable<List<PortEntity>>() {
      @Override
      @Nullable
      public List<PortEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "deviceId");
          final int _cursorIndexOfIfName = CursorUtil.getColumnIndexOrThrow(_cursor, "ifName");
          final int _cursorIndexOfIpAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "ipAddress");
          final int _cursorIndexOfIfDescr = CursorUtil.getColumnIndexOrThrow(_cursor, "ifDescr");
          final int _cursorIndexOfIfAlias = CursorUtil.getColumnIndexOrThrow(_cursor, "ifAlias");
          final int _cursorIndexOfIfOperStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "ifOperStatus");
          final int _cursorIndexOfIfAdminStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "ifAdminStatus");
          final int _cursorIndexOfIfSpeed = CursorUtil.getColumnIndexOrThrow(_cursor, "ifSpeed");
          final int _cursorIndexOfDisabled = CursorUtil.getColumnIndexOrThrow(_cursor, "disabled");
          final int _cursorIndexOfDeleted = CursorUtil.getColumnIndexOrThrow(_cursor, "deleted");
          final List<PortEntity> _result = new ArrayList<PortEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PortEntity _item;
            final String _tmpDeviceId;
            _tmpDeviceId = _cursor.getString(_cursorIndexOfDeviceId);
            final String _tmpIfName;
            _tmpIfName = _cursor.getString(_cursorIndexOfIfName);
            final String _tmpIpAddress;
            if (_cursor.isNull(_cursorIndexOfIpAddress)) {
              _tmpIpAddress = null;
            } else {
              _tmpIpAddress = _cursor.getString(_cursorIndexOfIpAddress);
            }
            final String _tmpIfDescr;
            if (_cursor.isNull(_cursorIndexOfIfDescr)) {
              _tmpIfDescr = null;
            } else {
              _tmpIfDescr = _cursor.getString(_cursorIndexOfIfDescr);
            }
            final String _tmpIfAlias;
            if (_cursor.isNull(_cursorIndexOfIfAlias)) {
              _tmpIfAlias = null;
            } else {
              _tmpIfAlias = _cursor.getString(_cursorIndexOfIfAlias);
            }
            final String _tmpIfOperStatus;
            if (_cursor.isNull(_cursorIndexOfIfOperStatus)) {
              _tmpIfOperStatus = null;
            } else {
              _tmpIfOperStatus = _cursor.getString(_cursorIndexOfIfOperStatus);
            }
            final String _tmpIfAdminStatus;
            if (_cursor.isNull(_cursorIndexOfIfAdminStatus)) {
              _tmpIfAdminStatus = null;
            } else {
              _tmpIfAdminStatus = _cursor.getString(_cursorIndexOfIfAdminStatus);
            }
            final Long _tmpIfSpeed;
            if (_cursor.isNull(_cursorIndexOfIfSpeed)) {
              _tmpIfSpeed = null;
            } else {
              _tmpIfSpeed = _cursor.getLong(_cursorIndexOfIfSpeed);
            }
            final String _tmpDisabled;
            if (_cursor.isNull(_cursorIndexOfDisabled)) {
              _tmpDisabled = null;
            } else {
              _tmpDisabled = _cursor.getString(_cursorIndexOfDisabled);
            }
            final String _tmpDeleted;
            if (_cursor.isNull(_cursorIndexOfDeleted)) {
              _tmpDeleted = null;
            } else {
              _tmpDeleted = _cursor.getString(_cursorIndexOfDeleted);
            }
            _item = new PortEntity(_tmpDeviceId,_tmpIfName,_tmpIpAddress,_tmpIfDescr,_tmpIfAlias,_tmpIfOperStatus,_tmpIfAdminStatus,_tmpIfSpeed,_tmpDisabled,_tmpDeleted);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
