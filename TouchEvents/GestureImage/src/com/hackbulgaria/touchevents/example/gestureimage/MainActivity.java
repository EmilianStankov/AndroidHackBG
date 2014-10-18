package com.hackbulgaria.touchevents.example.gestureimage;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ImageView pic;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pic = (ImageView) findViewById(R.id.picture);

		pic.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getActionMasked()) {
				case MotionEvent.ACTION_DOWN:
					break;
				case MotionEvent.ACTION_MOVE:
					float x_cord = event.getRawX();
					float y_cord = event.getRawY();
					pic.setX(x_cord - pic.getWidth() / 2);
					pic.setY(y_cord - pic.getHeight() / 2);
					break;
				default:
					break;
				}
				return true;
			}
		});

	}
}
