package com.nepathya.archive.system.constant;

public class UrlConstant {

	public static final String BASEURL = "api/v1";
	public static final String REGISTER = BASEURL + "/register";
	public static final String RESET_PASSWORD = BASEURL + "/resetPassword/";
	public static final String LOGIN = BASEURL + "/login";
	public static final String CHANGE_PASSWORD = BASEURL + "/changePassword";
	public static final String FORGOT_PASSWORD = BASEURL + "/forgetPassword";
	public static final String USER_NOT_EXIST = "user.not.exist";
	public static final String USER = BASEURL + "/users";
	public static final String BY_ID = "/{id}";
	public static final String ROLE = BASEURL + "/roles";	
	
	public static final String FILE_UPLOAD_SINGLE = BASEURL + "/fileupload/single";
	
	//Faculty End Points
	public static final String FACULTY = BASEURL + "/faculty";
	
	//Document End Points
	public static final String DOCUMENT = BASEURL + "/document";
	
	//Tag End Points
	public static final String TAG = BASEURL + "/tag";
	
	//Message End Points
	public static final String MESSAGE = BASEURL + "/message";
	
	public static final String DOCNOTICE = BASEURL + "/docNotice";
}
