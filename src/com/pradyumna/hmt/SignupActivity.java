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

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created with IntelliJ IDEA.
 * User: pradyumnad
 * Date: 07/05/13
 * Time: 10:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class SignupActivity extends Activity  {
	
	Button buttonRegister;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_signup);
		
		buttonRegister=(Button) findViewById(R.id.buttonRegister);
		buttonRegister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				RegisterTask registerTask = new RegisterTask();
				registerTask.execute("");
				
			}
		});

	}
	

	
		class RegisterTask extends AsyncTask<String, Void, String> {

        private Exception exception;

        protected String doInBackground(String... urls) {
            try {
            	  HttpClient httpclient = new DefaultHttpClient();
          	    HttpPost httppost = new HttpPost("http://becognizant.net/HMT/register.php");

          	    try {
          	    	
          	    	
          	        // Add your data
          	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
          	        nameValuePairs.add(new BasicNameValuePair("username", ((EditText)findViewById(R.id.editTextUserName)).getText().toString().trim() ));
          	        nameValuePairs.add(new BasicNameValuePair("password", ((EditText)findViewById(R.id.editTextPassword)).getText().toString().trim()));
          	        nameValuePairs.add(new BasicNameValuePair("email", ((EditText)findViewById(R.id.editTextMail)).getText().toString().trim()));
          	        nameValuePairs.add(new BasicNameValuePair("role", ((EditText)findViewById(R.id.editTextRole)).getText().toString().trim()));
          	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

          	        // Execute HTTP Post Request
          	        HttpResponse response = httpclient.execute(httppost);
	          	    BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
	          	    String json = reader.readLine();
	          	  JSONObject jsonObject = new JSONObject(json);
	          	  Log.e("jsonMesage",jsonObject.get("message").toString());
	          	  
	          	  if (jsonObject.getString("message").startsWith("U")) {
					startActivity(new Intent(SignupActivity.this, LoginActivity.class));
				} else {
					Toast.makeText(SignupActivity.this, "Registraton Failed", Toast.LENGTH_SHORT).show();
				}
          	        
          	    } catch (ClientProtocolException e) {
          	        // TODO Auto-generated catch block
          	    } catch (IOException e) {
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
        }
     }
}