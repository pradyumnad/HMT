package com.pradyumna.hmt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import com.pradyumna.hmt.adapters.HiringRequestAdapter;
import com.pradyumna.hmt.adapters.ResourceAdapter;
import com.pradyumna.hmt.models.HiringStatus;
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
import com.pradyumna.*;

enum Tab {
	INTERNAL_POOL_TAB,
	BENCH_TAB,
	HIRING_STATUS_TAB
}

/**
 * Created with IntelliJ IDEA. User: pradyumnad Date: 07/05/13 Time: 11:43 PM To
 * change this template use File | Settings | File Templates.
 */
public class HomeActivity extends BaseActivity implements TabHost.OnTabChangeListener {
	private TabHost tabHost;
	private Tab currentTab;

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

		TabSpec tabHiringStatus = tabHost.newTabSpec("Hiring Status");
		tabHiringStatus.setContent(R.id.tab3);
		tabHiringStatus.setIndicator("Hiring Status");

		if (AppSettings.userType == UserType.ADMIN || AppSettings.userType == UserType.EXECUTIVE_MANAGER) {
			tabHost.addTab(tabHiringStatus);
			tabHost.addTab(tabInternalPool);
			tabHost.addTab(tabBench);
		} else {
		tabHost.addTab(tabInternalPool);
		tabHost.addTab(tabBench);
		tabHost.addTab(tabHiringStatus);
		}
		updateTabContent(Tab.INTERNAL_POOL_TAB);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
//		getMenuInflater().inflate(R.menu.base, menu);

		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item){
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
		} else if (tabId.equals("Hiring Status")) {
			currentTab = Tab.HIRING_STATUS_TAB;
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
		switch (tab) {
			case INTERNAL_POOL_TAB:

				break;

			case BENCH_TAB:

			 	break;

			case HIRING_STATUS_TAB:

				break;
		}
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
				url = "http://becognizant.net/HMT/hiringstatus.php";
				listView = (ListView)findViewById(R.id.requestStatus_listView);
				break;
		}

		WSHelper helper = new WSHelper(url, null, HomeActivity.this);
		listView.setEmptyView(findViewById(R.id.empty));
		final ListView finalListView = listView;
		helper.addWSListener(new WSListener() {
			@Override
			public void onRequestCompleted(String response) {
				try {
					JSONObject responseObject = new JSONObject(response);
					JSONArray results = responseObject.getJSONArray("results");
					
					if (currentTab == Tab.HIRING_STATUS_TAB) {
						List<HiringStatus> requests = new ArrayList<HiringStatus>();
						for (int i = 0; i < results.length(); i++) {
							JSONObject object = results.getJSONObject(i);
							HiringStatus resource = new HiringStatus(object);
							requests.add(i, resource);
						}
				
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

						ResourceAdapter adapter = new ResourceAdapter(HomeActivity.this, R.layout.ip_list_row, resources);
						finalListView.setAdapter(adapter);
						adapter.notifyDataSetChanged();
					}

				} catch (JSONException e) {
					e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
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