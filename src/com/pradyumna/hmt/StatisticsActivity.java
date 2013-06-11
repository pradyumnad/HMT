package com.pradyumna.hmt;

import helpers.MyGraphview;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.LinearLayout;
import com.pradyumna.*; 

public class StatisticsActivity extends Activity {

    float values[]={500,400,300,200,100};

@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_statistics);
    LinearLayout linear=(LinearLayout) findViewById(R.id.graphView);
    values=calculateData(values);
    linear.addView(new MyGraphview(this,values));
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
