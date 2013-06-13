package com.bitsmanoj.hmt.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bitsmanoj.hmt.R;
import com.bitsmanoj.hmt.models.HiringStatus;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: BITSManoj
 * Date: 31/05/13
 * Time: 11:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class HiringRequestAdapter extends ArrayAdapter<HiringStatus> {
	Context context;
	List<HiringStatus> requestList;

	public HiringRequestAdapter(Context context, int textViewResourceId, List<HiringStatus> objects) {
	
		super(context, textViewResourceId, objects);
		this.context = context;
		this.requestList = objects;	
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.hr_list_row, parent, false);

		HiringStatus hiringRequest = requestList.get(position);

		TextView nameTextView = (TextView) rowView.findViewById(R.id.textViewRequestingMgrEmailID);
		nameTextView.setText(hiringRequest.RequestingMgrEmailID);

		TextView expTextView = (TextView) rowView.findViewById(R.id.textViewExperience);
		expTextView.setText("Exp #"+hiringRequest.ExpInYrs+" years");

		TextView techTextView = (TextView) rowView.findViewById(R.id.textViewTechnology);
		if (hiringRequest.InterviewStatus != null) {
//			if (hiringRequest.InterviewStatus.length() == 0) {
//				hiringRequest.InterviewStatus = "PENDING";
//			}
		} else {
			hiringRequest.InterviewStatus = "New";
		}
		techTextView.setText("Status #"+hiringRequest.CurrentPhaseStatus);
		
		TextView statusTextView = (TextView) rowView.findViewById(R.id.textViewStatus);
		statusTextView.setText("Remarks #"+hiringRequest.Remarks);
		
		return rowView;
	}
}
