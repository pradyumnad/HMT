package com.pradyumna.hmt;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Created with IntelliJ IDEA.
 * User: pradyumnad
 * Date: 29/05/13
 * Time: 1:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class HiringRequest extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hiring_request);

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.hiring_request, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()) {
			case R.id.req_save:
				saveNewHiringRequest();
				break;
			default:
				finish();
				break;
		}
		return true;
	}

	/**
	 * Actions
	 */

	private void saveNewHiringRequest() {

	}
}