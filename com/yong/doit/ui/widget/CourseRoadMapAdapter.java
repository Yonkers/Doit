package com.yong.doit.ui.widget;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yong.doit.R;
import com.yong.doit.data.bean.RoadMapItem;

public class CourseRoadMapAdapter extends BaseAdapter {

    private List<RoadMapItem> datas;
    
    private List<Boolean> expands = new ArrayList<Boolean>();

    private Context context;

    public CourseRoadMapAdapter(Context context) {
        super();
        this.context = context;
    }

    public CourseRoadMapAdapter(Context context, List<RoadMapItem> datas) {
        super();
        this.datas = datas;
        this.context = context;
    }

    public void setDatas(List<RoadMapItem> datas) {
        this.datas = datas;
        initExpands();
    }

    public void initExpands(){
    	expands.clear();
    	int count = getCount();
    	for(int i = 0 ;i < count ; i ++){
    		expands.add(Boolean.FALSE);
    	}
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (null == convertView) {
        	convertView = new CourseRoadmapItemView(context);
            holder = new ViewHolder();
            holder.itemView = (CourseRoadmapItemView) convertView;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.itemView.setData(datas.get(position), position);
        holder.itemView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				boolean visible = holder.itemView.changeDescVisiblity();
				expands.set(position, visible);
			}
		});
        holder.itemView.setDetailLayoutVisible(expands.get(position));

        holder.itemView.setFirst(position == 0);
        holder.itemView.setLast(position == getCount() - 1);
//        if(position % 2 == 0){
//            holder.itemView.setBackgroundColor(Color.GRAY);
//        }
//        holder.step.setText("" + position);
        return convertView;
    }

    public static class ViewHolder {
        CourseRoadmapItemView itemView;
    }
}
