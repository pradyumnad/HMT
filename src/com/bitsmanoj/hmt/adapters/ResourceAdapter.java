package com.bitsmanoj.hmt.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bitsmanoj.hmt.AppSettings;
import com.bitsmanoj.hmt.R;
import com.bitsmanoj.hmt.models.Resource;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: BITSManoj
 * Date: 29/05/13
 * Time: 12:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class ResourceAdapter extends ArrayAdapter<Resource> {
	Context context;
	List<Resource> resourcesList;
	Boolean isInternalPool;

	public ResourceAdapter(Context context, int textViewResourceId, List<Resource> objects, Boolean isInternalPool) {
		super(context, textViewResourceId, objects);
		this.context = context;
		resourcesList = objects;
		this.isInternalPool = isInternalPool;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.ip_list_row, parent, false);

		Resource resource = resourcesList.get(position);
		if (isInternalPool) {
			TextView tx1 = (TextView) rowView.findViewById(R.id.ip_TextView1);
			tx1.setText(resource.technology);

			TextView tx2 = (TextView) rowView.findViewById(R.id.ip_TextView2);
			tx2.setText(resource.role);

			TextView tx3 = (TextView) rowView.findViewById(R.id.ip_TextView3);
			tx3.setText(resource.city);

			TextView tx4 = (TextView) rowView.findViewById(R.id.ip_TextView4);
			tx4.setText(resource.projectStartDate);

			TextView tx5 = (TextView) rowView.findViewById(R.id.ip_textView5);
			tx5.setText(resource.projectStartDate);
		} else {
			TextView tx1 = (TextView) rowView.findViewById(R.id.ip_TextView1);
			tx1.setText(resource.technology);

			TextView tx2 = (TextView) rowView.findViewById(R.id.ip_TextView2);
			tx2.setText(resource.role);

			TextView tx3 = (TextView) rowView.findViewById(R.id.ip_TextView3);
			tx3.setText(resource.experience+" years");

			TextView tx4 = (TextView) rowView.findViewById(R.id.ip_TextView4);
			tx4.setText(resource.onBenchStartingDate);

			TextView tx5 = (TextView) rowView.findViewById(R.id.ip_textView5);
			tx5.setText(resource.city);
		}

		return rowView;
	}
}
