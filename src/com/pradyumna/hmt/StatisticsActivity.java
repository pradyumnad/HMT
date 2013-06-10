package com.pradyumna.hmt;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class StatisticsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statistics);
		
		WebView webView = (WebView)findViewById(R.id.webView);
		webView.loadData("<H1>Hello Pardhu</H1>", "text/html", "utf-8");
	}
	
}
