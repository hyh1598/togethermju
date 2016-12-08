package com.example.mathpresso.togethermju.core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.mathpresso.togethermju.model.Group;
import com.example.mathpresso.togethermju.model.Notice;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by choijinjoo on 2016. 11. 30..
 */
public class DatabaseManager extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "together.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Notice, Integer> simpleNoticeDao = null;
    private Dao<Group, Integer> simpleGroupDao = null;



    private final Class databaseClasses[] = {
            Group.class,
            Notice.class
    };

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            for(Class clazz : databaseClasses) {
                TableUtils.createTable(connectionSource, clazz);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            for(Class clazz : databaseClasses) {
                TableUtils.dropTable(connectionSource, clazz, true);
            }
            // after we drop the old databases, we create the new ones
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Dao<Notice, Integer> getSimpleNoticeDataDao() throws SQLException {
        if (simpleNoticeDao == null) {
            simpleNoticeDao = getDao(Notice.class);
        }
        return simpleNoticeDao;
    }
    public Dao<Group, Integer> getSimpleGroupDataDao() throws SQLException {
        if (simpleGroupDao == null) {
            simpleGroupDao = getDao(Group.class);
        }
        return simpleGroupDao;
    }
}
