package com.yong.doit.ui.widget;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.yong.doit.app.DoApplication;
import com.yong.doit.notify.TaskTimeReceiver;

import java.util.Date;

/**
 * Created by yonkers on 5/12/14.
 */
public class NotifyService {

    public static String ACTION = "com.yon.task.notify";

    private static  NotifyService instance;

    private AlarmManager alarm;

    public static NotifyService getInstance(){
        if(null == instance){
            instance = new NotifyService();
        }
        return instance;
    }

    public void startCheckTask(){
        alarm=(AlarmManager) DoApplication.getInstance().getSystemService(Context.ALARM_SERVICE);
        Intent intent =new Intent(DoApplication.getInstance(), TaskTimeReceiver.class);
        intent.setAction(ACTION);
        PendingIntent sender= PendingIntent.getBroadcast(DoApplication.getInstance(), 0, intent, 0);
        //早上9点
        Date startDate = new Date();
        //当天早上9点开始执行任务,重复执行是从第二天开始执行早上9点
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, startDate.getTime() + 5000,1*24*60*60*1000+startDate.getTime(), sender);
    }

}

