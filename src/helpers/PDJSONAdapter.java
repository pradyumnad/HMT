package helpers;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pradyumnad
 * Date: 08/06/13
 * Time: 1:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class PDJSONAdapter extends BaseAdapter implements Filterable {
	JSONObject jsonObject;
	List<NameValuePair> listRows;
	Context context;

	public PDJSONAdapter(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
		listRows = getListRowsFromJSONObject(jsonObject);
	}

	List<NameValuePair> getListRowsFromJSONObject(JSONObject object) {

		List<NameValuePair>list = new ArrayList<NameValuePair>(object.length());
		Iterator<String> iterator = object.keys();
		while (iterator.hasNext()) {
			String key = iterator.next();
			try {
				list.add(new BasicNameValuePair(key, ""+object.get(key)));
			} catch (JSONException e) {
				list.add(new BasicNameValuePair(key, "--"));
//				e.printStackTrace();
			}
		}
		return list;
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
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.d("PDJSONAdapter", convertView+"  "+position);
//		 android.R.layout.simple_list_item_1;
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public Filter getFilter() {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}
}
