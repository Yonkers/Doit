package com.yong.doit.ui.widget;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.bmob.im.demo.ui.FragmentBase;
import com.yong.doit.R;
import com.yong.doit.data.bean.Event;
import com.yong.doit.data.bean.Task;
import com.yong.doit.service.EventService;
import com.yong.doit.service.TaskService;
import com.yong.doit.ui.DoitMain;

public class EditTaskFragment extends FragmentBase implements OnClickListener {

    private static EditTaskFragment instance;

    public static EditTaskFragment getInstance(){
        if(null == instance){
            instance = new EditTaskFragment();

        }
        return instance;
    }

    List<Event> events;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        events = EventService.getInstance().getAll();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.edit_task, null);
        v.findViewById(R.id.btnSave).setOnClickListener(this);

        Spinner belongEvent = (Spinner) v.findViewById(R.id.belongEvent);

        CustomSpinnerAdapter<Event> eventAdapter = new CustomSpinnerAdapter<Event>(
                getActivity(), getEvents(events));
        belongEvent.setAdapter(eventAdapter);

        return v;
    }

    private List<Event> getEvents(List<Event> events) {
        if (null == events) {
            events = new ArrayList<Event>();
        }
        events.add(0, new Event("选择一个计划"));
        events.add(new Event("+添加一个计划"));

        return events;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                saveDayTask();
                break;

            default:
                break;
        }
    }

    private void saveDayTask() {
        EditText etName = (EditText) getView().findViewById(R.id.taskName);
        String name = etName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            UIUtil.showToast(getActivity(), "task name can't be empty");
            return;
        }
        Spinner belongEvent = (Spinner) getView().findViewById(R.id.belongEvent);
        int index = belongEvent.getSelectedItemPosition();
        if (index == 0 || index == events.size() - 1) {
            UIUtil.showToast(getActivity(), "select your event please");
            return;
        }
        EditText etNote = (EditText) getView().findViewById(R.id.taskNote);
        String note = etNote.getText().toString().trim();
        Task task = new Task();
        task.setName(name);
        task.setDate(new Date());
        task.setNote(note);
        task.setEvent(events.get(index));

        TaskService.getInstance().save(task);

        UIUtil.showToast(getActivity(), "saved: " + task.getName());

        switchFragment(DoitMain.FRAGMENT_DAY_TASKS, true);
    }



    @Override
    public void refreshFragment() {

    }

}
