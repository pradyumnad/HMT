package com.pradyumna.hmt;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.pradyumna.hmt.models.HiringStatus;

public class HiringStatusDetailActivity extends BaseActivity {
	private HiringStatus hiringStatus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hiring_status_detail);

		hiringStatus = (HiringStatus)getIntent().getExtras().getSerializable("hiringStatus");

		Log.d(this.getClass().toString(), ">>> Id : "+hiringStatus.RequestIdentifierNo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (AppSettings.userType != UserType.ADMIN) {
			return false;
		}
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
