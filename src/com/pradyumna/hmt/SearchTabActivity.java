package com.pradyumna.hmt;


import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class SearchTabActivity extends TabActivity {
  @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	 setContentView(R.layout.activity_search_tab);
       
        Intent testsIntent = new Intent(this,SearchHiringReq.class);
        Intent toolsIntent = new Intent(this,SearchHiringPool.class);
        Intent perfToolsIntent = new Intent(this,SearchBench.class);
                
        TabHost mTabHst = getTabHost();
        
        mTabHst.addTab(mTabHst.newTabSpec("displaytests").setIndicator("Hiring Request").setContent(testsIntent));
        mTabHst.addTab(mTabHst.newTabSpec("common_tools").setIndicator("Internal Pool").setContent(toolsIntent));    
        mTabHst.addTab(mTabHst.newTabSpec("stress_perf").setIndicator("Bench").setContent(perfToolsIntent));
        
        mTabHst.setCurrentTab(0);
        
    }
}