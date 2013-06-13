package com.bitsmanoj.hmt;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import helpers.WSHelper;
import helpers.WSListener;
import helpers.WSType;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.bitsmanoj.hmt.R;

public class SearchHiringPool extends Activity {
	private DatePicker dpStartResult;
	private DatePicker dpEndResult;
	private Spinner spinner1, spinner2;
	private int sYear, eYear;
	private int sMonth, eMonth;
	private int sDay, eDay;
	private String[] operators;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_hiring_pool);
		setCurrentDateOnView();

		Button benchSearchBtn = (Button)findViewById(R.id.hirPoolsearchBut);
		benchSearchBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText editText1 = (EditText)findViewById(R.id.hirPoolReqTech);
				EditText editText2 = (EditText)findViewById(R.id.hirPooleditText2);
				EditText editText3 = (EditText)findViewById(R.id.hirPooleditText3);

				DatePicker datePicker = (DatePicker) findViewById(R.id.hirPooldpResult);
				Spinner spinner = (Spinner)findViewById(R.id.hirPoolspinner1);

				DatePicker datePicker2 = (DatePicker) findViewById(R.id.hirPoolenddatepicker);
				Spinner spinner2 = (Spinner)findViewById(R.id.hirPoolspinner2);

				String operator1 = getEncodedOperator(spinner.getSelectedItemPosition());
				String operator2 = getEncodedOperator(spinner2.getSelectedItemPosition());


				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(5);
				nameValuePairs.add(new BasicNameValuePair("type", "B"));
				if (editText1.getText().toString().length() > 0) {
					nameValuePairs.add(new BasicNameValuePair("Technology", editText1.getText().toString()));
				}

				if (editText2.getText().toString().length() > 0) {
					nameValuePairs.add(new BasicNameValuePair("City", editText2.getText().toString()));
				}

				if (editText3.getText().toString().length() > 0) {
					nameValuePairs.add(new BasicNameValuePair("Country", editText3.getText().toString()));
				}

				if (!operator1.equals("")) {
					nameValuePairs.add(new BasicNameValuePair("ProjectStartDate", datePicker.getYear()+"-"+datePicker.getMonth()+"-"+datePicker.getDayOfMonth()));
					nameValuePairs.add(new BasicNameValuePair("ProjectStartDateOperator", operator1));
				}

				if (!operator2.equals("")) {
					nameValuePairs.add(new BasicNameValuePair("ProjectEndDate", datePicker.getYear()+"-"+datePicker.getMonth()+"-"+datePicker.getDayOfMonth()));
					nameValuePairs.add(new BasicNameValuePair("ProjectEndDateOperator", operator1));
				}

				WSHelper helper = new WSHelper("http://becognizant.net/HMT/search.php", nameValuePairs, getApplicationContext());
				helper.addWSListener(new WSListener() {
					@Override
					public void onRequestCompleted(String response) {
						try {
							JSONObject jsonObject = new JSONObject(response);
							ResultsActivity resultsActivity = new ResultsActivity(jsonObject.getJSONArray("results"), Tab.BENCH_TAB);
							Intent intent = new Intent(getApplicationContext(), resultsActivity.getClass());
							startActivity(intent);
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}

					@Override
					public void onRequestFailed(Exception exception) {

					}
				});
				helper.processRequest(WSType.WSGET);
			}
		});

	}

	private String getEncodedOperator (int position) {
		String operator;
		switch (position) {
			case 1:
				operator = ">";
				break;
			case 2:
				operator = "=";
				break;
			case 3:
				operator = "<";
				break;
			default:
				operator = "";
				break;
		}
		try {
			operator = URLEncoder.encode(operator, "utf-8");
		} catch (UnsupportedEncodingException e) {
			operator = "";
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
		return operator;
	}
	// display current date
		public void setCurrentDateOnView() {
	 
			dpStartResult = (DatePicker) findViewById(R.id.hirPooldpResult);
			spinner1 = (Spinner)findViewById(R.id.hirPoolspinner1);
	        spinner2 = (Spinner)findViewById(R.id.hirPoolspinner2);
	        
	        operators = new String[] {"-- SELECT --", "greater than", "less than", "equal to"};


	        ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(SearchHiringPool.this, android.R.layout.simple_spinner_item, operators);
	        adapter0.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
	        spinner1.setAdapter(adapter0);
	        spinner2.setAdapter(adapter0);
	        
			operators = new String[] {"New Joinee", "Trainee", "Employee"};
			adapter0 = new ArrayAdapter<String>(SearchHiringPool.this, android.R.layout.simple_spinner_item, operators);
	        adapter0.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
	        
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
