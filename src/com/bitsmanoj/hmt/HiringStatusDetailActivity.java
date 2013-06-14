package com.bitsmanoj.hmt;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import helpers.PDJSONAdapter;
import helpers.WSHelper;
import helpers.WSListener;
import helpers.WSType;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.bitsmanoj.hmt.R;

import java.util.ArrayList;
import java.util.List;

public class HiringStatusDetailActivity extends BaseActivity {
	private JSONObject hiringStatus;
	private String approvalStatus;
	private String remarksMessage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hiring_status_detail);
		//Have to convert to JSON
		String hiringStatusString =  (String)getIntent().getStringExtra("hiringStatus");
		Log.d(this.getClass().toString(), hiringStatusString);
		try {
			hiringStatus = new JSONObject(hiringStatusString);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String [] fields = {
				"SONo",
				"CurrentPhaseStatus",
				"SelectionConfirmed",
				"IdentifiedProfile",
				"ReqAssignedTo",
				"InternalHiringPOC",
				"ExternalHiringPOC",
				"InterviewerIdentified",
				"InterviewerAscID",
				"InterviewerEmailID",
				"InterviewerPhone",
				"InterviewDate",
				"InterviewStatus",
				"Approvers1Email",
				"Approvers2Email",
				"Approvers3Email",
				"Approvers4Email",
				"Remarks",
				"Technology",
				"ExpInYrs",
				"DesiredBillingRate",
				"JoiningDateNeededBy",
				"ProposedRule",
				"JobDescription",
				"CertificationNeed",
				"ProposedPlacementProject",
				"Vertical",
				"ProjectStartDate",
				"ProjectEndDate",
				"LongTerm_ShortTermProject",
				"PreferredVisaStatus",
				"Onsite_Offshore",
				"City",
				"State",
				"Country",
				"AdditionalInformation",
				"AdditionalEmailID1",
				"AdditionalEmailID2",
				"ReuqestingMgr",
				"RequestingMgrAscID",
				"RequestingMgrEmailID"
		};

		Log.d(this.getClass().toString(), ">>> Id : "+hiringStatus);

		ListView listView = (ListView)findViewById(R.id.hiring_status_details_listView);

		PDJSONAdapter pdjsonAdapter = new PDJSONAdapter(hiringStatus, getApplicationContext(), fields);
		listView.setAdapter(pdjsonAdapter);
		pdjsonAdapter.notifyDataSetChanged();
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (AppSettings.userType == UserType.REQUESTING_MANAGER) {
			return false;
		} else if (AppSettings.userType == UserType.EXECUTIVE_MANAGER) {
			super.onCreateOptionsMenu(menu);
			getMenuInflater().inflate(R.menu.edit_approval_status, menu);
			return true;
		} else {
			super.onCreateOptionsMenu(menu);
			getMenuInflater().inflate(R.menu.edit_hiring_status, menu);
			return true;
		}
	}

	public boolean onOptionsItemSelected(MenuItem item){
		Log.d("HiringStatusDetailActivity", ""+item.getItemId());
		if (item.getTitle().equals("HMT")) {
			finish();
		}
		switch (item.getItemId()) {
			case R.id.Edit : {
				Intent myIntent = new Intent(getApplicationContext(), HiringStatusEditActivity.class);
				myIntent.putExtra("hiringStatus", hiringStatus.toString());
				
				startActivityForResult(myIntent, 0);
				break;
			}
			case R.id.Approve : {
				approvalStatus = "Approved";
				takeUserReview();
				break;
			}
			case R.id.Reject : {
				approvalStatus = "Rejected";
				takeUserReview();
				break;
			}
			default:
				super.onOptionsItemSelected(item);
				finish();
		}
		return true;
	}

	void takeUserReview () {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle("Remarks");
		alert.setMessage("Enter here..");

		final EditText input = new EditText(this);
		alert.setView(input);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				String value = input.getText().toString();
				// Do something with value!
				remarksMessage = value;
				if (remarksMessage.length() > 0) {
					postApprovalRequest();
				} else {
					Toast.makeText(getApplicationContext(), "Enter your Remarks !", Toast.LENGTH_LONG).show();
				}
			}
		});

		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				// Canceled.
			}
		});

		alert.show();
	}
	void postApprovalRequest() {
		List<NameValuePair> params = new ArrayList<NameValuePair>(3);
//		params.add(new BasicNameValuePair("type", "AR"));
		try {
			params.add(new BasicNameValuePair("requestIdentifierNo", hiringStatus.getString("RequestIdentifierNo")));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		params.add(new BasicNameValuePair("CurrentPhaseStatus", approvalStatus));
		params.add(new BasicNameValuePair("Remarks", remarksMessage));

		WSHelper helper = new WSHelper("http://becognizant.net/HMT/hiringstatus.php", params, getApplicationContext());
		helper.addWSListener(new WSListener() {
			@Override
			public void onRequestCompleted(String response) {
				Toast.makeText(getApplicationContext(), "Hiring Status updated !", Toast.LENGTH_LONG).show();
				finish();
			}
			@Override
			public void onRequestFailed(Exception exception) {
				Toast.makeText(getApplicationContext(), "Hiring Status update failed, Please try again !", Toast.LENGTH_LONG).show();
			}
		});
		helper.processRequest(WSType.WSPOST);
	}

}
