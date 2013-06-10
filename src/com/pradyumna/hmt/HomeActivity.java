package com.pradyumna.hmt;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Element;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import com.pradyumna.hmt.adapters.HiringRequestAdapter;
import com.pradyumna.hmt.adapters.ResourceAdapter;
import com.pradyumna.hmt.models.HiringStatus;
import com.pradyumna.hmt.models.Resource;
import helpers.WSHelper;
import helpers.WSListener;
import helpers.WSType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

enum Tab {
	INTERNAL_POOL_TAB,
	BENCH_TAB,
	HIRING_STATUS_TAB,
	APPROVAL_STATUS_TAB
}

/**
 * Created with IntelliJ IDEA. User: pradyumnad Date: 07/05/13 Time: 11:43 PM To
 * change this template use File | Settings | File Templates.
 */
public class HomeActivity extends BaseActivity implements TabHost.OnTabChangeListener {
	private TabHost tabHost;
	private Tab currentTab;

	JSONArray internalPoolResults;
	JSONArray benchResults;
	JSONArray hiringStatusResults;
	JSONArray approvalRequestsResults;
	
	List<HiringStatus> requests;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_home);

		tabHost = (TabHost)findViewById(R.id.tabHost);
		tabHost.setOnTabChangedListener(this);
		tabHost.setup();

		TabSpec tabInternalPool = tabHost.newTabSpec("Internal Pool");
		tabInternalPool.setContent(R.id.tab1);
		tabInternalPool.setIndicator("Internal Pool");

		TabSpec tabBench = tabHost.newTabSpec("Bench");
		tabBench.setIndicator("Bench");
		tabBench.setContent(R.id.tab2);

		TabSpec tabHiringStatus = tabHost.newTabSpec("New Hiring Status");
		tabHiringStatus.setContent(R.id.tab3);
		tabHiringStatus.setIndicator("New Hiring Status");

		TabSpec approvalStatusTab = tabHost.newTabSpec("Approval Requests");
		approvalStatusTab.setContent(R.id.tab4);
		approvalStatusTab.setIndicator("Approval Requests");

		if (AppSettings.userType == UserType.ADMIN) {
			tabHost.addTab(tabHiringStatus);
			tabHost.addTab(tabInternalPool);
			tabHost.addTab(tabBench);
			updateTabContent(Tab.HIRING_STATUS_TAB);			
		} else if (AppSettings.userType == UserType.EXECUTIVE_MANAGER) {
			tabHost.addTab(approvalStatusTab);
			tabHost.addTab(tabHiringStatus);
			tabHost.addTab(tabInternalPool);
			tabHost.addTab(tabBench);
			updateTabContent(Tab.APPROVAL_STATUS_TAB);
		} else {
			tabHost.addTab(tabInternalPool);
			tabHost.addTab(tabBench);
			tabHost.addTab(tabHiringStatus);
			updateTabContent(Tab.INTERNAL_POOL_TAB);			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.search : {
				Intent myIntent = new Intent(getApplicationContext(), SearchActivity.class);
				startActivityForResult(myIntent, 0);
				break;
			}
			case R.id.new_request : {
				Intent myIntent = new Intent(getApplicationContext(), HiringRequest.class);
				startActivityForResult(myIntent, 0);
				break;
			}
			default:
				super.onOptionsItemSelected(item);
		}
		return true;
	}

	@Override
	public void onTabChanged(String tabId) {
		if (tabId.equals("Internal Pool")) {
			currentTab = Tab.INTERNAL_POOL_TAB;
		} else if (tabId.equals("Bench")) {
			currentTab = Tab.BENCH_TAB;
		} else if (tabId.equals("New Hiring Status")) {
			currentTab = Tab.HIRING_STATUS_TAB;
		} else if (tabId.equals("Approval Requests")) {
			currentTab = Tab.APPROVAL_STATUS_TAB;
		}

		updateTabContent(currentTab);
	}

	/**
	 *
	 * @param tab
	 *
	 * @description Used to update content when tapped on a Tab
	 */
	public void updateTabContent(Tab tab) {
		requestContentInTab(tab);
	}

	private void requestContentInTab(Tab tab) {
		String url = null;
		ListView listView = null;
		switch (tab) {
			case BENCH_TAB:
				url = "http://becognizant.net/HMT/bench.php";
				listView = (ListView)findViewById(R.id.bench_listView);
				break;

			case INTERNAL_POOL_TAB:
				url = "http://becognizant.net/HMT/internalpool.php";
				listView = (ListView)findViewById(R.id.internal_pool_listview);
				break;

			case HIRING_STATUS_TAB:
				url = "http://becognizant.net/HMT/hiringstatus.php?status=New";
				listView = (ListView)findViewById(R.id.requestStatus_listView);
				break;
			case APPROVAL_STATUS_TAB:
				url = "http://becognizant.net/HMT/hiringstatus.php?status=Approval%20Requested";
				listView = (ListView)findViewById(R.id.approvalRequest_listView);
				break;
		}

		WSHelper helper = new WSHelper(url, null, HomeActivity.this);
		listView.setEmptyView(findViewById(R.id.empty));
		final ListView finalListView = listView;
		finalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				 //Write code to handle View..
				 if (finalListView.getId() == R.id.requestStatus_listView || finalListView.getId() == R.id.approvalRequest_listView) {
					 Intent myIntent = new Intent(getApplicationContext(), HiringStatusDetailActivity.class);
					 try {
						 Log.d(this.getClass().toString(), hiringStatusResults.toString());
						 myIntent.putExtra("hiringStatus", hiringStatusResults.get(position).toString());
					} catch (JSONException e) {
						e.printStackTrace();
					}
					 startActivityForResult(myIntent, 0);
				 } else {
					 Intent myIntent = new Intent(getApplicationContext(), ResourceDetail.class);
					 JSONObject jsonResource = null;
					 Resource resource = null;
					 String type;
					 if (finalListView.getId() == R.id.internal_pool_listview) {
						 try {
							 jsonResource = (JSONObject) internalPoolResults.get(position);
						 } catch (JSONException e) {
							 e.printStackTrace();
							 return;
						 }
						 type = "IP";
						 resource = new Resource(jsonResource);
					 } else {
						 try {
							 jsonResource = (JSONObject) benchResults.get(position);
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

		helper.addWSListener(new WSListener() {
			@Override
			public void onRequestCompleted(String response) {
				try {
					JSONObject responseObject = new JSONObject(response);
					JSONArray results = responseObject.getJSONArray("results");
					
					if (currentTab == Tab.HIRING_STATUS_TAB || currentTab == Tab.APPROVAL_STATUS_TAB) {
						requests = new ArrayList<HiringStatus>();
						for (int i = 0; i < results.length(); i++) {
							JSONObject object = results.getJSONObject(i);
							HiringStatus resource = new HiringStatus(object);
							requests.add(i, resource);
						}
						//Assigning json objects
						hiringStatusResults = results;
						
						HiringRequestAdapter adapter = new HiringRequestAdapter(HomeActivity.this, R.layout.hr_list_row, requests);
						finalListView.setAdapter(adapter);
						adapter.notifyDataSetChanged();
					} else {
						List<Resource> resources = new ArrayList<Resource>();
						for (int i = 0; i < results.length(); i++) {
							JSONObject object = results.getJSONObject(i);
							Resource resource = new Resource(object);
							resources.add(i, resource);
						}
						
						if (currentTab == Tab.INTERNAL_POOL_TAB) {
							internalPoolResults = results;
						} else {
							benchResults = results;
						}
						
						ResourceAdapter adapter = new ResourceAdapter(HomeActivity.this, R.layout.ip_list_row, resources);
						finalListView.setAdapter(adapter);
						adapter.notifyDataSetChanged();
					}

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			@Override
			public void onRequestFailed(Exception exception) {
				exception.printStackTrace();
			}
		});
		helper.processRequest(WSType.WSGET);
	}
}