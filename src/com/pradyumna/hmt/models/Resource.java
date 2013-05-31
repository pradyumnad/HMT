package com.pradyumna.hmt.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: pradyumnad
 * Date: 29/05/13
 * Time: 12:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class Resource {
	public String ascId;
	public String name;
	public String designation;
	public int experience;
	public String technology;

	public Resource(JSONObject object) {
		try {
			this.ascId = object.getString("AscID");
			this.designation = object.getString("Designation");
			this.experience = object.getInt("ExpInYrs");
			this.name = object.getString("Name");
			this.technology = object.getString("Technology");
		} catch (JSONException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
	}
}
