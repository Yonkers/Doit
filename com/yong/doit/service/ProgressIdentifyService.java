/**
 *
 */
package com.yong.doit.service;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.yong.doit.dao.DataHelper;
import com.yong.doit.data.bean.Event;
import com.yong.doit.data.bean.ProgressIdentify;
import com.yong.doit.data.bean.ProgressIdentify;

/**
 * @author lyjiang
 */
public class ProgressIdentifyService {

    private static ProgressIdentifyService instance;

    private Dao<ProgressIdentify, Integer> progressIdentifyDao = null;

    private ProgressIdentifyService() {
        try {
            progressIdentifyDao = DataHelper.getInstance().getProgressIdentifyDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ProgressIdentifyService getInstance() {
        if (instance == null) {
            instance = new ProgressIdentifyService();
        }
        return instance;
    }

    public boolean save(ProgressIdentify identify) {
        if (!isExists(identify)) {
            try {
                progressIdentifyDao.create(identify);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    public List<ProgressIdentify> getAll() {
        try {
            List<ProgressIdentify> progressIdentifys = progressIdentifyDao.queryForAll();
            return progressIdentifys;
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
    public boolean isExists(ProgressIdentify progressIdentify) {
        try {
            List<ProgressIdentify> progressIdentifys = progressIdentifyDao.queryForEq("name", progressIdentify.getName());
            if (null != progressIdentifys && progressIdentifys.size() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void delete(ProgressIdentify progressIdentify) {
        try {
            progressIdentifyDao.delete(progressIdentify);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBach(List<ProgressIdentify> progressIdentifys) {
        try {
            progressIdentifyDao.delete(progressIdentifys);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
