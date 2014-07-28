package com.yong.doit.ui.widget;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yong.doit.R;
import com.yong.doit.app.Cst;
import com.yong.doit.app.EventMode;
import com.yong.doit.data.bean.Event;

public class EventListAdapter extends BaseAdapter {

    private List<Event> datas;

    private Context context;

    public EventListAdapter(Context context) {
        super();
        this.context = context;
    }

    public EventListAdapter(Context context, List<Event> datas) {
        super();
        this.datas = datas;
        this.context = context;
    }

    public void setDatas(List<Event> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas != null ? datas.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return datas != null ? datas.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.event_item, null);
            holder = new ViewHolder();
            holder.eventMode = (TextView) convertView
                    .findViewById(R.id.eventMode);
            holder.eventName = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.eventMode
                .setText(datas.get(position).getEventMode() == Cst.EVENT_MODE_MEMORY ? EventMode.MEMORY
                        .toString() : EventMode.PROGRESS.toString());
        holder.eventName.setText(datas.get(position).getName());
        return convertView;
    }

    public static class ViewHolder {
        TextView eventMode;
        TextView eventName;

    }
}
