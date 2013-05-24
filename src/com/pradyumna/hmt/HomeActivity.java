package com.pradyumna.hmt;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

/**
 * Created with IntelliJ IDEA. User: pradyumnad Date: 07/05/13 Time: 11:43 PM To
 * change this template use File | Settings | File Templates.
 */
public class HomeActivity extends Activity {
	TabHost tabHost;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_home);

		tabHost = (TabHost) findViewById(R.id.tabHost);
		tabHost.setup();

		TabSpec spec1 = tabHost.newTabSpec("TAB 1");
		spec1.setContent(R.id.tab1);
		spec1.setIndicator("TAB 1");

		TabSpec spec2 = tabHost.newTabSpec("TAB 2");
		spec2.setIndicator("TAB 2");
		spec2.setContent(R.id.tab2);

		TabSpec spec3 = tabHost.newTabSpec("TAB 3");
		spec3.setContent(R.id.tab3);
		spec3.setIndicator("TAB 3");
		tabHost.addTab(spec1);
		tabHost.addTab(spec2);
		tabHost.addTab(spec3);
	}
}