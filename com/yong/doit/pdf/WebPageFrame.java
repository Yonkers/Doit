/*
 * File:WebPageFrame.java
 * Date:2014-3-12上午10:17:47
 *
 * 四川长虹网络科技有限责任公司 (智能应用研发部)© 版权所有 
 */
package com.yong.doit.pdf;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.webkit.WebView;
import android.widget.TextView;

import com.yong.doit.R;

/**
 * @author yonkers
 */
public class WebPageFrame extends PageFrame {

	/**
	 * @param context
	 */
	public WebPageFrame(Context context) {
		super(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public WebPageFrame(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void init(Context context, AttributeSet attrs) {
		LayoutInflater.from(context).inflate(R.layout.web_page_frame, this);
	}

	@Override
	public void setPageData(Object obj) {
		if(obj instanceof String){
			WebView webView = (WebView) findViewById(R.id.webPage);
			webView.loadData((String)obj, "text/html; charset=utf-8", "utf-8");
		}
	}

	@Override
	public void setPageIndex(int page) {
		TextView tvPage = (TextView) findViewById(R.id.tvCurrPage);
		tvPage.setText("" + page);
	}

}
