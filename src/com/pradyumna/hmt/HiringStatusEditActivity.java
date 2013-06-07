package com.pradyumna.hmt;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import helpers.WSHelper;
import helpers.WSType;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class HiringStatusEditActivity extends BaseActivity {

	public String RequestIdentifierNo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hiring_status_edit);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.hiring_status_edit, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.StatusSave: {
			savHiringStatus();
			break;
		}
		default:
			super.onOptionsItemSelected(item);
		}
		return true;
	}
	
		private void savHiringStatus() {
			
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("RequestIdentifierNo", RequestIdentifierNo));
			nameValuePairs.add(new BasicNameValuePair("SONo", ((EditText)findViewById(R.id.editTextSONo)).getText().toString().trim()));
			nameValuePairs.add(new BasicNameValuePair("ReqAssignedTo", ((Spinner)findViewById(R.id.spinnerReqAssignedTo)).getSelectedItem().toString()));
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
			nameValuePairs.add(new BasicNameValuePair("ProjectStartDate", dateFormat));
			datePicker = (DatePicker)findViewById(R.id.datePickerProjectEndDate); 
			dateFormat = datePicker.getDayOfMonth()+"-"+datePicker.getMonth()+"-"+datePicker.getYear();
			
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
			
			helper.processRequest(WSType.WSPOST);

		}
	
}
