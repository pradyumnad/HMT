package com.pradyumna.hmt.models;

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

	public Resource(String ascId, String designation, int experience, String name, String technology) {
		this.ascId = ascId;
		this.designation = designation;
		this.experience = experience;
		this.name = name;
		this.technology = technology;
	}
}
