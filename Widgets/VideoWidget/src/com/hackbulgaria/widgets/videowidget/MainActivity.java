package com.hackbulgaria.widgets.videowidget;

import java.io.File;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.VideoView;

public class MainActivity extends Activity {

	public static final int SEEK_DISTANCE = 3000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ImageButton prev = (ImageButton) findViewById(R.id.seek_left);
		ImageButton next = (ImageButton) findViewById(R.id.seek_right);
		final ImageButton playPause = (ImageButton) findViewById(R.id.play_pause);
		final VideoView vid = (VideoView) findViewById(R.id.video);
		File video = new File(Environment.getExternalStorageDirectory(),
				"A.mp4");
		vid.setVideoURI(Uri.fromFile(video));

		playPause.setOnClickListener(new View.OnClickListener() {
			boolean playing = false;

			@Override
			public void onClick(View v) {
				if (!playing) {
					playing = true;
					playPause.setImageURI(Uri
							.parse("android.resource://com.hackbulgaria.widgets.videowidget/"
									+ R.drawable.pause));
					vid.start();
				} else {
					playing = false;
					playPause.setImageURI(Uri
							.parse("android.resource://com.hackbulgaria.widgets.videowidget/"
									+ R.drawable.play));
					vid.pause();
				}
			}
		});

		prev.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				vid.pause();
				vid.seekTo(vid.getCurrentPosition() - SEEK_DISTANCE);
			}
		});

		next.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				vid.pause();
				vid.seekTo(vid.getCurrentPosition() + SEEK_DISTANCE);
			}
		});
	}
}
