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
public class  HiringRequestAdapter extends ArrayAdapter<HiringStatus> {
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

		TextView tx1 = (TextView) rowView.findViewById(R.id.hr_text_view1);
		tx1.setText("SONo # "+hiringRequest.SONo);

		TextView tx2 = (TextView) rowView.findViewById(R.id.hr_text_view2);
		tx2.setText(hiringRequest.technology);

		TextView tx3 = (TextView) rowView.findViewById(R.id.hr_text_view3);
		tx3.setText(hiringRequest.role);

		TextView expTextView = (TextView) rowView.findViewById(R.id.hr_text_view4);
		expTextView.setText("Exp #"+hiringRequest.ExpInYrs+" years");
		
		TextView statusTextView = (TextView) rowView.findViewById(R.id.hr_text_view5);
		statusTextView.setText(hiringRequest.city);

		TextView tx6 = (TextView) rowView.findViewById(R.id.hr_text_view6);
		tx6.setText(hiringRequest.joiningDateNeededBy);

		TextView tx7 = (TextView) rowView.findViewById(R.id.hr_text_view7);
		tx7.setText(hiringRequest.CurrentPhaseStatus);

		return rowView;
	}
}
