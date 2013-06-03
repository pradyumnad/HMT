package com.pradyumna.hmt;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created with IntelliJ IDEA.
 * User: pradyumnad
 * Date: 03/06/13
 * Time: 8:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class SearchActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		Button searchBtn = (Button)findViewById(R.id.go_button);
		searchBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Implement Search call to Webservice
			}
		});
	}

	public boolean onOptionsItemSelected(MenuItem item){
		finish();
		return true;
	}
}