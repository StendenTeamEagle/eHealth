package com.example.mhealthapp;

import zh.wang.android.apis.yweathergetter4a.YahooWeather;
import zh.wang.android.apis.yweathergetter4a.YahooWeatherExceptionListener;
import zh.wang.android.apis.yweathergetter4a.YahooWeatherInfoListener;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import zh.wang.android.apis.yweathergetter4a.WeatherInfo;
import zh.wang.android.apis.yweathergetter4a.YahooWeather.SEARCH_MODE;
import com.example.mhealthapp.R;

public class WeatherActivity extends ActionBarActivity implements YahooWeatherInfoListener,
YahooWeatherExceptionListener {
	
	private EditText textweather;
	private Button btnweathersearch;
	private TextView textviewweather;
	
	private YahooWeather mYahooWeather = YahooWeather.getInstance(5000, 5000, true);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weather_layout);
		textviewweather = (TextView) findViewById(R.id.weathertextview);
		textweather = (EditText) findViewById(R.id.weatheredit);
		btnweathersearch = (Button) findViewById(R.id.weathersearchbtn);
        btnweathersearch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String _location = textweather.getText().toString();
				if (!TextUtils.isEmpty(_location)) {
					InputMethodManager imm = (InputMethodManager)getSystemService(
	              	      Context.INPUT_METHOD_SERVICE);
	              	imm.hideSoftInputFromWindow(textweather.getWindowToken(), 0);
	                searchByPlaceName(_location);
				} else {
					Toast.makeText(getApplicationContext(), "location is not inputted", 1).show();
				}
			}
		});
	}

	@Override
	public void onFailConnection(Exception e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFailParsing(Exception e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFailFindLocation(Exception e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gotWeatherInfo(WeatherInfo weatherInfo) {
		// TODO Auto-generated method stub
		 if (weatherInfo != null) {
				textviewweather.setText(weatherInfo.getTitle() + "\n"
						+ weatherInfo.getWOEIDneighborhood() + ", "
						+ weatherInfo.getWOEIDCounty() + ", "
						+ weatherInfo.getWOEIDState() + ", " 
						+ weatherInfo.getWOEIDCountry()+ "\n" + "\n" +
						"====== CURRENT ======" + "\n" +
			           "date: " + weatherInfo.getCurrentConditionDate() + "\n" +
			           "weather: " + weatherInfo.getCurrentText() + "\n" +
				       "temperature in ºC: " + weatherInfo.getCurrentTempC() + "\n" +
			           "temperature in ºF: " + weatherInfo.getCurrentTempF() + "\n" +
				       "wind chill in ºF: " + weatherInfo.getWindChill() + "\n" +
			           "wind direction: " + weatherInfo.getWindDirection() + "\n" +
				       "wind speed: " + weatherInfo.getWindSpeed() + "\n" +
			           "Humidity: " + weatherInfo.getAtmosphereHumidity() + "\n" +
				       "Pressure: " + weatherInfo.getAtmospherePressure() + "\n" +
			           "Visibility: " + weatherInfo.getAtmosphereVisibility()
				);
		 }
		
	}
	
	private void searchByPlaceName(String location) {
		mYahooWeather.setNeedDownloadIcons(true);
		mYahooWeather.setSearchMode(SEARCH_MODE.PLACE_NAME);
		mYahooWeather.queryYahooWeatherByPlaceName(getApplicationContext(), location, WeatherActivity.this);
	}

}
