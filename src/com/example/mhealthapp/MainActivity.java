package com.example.mhealthapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	Button btnWeather;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnWeather = (Button) findViewById(R.id.weatherbtn);
		btnWeather.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
        switch (v.getId()) {
        case R.id.weatherbtn:
            Intent newWeather= new Intent(this, WeatherActivity.class);
            startActivity(newWeather);
            this.finish();
            break;
        }
		
	}
	

}
