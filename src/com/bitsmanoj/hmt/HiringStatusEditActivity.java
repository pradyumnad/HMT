package com.bitsmanoj.hmt;

import android.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import helpers.WSHelper;
import helpers.WSListener;
import helpers.WSType;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bitsmanoj.hmt.R;
import com.bitsmanoj.hmt.models.HiringStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class HiringStatusEditActivity extends BaseActivity implements OnDateChangedListener {
	
	@Override
	public void onDateChanged(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
	}

	private JSONObject hiringStatus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hiring_status_edit);
		
		String hiringStatusString =  (String)getIntent().getStringExtra("hiringStatus");

		Log.d(this.getClass().toString(), hiringStatusString);
		try {
			hiringStatus = new JSONObject(hiringStatusString);
//			RequestIdentifierNo = hiringStatus.getString("");
			((EditText)findViewById(R.id.editTextSONo)).setText(hiringStatus.getString("SONo"));
			
			((EditText)findViewById(R.id.editTextInternalHiringPOC)).setText(hiringStatus.getString("InternalHiringPOC"));
			((EditText)findViewById(R.id.editTextExternalHiringPOC)).setText(hiringStatus.getString("ExternalHiringPOC"));
			((EditText)findViewById(R.id.editTextIdentifiedProfile)).setText(hiringStatus.getString("IdentifiedProfile"));
			((EditText)findViewById(R.id.editTextCurrentPhaseStatus)).setText(hiringStatus.getString("CurrentPhaseStatus"));
			((EditText)findViewById(R.id.editTextInterviewerIdentified)).setText(hiringStatus.getString("InterviewerIdentified"));
			((EditText)findViewById(R.id.editTextInterviewerAscID)).setText(hiringStatus.getString("InterviewerAscID"));
			((EditText)findViewById(R.id.editTextInterviewerEmailID)).setText(hiringStatus.getString("InterviewerEmailID"));
			((EditText)findViewById(R.id.editTextInterviewerPhone)).setText(hiringStatus.getString("InterviewerPhone"));
			String dateString =  hiringStatus.getString("InterviewDate");
			StringTokenizer tokenizer  = new StringTokenizer(dateString, "-");
			DatePicker datePicker = (DatePicker)findViewById(R.id.datePickerInterviewDate);
			datePicker.init(Integer.parseInt((String) tokenizer.nextElement()),Integer.parseInt((String) tokenizer.nextElement()), Integer.parseInt((String) tokenizer.nextElement()), this);
			
			((EditText)findViewById(R.id.editTextInterviewStatus)).setText(hiringStatus.getString("InterviewStatus"));
			((EditText)findViewById(R.id.editTextSelectionConfirmed)).setText(hiringStatus.getString("SelectionConfirmed"));
			((EditText)findViewById(R.id.editTextApprovers1Email)).setText(hiringStatus.getString("Approvers1Email"));
			((EditText)findViewById(R.id.editTextApprovers2Email)).setText(hiringStatus.getString("Approvers2Email"));
			((EditText)findViewById(R.id.editTextApprovers3Email)).setText(hiringStatus.getString("Approvers3Email"));
			((EditText)findViewById(R.id.editTextApprovers4Email)).setText(hiringStatus.getString("Approvers4Email"));
			((EditText)findViewById(R.id.editTextRemarks)).setText(hiringStatus.getString("Remarks"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.hiring_status_edit, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getTitle() == "HMT") {
			finish();
		}
		switch (item.getItemId()) {
			case R.id.StatusSave: {
				savHiringStatus();
				break;
			}
			default:
				finish();
			}
		return true;
	}
	
		private void savHiringStatus() {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			try {
				nameValuePairs.add(new BasicNameValuePair("requestIdentifierNo",hiringStatus.getString("RequestIdentifierNo")));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			nameValuePairs.add(new BasicNameValuePair("SONo", ((EditText)findViewById(R.id.editTextSONo)).getText().toString().trim()));
			nameValuePairs.add(new BasicNameValuePair("ReqAssignedTo", AppSettings.currentUser.name));
			nameValuePairs.add(new BasicNameValuePair("InternalHiringPOC", ((EditText)findViewById(R.id.editTextInternalHiringPOC)).getText().toString().trim()));
			nameValuePairs.add(new BasicNameValuePair("ExternalHiringPOC", ((EditText)findViewById(R.id.editTextExternalHiringPOC)).getText().toString().trim()));
			nameValuePairs.add(new BasicNameValuePair("IdentifiedProfile", ((EditText)findViewById(R.id.editTextIdentifiedProfile)).getText().toString().trim()));
			nameValuePairs.add(new BasicNameValuePair("CurrentPhaseStatus", ((EditText)findViewById(R.id.editTextCurrentPhaseStatus)).getText().toString().trim()));
			nameValuePairs.add(new BasicNameValuePair("InterviewerIdentified", ((EditText)findViewById(R.id.editTextInterviewerIdentified)).getText().toString().trim()));
			nameValuePairs.add(new BasicNameValuePair("InterviewerAscID", ((EditText)findViewById(R.id.editTextInterviewerAscID)).getText().toString().trim()));
			nameValuePairs.add(new BasicNameValuePair("InterviewerEmailID", ((EditText)findViewById(R.id.editTextInterviewerEmailID)).getText().toString().trim()));
			nameValuePairs.add(new BasicNameValuePair("InterviewerPhone", ((EditText)findViewById(R.id.editTextInterviewerPhone)).getText().toString().trim()));
			
			DatePicker datePicker = (DatePicker)findViewById(R.id.datePickerInterviewDate); 
			String dateFormat = datePicker.getDayOfMonth()+"-"+datePicker.getMonth()+"-"+datePicker.getYear();
			nameValuePairs.add(new BasicNameValuePair("InterviewDate", dateFormat));
			
			nameValuePairs.add(new BasicNameValuePair("InterviewStatus", ((EditText)findViewById(R.id.editTextInterviewStatus)).getText().toString().trim()));
			nameValuePairs.add(new BasicNameValuePair("SelectionConfirmed", ((EditText)findViewById(R.id.editTextSelectionConfirmed)).getText().toString().trim()));
			nameValuePairs.add(new BasicNameValuePair("Approvers1Email", ((EditText)findViewById(R.id.editTextApprovers1Email)).getText().toString().trim()));
			nameValuePairs.add(new BasicNameValuePair("Approvers2Email", ((EditText)findViewById(R.id.editTextApprovers2Email)).getText().toString().trim()));
			nameValuePairs.add(new BasicNameValuePair("Approvers3Email", ((EditText)findViewById(R.id.editTextApprovers3Email)).getText().toString().trim()));
			nameValuePairs.add(new BasicNameValuePair("Approvers4Email", ((EditText)findViewById(R.id.editTextApprovers4Email)).getText().toString().trim()));
			nameValuePairs.add(new BasicNameValuePair("Remarks", ((EditText)findViewById(R.id.editTextRemarks)).getText().toString().trim()));
			System.out.println(nameValuePairs);
			
			WSHelper helper = new WSHelper("http://becognizant.net/HMT/hiringstatus.php", nameValuePairs, HiringStatusEditActivity.this);
			helper.addWSListener(new WSListener() {
				
				@Override
				public void onRequestFailed(Exception exception) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onRequestCompleted(String response) {
					// TODO Auto-generated method stub
					Log.e("EDIT Response", response);
					finish();
				}
			});
			helper.processRequest(WSType.WSPOST);

		}
	
}
