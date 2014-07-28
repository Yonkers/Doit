package com.yong.doit.ui.widget;

import java.util.List;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import android.widget.ListView;
import com.yong.doit.R;
import com.yong.doit.data.bean.Task;
import com.yong.doit.service.TaskService;

public class DayTaskFragment extends BaseFragment implements OnClickListener {

    List<Task> tasks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tasks = TaskService.getInstance().getAll();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_day_tasks, null);
        // v.findViewById(R.id.addTask).setVisibility(View.GONE);

//        ListView lv = (ListView) v.findViewById(R.id.list_day_tasks);
//        lv.setSwipeMode(SettingsManager.getInstance().getSwipeMode());
//        lv.setSwipeActionLeft(SettingsManager.getInstance().getSwipeActionLeft());
//        lv.setSwipeActionRight(SettingsManager.getInstance().getSwipeActionRight());
//        lv.setSwipeListViewListener(new SwipeListListener() {
//
//            @Override
//            public void onClickFrontView(int position) {
//                if (null != tasks && tasks.size() > position) {
//                    Task t = tasks.get(position);
//                    showTaskDetailDialog(t);
//                }
//            }
//
//            @Override
//            public void onClickBackView(int position) {
//
//            }
//
//        });
        v.findViewById(R.id.addTask).setOnClickListener(this);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        checkData(false);
        setuptData();
    }

    private void checkData(boolean forceQuery) {
        if (forceQuery || null == tasks) {
            tasks = TaskService.getInstance().getAll();
        }
    }

    private void setuptData() {
        if (tasks != null && tasks.size() > 0) {
            ListView lv = (ListView) getView().findViewById(R.id.list_day_tasks);
            lv.setAdapter(new DayTaskListAdapter(getActivity(), tasks));
        } else {
            UIUtil.showToast(getActivity(), "no task today!");
            // getView().findViewById(R.id.addTask).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addTask:
                switchFragment(FRAGMENT_ADD_TASK, false);
                break;

            default:
                break;
        }
    }

    @Override
    public void refreshFragment() {
        checkData(true);
        setuptData();
    }

    private void showTaskDetailDialog(Task e) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("任务详细");
        builder.setMessage(e.print());
        builder.setPositiveButton("OK", null);
        builder.create().show();
    }
}
