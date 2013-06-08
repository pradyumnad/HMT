package com.pradyumna.hmt;

import org.json.JSONException;
import org.json.JSONObject;

import helpers.PDJSONAdapter;
import helpers.WSHelper;
import helpers.WSListener;
import helpers.WSType;
import android.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

/**
 * Created with IntelliJ IDEA.
 * User: pradyumnad
 * Date: 29/05/13
 * Time: 1:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResourceDetail extends BaseActivity {
	JSONObject resourceJsonObject;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resource_detail);
		
		String ascID =  (String)getIntent().getStringExtra("resourceId");
		String type =  (String)getIntent().getStringExtra("type");
		String url;
		
		if (type.equals("IP")) {
			url = "http://becognizant.net/HMT/internalpool.php?ascID="+ascID;
		} else {
			url = "http://becognizant.net/HMT/bench.php?ascID="+ascID;
		}
		
		WSHelper wsHelper = new WSHelper(url, null, getApplicationContext());
		wsHelper.addWSListener(new WSListener() {
			@Override
			public void onRequestFailed(Exception exception) {
				Log.d(this.getClass().toString(), "Error loading details");
			}
			
			@Override
			public void onRequestCompleted(String response) {
				try {
					Log.d("JSON", response);
					resourceJsonObject = new JSONObject(response);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				ListView listView = (ListView)findViewById(R.id.listView);
				PDJSONAdapter adapter = new PDJSONAdapter(resourceJsonObject, getApplicationContext());
				listView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
			}
		});
		wsHelper.processRequest(WSType.WSGET);
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		finish();
		return true;
	}
}