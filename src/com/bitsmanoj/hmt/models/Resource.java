package com.bitsmanoj.hmt.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: BITSManoj
 * Date: 29/05/13
 * Time: 12:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class Resource {
	public String ascId;
	/*
	 * Bench
	 */
	//Technology
	//Role
	public int experience;
	public String onBenchStartingDate;
	//City

	/*
	 * IP
	 */
	public String technology;
	public String role;
	public String city;
	public String projectStartDate;
	public String projectEndDate;


	public Resource(JSONObject object, Boolean isInternalPool) {
		try {
			this.technology = object.getString("Technology");
			this.role = object.getString("Role");
			this.city = object.getString("City");
			this.ascId = object.getString("AscID");
			if (!isInternalPool) {
				this.onBenchStartingDate = object.getString("OnBenchStartingDate");
				this.experience = object.getInt("ExpInYrs");
			} else {
				this.projectEndDate = object.getString("ProjectEndDate");
				this.projectStartDate = object.getString("ProjectStartDate");
			}
		} catch (JSONException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
	}
}
