package com.hackbulgaria.tasks.funwithflags;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flags_easy);
		final View first = findViewById(R.id.first);
		final View second = findViewById(R.id.second);
		final View third = findViewById(R.id.third);
		final int[] rainbow = getResources().getIntArray(R.array.rainbow);
		first.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Random r = new Random();
				first.setBackgroundColor(rainbow[r.nextInt(rainbow.length - 1)]);
			}
		});
		second.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Random r = new Random();
				second.setBackgroundColor(rainbow[r.nextInt(rainbow.length - 1)]);
			}
		});
		third.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Random r = new Random();
				third.setBackgroundColor(rainbow[r.nextInt(rainbow.length - 1)]);
			}
		});
		//setContentView(R.layout.flags_easy);
	}
}
