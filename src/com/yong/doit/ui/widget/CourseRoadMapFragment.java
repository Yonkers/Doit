package com.yong.doit.ui.widget;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.yong.doit.R;
import com.yong.doit.data.bean.RoadMapItem;

public class CourseRoadMapFragment extends BaseFragment {

	private CourseRoadMapAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	private ListView list;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		PullListView v = (PullListView) inflater.inflate(R.layout.fragment_course_road_map, null);
		v.enablePullUp2LoadMore(false);
		
		list = new ListView(getActivity());
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		list.setLayoutParams(params);
		list.setDividerHeight(0);
		list.setSelector(android.R.color.transparent);
		v.setContentView(list);

		return v;
	}

	@Override
	public void onResume() {
		super.onResume();
		if(null == adapter){
			adapter = new CourseRoadMapAdapter(getActivity());
			RoadMapItem item;
			List<RoadMapItem> datas = new ArrayList<RoadMapItem>();
			for(int i = 0;i < 10 ; i++){
				item = new RoadMapItem();
				item.setName("eslpod course " + i);
				item.setDescription("Any idea what I have to change to get a well looking gradient in my live wallpaper?");
				datas.add(item);
			}
			adapter.setDatas(datas);
			bindData2UI();
		}
	}

	private void bindData2UI(){
		list.setAdapter(adapter);
	}
	
	@Override
	public void refreshFragment() {

	}

}
