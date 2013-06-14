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

public class SearchBench extends Activity {
	private EditText dpStartResult;
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

		Button benchSearchBtn = (Button)findViewById(R.id.bench_searchBtn);
		benchSearchBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText editText1 = (EditText)findViewById(R.id.bench_TechnologyEditText);
				EditText editText2 = (EditText)findViewById(R.id.bench_cityEditText);
				EditText editText3 = (EditText)findViewById(R.id.bench_CountryEditText);
				EditText datePicker = (EditText) findViewById(R.id.editTextsearch_startDatePicker);
				Spinner spinner = (Spinner)findViewById(R.id.lastDateOnProjectOperatorSpinner);
				CheckBox checkBox = (CheckBox)findViewById(R.id.benchPolicyInitiatedcheckBox);

				String operator;
				switch (spinner.getSelectedItemPosition()) {
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
					e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
				}

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

				if (!operator.equals("")) {
					nameValuePairs.add(new BasicNameValuePair("LastDateOnProject", datePicker.getText().toString()));
					nameValuePairs.add(new BasicNameValuePair("LastDateOnProjectOperator", operator));
				}


				if (checkBox.isChecked()) {
					nameValuePairs.add(new BasicNameValuePair("benchPolicyInitiated", "1"));
				} else {
					nameValuePairs.add(new BasicNameValuePair("benchPolicyInitiated", "0"));
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

	// display current date
		public void setCurrentDateOnView() {
	 
			dpStartResult = (EditText) findViewById(R.id.editTextsearch_startDatePicker);
			spinner1 = (Spinner)findViewById(R.id.lastDateOnProjectOperatorSpinner);
	        
	        operators = new String[] {"-- SELECT --", "greater than", "less than", "equal to"};

	        ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(SearchBench.this, android.R.layout.simple_spinner_item, operators);
	        adapter0.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
	        spinner1.setAdapter(adapter0);
			
//	        final Calendar c = Calendar.getInstance();
//			sYear = c.get(Calendar.YEAR);
//			sMonth = c.get(Calendar.MONTH);
//			sDay = c.get(Calendar.DAY_OF_MONTH);
//	
//			// set current date into datepicker
//			dpStartResult.init(sYear, sMonth, sDay, null);
		}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_bench, menu);
		return true;
	}
}
