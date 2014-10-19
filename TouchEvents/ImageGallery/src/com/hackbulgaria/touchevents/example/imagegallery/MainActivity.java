package com.hackbulgaria.touchevents.example.imagegallery;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static final int ZOOMED_SIZE = 2;
	private static final int ORIGINAL_SIZE = 1;
	private GestureDetectorCompat myGestureDetector = null;
	private GestureDetector.SimpleOnGestureListener detector = null;
	private TypedArray images = null;
	private ImageView image = null;
	private TextView tv = null;
	private int current = 0;
	private boolean upScaled = false;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		detector = new GestureDetector.SimpleOnGestureListener() {
			@Override
			public boolean onDown(MotionEvent e) {
				return true;
			}

			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2,
					float velocityX, float velocityY) {
				float diffX = e2.getX() - e1.getX();
				if (diffX < 0) {
					next();
				} else {
					previous();
				}
				tv.setText(String.format("%d/%d", current + 1, images.length()));
				return false;
			}

			private void next() {
				if (current < images.length() - 1) {
					image.setBackground(images.getDrawable(current + 1));
					current += 1;
				}
			}

			private void previous() {
				if (current > 0) {
					image.setBackground(images.getDrawable(current - 1));
					current -= 1;
				}
			}

			@Override
			public boolean onDoubleTap(MotionEvent e) {
				if (upScaled) {
					image.setScaleX(ORIGINAL_SIZE);
					image.setScaleY(ORIGINAL_SIZE);
					upScaled = false;
				} else {
					image.setScaleX(ZOOMED_SIZE);
					image.setScaleY(ZOOMED_SIZE);
					upScaled = true;
				}
				return super.onDoubleTap(e);
			}
		};
		if (myGestureDetector == null)
			myGestureDetector = new GestureDetectorCompat(this, detector);
		myGestureDetector.onTouchEvent(event);
		return false;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		images = getResources().obtainTypedArray(R.array.images);
		image = (ImageView) findViewById(R.id.image);
		tv = (TextView) findViewById(R.id.progress);

	}
}
