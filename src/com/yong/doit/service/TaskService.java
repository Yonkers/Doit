/**
 *
 */
package com.yong.doit.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.yong.doit.dao.DataHelper;
import com.yong.doit.data.bean.Event;
import com.yong.doit.data.bean.Task;

/**
 * @author lyjiang
 */
public class TaskService {

    private static TaskService instance;

    private Dao<Task, Integer> taskDao = null;

    private TaskService() {
        try {
            taskDao = DataHelper.getInstance().getTaskDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static TaskService getInstance() {
        if (instance == null) {
            instance = new TaskService();
        }
        return instance;
    }

    public void save(Task task) {
        try {
            taskDao.create(task);
            System.out.println("saved task: " + task.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Task> getAll() {
        try {
            List<Task> tasks = taskDao.queryForAll();
            return tasks;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Task> getTodayTasks(Event event) {
        return getTasks(new Date(), event);
    }

    public List<Task> getTasks(Date date, Event event) {
        if (null == event) {
            return null;
        }
        try {
            List<Date> selDate = new ArrayList<Date>();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
//			cal.get
            QueryBuilder<Task, Integer> qb = taskDao.queryBuilder();
            Where<Task, Integer> where = qb.where();
            where.eq("event_id", date.toString());
            where.and();
            where.in("date", date);
            PreparedQuery<Task> pQuery = qb.prepare();
            List<Task> tasks = taskDao.query(pQuery);
            return tasks;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 某个任务是否存在
     *
     * @param
     * @return
     */
    public boolean isExists(Task task) {
        try {
            List<Task> tasks = taskDao.queryForEq("name", task.getName());
            if (null != tasks && tasks.size() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void delete(Task task) {
        try {
            taskDao.delete(task);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBach(List<Task> tasks) {
        try {
            taskDao.delete(tasks);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
