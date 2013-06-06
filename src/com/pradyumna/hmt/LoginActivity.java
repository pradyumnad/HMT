package com.pradyumna.hmt;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import helpers.WSHelper;
import helpers.WSListener;
import helpers.WSType;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

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
						try {
							JSONObject jsonObject = new JSONObject(response);
							Integer success = jsonObject.getInt("status");
							if (success == 1) {
								String type = jsonObject.getString("role");
								Log.d("LoginActivity", type);
								if (type.equalsIgnoreCase("Executive Manager")) {
									AppSettings.userType = UserType.EXECUTIVE_MANAGER;
								} else if (type.equalsIgnoreCase("Admin")) {
									AppSettings.userType = UserType.ADMIN;
								} else if (type.equalsIgnoreCase("Requesting Manger")) {
									AppSettings.userType = UserType.REQUESTING_MANAGER;
								}
								
								AppSettings.userId = jsonObject.getString("user_id");
								startActivity(new Intent(LoginActivity.this, HomeActivity.class));
							} else {
								AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
								alert.setTitle("Login");
								alert.setMessage("Login failed!");
								alert.setNegativeButton("Ok", new DialogInterface.OnClickListener()
								{
									public void onClick(DialogInterface dialog, int id)
									{
										// Action for 'Cancel' Button
										dialog.cancel();
									}
								});
								alert.show();
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}
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
