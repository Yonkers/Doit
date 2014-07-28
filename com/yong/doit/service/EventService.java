package com.yong.doit.service;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.yong.doit.dao.DataHelper;
import com.yong.doit.data.bean.Event;

public class EventService {

    private static String TAG = "TaskService";

    private static EventService eventService;

    private Dao<Event, Integer> eventDao = null;

    private EventService() {
        try {
            this.eventDao = DataHelper.getInstance().getEventDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static EventService getInstance() {
        if (null == eventService) {
            eventService = new EventService();
        }
        return eventService;
    }

    public void save(Event event) {
        try {
            if (!isExists(event)) {
                eventDao.create(event);
                System.out.println("saved " + event.getName());
            } else {
                System.out.println("-------not save for exists ! ----->\n"
                        + event.getName());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveBatch(final List<Event> notes) {
        try {
            Log.d(TAG, "will save batch events !");
            eventDao.callBatchTasks(new Callable<Event>() {

                @Override
                public Event call() throws Exception {
                    int n = notes.size();
                    for (int i = 0; i < n; i++) {
                        save(notes.get(i));
                    }
                    return null;
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Event note) {
        try {
            eventDao.update(note);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取所有的单词
     *
     * @return
     */
    public List<Event> getAll() {
        try {
            List<Event> curses = eventDao.queryForAll();
            return curses;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取单词总数
     *
     * @return
     */
    public long getCount() {
        try {
            return eventDao.countOf();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 分页查询
     *
     * @param page
     * @param size
     * @return
     */
    public List<Event> getByPageSize(int page, int size) {
        try {
            QueryBuilder<Event, Integer> builder = eventDao.queryBuilder();
            Where<Event, Integer> where = builder.where();
            int low = size * (page - 1);
            int high = size * page - 1;
            where.between("id", low, high);
            builder.setWhere(where);
            PreparedQuery<Event> pq = builder.prepare();
            System.out.println(pq.getStatement());
            return eventDao.query(pq);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从start开始查询size个单词
     *
     * @param start
     * @param size
     * @return
     */
    public List<Event> getByRegion(int start, int size) {
        try {
            QueryBuilder<Event, Integer> builder = eventDao.queryBuilder();
            Where<Event, Integer> where = builder.where();
            where.between("id", start, start + size);
            builder.setWhere(where);
            PreparedQuery<Event> pq = builder.prepare();
            System.out.println(pq.getStatement());
            return eventDao.query(pq);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 某个笔记是否存在
     *
     * @param
     * @return
     */
    public boolean isExists(Event note) {
        try {
            List<Event> events = eventDao.queryForEq("note", note.getNote());
            if (null != events && events.size() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void delete(Event event) {
        try {
            eventDao.delete(event);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBach(List<Event> events) {
        try {
            eventDao.delete(events);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Event> getByTime(List<String> params) {
        try {
            QueryBuilder<Event, Integer> builder = eventDao.queryBuilder();
            Where<Event, Integer> where = builder.where();
            where.in("time", params.toArray());
            builder.setWhere(where);
            PreparedQuery<Event> pq = builder.prepare();
            System.out.println(pq.getStatement());
            return eventDao.query(pq);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
