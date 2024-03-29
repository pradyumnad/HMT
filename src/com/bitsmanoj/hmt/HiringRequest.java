package com.bitsmanoj.hmt;

import android.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import helpers.WSHelper;
import helpers.WSListener;
import helpers.WSType;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.bitsmanoj.hmt.R;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created with IntelliJ IDEA. User: BITSManoj Date: 29/05/13 Time: 1:21 PM To
 * change this template use File | Settings | File Templates.
 */
public class HiringRequest extends BaseActivity implements WSListener {

	Spinner spinner;
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

		spinner = (Spinner) findViewById(R.id.spinnerOnSite_Offshore);
		String[] values = {"OnSite", "OffShore"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, values);
		spinner.setAdapter(adapter);
		
		((EditText) findViewById(R.id.editTextReuqestingMgr)).setText(AppSettings.currentUser.name);
		((EditText) findViewById(R.id.editTextRequestingMgrAscID)).setText(""+AppSettings.currentUser.ASCId);
		((EditText) findViewById(R.id.editTextRequestingMgrEmailID)).setText(AppSettings.currentUser.emailId);
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

	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getTitle().equals("HMT")) {
			finish();
		}
		switch (item.getItemId()) {
		case R.id.req_save:
			saveNewHiringRequest();
			break;
		default:
			super.onOptionsItemSelected(item);
			break;
		}
		return true;
	}

	/**
	 * Actions
	 */

	private void saveNewHiringRequest() {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		nameValuePairs.add(new BasicNameValuePair("SONo",
				((EditText) findViewById(R.id.editTexSONot)).getText()
						.toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("ReuqestingMgr",
				((EditText) findViewById(R.id.editTextReuqestingMgr)).getText()
						.toString().trim()));
		nameValuePairs
				.add(new BasicNameValuePair("user_id", AppSettings.userId));
		nameValuePairs.add(new BasicNameValuePair("RequestingMgrAscID",
				((EditText) findViewById(R.id.editTextRequestingMgrAscID))
						.getText().toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("RequestingMgrEmailID",
				((EditText) findViewById(R.id.editTextRequestingMgrEmailID))
						.getText().toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("AdditionalEmailID1",
				((EditText) findViewById(R.id.editTextEmail1)).getText()
						.toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("AdditionalEmailID2",
				((EditText) findViewById(R.id.editTextEmail2)).getText()
						.toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("JobDescription",
				((EditText) findViewById(R.id.editTextJobDescription))
						.getText().toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("Technology",
				((EditText) findViewById(R.id.editTextTechnology)).getText()
						.toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("CertificationNeed",
				((EditText) findViewById(R.id.editTextCertificationNeed))
						.getText().toString().trim()));
		nameValuePairs
				.add(new BasicNameValuePair(
						"ProposedPlacementProject",
						((EditText) findViewById(R.id.editTextProposedPlacementProject))
								.getText().toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("Vertical",
				((EditText) findViewById(R.id.editTextVertical)).getText()
						.toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("ProposedRule",
				((EditText) findViewById(R.id.editTextProposedRule)).getText()
						.toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("ExpInYrs",
				((EditText) findViewById(R.id.editTextExpInYrs)).getText()
						.toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("DesiredBillingRate",
				((EditText) findViewById(R.id.editTextDesiredBillingRate))
						.getText().toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("JoiningDateNeededBy",
				((EditText) findViewById(R.id.editTextJoiningDateNeededBy))
						.getText().toString().trim()));

		StringTokenizer tokenizer = new StringTokenizer(((EditText) findViewById(R.id.editTextProjectStartDate)).getText().toString()
				, "-");
		String[] tokens = new String[tokenizer.countTokens()];
				
		nameValuePairs.add(new BasicNameValuePair("ProjectStartDate",
				tokens[2]+"-"+tokens[0]+"-"+tokens[1] ));
		
		tokenizer = new StringTokenizer((((EditText) findViewById(R.id.editTextProjectEndDate))
				.getText().toString().trim()), "-");
		tokens = new String[tokenizer.countTokens()];

		nameValuePairs.add(new BasicNameValuePair("ProjectEndDate",tokens[2]+"-"+tokens[0]+"-"+tokens[1]));
		
		nameValuePairs
				.add(new BasicNameValuePair(
						"LongTerm_ShortTermProject",
						((EditText) findViewById(R.id.editTextLongTermShortTermProject))
								.getText().toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("PreferredVisaStatus",
				((EditText) findViewById(R.id.editTextPreferredVisaStatus))
						.getText().toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("Onsite_Offshore",
				spinner.getSelectedItem().toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("City",
				((EditText) findViewById(R.id.editTextCity)).getText()
						.toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("State",
				((EditText) findViewById(R.id.editTextState)).getText()
						.toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("Country",
				((EditText) findViewById(R.id.editTextCountry)).getText()
						.toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("AdditionalInformation",
				((EditText) findViewById(R.id.editTextAdditionalInformation))
						.getText().toString().trim()));

		System.out.println(nameValuePairs);

		WSHelper helper = new WSHelper(
				"http://becognizant.net/HMT/hiringrequest.php", nameValuePairs,
				getApplicationContext());
		helper.addWSListener(new WSListener() {

			@Override
			public void onRequestFailed(Exception exception) {
				finish();
			}

			@Override
			public void onRequestCompleted(String response) {
				finish();
			}
		});
		helper.processRequest(WSType.WSPOST);

	}
}