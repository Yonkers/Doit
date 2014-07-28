/*
 * File:ImagePageFrame.java
 * Date:2014-3-12上午10:19:42
 *
 * 四川长虹网络科技有限责任公司 (智能应用研发部)© 版权所有 
 */
package com.yong.doit.pdf;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebSettings.ZoomDensity;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.yong.doit.R;

/**
 * @author yonkers
 */
public class ReaderPageFrame extends PageFrame {

	/**
	 * @param context
	 */
	public ReaderPageFrame(Context context) {
		super(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public ReaderPageFrame(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	protected void init(Context context,AttributeSet attrs){
		LayoutInflater.from(context).inflate(R.layout.image_page_frame, this);
	}

	public void setPageIndex(int page){
		TextView tvPage = (TextView) findViewById(R.id.tvCurrPage);
		tvPage.setText("" + page);
	}

	@Override
	public void setPageData(Object obj) {
		ImageView img = (ImageView) findViewById(R.id.imgPage);
		WebView webPage = (WebView) findViewById(R.id.webPage);
		if(obj instanceof Bitmap){
			webPage.setVisibility(View.GONE);
			if(img.getVisibility()!= View.VISIBLE){
				img.setVisibility(View.VISIBLE);
			}
			img.setImageBitmap((Bitmap) obj);
		}else{
			img.setVisibility(View.GONE);
			if(webPage.getVisibility()!= View.VISIBLE){
				webPage.setVisibility(View.VISIBLE);
			}
			webPage.loadData((String)obj, "text/html; charset=utf-8", "utf-8");
		}
	}
	
}
