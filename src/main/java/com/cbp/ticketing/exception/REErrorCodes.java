package com.cbp.ticketing.exception;

public enum REErrorCodes {
	 PROJ_NAME_ALREADY_EXISTS	(1, "Project Name already Exists"),
	 USER_NAME_ALREADY_EXISTS	(2, "User Name already Exists"),
	 REPOSITORY_NAME_ALREADY_EXISTS	(3, "Repository Name already Exists"),
	 RULE_GROUP_ALREADY_EXISTS	(4, "Rule Group already Exists"),
	 FOLDER_NAME_ALREADY_EXISTS	(5, "Folder Name already Exists for this Repository"),
	 CATEGORY_NAME_ALREADY_EXISTS	(6, "Category Name already Exists"),
	 CRITICALITY_LEVEL_ALREADY_EXISTS	(7, "Criticality Level already Exists"),
	 PARAMETER_CREATION_FAILED	(8, "Parameter creation failed"),
	 REPORT_NAME_ALREADY_EXISTS	(9, "Report Name already exists"),
	 BATCH_GROUP_NAME_ALREADY_EXISTS	(10, "Batch Group Name already exists"),
	 
	 DIRECTORYSERVICE_NAME_ALREADY_EXISTS	(11, "Directory Service Name already Exists"),
	 
	 XYZ_ALREADY_EXISTS			(99, "Project Name already Exists"),
	 UNABLE_TO_CONNECT_TO_REPOSITORY	(100, "Unable to connect to repository"),
	 UNABLE_TO_EXECUTE_RULE	(101, "Unable to execute rule"),
	 NO_LICENSE	(900, "No License found"),
	 INVALID_LICENSE	(902, "Invalid License found"),
	 INVALID_LICENSE_KEY	(903, "Invalid License Key"),
	 UNDERTERMINED_IP_STATUS	(904, "Unable to find IP Address"),
	 UNALLOWED_IP_ADDRESS	(911, "Unallowed IP Address"),
	 LICENSE_ERROR_ADVANCE_USAGE	(912, "License cannot be activated well before the start date"),
	 LICENSE_ERROR_EXPIRED_USAGE	(913, "License Expired"),
	 PROJECT_LIMITS_REACHED	(921, "Project Limits reached"),
	 REPOSITORY_LIMITS_REACHED	(922, "Repository Limits reached"),
	 FOLDER_LIMITS_REACHED	(923, "Folder Limits reached"),
	 USERS_LIMIT_REACHED	(924, "Users Limit reached"),
	 UNEXPECTED_ERROR			(999, "Unexpected Error. Please attend immediately"),
	 ENVIRONMENT_NAME_ALREADY_EXISTS	(100011, "Environment Name Already Exists"),
	 STAGE_NAME_ALREADY_EXISTS	(100012, "Stage Name Already Exists"),
	 CONNECTION_NAME_ALREADY_EXISTS	(100003, "Connection Name Already Exists for this Repository"),
	 FOLDER_WAIVER_EXISTS (100013, "Waiver Exists For Folder"),
	 FOLDER_EXISTS (100014, "Folder Already Exists"),
	 REGEX_INVALID(10016, "Regular Expression Is Not Valid"),
	 MAILGROUP_NAME_ALREADY_EXISTS	(100022, "Mail Group Already Exists"),
	 CREATE_REPORT_ERROR	(100029, "Report Name already exists");
	

	 // **********************************************************************************

	 private static final String OTA_PREFIX =".ERR";
	 private static final int OTA_DEFAULT_CODE = 999;
	 private int reErrorCode;
	 private String reErrorMessage;


	 // Constructor
	 private REErrorCodes(int errorCode, String errorMessage) {
		 reErrorCode = errorCode;
		 reErrorMessage = errorMessage;
	 }

	 // Overloaded constructor
	 private REErrorCodes() {
		 reErrorCode = OTA_DEFAULT_CODE;
	 }


	  public String getREErrorCode() {
		  return  reErrorCode + OTA_PREFIX;
	  }

	  public String getREErrorCodeMessage() {
		  return reErrorMessage;
	  }

	  public String toString() {
		  return getREErrorCode() + " - " + getREErrorCodeMessage();
	  }


}
