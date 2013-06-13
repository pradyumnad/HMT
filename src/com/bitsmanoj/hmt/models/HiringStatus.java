package com.bitsmanoj.hmt.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class HiringStatus implements Serializable {

	public String RequestIdentifierNo ;
	public String SONo;
	public String RequestingMgrEmailID;
	public String ExpInYrs;
	public String ReqAssignedTo;
	public String InternalHiringPOC;
	public String ExternalHiringPOC;
	public String IdentifiedProfile;
	public String CurrentPhaseStatus;
	public String InterviewerIdentified;
	public String InterviewerAscID;
	public String InterviewerEmailID;
	public String InterviewerPhone;
	public String InterviewDate;
	public String InterviewStatus;
	public String SelectionConfirmed;
	public String Approvers1Email;
	public String Approvers2Email;
	public String Approvers3Email;
	public String Approvers4Email;
	public String Remarks;

	public  HiringStatus(JSONObject statusObject) {
		try {
			this.RequestingMgrEmailID = statusObject.getString("RequestingMgrEmailID");
			this.ExpInYrs = statusObject.getString("ExpInYrs");
			this.RequestIdentifierNo = statusObject.getString("RequestIdentifierNo");
			this.SONo = statusObject.getString("SONo");
			this.ReqAssignedTo = statusObject.getString("ReqAssignedTo");
			this.InternalHiringPOC = statusObject.getString("InternalHiringPOC");
			this.ExternalHiringPOC = statusObject.getString("ExternalHiringPOC");
			this.IdentifiedProfile = statusObject.getString("IdentifiedProfile");
			this.CurrentPhaseStatus = statusObject.getString("CurrentPhaseStatus");
			this.InterviewerIdentified = statusObject.getString("InterviewerIdentified");
			this.InterviewerAscID = statusObject.getString("InterviewerAscID");
			this.InterviewerEmailID = statusObject.getString("InterviewerEmailID");
			this.InterviewerPhone = statusObject.getString("InterviewerPhone");
			this.InterviewDate = statusObject.getString("InterviewDate");
			this.SelectionConfirmed = statusObject.getString("SelectionConfirmed");
			this.Approvers1Email = statusObject.getString("Approvers1Email");
			this.Approvers2Email = statusObject.getString("Approvers2Email");
			this.Approvers3Email = statusObject.getString("Approvers3Email");
			this.Approvers4Email = statusObject.getString("Approvers4Email");
			this.Remarks = statusObject.getString("Remarks");
		} catch (JSONException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
	}
}
