/*
 * File:RoundStepView.java
 * Date:2014-3-14上午10:26:15
 *
 * 四川长虹网络科技有限责任公司 (智能应用研发部)© 版权所有 
 */
package com.yong.doit.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

/**
 * @author yonkers
 */
public class RoundStepView extends View {

	private int lineColor;
	private int ovalColor;
	private static Paint paint = new Paint();

	private String text = "5";

	private static float density;

	private boolean fromTop = true;
	private boolean toBottom = true;
	private boolean lineMode = false;

	/**
	 * @param context
	 */
	public RoundStepView(Context context) {
		super(context);
		init(context, null);
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public RoundStepView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public RoundStepView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs) {
		if (null == attrs) {

		} else {
			
		}
		lineColor = Color.BLUE;
		ovalColor = Color.GREEN;
		paint.setAntiAlias(true);
		density = context.getResources().getDisplayMetrics().density;
	}

	public void setText(String text) {
		this.text = text;
		invalidate();
	}

	/**
	 * @param fromTop
	 *            the fromTop to set
	 */
	public void setFromTop(boolean fromTop) {
		this.fromTop = fromTop;
	}

	/**
	 * @param toBottom
	 *            the toBottom to set
	 */
	public void setToBottom(boolean toBottom) {
		this.toBottom = toBottom;
	}

	/**
	 * @param lineMode the lineMode to set
	 */
	public void setLineMode(boolean lineMode) {
		this.lineMode = lineMode;
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		System.out.println("onSizeChanged....w:" + w + " h:" + h + " oldw:"
				+ oldw + " oldh:" + oldh);
	}

	public void resetSize(int w,int h){
		LayoutParams params = getLayoutParams();
//		params.width = w;
//		params.height = h;
//		requestLayout();
        postInvalidateDelayed(200);
	}
	
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		int width = getMeasuredWidth();
		int height = getMeasuredHeight();
		
		if(height == 0 ){
			return ;
		}
		System.out.println("w:" + width + " h:" + height);

		// draw line
		paint.setColor(lineColor);
		paint.setStrokeWidth(2 * density);
		int sy = 0, ey = height;
		if (!fromTop) {
			sy = height / 2;
		}
		if (!toBottom) {
			ey = height / 2;
		}
//		canvas.drawLine(width / 2 - 1, sy, width / 2 - 1, ey, paint);

		if(lineMode){
			//draw line only
			return;
		}
		
		// draw oval
		paint.setColor(ovalColor);
		int radius;
		if (width > height) {
			radius = (int) (height * 0.4 / 2);
		} else {
			radius = (int) (width * 0.4 / 2);
		}
		int cx = width / 2;
		int cy = height / 2;
		System.out.println("radius:" + radius + " cx:" + cx + " cy:" + cy);
		paint.setStyle(Paint.Style.FILL);
		RadialGradient RadialGradient = new RadialGradient(0, 10, radius,
				Color.MAGENTA, Color.BLUE, TileMode.MIRROR);
		paint.setShader(RadialGradient);
		canvas.drawCircle(cx, cy, radius, paint);

		// draw text
		if (!TextUtils.isEmpty(text)) {
			paint.setShader(null);// if not call, can't draw text
			paint.setColor(Color.WHITE);
			paint.setTextSize(radius * 1.5f);
			Rect rect = getTextBound(text);
			System.out.println("rect:" + rect);
			canvas.drawText(text, width / 2 - rect.centerX(),
					height / 2 - rect.centerY(), paint);
		}

		paint.reset();
	}

	private Rect getTextBound(String text) {
		TextPaint textPaint = new TextPaint(paint);
		Rect rect = new Rect();
		textPaint.getTextBounds(text, 0, text.length(), rect);
		return rect;
	}

}
