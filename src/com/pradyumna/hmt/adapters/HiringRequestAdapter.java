package com.pradyumna.hmt.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pradyumna.hmt.R;
import com.pradyumna.hmt.models.Request;

import java.util.List;
import com.pradyumna.*;
/**
 * Created with IntelliJ IDEA.
 * User: pradyumnad
 * Date: 31/05/13
 * Time: 11:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class HiringRequestAdapter extends ArrayAdapter<Request> {
	Context context;
	List<Request> requestList;

	public HiringRequestAdapter(Context context, int textViewResourceId, List<Request> objects) {
	
		super(context, textViewResourceId, objects);
		this.context = context;
		this.requestList = objects;	
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.hr_list_row, parent, false);

		Request hiringRequest = requestList.get(position);

		TextView nameTextView = (TextView) rowView.findViewById(R.id.textViewRequestingMgrEmailID);
		nameTextView.setText(hiringRequest.requestingMgrEmailID);

		TextView expTextView = (TextView) rowView.findViewById(R.id.textViewExperience);
		expTextView.setText("Exp #"+hiringRequest.experience+" years");

		TextView techTextView = (TextView) rowView.findViewById(R.id.textViewTechnology);
		if (hiringRequest.interviewStatus != null) {
			if (hiringRequest.interviewStatus.length() == 0) {
				hiringRequest.interviewStatus = "PENDING";
			}
		} else {
			hiringRequest.interviewStatus = "PENDING";
		}
		techTextView.setText("Status #"+hiringRequest.interviewStatus);
		
		TextView statusTextView = (TextView) rowView.findViewById(R.id.textViewStatus);
		statusTextView.setText("Remarks #"+hiringRequest.remarks);
		
		return rowView;
	}
}
