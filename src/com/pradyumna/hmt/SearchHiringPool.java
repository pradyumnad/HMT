package com.pradyumna.hmt;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

public class SearchHiringPool extends Activity {
	private DatePicker dpStartResult;
	private DatePicker dpEndResult;
	private Spinner spinner1, spinner2, spinner3;
	private int sYear, eYear;
	private int sMonth, eMonth;
	private int sDay, eDay;
	private String[] operators;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_hiring_pool);
		setCurrentDateOnView();
	}

	// display current date
		public void setCurrentDateOnView() {
	 
			dpStartResult = (DatePicker) findViewById(R.id.hirPooldpResult);
			spinner1 = (Spinner)findViewById(R.id.hirPoolspinner1);
	        spinner2 = (Spinner)findViewById(R.id.hirPoolspinner4);
	        spinner3 = (Spinner)findViewById(R.id.hirPoolspinner3);
	        
	        operators = new String[] {"greater than", "less than", "equal to"};


	        ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(SearchHiringPool.this, android.R.layout.simple_spinner_item, operators);
	        adapter0.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
	        spinner1.setAdapter(adapter0);
	        spinner2.setAdapter(adapter0);
	        
			operators = new String[] {"New Joinee", "Trainee", "Employee"};
			adapter0 = new ArrayAdapter<String>(SearchHiringPool.this, android.R.layout.simple_spinner_item, operators);
	        adapter0.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
	        spinner3.setAdapter(adapter0);
	        
	        final Calendar c = Calendar.getInstance();
			sYear = c.get(Calendar.YEAR);
			sMonth = c.get(Calendar.MONTH);
			sDay = c.get(Calendar.DAY_OF_MONTH);
	 
			// set current date into textview
	 
			// set current date into datepicker
			dpStartResult.init(sYear, sMonth, sDay, null);
			
			dpEndResult = (DatePicker) findViewById(R.id.hirPoolenddatepicker);
	 
			final Calendar c1 = Calendar.getInstance();
			eYear = c.get(Calendar.YEAR);
			eMonth = c.get(Calendar.MONTH);
			eDay = c.get(Calendar.DAY_OF_MONTH);
	 
			// set current date into datepicker
			dpEndResult.init(eYear, eMonth, eDay, null);
	 
		}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_bench, menu);
		return true;
	}

}
