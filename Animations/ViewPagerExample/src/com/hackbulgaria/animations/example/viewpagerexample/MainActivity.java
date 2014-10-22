package com.hackbulgaria.animations.example.viewpagerexample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class MainActivity extends FragmentActivity {

	private static final int NUM_PAGES = 10;
	private ViewPager pager;
	private PagerAdapter pagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pager);

		pager = (ViewPager) findViewById(R.id.pager);
		pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
		pager.setAdapter(pagerAdapter);
		pager.setPageTransformer(true, new MyPageTransformer());
	}

	@Override
	public void onBackPressed() {
		if (pager.getCurrentItem() == 0) {
			super.onBackPressed();
		} else {
			pager.setCurrentItem(pager.getCurrentItem() - 1);
		}
	}

	private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
		public ScreenSlidePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return new ScreenSlidePageFragment();
		}

		@Override
		public int getCount() {
			return NUM_PAGES;
		}
	}

	private class MyPageTransformer implements ViewPager.PageTransformer {
		private static final float MIN_SCALE = 0.85f;
		private static final float MIN_ALPHA = 0.5f;

		@Override
		public void transformPage(View view, float position) {
			int pageWidth = view.getWidth();
			int pageHeight = view.getHeight();

			if (position < -1) {
				view.setAlpha(0);
			} else if (position <= 1) {
				float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));

				view.setScaleX(scaleFactor);
				view.setScaleY(scaleFactor);

				view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE)
						/ (1 - MIN_SCALE) * (1 - MIN_ALPHA));

			} else {
				view.setAlpha(0);
			}
		}
	}
}
