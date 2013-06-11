package com.pradyumna.hmt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.pradyumna.hmt.adapters.HiringRequestAdapter;
import com.pradyumna.hmt.adapters.ResourceAdapter;
import com.pradyumna.hmt.models.HiringStatus;
import com.pradyumna.hmt.models.Resource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pradyumnad
 * Date: 12/06/13
 * Time: 2:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class ResultsActivity extends Activity {
	public static JSONArray results;
	public static Tab dataType;

	JSONArray internalPoolResults;
	JSONArray benchResults;
	JSONArray hiringStatusResults;
	JSONArray approvalRequestsResults;

	List<HiringStatus> requests;

	public ResultsActivity () {
		super();
	}

	public ResultsActivity (JSONArray results, Tab type) {
		super();
		dataType = type;
		this.results = results;
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results);

		ListView listView = (ListView)findViewById(R.id.results_listView);

		if (dataType == Tab.HIRING_STATUS_TAB) {
			 requests = new ArrayList<HiringStatus>();
			for (int i = 0; i < results.length(); i++) {
				JSONObject object = null;
				try {
					object = results.getJSONObject(i);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				HiringStatus resource = new HiringStatus(object);
				requests.add(i, resource);
			}

			//Assigning json objects
			hiringStatusResults = results;

			HiringRequestAdapter adapter = new HiringRequestAdapter(ResultsActivity.this, R.layout.hr_list_row, requests);
			listView.setAdapter(adapter);
			adapter.notifyDataSetChanged();
		} else if (dataType == Tab.APPROVAL_STATUS_TAB) {
			requests = new ArrayList<HiringStatus>();
			for (int i = 0; i < results.length(); i++) {
				JSONObject object = null;
				try {
					object = results.getJSONObject(i);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				HiringStatus resource = new HiringStatus(object);
				requests.add(i, resource);
			}
			//Assigning json objects
			approvalRequestsResults = results;

			HiringRequestAdapter adapter = new HiringRequestAdapter(ResultsActivity.this, R.layout.hr_list_row, requests);
			listView.setAdapter(adapter);
			adapter.notifyDataSetChanged();
		} else {
			List<Resource> resources = new ArrayList<Resource>();
			for (int i = 0; i < results.length(); i++) {
				JSONObject object = null;
				try {
					object = results.getJSONObject(i);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				Resource resource = new Resource(object);
				resources.add(i, resource);
			}

			if (dataType == Tab.INTERNAL_POOL_TAB) {
				internalPoolResults = results;
			} else {
				benchResults = results;
			}

			ResourceAdapter adapter = new ResourceAdapter(ResultsActivity.this, R.layout.ip_list_row, resources);
			listView.setAdapter(adapter);
			adapter.notifyDataSetChanged();
		}

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//Write code to handle View..
				if (dataType == Tab.HIRING_STATUS_TAB) {
					Intent myIntent = new Intent(getApplicationContext(), HiringStatusDetailActivity.class);
					try {
						Log.d(this.getClass().toString(), results.toString());
						myIntent.putExtra("hiringStatus", results.get(position).toString());
					} catch (JSONException e) {
						e.printStackTrace();
					}
					startActivityForResult(myIntent, 0);
				} else {
					Intent myIntent = new Intent(getApplicationContext(), ResourceDetail.class);
					JSONObject jsonResource = null;
					Resource resource = null;
					String type;
					if (dataType == Tab.INTERNAL_POOL_TAB) {
						try {
							jsonResource = (JSONObject) results.get(position);
						} catch (JSONException e) {
							e.printStackTrace();
							return;
						}
						type = "IP";
						resource = new Resource(jsonResource);
					} else {
						try {
							jsonResource = (JSONObject) results.get(position);
						} catch (JSONException e) {
							e.printStackTrace();
							return;
						}
						type = "B";
						resource = new Resource(jsonResource);
					}
					myIntent.putExtra("resourceId", resource.ascId);
					myIntent.putExtra("type", type);
					startActivity(myIntent);
				}
			}
		});
	}
}