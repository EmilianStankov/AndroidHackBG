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
	private List<Drawable> images;
	private List<Drawable> imagesShuffled;
	private List<ImageView> views;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main);
		views = new ArrayList<ImageView>();
		getImages();
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
				views.add(iv);
				iv.setImageDrawable(imagesShuffled.get((int) (i
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
		imagesShuffled = new ArrayList<Drawable>(images);
		Collections.shuffle(imagesShuffled);
	}

	private void getImages() {
		TypedArray ta = getResources().obtainTypedArray(R.array.images);
		images = new ArrayList<Drawable>();
		for (int i = 0; i < ta.length(); i++) {
			images.add(ta.getDrawable(i));
		}
		ta.recycle();
	}

	private boolean won() {
		return images.equals(getDrawablesFromViews());
	}

	private void toast(String s) {
		Toast.makeText(this, s, Toast.LENGTH_LONG).show();
	}

	private List<Drawable> getDrawablesFromViews() {
		List<Drawable> drawables = new ArrayList<Drawable>();
		for (ImageView view : views) {
			drawables.add(view.getDrawable());
		}
		return drawables;
	}
}
