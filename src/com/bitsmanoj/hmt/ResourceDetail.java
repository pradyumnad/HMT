package com.bitsmanoj.hmt;

import org.json.JSONException;
import org.json.JSONObject;

import com.bitsmanoj.hmt.R;

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
 * User: BITSManoj
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

		String [] ip_fields = {
				"AscID",
				"Name",
				"Designation",
				"Technology",
				"ExpInYrs",
				"CurrentProject",
				"Role",
				"ProjRMEmailId",
				"HCMEmailID",
				"Vertical",
				"CurrentBillingRate",
				"ProjectStartDate",
				"ProjectEndDate",
				"ProposedReleaseDate",
				"VisaStatus",
				"Onsite_Offshore",
				"City",
				"State",
				"Country"
		};

		String [] bench_fields = {
				"AscID",
				"Name",
				"Designation",
				"Technology",
				"ExpInYrs",
				"BillingRate",
				"ReadyToRelocate",
				"Role",
				"VisaStatus",
				"onsite_Offshore",
				"LastDateOnProject",
				"benchPolicyInitiated",
				"OnBenchStartingDate",
				"DurationOnBenchInDays",
				"Account",
				"Vertical",
				"City",
				"State",
				"Country",
				"HCMEmailID",
				"AllocationIdentified",
				"IdentifiedProject",
				"IdentifiedProjectDate",
				"Remarks"
		};
		final String fields [];
		if (type.equals("IP")) {
			url = "http://becognizant.net/HMT/internalpool.php?ascID="+ascID;
			fields = ip_fields;
		} else {
			url = "http://becognizant.net/HMT/bench.php?ascID="+ascID;
			fields = bench_fields;
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

				PDJSONAdapter adapter = new PDJSONAdapter(resourceJsonObject, getApplicationContext(), fields);
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