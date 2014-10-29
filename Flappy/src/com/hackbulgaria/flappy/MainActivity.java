package com.hackbulgaria.flappy;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity {
	private DrawingView game;
	private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = (DrawingView) findViewById(R.id.game);
        hideSystemUI();
        setContentView(R.layout.activity_main);
        
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	mediaPlayer = MediaPlayer.create(this, R.raw.prey_overture);
    	mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				mp.start();
			}
		});
    	mediaPlayer.start();
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	mediaPlayer.pause();
    }
    
    private void hideSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(
        		  View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }
}