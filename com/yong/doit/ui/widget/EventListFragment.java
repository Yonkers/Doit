package com.yong.doit.ui.widget;

import java.util.List;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.yong.doit.R;
import com.yong.doit.data.bean.Event;
import com.yong.doit.service.EventService;

public class EventListFragment extends BaseFragment implements OnClickListener, OnItemClickListener {

    private List<Event> events;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        events = EventService.getInstance().getAll();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_event, null);
        v.findViewById(R.id.addEvent).setOnClickListener(this);
//		v.findViewById(R.id.addEvent).setVisibility(View.GONE);
        ListView eventList = (ListView) v.findViewById(R.id.list_events);
        eventList.setOnItemClickListener(this);
        return v;
    }

    /* (non-Javadoc)
     * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
     */
    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        if (null != events && events.size() > arg2) {
            Event e = events.get(arg2);
            showEventDetailDialog(e);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        checkData(false);
        initData();
    }

    private void checkData(boolean forceQuery) {
        if (forceQuery || null == events) {
            events = EventService.getInstance().getAll();
        }
    }

    private void initData() {
        if (events != null && events.size() > 0) {
            ListView eventList = (ListView) getView().findViewById(R.id.list_events);
            eventList.setAdapter(new EventListAdapter(getActivity(), events));
        } else {
            UIUtil.showToast(getActivity(), "no event!");
//			getView().findViewById(R.id.addEvent).setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addEvent:
                switchFragment(FRAGMENT_ADD_EVENT, false);
                break;

            default:
                break;
        }
    }

    @Override
    public void refreshFragment() {
        checkData(true);
        initData();
    }

    private void showEventDetailDialog(Event e) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("事件详细");
        builder.setMessage(e.print());
        builder.setPositiveButton("OK", null);
        builder.create().show();
    }

}
