package com.pradyumna.hmt.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: pradyumnad
 * Date: 31/05/13
 * Time: 9:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class Request {
	public String requestId;
	public String requestingMgrEmailID;
	public int experience;
	public int soNo;

	public Request(JSONObject request) {
		try {
			this.requestId = request.getString("RequestIdentifierNo");
			this.requestingMgrEmailID = request.getString("RequestingMgrEmailID");
			this.experience = request.getInt("ExpInYrs");
			this.soNo = request.getInt("SONo");
		} catch (JSONException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
	}
}
