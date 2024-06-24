package org.example;

public class GroupVisitors {
	private int id;
	private String rnu;
	private int vepPepId;
	private String identityType;
	private String nricPassportNo;
	private String visitorName;
	public GroupVisitors(int id, String rnu, int vepPepId, String identityType, String nricPassportNo,
			String visitorName) {
		super();
		this.id = id;
		this.rnu = rnu;
		this.vepPepId = vepPepId;
		this.identityType = identityType;
		this.nricPassportNo = nricPassportNo;
		this.visitorName = visitorName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRnu() {
		return rnu;
	}
	public void setRnu(String rnu) {
		this.rnu = rnu;
	}
	public int getVepPepId() {
		return vepPepId;
	}
	public void setVepPepId(int vepPepId) {
		this.vepPepId = vepPepId;
	}
	public String getIdentityType() {
		return identityType;
	}
	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}
	public String getNricPassportNo() {
		return nricPassportNo;
	}
	public void setNricPassportNo(String nricPassportNo) {
		this.nricPassportNo = nricPassportNo;
	}
	public String getVisitorName() {
		return visitorName;
	}
	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}
	
}
