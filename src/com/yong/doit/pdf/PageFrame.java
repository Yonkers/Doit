/*
 * File:PageFrame.java
 * Date:2014-3-11下午2:44:34
 *
 * 四川长虹网络科技有限责任公司 (智能应用研发部)© 版权所有 
 */
package com.yong.doit.pdf;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.yong.doit.pdf.PdfReaderActivity.ViewType;

/**
 * @author yonkers
 */
public abstract class PageFrame extends FrameLayout {

	protected int position ;
	
	protected ViewType type;
	
	/**
	 * @param context
	 */
	public PageFrame(Context context) {
		super(context);
		init(context,null);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public PageFrame(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context,attrs);
	}
	
	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}
	
	/**
	 * @return the type
	 */
	public ViewType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(ViewType type) {
		this.type = type;
	}

	protected abstract void init(Context context,AttributeSet attrs);
	
	public abstract void setPageData(Object obj);
	
	public abstract void setPageIndex(int page);
}
