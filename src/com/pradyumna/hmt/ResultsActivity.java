package com.pradyumna.hmt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.pradyumna.hmt.models.Resource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

	public ResultsActivity (JSONArray results, Tab type) {
		super();
		dataType = type;
		this.results = results;
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results);

		ListView listView = (ListView)findViewById(R.id.results_listView);


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