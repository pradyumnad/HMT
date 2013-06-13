package com.bitsmanoj.hmt;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

import com.bitsmanoj.hmt.R;

/**
 * Created with IntelliJ IDEA.
 * User: BITSManoj
 * Date: 03/06/13
 * Time: 8:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class SearchActivityOld extends BaseActivity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_old);

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		//View change Handling
		Spinner spinner = (Spinner)findViewById(R.id.type_spinner);
		List<String> list = new ArrayList<String>();
		list.add("Bench");
		list.add("Internal Pool");
		list.add("Hiring Request");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(dataAdapter);

		spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

			}
		});

		LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.search_bench, null, false);

		LinearLayout searchContainer = (LinearLayout)findViewById(R.id.search_container);
		searchContainer.addView(searchContainer);

		//Search Call
		Button searchBtn = (Button)findViewById(R.id.go_button);
		searchBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Implement Search call to Webservice

			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		finish();
		return true;
	}

	
}