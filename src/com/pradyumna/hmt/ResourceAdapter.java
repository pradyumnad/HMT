package com.pradyumna.hmt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.pradyumna.hmt.models.Resource;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pradyumnad
 * Date: 29/05/13
 * Time: 12:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class ResourceAdapter extends ArrayAdapter<Resource> {
	Context context;
	List<Resource> resourcesList;

	public ResourceAdapter(Context context, int textViewResourceId, List<Resource> objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
		resourcesList = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.ip_list_row, parent, false);

		Resource resource = resourcesList.get(position);

		TextView nameTextView = (TextView) rowView.findViewById(R.id.nameTextView);
		nameTextView.setText(resource.name);

		TextView designationTextView = (TextView) rowView.findViewById(R.id.designationTextView);
		designationTextView.setText(resource.designation);

		TextView expTextView = (TextView) rowView.findViewById(R.id.expTextView);
		expTextView.setText(resource.experience+" years");

		TextView techTextView = (TextView) rowView.findViewById(R.id.techTextView);
		techTextView.setText(resource.technology);
		return rowView;
	}
}
