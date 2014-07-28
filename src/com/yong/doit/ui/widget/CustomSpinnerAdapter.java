package com.yong.doit.ui.widget;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yong.doit.R;

public class CustomSpinnerAdapter<T> extends BaseAdapter {

    private List<T> datas;

    private Context context;

    public CustomSpinnerAdapter(Context context) {
        super();
        this.context = context;
    }

    public CustomSpinnerAdapter(Context context, List<T> datas) {
        super();
        this.datas = datas;
        this.context = context;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public CustomSpinnerAdapter(List<T> identifies) {
        super();
        this.datas = identifies;
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
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.event_mode_item, null);
        }
        TextView modeItem = (TextView) convertView
                .findViewById(R.id.modeName);
        modeItem.setText(datas.get(position).toString());
        return convertView;
    }
}
