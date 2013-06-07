package com.pradyumna.hmt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import helpers.PDJSONAdapter;
import org.json.JSONException;
import org.json.JSONObject;

public class HiringStatusDetailActivity extends BaseActivity {
	private JSONObject hiringStatus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hiring_status_detail);
		//Have to convert to JSON
		String hiringStatusString =  (String)getIntent().getStringExtra("hiringStatus");
		try {
			hiringStatus = new JSONObject(hiringStatusString);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		Log.d(this.getClass().toString(), ">>> Id : "+hiringStatus);

		ListView listView = (ListView)findViewById(R.id.hiring_status_details_listView);

		PDJSONAdapter pdjsonAdapter = new PDJSONAdapter(hiringStatus);
		listView.setAdapter(pdjsonAdapter);
		pdjsonAdapter.notifyDataSetChanged();
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
