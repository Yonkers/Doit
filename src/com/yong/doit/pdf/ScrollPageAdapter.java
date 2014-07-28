/*
 * File:ScrollPageAdapter.java
 * Date:2014-3-11上午11:41:35
 */
package com.yong.doit.pdf;

import java.util.List;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * @author yonkers
 */
public class ScrollPageAdapter extends PagerAdapter{

	private static String TAG = "ScrollPageAdapter";
	
	private int dataCount;
	
	private List<PageFrame> pages;
	
	/**
	 * @param pages the pages to set
	 */
	public void setPages(List<PageFrame> pages) {
		this.pages = pages;
	}

	/**
	 * @param dataCount the dataCount to set
	 */
	public void setDataCount(int dataCount) {
		this.dataCount = dataCount;
	}

	@Override
	public int getCount() {
		return dataCount;
	}

	@Override
	public boolean isViewFromObject(View view, Object obj) {
		return view == obj;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		Log.d(TAG, "destroyItem " + position);
		PageFrame imgView = pages.get(position % 5);
		container.removeView(imgView);
//		imgView.setPageImage(null);
//		imgView.setPageIndex(-1);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		Log.d(TAG, "instantiateItem " + position);
		PageFrame img = pages.get(position % 5);
		img.setPageIndex(position + 1);
		container.addView(img);
		return img;
	}

}
