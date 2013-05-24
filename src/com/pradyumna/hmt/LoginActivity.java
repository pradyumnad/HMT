package com.pradyumna.hmt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity  {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Button loginButton = (Button)findViewById(R.id.login_button);
		loginButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
				nameValuePairs.add(new BasicNameValuePair("email", ((EditText)findViewById(R.id.editTextLoginEmail)).getText().toString().trim() ));
				nameValuePairs.add(new BasicNameValuePair("password", ((EditText)findViewById(R.id.editTextLoginPassword)).getText().toString().trim()));

				WSHelper wsHelper = new WSHelper("http://becognizant.net/HMT/login.php", nameValuePairs, getApplicationContext());
				wsHelper.addWSListener(new WSListener() {
					@Override
					public void onRequestCompleted(String response) {
						Log.d(this.getClass().toString(), response);
						//Parse the JSON and get the parameters
					}

					@Override
					public void onRequestFailed(Exception exception) {
						Log.d(this.getClass().toString(), exception.getMessage());
					}
				});
				wsHelper.processRequest(WSType.WSPOST);
			 }
		});
		Button signupButton = (Button)findViewById(R.id.signup_button);
		signupButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.e(">>>"+this.getClass().toString(), "Sign Up");
			  startActivity(new Intent(LoginActivity.this, SignupActivity.class));	
			}
		});
	}
}
