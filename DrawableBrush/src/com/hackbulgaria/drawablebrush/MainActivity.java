package com.hackbulgaria.drawablebrush;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	private Paint paint;
	private Canvas canvas;
	private LinearLayout ll;
	private int width;
	private int height;
	private Matrix matrix;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ll = (LinearLayout) findViewById(R.id.rect);
		paint = new Paint();
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		width = size.x;
		height = size.y;
		matrix = new Matrix();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		paint.setColor(Color.parseColor("#CD5C5C"));
		Bitmap bg = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		ll.setBackground(new BitmapDrawable(getResources(), bg));
		canvas = new Canvas(bg);
		Bitmap brush = BitmapFactory.decodeResource(getResources(),
				R.drawable.brush);
		canvas.drawBitmap(brush, matrix, paint);
		return super.onTouchEvent(event);
	}
}
