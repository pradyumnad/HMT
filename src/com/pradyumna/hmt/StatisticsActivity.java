package com.pradyumna.hmt;

import helpers.MyGraphview;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.pradyumna.*; 

public class StatisticsActivity extends Activity {

    float values[]={500,400,300,200,100};
    Spinner spinnerOptions;
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_statistics);
    LinearLayout linear=(LinearLayout) findViewById(R.id.graphView);
    values=calculateData(values);
    linear.addView(new MyGraphview(this,values));
    
    //settingup spinner
    spinnerOptions = (Spinner) findViewById(R.id.spinnerGraphOptions);
    String arraySpinnerOptions[] = {"By technology resource count","By Role resource count","By country resource count0"};
	ArrayAdapter<String> optionsAdapter = new ArrayAdapter<String>(StatisticsActivity.this, android.R.layout.simple_list_item_1, arraySpinnerOptions);
    spinnerOptions.setAdapter(optionsAdapter);
    spinnerOptions.setOnItemSelectedListener(new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			System.out.println(spinnerOptions.getSelectedItem().toString());
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			
		}
	});
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
