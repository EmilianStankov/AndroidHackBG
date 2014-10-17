package com.hackbulgaria.touchevents.example.puzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
	List<Drawable> images;
	List<Drawable> imagesShuffled;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main);
		TypedArray ta = getResources().obtainTypedArray(R.array.images);
		getImages(ta);
		shuffleImages();
		for (int i = 0; i < Math.sqrt(images.size()); i++) {
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			LinearLayout layout = new LinearLayout(this);
			layout.setLayoutParams(params);
			mainLayout.addView(layout);
			for (int j = 0; j < Math.sqrt(images.size()); j++) {
				final ImageView iv = new ImageView(this);
				iv.setAdjustViewBounds(true);
				iv.setScaleType(ImageView.ScaleType.FIT_XY);
				iv.setImageDrawable(images.get((int) (i
						* Math.sqrt(images.size()) + j)));
				iv.setLayoutParams(new LinearLayout.LayoutParams(100, 80));
				iv.setOnTouchListener(new View.OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						View.DragShadowBuilder builder = new DragShadowBuilder(
								v);
						iv.startDrag(null, builder, iv, 0);
						return false;
					}

				});
				iv.setOnDragListener(new View.OnDragListener() {

					@Override
					public boolean onDrag(View v, DragEvent de) {
						if (de.getAction() == DragEvent.ACTION_DROP) {
							ImageView source = (ImageView) de.getLocalState();
							Drawable temp = source.getDrawable();
							source.setImageDrawable(iv.getDrawable());
							iv.setImageDrawable(temp);
							if (won()) {
								toast("You win!");
							}
						}
						return true;
					}
				});
				layout.addView(iv);
			}
		}
	}

	private void shuffleImages() {
		imagesShuffled = images;
		Collections.shuffle(imagesShuffled);
	}

	private void getImages(TypedArray ta) {
		images = new ArrayList<Drawable>();
		for (int i = 0; i < ta.length(); i++) {
			images.add(ta.getDrawable(i));
		}
		ta.recycle();
	}

	private boolean won() {
		return imagesShuffled.equals(images);
	}

	private void toast(String s) {
		Toast.makeText(this, s, Toast.LENGTH_LONG).show();
	}
}
