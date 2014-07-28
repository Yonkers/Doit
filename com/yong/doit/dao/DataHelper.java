/**
 *
 */
package com.yong.doit.dao;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.yong.doit.app.DoApplication;
import com.yong.doit.data.bean.Event;
import com.yong.doit.data.bean.ProgressIdentify;
import com.yong.doit.data.bean.Task;

/**
 * @author lyjiang
 */
public class DataHelper extends OrmLiteSqliteOpenHelper {

    public static final String DATABASE_NAME = "task_events.db";

    public static final int DATABASE_VERSION = 1;

    private static DataHelper dataHelper = null;

    private Dao<Event, Integer> eventDao = null;
    private Dao<Task, Integer> taskDao = null;
    private Dao<ProgressIdentify, Integer> progressIdentifyDao = null;


    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * 第一次初始化需要传递一个context
     *
     * @param context
     * @return
     */
    public static DataHelper getInstance() {
        if (null == dataHelper)
            dataHelper = new DataHelper(DoApplication.getInstance());
        return dataHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase arg0, ConnectionSource arg1) {
        try {
            TableUtils.createTable(connectionSource, Event.class);
            TableUtils.createTable(connectionSource, Task.class);
            TableUtils.createTable(connectionSource, ProgressIdentify.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        super.close();
        eventDao = null;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource arg1, int arg2,
                          int arg3) {
        try {
            TableUtils.dropTable(connectionSource, Event.class, true);
            TableUtils.dropTable(connectionSource, Task.class, true);
            TableUtils.dropTable(connectionSource, ProgressIdentify.class, true);
            onCreate(db, connectionSource);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<Event, Integer> getEventDao() throws SQLException {
        if (eventDao == null) {
            eventDao = getDao(Event.class);
        }
        return eventDao;
    }

    public Dao<Task, Integer> getTaskDao() throws SQLException {
        if (taskDao == null) {
            taskDao = getDao(Task.class);
        }
        return taskDao;
    }

    public Dao<ProgressIdentify, Integer> getProgressIdentifyDao() throws SQLException {
        if (progressIdentifyDao == null) {
            progressIdentifyDao = getDao(ProgressIdentify.class);
        }
        return progressIdentifyDao;
    }

}
