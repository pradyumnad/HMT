package com.bitsmanoj.hmt.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: BITSManoj
 * Date: 31/05/13
 * Time: 9:56 PM
 * To change this template use File | Settings | File Templates.
 */

public class Request {
	public String requestId;

	public int soNo;
	public String technology;
	public String role;
	public int experience;
	public String city;
	public String status;
	public String joiningDateNeededBy;

	public String requestingMgrEmailID;

//	SO #, Tech, Role, Exp, City, By Dt, Status

	public Request(JSONObject request) {
		try {
			this.requestId = request.getString("RequestIdentifierNo");
			this.requestingMgrEmailID = request.getString("RequestingMgrEmailID");

			this.soNo = request.getInt("SONo");
			this.technology = request.getString("Technology");
			this.role = request.getString("Role");
			this.city = request.getString("City");
			this.experience = request.getInt("ExpInYrs");
			this.status = request.getString("CurrentPhaseStatus");
			this.joiningDateNeededBy = request.getString("JoiningDateNeededBy");
		} catch (JSONException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
	}

	public Request(JSONObject request, int type) {
		if (type == 1) {
		try {
			this.requestId = request.getString("RequestIdentifierNo");
			this.requestingMgrEmailID = request.getString("RequestingMgrEmailID");
			this.experience = request.getInt("ExpInYrs");
		} catch (JSONException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
		}
	}
}
