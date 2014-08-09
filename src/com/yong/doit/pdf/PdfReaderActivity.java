/*
 * File:PdfReaderActivity.java
 * Date:2014-3-10下午5:38:57
 *
 * 四川长虹网络科技有限责任公司 (智能应用研发部)© 版权所有 
 */
package com.yong.doit.pdf;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yong.doit.R;

/**
 * @author yonkers
 */
public class PdfReaderActivity extends Activity implements OnPageChangeListener,OnClickListener{

	public enum ViewType{WEB,IMAGE};
	
	private static String TAG = "PdfReaderActivity";
	
	private ViewType viewType;

	private ImageView imgPdfPage;

    @ViewInject(R.id.viewPager)
	private ViewPager viewPager;

	private MuPDFCore core;

	private String mFileName;

	private int pageCount;
	private int currPage = 0;

	private ScrollPageAdapter pageAdapter;
	
	private static int w;
	private static int h;

	private DrawPageTask drawPageTask;

    @ViewInject(R.id.btn_fix)
	private Button btnFix;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pdf_reader);
        ViewUtils.inject(this);
//		 imgPdfPage = (ImageView) findViewById(R.id.imgPdfPage);
		viewPager.setOnPageChangeListener(this);
		
		btnFix.setOnClickListener(this);
		
		Intent intent = getIntent();
		Uri uri = intent.getData();
		core = openFile(Uri.decode(uri.getEncodedPath()));

		pageCount = core.countPages();

		Log.i(TAG, "get page count:" + pageCount);

		DisplayMetrics metrics = getResources().getDisplayMetrics();
		w = metrics.widthPixels;
		h = metrics.heightPixels;

		viewType = ViewType.IMAGE;
		btnFix.setText("重排");
		
//		Bitmap bm = drawPage(1);
//		 imgPdfPage.setImageBitmap(bm);
		
		initViewPager(viewType);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_fix:
			if(viewType == ViewType.IMAGE){
				viewType = ViewType.WEB;
				btnFix.setText("取消重排");
			}else{
				viewType = ViewType.IMAGE;
				btnFix.setText("重排");
			}
			reloadData();
			break;

		default:
			break;
		}
	}
	
	private void reloadData(){
		new DrawPageTask().execute(currPage);
		new DrawPageTask().execute(currPage+1);
		new DrawPageTask().execute(currPage-1);
	}

	@Override
	public void onPageScrollStateChanged(int state) {
		Log.d(TAG, "onPageScrollStateChanged " + state);
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//		Log.d(TAG, "onPageScrolled " + position + " " + positionOffset + " " + positionOffsetPixels);
		
	}

	@Override
	public void onPageSelected(int position) {
		Log.d(TAG, "onPageSelected " + position);
		currPage = position;
//		drawPageTask.execute(position+1);
//		drawPageTask.execute(position-1);
		new DrawPageTask().execute(position+1);
		new DrawPageTask().execute(position-1);
	}
	
	class DrawPageTask extends AsyncTask<Integer,Void,Object>{
		private int position;
		@Override
		protected Object doInBackground(Integer... params) {
			position = params[0];
			if(position >=0 ){
//				toText(position);
				PageFrame frame = views.get(position % 5);
				int tag = (Integer) frame.getPosition();
				if(position != tag || frame.getType() != viewType){
					if(viewType == ViewType.IMAGE){
						return drawPage(position);
					}else{
						return toText(position);
					}
				}
			}
			return null;
		}

		@Override
		protected void onPostExecute(Object result) {
			super.onPostExecute(result);
			if(null != result){
				updateUIData(position,result);
			}
		}
	}
	
	private void updateUIData(int position,Object bm){
		if(position >=0 ){
			PageFrame frame = views.get(position % 5);
			int tag = (Integer) frame.getPosition();
			if(position != tag || frame.getType() != viewType){
				frame.setPosition(position);
				frame.setType(viewType);
				frame.setPageData(bm);
				frame.setPageIndex(position + 1);
			}
		}
	}
	
	private void initViewPager(ViewType viewType){
		initPageViews(viewType);
		
		if(pageAdapter!= null){
			pageAdapter = null;
		}
		pageAdapter = new ScrollPageAdapter();
		pageAdapter.setPages(views);
		pageAdapter.setDataCount(pageCount);
		
		viewPager.setAdapter(pageAdapter);
		viewPager.setCurrentItem(currPage);
		reloadData();
	}

	private List<PageFrame> views;
	private void initPageViews(ViewType viewType) {
		if(null != views && this.viewType == viewType){
			return ;
		}
		if(null != views){
			views.clear();
		}else{
			views = new ArrayList<PageFrame>();
		}
		PageFrame pageFrame;
		for(int i = 0 ; i < 5 ; i++){
			pageFrame = new ReaderPageFrame(this);
			ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.MATCH_PARENT);
			pageFrame.setLayoutParams(params);
			pageFrame.setType(viewType);
			pageFrame.setPosition(-1);
			views.add(pageFrame);
		}

	}
	
	private Bitmap drawPage(int page){
		return core.drawPage(page, w, h, 0, 0, w, h);
	}
	
	private String toText(int page){
		byte[] result = core.html(page);
		return new String(result);
	}

	private MuPDFCore openFile(String path) {
		int lastSlashPos = path.lastIndexOf('/');
		mFileName = new String(lastSlashPos == -1 ? path
				: path.substring(lastSlashPos + 1));
		System.out.println("Trying to open " + path);
		try {
			core = new MuPDFCore(this, path);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return core;
	}
}
