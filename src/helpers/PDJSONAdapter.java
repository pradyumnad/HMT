package helpers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.bitsmanoj.hmt.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: BITSManoj
 * Date: 08/06/13
 * Time: 1:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class PDJSONAdapter extends BaseAdapter {
	JSONObject jsonObject;
	List<NameValuePair> listRows;
	Context context;
	String[] orderedFields;

	public PDJSONAdapter(JSONObject jsonObject, Context context) {
		this.jsonObject = jsonObject;
		this.context = context;
		listRows = getListRowsFromJSONObject(jsonObject);
	}

	public PDJSONAdapter(JSONObject jsonObject, Context context, String[] fields) {
		this.orderedFields = fields;
		this.jsonObject = jsonObject;
		this.context = context;
		listRows = getListRowsFromJSONObject(jsonObject);
	}

	List<NameValuePair> getListRowsFromJSONObject(JSONObject object) {
		List<NameValuePair>list;
		if (orderedFields == null) {
			list = new ArrayList<NameValuePair>(object.length());
			Iterator<String> iterator = object.keys();
			while (iterator.hasNext()) {
				String key = iterator.next();
				Log.d(">>JSON", key);
				try {
					list.add(new BasicNameValuePair(key, ""+object.get(key)));
				} catch (JSONException e) {
					list.add(new BasicNameValuePair(key, "--"));
//				e.printStackTrace();
				}
			}
			return list;
		} else {
			list = new ArrayList<NameValuePair>(orderedFields.length);

			for (int i =0 ; i < orderedFields.length; i++) {
				String key = orderedFields[i];
				Log.d(">>JSON", key);
				try {
					list.add(new BasicNameValuePair(key, ""+object.get(key)));
				} catch (JSONException e) {
					list.add(new BasicNameValuePair(key, "--"));
				}
			}
			return list;
		}
	}

	@Override
	public int getCount() {
		return listRows.size();
	}

	@Override
	public Object getItem(int position) {
		return listRows.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(android.R.layout.simple_list_item_2, parent, false);
		rowView.getLayoutParams().height = 100;
		TextView textView1 = (TextView)rowView.findViewById(android.R.id.text1);
		textView1.setTextSize(15);
		textView1.setTextColor(Color.GRAY);
		TextView textView2 = (TextView)rowView.findViewById(android.R.id.text2);
		textView2.setTextColor(Color.BLACK);
		NameValuePair rowData = listRows.get(position); 
		
		textView1.setText(rowData.getName());
		textView2.setText(rowData.getValue());
//		Log.d("PDJSONAdapter", "view "+convertView+"  parent "+parent+ " row"+rowView);
		return rowView;  //To change body of implemented methods use File | Settings | File Templates.
	}
}
