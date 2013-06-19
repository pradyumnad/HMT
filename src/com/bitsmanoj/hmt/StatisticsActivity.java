package com.bitsmanoj.hmt;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import helpers.MyGraphview;
import helpers.WSHelper;
import helpers.WSListener;
import helpers.WSType;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
 
import com.bitsmanoj.hmt.R;

enum Options{
	By_Technology_Resource_Count,
	Role_Resource_Count,
	By_Country_Resource_Count
}

public class StatisticsActivity extends Activity {

    float values[]={5,4,3,2,1};
    Spinner spinnerOptions;
    LinearLayout linear;
    public static String tableName;

	public StatisticsActivity () {
		super();
	}

	public StatisticsActivity (String table) {
		super();
		this.tableName = table;
	}

    @Override
    public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_statistics);
    
    linear=(LinearLayout) findViewById(R.id.graphView);
        
    //settingup spinner
    spinnerOptions = (Spinner) findViewById(R.id.spinnerGraphOptions);
    String arraySpinnerOptions[] = {"By Technology Resource Count","By Role Resource Count","By Country Resource Count"};
	ArrayAdapter<String> optionsAdapter = new ArrayAdapter<String>(StatisticsActivity.this, android.R.layout.simple_list_item_1, arraySpinnerOptions);
    spinnerOptions.setAdapter(optionsAdapter);
    final String columns[] = {"Technology","Role","Country"};
    spinnerOptions.setOnItemSelectedListener(new OnItemSelectedListener() {
    	
		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			
			updateGraphView(columns[arg2]);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			
		}
	});
    
    updateGraphView("Technology");
        
}

    public void  updateGraphView(String fieldName){
    	
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
    	nameValuePairs.add(new BasicNameValuePair("type","Stats"));
    	nameValuePairs.add(new BasicNameValuePair("table", tableName));
    	nameValuePairs.add(new BasicNameValuePair("field",fieldName));
    	

        WSHelper helper = new WSHelper("http://becognizant.net/HMT/search.php", nameValuePairs, getApplicationContext());
        helper.addWSListener(new WSListener() {
    		
    		@Override
    		public void onRequestFailed(Exception exception) {
    			System.out.println("Error in getting statistics");	
    		}
    		
    		@Override
    		public void onRequestCompleted(String response) {
				Log.d("Statistics", response);
				try {
    				JSONObject jsonObject = new JSONObject(response);
    				JSONArray array = jsonObject.getJSONArray("results");
    				float counts[] = new float[array.length()];
    				for (int i=0; i < array.length();i++) {
    					JSONObject object = array.getJSONObject(i);
    					counts[i] = (float) object.getDouble("Count");
    				}
    				linear.removeAllViews();
    			    linear.addView(new MyGraphview(getApplicationContext(),calculateData(counts)));
    			} catch (JSONException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
    	});
        helper.processRequest(WSType.WSPOST);
        }

private float[] calculateData(float[] data) {
    // TODO Auto-generated method stub
    float total=0;
    for(int i=0;i<data.length;i++)
    {
        total+=data[i];
    }
    for(int i=0;i<data.length;i++)
    {
    data[i]=360*(data[i]/total);            
    }
    return data;

}
}
