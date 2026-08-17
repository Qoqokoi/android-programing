package com.qoqokoi.librepulse.data.local;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
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
public final class DeviceDao_Impl implements DeviceDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DeviceEntity> __insertionAdapterOfDeviceEntity;

  public DeviceDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDeviceEntity = new EntityInsertionAdapter<DeviceEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `devices` (`deviceId`,`sysName`,`hostname`,`ip`,`status`,`portsUp`,`portsDown`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DeviceEntity entity) {
        statement.bindString(1, entity.getDeviceId());
        statement.bindString(2, entity.getSysName());
        statement.bindString(3, entity.getHostname());
        statement.bindString(4, entity.getIp());
        statement.bindString(5, entity.getStatus());
        if (entity.getPortsUp() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getPortsUp());
        }
        if (entity.getPortsDown() == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.getPortsDown());
        }
      }
    };
  }

  @Override
  public Object insertDevices(final List<DeviceEntity> devices,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfDeviceEntity.insert(devices);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<DeviceEntity>> getAllDevices() {
    final String _sql = "SELECT * FROM devices";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"devices"}, false, new Callable<List<DeviceEntity>>() {
      @Override
      @Nullable
      public List<DeviceEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "deviceId");
          final int _cursorIndexOfSysName = CursorUtil.getColumnIndexOrThrow(_cursor, "sysName");
          final int _cursorIndexOfHostname = CursorUtil.getColumnIndexOrThrow(_cursor, "hostname");
          final int _cursorIndexOfIp = CursorUtil.getColumnIndexOrThrow(_cursor, "ip");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfPortsUp = CursorUtil.getColumnIndexOrThrow(_cursor, "portsUp");
          final int _cursorIndexOfPortsDown = CursorUtil.getColumnIndexOrThrow(_cursor, "portsDown");
          final List<DeviceEntity> _result = new ArrayList<DeviceEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DeviceEntity _item;
            final String _tmpDeviceId;
            _tmpDeviceId = _cursor.getString(_cursorIndexOfDeviceId);
            final String _tmpSysName;
            _tmpSysName = _cursor.getString(_cursorIndexOfSysName);
            final String _tmpHostname;
            _tmpHostname = _cursor.getString(_cursorIndexOfHostname);
            final String _tmpIp;
            _tmpIp = _cursor.getString(_cursorIndexOfIp);
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final Integer _tmpPortsUp;
            if (_cursor.isNull(_cursorIndexOfPortsUp)) {
              _tmpPortsUp = null;
            } else {
              _tmpPortsUp = _cursor.getInt(_cursorIndexOfPortsUp);
            }
            final Integer _tmpPortsDown;
            if (_cursor.isNull(_cursorIndexOfPortsDown)) {
              _tmpPortsDown = null;
            } else {
              _tmpPortsDown = _cursor.getInt(_cursorIndexOfPortsDown);
            }
            _item = new DeviceEntity(_tmpDeviceId,_tmpSysName,_tmpHostname,_tmpIp,_tmpStatus,_tmpPortsUp,_tmpPortsDown);
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
