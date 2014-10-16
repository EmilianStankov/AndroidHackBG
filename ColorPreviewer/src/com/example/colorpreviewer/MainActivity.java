package com.example.colorpreviewer;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final EditText text = (EditText) findViewById(R.id.text);
		// final Button button = (Button) findViewById(R.id.button);
		final View background = (View) findViewById(R.id.bg);
		text.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				try {
					background.setBackgroundColor(Color.parseColor(text
							.getText().toString()));
				} catch (Exception e) {

				}
			}
		});
		/*
		 * button.setOnClickListener(new View.OnClickListener() {
		 * 
		 * @Override public void onClick(View v) {
		 * background.setBackgroundColor(Color.parseColor(text.getText()
		 * .toString())); } });
		 */
	}
}
