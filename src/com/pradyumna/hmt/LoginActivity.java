package com.pradyumna.hmt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
			 LoginTask loginTask = new LoginTask();
			 loginTask.execute("");
			 }
		});
		Button signupButton = (Button)findViewById(R.id.signup_button);
		signupButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			  startActivity(new Intent(LoginActivity.this, SignupActivity.class));	
			}
		});
	}

	class LoginTask extends AsyncTask<String, Void, String> {

        private Exception exception;

        protected String doInBackground(String... urls) {
            try {
            	HttpClient httpclient = new DefaultHttpClient();
          	    HttpPost httppost = new HttpPost("http://becognizant.net/HMT/login.php");

          	    try {
          	        // Add your data
          	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
          	        nameValuePairs.add(new BasicNameValuePair("email", ((EditText)findViewById(R.id.editTextLoginEmail)).getText().toString().trim() ));
          	        nameValuePairs.add(new BasicNameValuePair("password", ((EditText)findViewById(R.id.editTextLoginPassword)).getText().toString().trim()));
          	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
          	        
          	        // Execute HTTP Post Request
          	        HttpResponse response = httpclient.execute(httppost);
          	        System.out.println(response.toString());
	          	    BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
	          	    String json = reader.readLine();
	          	    Log.e("Res", json);
	          	  	JSONObject jsonObject = new JSONObject(json);
	          	  	Log.e("jsonMesage",jsonObject.get("message").toString());
	          	  
	          	  if (jsonObject.getString("status").equals("1")) {
					startActivity(new Intent(LoginActivity.this, HomeActivity.class));
				} else {
					Log.e("LLogin error", "Login error");
					Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
				}
          	        
          	    } catch (ClientProtocolException e) {
          	    	e.printStackTrace();
          	        // TODO Auto-generated catch block
          	    } catch (IOException e) {
          	      e.printStackTrace();	
          	        // TODO Auto-generated catch block
          	    }

              } catch (Exception e) {
                this.exception = e;
                return null;
            }
			return null;
        }

        protected void onPostExecute(String feed) {
            // TODO: check this.exception 
            // TODO: do something with the feed
        	System.out.println("Fininsheed" + feed);
        }
     }
	
	 
}
