package com.pradyumna.hmt;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class HiringStatusDetailActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hiring_status_detail);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.edit_hiring_status, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()) {
			case R.id.Edit : {
				Intent myIntent = new Intent(getApplicationContext(), SearchActivity.class);
				startActivityForResult(myIntent, 0);
				break;
			}
			default:
				super.onOptionsItemSelected(item);
		}
		return true;
	}

}
