package com.bitsmanoj.hmt;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import helpers.WSHelper;
import helpers.WSListener;
import helpers.WSType;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.bitsmanoj.hmt.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: BITSManoj
 * Date: 07/05/13
 * Time: 10:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class SignupActivity extends Activity  {
	
	Button buttonRegister;
	Spinner spinnerRole;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_signup);
		
		//Adapter for roles 
		String rolesArray[] = {"--SELECT ROLE--", "Hiring Executive","Hiring Manager","Hiring Admin"};
		ArrayAdapter<String> rolesAdapter = new ArrayAdapter<String>(SignupActivity.this, android.R.layout.simple_list_item_1, rolesArray);
		spinnerRole = (Spinner)findViewById(R.id.spinnerRole);
		spinnerRole.setAdapter(rolesAdapter);
		buttonRegister=(Button) findViewById(R.id.buttonRegister);
		buttonRegister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (spinnerRole.getSelectedItemPosition() == 0) {
					AlertDialog.Builder alert = new AlertDialog.Builder(SignupActivity.this);
					alert.setTitle("Signup");
					alert.setMessage("Enter valid Role");
					alert.setNegativeButton("Ok", new DialogInterface.OnClickListener()
					{
						public void onClick(DialogInterface dialog, int id)
						{
							// Action for 'Cancel' Button
							dialog.cancel();
						}
					});
					alert.show();
					return;
				}

				// Add your data
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(5);
				nameValuePairs.add(new BasicNameValuePair("username", ((EditText)findViewById(R.id.editTextUserName)).getText().toString().trim() ));
				nameValuePairs.add(new BasicNameValuePair("password", ((EditText)findViewById(R.id.editTextPassword)).getText().toString().trim()));
				nameValuePairs.add(new BasicNameValuePair("email", ((EditText)findViewById(R.id.editTextMail)).getText().toString().trim()));
				nameValuePairs.add(new BasicNameValuePair("role", spinnerRole.getSelectedItem().toString()));
				nameValuePairs.add(new BasicNameValuePair("ASCId", ((EditText)findViewById(R.id.editTextASCId)).getText().toString().trim()));
				System.out.println(nameValuePairs);

				WSHelper helper = new WSHelper("http://becognizant.net/HMT/register.php", nameValuePairs, getApplicationContext());
				helper.addWSListener(new WSListener() {
					@Override
					public void onRequestCompleted(String response) {

						try {
							JSONObject jsonObject = new JSONObject(response);
						} catch (JSONException e) {

						}
						finish();
					}
					@Override
					public void onRequestFailed(Exception exception) {
						Toast.makeText(SignupActivity.this, "Registraton Failed", Toast.LENGTH_SHORT).show();
					}
				});
				helper.processRequest(WSType.WSPOST);
			}
		});

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		Log.d(">>>", ""+item);
		finish();
		return true;
	}
}