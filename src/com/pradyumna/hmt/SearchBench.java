package com.pradyumna.hmt;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

public class SearchBench extends Activity {
	private DatePicker dpStartResult;
	private Spinner spinner1;
	private int sYear;
	private int sMonth;
	private int sDay;
	private String[] operators;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_bench);
		setCurrentDateOnView();
	}

	// display current date
		public void setCurrentDateOnView() {
	 
			dpStartResult = (DatePicker) findViewById(R.id.lastDateOnProjectDatePicker);
			spinner1 = (Spinner)findViewById(R.id.lastDateOnProjectOperatorSpinner);
	        
	        operators = new String[] {"greater than", "less than", "equal to"};

	        ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(SearchBench.this, android.R.layout.simple_spinner_item, operators);
	        adapter0.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
	        spinner1.setAdapter(adapter0);
			
	        final Calendar c = Calendar.getInstance();
			sYear = c.get(Calendar.YEAR);
			sMonth = c.get(Calendar.MONTH);
			sDay = c.get(Calendar.DAY_OF_MONTH);
	
			// set current date into datepicker
			dpStartResult.init(sYear, sMonth, sDay, null);
	 
		}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_bench, menu);
		return true;
	}
}
