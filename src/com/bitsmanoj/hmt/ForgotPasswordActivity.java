package com.bitsmanoj.hmt;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import helpers.WSHelper;
import helpers.WSListener;
import helpers.WSType;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.bitsmanoj.hmt.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: BITSManoj
 * Date: 10/06/13
 * Time: 12:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class ForgotPasswordActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forgotpassword);

		Button submit = (Button)findViewById(R.id.submit_btn);
		submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView ascIdTextView = (TextView)findViewById(R.id.ascIdTextView);
				TextView emailIdTextView = (TextView)findViewById(R.id.emailIdTextView);

				List<NameValuePair> list = new ArrayList<NameValuePair>(3);
				list.add(new BasicNameValuePair("ascId", ascIdTextView.getText().toString()));
				list.add(new BasicNameValuePair("emailId", emailIdTextView.getText().toString()));
				list.add(new BasicNameValuePair("q", "forgotPassword"));

				WSHelper helper = new WSHelper("http://becognizant.net/HMT/users.php", list, getApplicationContext());
				helper.addWSListener(new WSListener() {
					@Override
					public void onRequestCompleted(String response) {
						Log.d(this.getClass().toString(), response);
						finish();
					}
					@Override
					public void onRequestFailed(Exception exception) {
						Log.d(this.getClass().toString(), exception.getMessage());
					}
				});
				helper.processRequest(WSType.WSGET);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

	public boolean onOptionsItemSelected(MenuItem item){
		finish();
		return true;
	}
}