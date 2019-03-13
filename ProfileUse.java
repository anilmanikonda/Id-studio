package com.mycompany.myproject.mayapp.core.use;

import com.adobe.cq.sightly.WCMUsePojo;

public class ProfileUse extends WCMUsePojo{

	private String myTitle;
	
	private String myFullName;
	
	
	
	public String getMyTitle() {
		return myTitle;
	}



	public void setMyTitle(String myTitle) {
		this.myTitle = myTitle;
	}



	public String getMyFullName() {
		return myFullName;
	}



	public void setMyFullName(String myFullName) {
		this.myFullName = myFullName;
	}



	@Override
	public void activate() throws Exception {
		
		myTitle = "May Title : " + getCurrentPage().getTitle();
		setMyTitle(myTitle);
		
		String firstName = get("fName" , String.class);
		String lastName = get("lName" , String.class);
		
		myFullName = firstName + " " + lastName;
		setMyFullName(myFullName);
	}

	
	
}
