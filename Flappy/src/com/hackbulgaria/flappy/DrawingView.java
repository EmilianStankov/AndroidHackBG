package com.hackbulgaria.flappy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;


public class DrawingView extends ImageView {
	 private Display display;
	 public DrawingView(Context context, AttributeSet attrs) {
		 super(context, attrs);
		 WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		 display = wm.getDefaultDisplay();
	 }
	  
	 @Override
	 protected void onDraw(Canvas canvas) {
		 super.onDraw(canvas);
		 Point size = new Point();
		 display.getSize(size);
		 int width = size.x;
		 int height = size.y;
		 Paint p = new Paint();
		 Bitmap bg = BitmapFactory.decodeResource(getResources(), R.drawable.clouds);
		 GameObject background = new GameObject(Bitmap.createScaledBitmap(bg, width, height, false), 0, 0);
		 GameObject bird = new GameObject(BitmapFactory.decodeResource(getResources(), R.drawable.bird), width / 8, height / 2);
		 background.update();
		 bird.update();
		 background.draw(canvas, p);
		 bird.draw(canvas, p);
		 /*try {
			Thread.sleep(10);
			invalidate();
		 } catch (InterruptedException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }*/
	 }
}
