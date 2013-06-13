package com.bitsmanoj.hmt;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
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
 * Date: 29/05/13
 * Time: 1:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class HiringRequest extends BaseActivity implements WSListener {

	@Override
	public void onRequestCompleted(String response) {
		// TODO Auto-generated method stub
		System.out.println(response);
	}

	@Override
	public void onRequestFailed(Exception exception) {
		// TODO Auto-generated method stub
		
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hiring_request);

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.hiring_request, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()) {
			case R.id.req_save:
				saveNewHiringRequest();
				break;
			default:
				super.onOptionsItemSelected(item);
				finish();
				break;
		}
		return true;
	}

	/**
	 * Actions
	 */

	private void saveNewHiringRequest() {
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		nameValuePairs.add(new BasicNameValuePair("SONo", ((EditText)findViewById(R.id.editTexSONot)).getText().toString().trim() ));
		nameValuePairs.add(new BasicNameValuePair("ReuqestingMgr", ((EditText)findViewById(R.id.editTextReuqestingMgr)).getText().toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("user_id", AppSettings.userId));
		nameValuePairs.add(new BasicNameValuePair("RequestingMgrAscID", ((EditText)findViewById(R.id.editTextRequestingMgrAscID)).getText().toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("RequestingMgrEmailID", ((EditText)findViewById(R.id.editTextRequestingMgrEmailID)).getText().toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("AdditionalEmailID1", ((EditText)findViewById(R.id.editTextEmail1)).getText().toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("AdditionalEmailID2", ((EditText)findViewById(R.id.editTextEmail2)).getText().toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("JobDescription", ((EditText)findViewById(R.id.editTextJobDescription)).getText().toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("Technology", ((EditText)findViewById(R.id.editTextTechnology)).getText().toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("CertificationNeed", ((EditText)findViewById(R.id.editTextCertificationNeed)).getText().toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("ProposedPlacementProject", ((EditText)findViewById(R.id.editTextProposedPlacementProject)).getText().toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("Vertical", ((EditText)findViewById(R.id.editTextVertical)).getText().toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("ProposedRule", ((EditText)findViewById(R.id.editTextProposedRule)).getText().toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("ExpInYrs", ((EditText)findViewById(R.id.editTextExpInYrs)).getText().toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("DesiredBillingRate", ((EditText)findViewById(R.id.editTextDesiredBillingRate)).getText().toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("JoiningDateNeededBy", ((EditText)findViewById(R.id.editTextJoiningDateNeededBy)).getText().toString().trim()));
		
		DatePicker datePicker = (DatePicker)findViewById(R.id.datePickerProjectStartDate); 
		String dateFormat = datePicker.getDayOfMonth()+"-"+datePicker.getMonth()+"-"+datePicker.getYear();
		nameValuePairs.add(new BasicNameValuePair("ProjectStartDate", dateFormat));
		datePicker = (DatePicker)findViewById(R.id.datePickerProjectEndDate); 
		dateFormat = datePicker.getDayOfMonth()+"-"+datePicker.getMonth()+"-"+datePicker.getYear();
		
		nameValuePairs.add(new BasicNameValuePair("ProjectEndDate", dateFormat));
		nameValuePairs.add(new BasicNameValuePair("LongTerm-ShortTermProject", ((EditText)findViewById(R.id.editTextLongTermShortTermProject)).getText().toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("PreferredVisaStatus", ((EditText)findViewById(R.id.editTextPreferredVisaStatus)).getText().toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("OnsiteOffshore", ((EditText)findViewById(R.id.editTextOnsite_Offshore)).getText().toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("City", ((EditText)findViewById(R.id.editTextCity)).getText().toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("State", ((EditText)findViewById(R.id.editTextState)).getText().toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("Country", ((EditText)findViewById(R.id.editTextCountry)).getText().toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("AdditionalInformation", ((EditText)findViewById(R.id.editTextAdditionalInformation)).getText().toString().trim()));
		
		System.out.println(nameValuePairs);
		
		WSHelper helper = new WSHelper("http://becognizant.net/HMT/hiringrequest.php", nameValuePairs, getApplicationContext());
//		helper.addWSListener(new WSListener() {
//			
//			@Override
//			public void onRequestFailed(Exception exception) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void onRequestCompleted(String response) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
		helper.processRequest(WSType.WSPOST);

	}
}