package com.hackbulgaria.animations.example.animationtest;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;

public class MainActivity extends Activity {
	private ProgressBar progressBar;
	private ScrollView scrollView;
	private int animationDuration;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		progressBar = (ProgressBar) findViewById(R.id.loading_spinner);
		scrollView = (ScrollView) findViewById(R.id.content);

		scrollView.setVisibility(View.GONE);

		animationDuration = 3000;

		crossfade();
	}

	private void crossfade() {
		scrollView.setVisibility(View.VISIBLE);
		scrollView.setAlpha(0);
		scrollView.animate().alpha(1).setDuration(animationDuration)
				.setListener(null);
		progressBar.animate().alpha(0).setDuration(animationDuration)
				.setListener(new AnimatorListenerAdapter() {
					public void onAnimationEnd(Animator animation) {
						progressBar.setVisibility(View.GONE);
					}
				});

	}
}
