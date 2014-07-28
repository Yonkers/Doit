/*
 * File:CourseRoadmapItemView.java
 * Date:2014-3-14下午3:23:13
 *
 * 四川长虹网络科技有限责任公司 (智能应用研发部)© 版权所有 
 */
package com.yong.doit.ui.widget;

import com.yong.doit.R;
import com.yong.doit.data.bean.RoadMapItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author yonkers
 */
public class CourseRoadmapItemView extends LinearLayout implements OnClickListener{

	/**
	 * @param context
	 */
	public CourseRoadmapItemView(Context context) {
		super(context);
		init(context);
	}

	TextView tvName;
	TextView tvDesc;
	RoundStepView step;
	private void init(Context context){
		
		LayoutInflater.from(context).inflate(R.layout.course_road_map_item, this);
		
		tvName = (TextView) findViewById(R.id.name);
		tvDesc = (TextView) findViewById(R.id.tvDesc);
		step = (RoundStepView) findViewById(R.id.road_step);
		descLayout = (LinearLayout) findViewById(R.id.desc_layout);
		descLayout.setVisibility(View.GONE);

//		setOnClickListener(this);
	}
	
	LinearLayout descLayout ;
	
	@Override
	public void onClick(View v) {
		
	}
	
	public boolean changeDescVisiblity(){
		if(descLayout.getVisibility() != View.VISIBLE){
			descLayout.setVisibility(View.VISIBLE);
			return true;
		}else{
			descLayout.setVisibility(View.GONE);
			return false;
		}
	}
	
	public boolean setDetailLayoutVisible(boolean visible){
		if(visible){
			descLayout.setVisibility(View.VISIBLE);
			return true;
		}else{
			descLayout.setVisibility(View.GONE);
			return false;
		}
	}
	
	public boolean detailLayoutVisible(){
		return descLayout.getVisibility() == View.VISIBLE;
	}

	public void setData(RoadMapItem item,int position){
		tvName.setText(item.getName());
		tvDesc.setText(item.getDescription());
		step.setText(""+position);
	}
	
	public void setName(String name){
		tvName.setText(name);
	}
	
	public void setDescription(String desc){
		tvDesc.setText(desc);
	}
	
	public void setStep(int step){
		this.step.setText(""+step);
	}
	
	public void setFirst(boolean isFirst){
		if(isFirst){
			step.setFromTop(false);
		}else{
			step.setFromTop(true);
		}
	}
	
	public void setLast(boolean isLast){
		if(isLast){
			step.setToBottom(false);
		}else{
			step.setToBottom(true);
		}
	}

}
