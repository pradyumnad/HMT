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

		tabHost = (TabHost)findViewById(R.id.tabHost);
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
}