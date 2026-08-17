package com.qoqokoi.librepulse.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile DeviceDao _deviceDao;

  private volatile PortDao _portDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(5) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `devices` (`deviceId` TEXT NOT NULL, `sysName` TEXT NOT NULL, `hostname` TEXT NOT NULL, `ip` TEXT NOT NULL, `status` TEXT NOT NULL, `portsUp` INTEGER, `portsDown` INTEGER, PRIMARY KEY(`deviceId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `ports` (`deviceId` TEXT NOT NULL, `ifName` TEXT NOT NULL, `ipAddress` TEXT, `ifDescr` TEXT, `ifAlias` TEXT, `ifOperStatus` TEXT, `ifAdminStatus` TEXT, `ifSpeed` INTEGER, `disabled` TEXT, `deleted` TEXT, PRIMARY KEY(`deviceId`, `ifName`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '033494ff8ecfdc5e6d0f7a272d21d48d')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `devices`");
        db.execSQL("DROP TABLE IF EXISTS `ports`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsDevices = new HashMap<String, TableInfo.Column>(7);
        _columnsDevices.put("deviceId", new TableInfo.Column("deviceId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDevices.put("sysName", new TableInfo.Column("sysName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDevices.put("hostname", new TableInfo.Column("hostname", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDevices.put("ip", new TableInfo.Column("ip", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDevices.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDevices.put("portsUp", new TableInfo.Column("portsUp", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDevices.put("portsDown", new TableInfo.Column("portsDown", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDevices = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDevices = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDevices = new TableInfo("devices", _columnsDevices, _foreignKeysDevices, _indicesDevices);
        final TableInfo _existingDevices = TableInfo.read(db, "devices");
        if (!_infoDevices.equals(_existingDevices)) {
          return new RoomOpenHelper.ValidationResult(false, "devices(com.qoqokoi.librepulse.data.local.DeviceEntity).\n"
                  + " Expected:\n" + _infoDevices + "\n"
                  + " Found:\n" + _existingDevices);
        }
        final HashMap<String, TableInfo.Column> _columnsPorts = new HashMap<String, TableInfo.Column>(10);
        _columnsPorts.put("deviceId", new TableInfo.Column("deviceId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPorts.put("ifName", new TableInfo.Column("ifName", "TEXT", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPorts.put("ipAddress", new TableInfo.Column("ipAddress", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPorts.put("ifDescr", new TableInfo.Column("ifDescr", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPorts.put("ifAlias", new TableInfo.Column("ifAlias", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPorts.put("ifOperStatus", new TableInfo.Column("ifOperStatus", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPorts.put("ifAdminStatus", new TableInfo.Column("ifAdminStatus", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPorts.put("ifSpeed", new TableInfo.Column("ifSpeed", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPorts.put("disabled", new TableInfo.Column("disabled", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPorts.put("deleted", new TableInfo.Column("deleted", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPorts = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPorts = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPorts = new TableInfo("ports", _columnsPorts, _foreignKeysPorts, _indicesPorts);
        final TableInfo _existingPorts = TableInfo.read(db, "ports");
        if (!_infoPorts.equals(_existingPorts)) {
          return new RoomOpenHelper.ValidationResult(false, "ports(com.qoqokoi.librepulse.data.local.PortEntity).\n"
                  + " Expected:\n" + _infoPorts + "\n"
                  + " Found:\n" + _existingPorts);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "033494ff8ecfdc5e6d0f7a272d21d48d", "c82ea12c32d4738999ee366be8a5f2f2");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "devices","ports");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `devices`");
      _db.execSQL("DELETE FROM `ports`");
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
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(DeviceDao.class, DeviceDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PortDao.class, PortDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public DeviceDao deviceDao() {
    if (_deviceDao != null) {
      return _deviceDao;
    } else {
      synchronized(this) {
        if(_deviceDao == null) {
          _deviceDao = new DeviceDao_Impl(this);
        }
        return _deviceDao;
      }
    }
  }

  @Override
  public PortDao portDao() {
    if (_portDao != null) {
      return _portDao;
    } else {
      synchronized(this) {
        if(_portDao == null) {
          _portDao = new PortDao_Impl(this);
        }
        return _portDao;
      }
    }
  }
}
