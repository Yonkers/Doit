package com.yong.doit.ui.widget;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

    protected static String TAG;

    public static final int WHAT_SWITCH_FRAGMENT = 1;
    public static final int FRAGMENT_EVENT_LIST = 2;
    public static final int FRAGMENT_TASK_LIST = 3;
    public static final int FRAGMENT_DAY_TASKS = 4;// 一天的任务列表界面
    public static final int FRAGMENT_ADD_TASK = 5;
    public static final int FRAGMENT_ADD_EVENT = 6;
    public static final int FRAGMENT_SETTING = 7;
    public static final int FRAGMENT_COURSE_ROAD_MAP = 8;

    public static final int WHAT_FINISH_FRAGMENT = 100;

    private Handler baseHandler;

    protected int type;

    public int getType() {
        return type;
    }

    public void setBaseHandler(Handler baseHandler) {
        this.baseHandler = baseHandler;
    }

    public boolean isBaseHandlerNull() {
        return baseHandler == null ? true : false;
    }

    public abstract void refreshFragment();

    protected void switchFragment(int type, boolean refresh) {
        if (null != baseHandler) {
            Message msg = baseHandler.obtainMessage(WHAT_SWITCH_FRAGMENT);
            msg.arg1 = type;
            msg.obj = refresh;
            msg.sendToTarget();
        }
    }

    protected void finishFragment(int type) {
        if (null != baseHandler) {
            Message msg = baseHandler.obtainMessage(WHAT_FINISH_FRAGMENT);
            msg.arg1 = type;
            msg.sendToTarget();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        TAG = this.getClass().getSimpleName();
        Log.d(TAG, "...onAttach....");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "...onCreate....");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "...onCreateView....");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "...onActivityCreated....");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "...onStart....");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "...onPause....");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "...onStop....");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "...onDestroyView....");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "...onDestroy....");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "...onDetach....");
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        return false;
    }
}
