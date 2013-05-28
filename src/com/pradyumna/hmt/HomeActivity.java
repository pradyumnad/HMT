package com.pradyumna.hmt;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

enum Tab {
	INTERNAL_POOL_TAB,
	BENCH_TAB,
	HIRING_STATUS_TAB
}

/**
 * Created with IntelliJ IDEA. User: pradyumnad Date: 07/05/13 Time: 11:43 PM To
 * change this template use File | Settings | File Templates.
 */
public class HomeActivity extends Activity implements TabHost.OnTabChangeListener {
	private TabHost tabHost;
	private Tab currentTab;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_home);

		tabHost = (TabHost)findViewById(R.id.tabHost);
		tabHost.setOnTabChangedListener(this);
		tabHost.setup();

		TabSpec spec1 = tabHost.newTabSpec("Internal Pool");
		spec1.setContent(R.id.tab1);
		spec1.setIndicator("Internal Pool");

		TabSpec spec2 = tabHost.newTabSpec("Bench");
		spec2.setIndicator("Bench");
		spec2.setContent(R.id.tab2);

		TabSpec spec3 = tabHost.newTabSpec("Hiring Status");
		spec3.setContent(R.id.tab3);
		spec3.setIndicator("Hiring Status");

		tabHost.addTab(spec1);
		tabHost.addTab(spec2);
		tabHost.addTab(spec3);
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
		switch (tab) {
			case INTERNAL_POOL_TAB:

				break;

			case BENCH_TAB:

			 	break;

			case HIRING_STATUS_TAB:

				break;
		}
	}
}