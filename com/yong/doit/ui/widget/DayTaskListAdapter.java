package com.yong.doit.ui.widget;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yong.doit.R;
import com.yong.doit.data.bean.Task;

public class DayTaskListAdapter extends BaseAdapter {

    private List<Task> datas;

    private Context context;

    public DayTaskListAdapter(Context context) {
        super();
        this.context = context;
    }

    public DayTaskListAdapter(Context context, List<Task> datas) {
        super();
        this.datas = datas;
        this.context = context;
    }

    public void setDatas(List<Task> datas) {
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
                    R.layout.day_task_item, null);
            holder = new ViewHolder();
            holder.isNew = (TextView) convertView
                    .findViewById(R.id.isNew);
            holder.taskName = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.taskName.setText(datas.get(position).getName());
        return convertView;
    }

    public static class ViewHolder {
        TextView isNew;
        TextView taskName;

    }
}
