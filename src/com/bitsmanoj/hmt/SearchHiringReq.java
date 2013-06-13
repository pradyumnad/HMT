package com.bitsmanoj.hmt;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import helpers.WSHelper;
import helpers.WSListener;
import helpers.WSType;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.bitsmanoj.hmt.R;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class SearchHiringReq extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_hiring_req);

		Button button = (Button)findViewById(R.id.hr_search_button);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText editText = (EditText)findViewById(R.id.hr_SONoEditText);
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
				nameValuePairs.add(new BasicNameValuePair("type", "HR"));
				nameValuePairs.add(new BasicNameValuePair("SONo", editText.getText().toString()));

				WSHelper helper = new WSHelper("http://becognizant.net/HMT/search.php", nameValuePairs, getApplicationContext());
				helper.addWSListener(new WSListener() {
					@Override
					public void onRequestCompleted(String response) {
						try {
							JSONObject jsonObject = new JSONObject(response);
							ResultsActivity resultsActivity = new ResultsActivity(jsonObject.getJSONArray("results"), Tab.HIRING_STATUS_TAB);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_hiring_req, menu);
		return true;
	}

}
