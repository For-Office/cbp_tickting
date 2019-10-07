package com.cbp.ticketing.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class ErrorCodes {
	@Value("${errors.lookup.unknown}")
	public String ERRORS_LOOKUP_UNKNOWN;
	@Value("${errors.newapplicationcreation.success}")
	private String ERRORS_NEWAPPLICATIONCREATION_SUCCESS;
	@Value("${errors.applicationupdated.success}")
	private String ERRORS_APPLICATIONUPDATED_SUCCES;
	@Value("${errors.deleteapplication.success}")
	private String ERRORS_DELETEAPPLICATION_SUCCES;
	@Value("${errors.deleteapplication.failure}")
	private String ERRORS_DELETEAPPLICATION_FAILURE;

	@Value("${errors.newteamcreation.success}")
	private String ERRORS_NEWTEAMCREATION_SUCCESS;
	@Value("${errors.teamupdated.success}")
	private String ERRORS_NEWTEAMUPDATED_SUCCESS;
	@Value("${errors.deleteteam.failure}")
	private String ERRORS_DELETETEAM_FAILURE;
	@Value("${errors.deleteteam.success}")
	private String ERRORS_DELETETEAM_SUCCESS;
	@Value("${errors.newresourcecreation.success}")
	private String ERRORS_NEWRESOURCECREATION_SUCCESS;
	@Value("${errors.resourceupdated.success}")
	private String ERRORS_RESOURCEUPDATED_SUCCESS;
	@Value("$errors.deleteresource.failure}")
	private String ERRORS_DELETERESOURCE_FAILURE;
	@Value("${success.createenvironment}")
	private String CREATE_ENVIRONMENT_SUCCESS;
	@Value("${success.modifyenvironment}")
	private String UPDATE_ENVIRONMENT_SUCCESS;
	@Value("${success.deleteenvironment}")
	private String DELETE_ENVIRONMENT_SUCCESS;

	@Value("${errors.newresourcetypecreation.success}")
	private String ERRORS_NEWRESOURCETYPECREATION_SUCCESS;
	@Value("${errors.resourcetypeupdated.success}")
	private String ERRORS_RESOURCETYPEUPDATED_SUCCESS;
	@Value("${errors.deleteresourcetype.success}")
	private String ERRORS_DELETERESOURCETYPE_SUCCESS;

	@Value("${errors.newrolecreation.success}")
	private String ERRORS_NEWROLECREATION_SUCCESS;
	@Value("${errors.roleupdated.success}")
	private String ERRORS_NEWROLEUPDATED_SUCCESS;
	@Value("${errors.deletedrole.success}")
	private String ERRORS_DELETEDROLE_SUCCESS;

	@Value("${errors.newusercreation.success}")
	private String ERRORS_NEWUSERCREATION_SUCCESS;
	@Value("${errors.userupdated.success}")
	private String ERRORS_USERUPDATED_SUCCESS;
	@Value("${errors.deleteuser.success}")
	private String ERRORS_USERDELETED_SUCCESS;

	public String getERRORS_NEWUSERCREATION_SUCCESS() {
		return ERRORS_NEWUSERCREATION_SUCCESS;
	}

	public void setERRORS_NEWUSERCREATION_SUCCESS(String eRRORS_NEWUSERCREATION_SUCCESS) {
		ERRORS_NEWUSERCREATION_SUCCESS = eRRORS_NEWUSERCREATION_SUCCESS;
	}

	public String getERRORS_USERUPDATED_SUCCESS() {
		return ERRORS_USERUPDATED_SUCCESS;
	}

	public void setERRORS_USERUPDATED_SUCCESS(String eRRORS_USERUPDATED_SUCCESS) {
		ERRORS_USERUPDATED_SUCCESS = eRRORS_USERUPDATED_SUCCESS;
	}

	public String getERRORS_USERDELETED_SUCCESS() {
		return ERRORS_USERDELETED_SUCCESS;
	}

	public void setERRORS_USERDELETED_SUCCESS(String eRRORS_USERDELETED_SUCCESS) {
		ERRORS_USERDELETED_SUCCESS = eRRORS_USERDELETED_SUCCESS;
	}

	public String getERRORS_NEWROLECREATION_SUCCESS() {
		return ERRORS_NEWROLECREATION_SUCCESS;
	}

	public void setERRORS_NEWROLECREATION_SUCCESS(String eRRORS_NEWROLECREATION_SUCCESS) {
		ERRORS_NEWROLECREATION_SUCCESS = eRRORS_NEWROLECREATION_SUCCESS;
	}

	public String getERRORS_NEWROLEUPDATED_SUCCESS() {
		return ERRORS_NEWROLEUPDATED_SUCCESS;
	}

	public void setERRORS_NEWROLEUPDATED_SUCCESS(String eRRORS_NEWROLEUPDATED_SUCCESS) {
		ERRORS_NEWROLEUPDATED_SUCCESS = eRRORS_NEWROLEUPDATED_SUCCESS;
	}

	public String getERRORS_DELETEDROLE_SUCCESS() {
		return ERRORS_DELETEDROLE_SUCCESS;
	}

	public void setERRORS_DELETEDROLE_SUCCESS(String eRRORS_DELETEDROLE_SUCCESS) {
		ERRORS_DELETEDROLE_SUCCESS = eRRORS_DELETEDROLE_SUCCESS;
	}

	public String getCREATE_ENVIRONMENT_SUCCESS() {
		return CREATE_ENVIRONMENT_SUCCESS;
	}

	public String getERRORS_NEWRESOURCETYPECREATION_SUCCESS() {
		return ERRORS_NEWRESOURCETYPECREATION_SUCCESS;
	}

	public void setERRORS_NEWRESOURCETYPECREATION_SUCCESS(String eRRORS_NEWRESOURCETYPECREATION_SUCCESS) {
		ERRORS_NEWRESOURCETYPECREATION_SUCCESS = eRRORS_NEWRESOURCETYPECREATION_SUCCESS;
	}

	public String getERRORS_RESOURCETYPEUPDATED_SUCCESS() {
		return ERRORS_RESOURCETYPEUPDATED_SUCCESS;
	}

	public void setERRORS_RESOURCETYPEUPDATED_SUCCESS(String eRRORS_RESOURCETYPEUPDATED_SUCCESS) {
		ERRORS_RESOURCETYPEUPDATED_SUCCESS = eRRORS_RESOURCETYPEUPDATED_SUCCESS;
	}

	public String getERRORS_DELETERESOURCETYPE_SUCCESS() {
		return ERRORS_DELETERESOURCETYPE_SUCCESS;
	}

	public void setERRORS_DELETERESOURCETYPE_SUCCESS(String eRRORS_DELETERESOURCETYPE_SUCCESS) {
		ERRORS_DELETERESOURCETYPE_SUCCESS = eRRORS_DELETERESOURCETYPE_SUCCESS;
	}

	public void setCREATE_ENVIRONMENT_SUCCESS(String cREATE_ENVIRONMENT_SUCCESS) {
		CREATE_ENVIRONMENT_SUCCESS = cREATE_ENVIRONMENT_SUCCESS;
	}

	public String getUPDATE_ENVIRONMENT_SUCCESS() {
		return UPDATE_ENVIRONMENT_SUCCESS;
	}

	public void setUPDATE_ENVIRONMENT_SUCCESS(String uPDATE_ENVIRONMENT_SUCCESS) {
		UPDATE_ENVIRONMENT_SUCCESS = uPDATE_ENVIRONMENT_SUCCESS;
	}

	public String getDELETE_ENVIRONMENT_SUCCESS() {
		return DELETE_ENVIRONMENT_SUCCESS;
	}

	public void setDELETE_ENVIRONMENT_SUCCESS(String dELETE_ENVIRONMENT_SUCCESS) {
		DELETE_ENVIRONMENT_SUCCESS = dELETE_ENVIRONMENT_SUCCESS;
	}

	public String getERRORS_NEWRESOURCECREATION_SUCCESS() {
		return ERRORS_NEWRESOURCECREATION_SUCCESS;
	}

	public void setERRORS_NEWRESOURCECREATION_SUCCESS(String eRRORS_NEWRESOURCECREATION_SUCCESS) {
		ERRORS_NEWRESOURCECREATION_SUCCESS = eRRORS_NEWRESOURCECREATION_SUCCESS;
	}

	public String getERRORS_RESOURCEUPDATED_SUCCESS() {
		return ERRORS_RESOURCEUPDATED_SUCCESS;
	}

	public void setERRORS_RESOURCEUPDATED_SUCCESS(String eRRORS_RESOURCEUPDATED_SUCCESS) {
		ERRORS_RESOURCEUPDATED_SUCCESS = eRRORS_RESOURCEUPDATED_SUCCESS;
	}

	public String getERRORS_DELETERESOURCE_FAILURE() {
		return ERRORS_DELETERESOURCE_FAILURE;
	}

	public void setERRORS_DELETERESOURCE_FAILURE(String eRRORS_DELETERESOURCE_FAILURE) {
		ERRORS_DELETERESOURCE_FAILURE = eRRORS_DELETERESOURCE_FAILURE;
	}

	public String getERRORS_DELETERESOURCE_SUCCESS() {
		return ERRORS_DELETERESOURCE_SUCCESS;
	}

	public void setERRORS_DELETERESOURCE_SUCCESS(String eRRORS_DELETERESOURCE_SUCCESS) {
		ERRORS_DELETERESOURCE_SUCCESS = eRRORS_DELETERESOURCE_SUCCESS;
	}

	@Value("${errors.deleteresource.success}")
	private String ERRORS_DELETERESOURCE_SUCCESS;

	public String getERRORS_NEWTEAMCREATION_SUCCESS() {
		return ERRORS_NEWTEAMCREATION_SUCCESS;
	}

	public void setERRORS_NEWTEAMCREATION_SUCCESS(String eRRORS_NEWTEAMCREATION_SUCCESS) {
		ERRORS_NEWTEAMCREATION_SUCCESS = eRRORS_NEWTEAMCREATION_SUCCESS;
	}

	public String getERRORS_NEWTEAMUPDATED_SUCCESS() {
		return ERRORS_NEWTEAMUPDATED_SUCCESS;
	}

	public void setERRORS_NEWTEAMUPDATED_SUCCESS(String eRRORS_NEWTEAMUPDATED_SUCCESS) {
		ERRORS_NEWTEAMUPDATED_SUCCESS = eRRORS_NEWTEAMUPDATED_SUCCESS;
	}

	public String getERRORS_DELETETEAM_FAILURE() {
		return ERRORS_DELETETEAM_FAILURE;
	}

	public void setERRORS_DELETETEAM_FAILURE(String eRRORS_DELETETEAM_FAILURE) {
		ERRORS_DELETETEAM_FAILURE = eRRORS_DELETETEAM_FAILURE;
	}

	public String getERRORS_DELETETEAM_SUCCESS() {
		return ERRORS_DELETETEAM_SUCCESS;
	}

	public void setERRORS_DELETETEAM_SUCCESS(String eRRORS_DELETETEAM_SUCCESS) {
		ERRORS_DELETETEAM_SUCCESS = eRRORS_DELETETEAM_SUCCESS;
	}

	public String getERRORS_APPLICATIONUPDATED_SUCCES() {
		return ERRORS_APPLICATIONUPDATED_SUCCES;
	}

	public void setERRORS_APPLICATIONUPDATED_SUCCES(String eRRORS_APPLICATIONUPDATED_SUCCES) {
		ERRORS_APPLICATIONUPDATED_SUCCES = eRRORS_APPLICATIONUPDATED_SUCCES;
	}

	public String getERRORS_LOOKUP_UNKNOWN() {
		return ERRORS_LOOKUP_UNKNOWN;
	}

	public void setERRORS_LOOKUP_UNKNOWN(String eRRORS_LOOKUP_UNKNOWN) {
		ERRORS_LOOKUP_UNKNOWN = eRRORS_LOOKUP_UNKNOWN;
	}

	public String getERRORS_NEWAPPLICATIONCREATION_SUCCESS() {
		return ERRORS_NEWAPPLICATIONCREATION_SUCCESS;
	}

	public void setERRORS_NEWAPPLICATIONCREATION_SUCCESS(String eRRORS_NEWAPPLICATIONCREATION_SUCCESS) {
		ERRORS_NEWAPPLICATIONCREATION_SUCCESS = eRRORS_NEWAPPLICATIONCREATION_SUCCESS;
	}

	/**
	 * @return the eRRORS_DELETEAPPLICATION_FAILURE
	 */
	public String getERRORS_DELETEAPPLICATION_FAILURE() {
		return ERRORS_DELETEAPPLICATION_FAILURE;
	}

	/**
	 * @param eRRORS_DELETEAPPLICATION_FAILURE the eRRORS_DELETEAPPLICATION_FAILURE
	 *                                         to set
	 */
	public void setERRORS_DELETEAPPLICATION_FAILURE(String eRRORS_DELETEAPPLICATION_FAILURE) {
		ERRORS_DELETEAPPLICATION_FAILURE = eRRORS_DELETEAPPLICATION_FAILURE;
	}

	/**
	 * @return the eRRORS_DELETEAPPLICATION_SUCCES
	 */
	public String getERRORS_DELETEAPPLICATION_SUCCES() {
		return ERRORS_DELETEAPPLICATION_SUCCES;
	}

	/**
	 * @param eRRORS_DELETEAPPLICATION_SUCCES the eRRORS_DELETEAPPLICATION_SUCCES to
	 *                                        set
	 */
	public void setERRORS_DELETEAPPLICATION_SUCCES(String eRRORS_DELETEAPPLICATION_SUCCES) {
		ERRORS_DELETEAPPLICATION_SUCCES = eRRORS_DELETEAPPLICATION_SUCCES;
	}

	/*
	 * public static final String ERRORS_LOOKUP_REQUIRED = "errors.lookup.required";
	 * public static final String ERRORS_USERNAME_REQUIRED =
	 * "errors.username.required"; public static final String
	 * ERRORS_PASSWORD_REQUIRED = "errors.password.required";
	 * 
	 * public static final String ERRORS_NEWUSERCREATION_SUCCESS =
	 * "errors.newusercreation.success"; public static final String
	 * ERRORS_USERUPDATED_SUCCESS = "errors.userupdated.success"; public static
	 * final String ERRORS_LOOKUP_UPDATEUSER = "errors.lookup.updateuser"; public
	 * static final String ERRORS_MODIFYUSER_SUCCESS = "errors.modifyuser.success";
	 * public static final String ERRORS_DELETEUSER_SUCCESS =
	 * "errors.deleteuser.success"; public static final String
	 * ERRORS_DELETEUSER_FAILURE = "errors.deleteuser.failure";
	 * 
	 * public static final String ERRORS_LOOKUP_PROJECT = "errors.lookup.project";
	 * public static final String ERRORS_MODIFYPROJECT_SUCCESS =
	 * "errors.modifyproject.success"; public static final String
	 * ERRORS_CREATEPROJECT_SUCCESS = "errors.createproject.success"; public static
	 * final String USER_ASSIGNED_TO_PROJECT_SUCCESS =
	 * "status.siteadmin.userassignedtoproject.success"; public static final String
	 * ERRORS_DELETEPROJECT_SUCCESS = "errors.deleteproject.success"; public static
	 * final String ERRORS_DELETEPROJECT_FAILURE = "errors.deleteproject.failure";
	 * 
	 * public static final String ERRORS_LOOKUP_USER = "errors.lookup.user"; public
	 * static final String ERRORS_ASSIGNPRJUSER_SUCCESS =
	 * "errors.assignprjuser.success"; public static final String
	 * ERRORS_LOOKUP_PROJECT1 = "errors.lookup.project1"; public static final String
	 * ERRORS_LOOKUP_REPOSITORY = "errors.lookup.repository"; public static final
	 * String ERRORS_LOOKUP_ASSIGNREPOPROJ = "errors.lookup.assignrepoproj"; public
	 * static final String ERRORS_LOOKUP_NEWUSER = "errors.lookup.newuser"; public
	 * static final String ERRORS_LOOKUP_FOLDER = "errors.lookup.folder"; public
	 * static final String ERRORS_FOLDER_SUCCESS = "errors.folder.success"; public
	 * static final String ERRORS_LOOKUP_DIRECTORYSERVICE =
	 * "errors.lookup.directoryservice";
	 * 
	 * public static final String ERRORS_DATABASE_ERROR = "errors.database.error";
	 * 
	 * public static final String ERRORS_LOOKUP_PHASE = "errors.lookup.phase";
	 * public static final String ERRORS_PHASE_SUCCESS = "errors.phase.success";
	 * 
	 * public static final String ERRORS_LOOKUP_RULELIST = "errors.lookup.rulelist";
	 * public static final String ERRORS_RULELIST_SUCCESS =
	 * "errors.rulelist.success";
	 * 
	 * public static final String ERRORS_RULELISTTOPROJ_SUCCESS =
	 * "errors.rulelisttoproj.success"; public static final String
	 * ERRORS_TAGGINGPROJECT_SUCCESS = "errors.assignprjuser.success"; public static
	 * final String ERRORS_REGISTERFOLDER_SUCCESS = "errors.folder.success"; public
	 * static final String ERRORS_SAASSIGNREPOPROJECT_SUCCESS =
	 * "errors.assignprjuser.success"; public static final String
	 * ERRORS_NEWRULELISTCREATION_SUCCESS = "errors.rulelist.success"; public static
	 * final String ERRORS_MODIFYRULELIST_SUCCESS =
	 * "errors.updateprojectrulelist.success"; public static final String
	 * ERRORS_DELETERULELIST_SUCCESS = "errors.deleteprojectrulelist.success";
	 * 
	 * public static final String ASSIGN_USERS_TO_PROJECT_SUCCESS =
	 * "success.assignuserstoproject"; public static final String
	 * UNASSIGN_USERS_FROM_PROJECT_SUCCESS = "success.unassignusersfromproject";
	 * public static final String ASSIGN_USERS_TO_PROJECT_ERROR =
	 * "error.assignuserstoproject"; public static final String
	 * UNASSIGN_USERS_FROM_PROJECT_ERROR = "error.unassignusersfromproject";
	 * 
	 * public static final String ASSIGN_RULELISTS_TO_PROJECT_SUCCESS =
	 * "success.assignruleliststoproject"; public static final String
	 * UNASSIGN_RULELISTS_FROM_PROJECT_SUCCESS =
	 * "success.unassignrulelistsfromproject"; public static final String
	 * ASSIGN_RULELISTS_TO_PROJECT_ERROR = "error.assignruleliststoproject"; public
	 * static final String UNASSIGN_RULELISTS_FROM_PROJECT_ERROR =
	 * "error.unassignrulelistsfromproject";
	 * 
	 * public static final String ASSIGN_RULES_TO_RULELIST_SUCCESS =
	 * "success.assignrulestorulelist"; public static final String
	 * UNASSIGN_RULES_FROM_RULELIST_SUCCESS = "success.unassignrulesfromrulelist";
	 * public static final String ASSIGN_RULES_TO_RULELIST_ERROR =
	 * "error.assignrulestorulelist"; public static final String
	 * UNASSIGN_RULES_FROM_RULELIST_ERROR = "error.unassignrulesfromrulelist";
	 * 
	 * public static final String ASSIGN_WORKSPACES_TO_REPOSITORY_SUCCESS =
	 * "success.assignworkspacestorepository"; public static final String
	 * UNASSIGN_WORKSPACES_FROM_REPOSITORY_SUCCESS =
	 * "success.unassignworkspacesfromrepository"; public static final String
	 * ASSIGN_WORKSPACES_TO_REPOSITORY_ERROR = "error.assignworkspacestorepository";
	 * public static final String UNASSIGN_WORKSPACES_FROM_REPOSITORY_ERROR =
	 * "error.unassignworkspacesfromrepository";
	 * 
	 * 
	 * public static final String ASSIGN_PROJECTS_TO_REPOSITORY_SUCCESS =
	 * "success.assignprojectstorepository"; public static final String
	 * UNASSIGN_PROJECTS_FROM_REPOSITORY_SUCCESS =
	 * "success.unassignprojectsfromrepository"; public static final String
	 * ASSIGN_PROJECTS_TO_REPOSITORY_ERROR = "error.assignprojectstorepository";
	 * public static final String UNASSIGN_PROJECTS_FROM_REPOSITORY_ERROR =
	 * "error.unassignprojectsfromrepository";
	 * 
	 * public static final String REGISTER_FOLDERS_WITH_REPOSITORY_SUCCESS =
	 * "success.registerfolderswithrepository"; public static final String
	 * UNREGISTER_FOLDERS_FROM_REPOSITORY_SUCCESS =
	 * "success.unregisterfoldersfromrepository"; public static final String
	 * REGISTER_FOLDERS_WITH_REPOSITORY_ERROR =
	 * "error.registerfolderswithrepository"; public static final String
	 * UNREGISTER_FOLDERS_FROM_REPOSITORY_ERROR =
	 * "error.unregisterfoldersfromrepository";
	 * 
	 * public static final String REGISTER_FOLDERS_WITH_PROJECT_SUCCESS =
	 * "success.registerfolderswithproject"; public static final String
	 * UNREGISTER_FOLDERS_FROM_PROJECT_SUCCESS =
	 * "success.unregisterfoldersfromproject"; public static final String
	 * REGISTER_FOLDERS_WITH_PROJECT_ERROR = "error.registerfolderswithproject";
	 * public static final String UNREGISTER_FOLDERS_FROM_PROJECT_ERROR =
	 * "error.unregisterfoldersfromproject";
	 * 
	 * 
	 * public static final String ERRORS_TESTREPOSITORY_SUCCESS =
	 * "success.testrepository"; public static final String
	 * ERRORS_TESTREPOSITORY_FAILURE = "error.testrepository"; public static final
	 * String REGISTER_REPOSITORY_SUCCESS = "success.registerrepository"; public
	 * static final String REGISTER_REPOSITORY_ERROR = "error.registerrepository";
	 * public static final String MODIFY_REPOSITORY_SUCCESS =
	 * "success.modifyrepository"; public static final String
	 * MODIFY_REPOSITORY_ERROR = "error.modifyrepository"; public static final
	 * String REPOSITORY_CONNECTION_NOT_TESTED_ERROR =
	 * "error.repository.connectionnottested";
	 * 
	 * //nagaraju public static final String MODIFY_DATASTAGE_SUCCESS =
	 * "success.modifydatastage"; public static final String MODIFY_DATASTAGE_ERROR
	 * = "error.modifydatastage"; public static final String
	 * REGISTER_DATASTAGE_ERROR = "error.registerdataStage"; public static final
	 * String REGISTER_DATASTAGE_SUCCESS = "success.registerdataStage"; public
	 * static final String ERRORS_TESTDATASTAGE_SUCCESS = "success.testdatastage";
	 * public static final String ERRORS_TESTDATASTAGE_FAILURE =
	 * "error.testdatastage";
	 * 
	 * public static final String ERRORS_TESTDIRECTORYSERVICE_SUCCESS =
	 * "success.testdirectoryservice"; public static final String
	 * ERRORS_TESTDIRECTORYSERVICE_FAILURE = "error.testdirectoryservice"; public
	 * static final String REGISTER_DIRECTORYSERVICE_SUCCESS =
	 * "success.registerdirectoryservice";
	 * 
	 * public static final String REGISTER_DIRECTORYSERVICE_ERROR =
	 * "error.registerdirectoryservice"; public static final String
	 * MODIFY_DIRECTORYSERVICE_SUCCESS = "success.modifydirectoryservice"; public
	 * static final String MODIFY_DIRECTORYSERVICE_ERROR =
	 * "error.modifydirectoryservice"; public static final String
	 * DIRECTORYSERVICE_CONNECTION_NOT_TESTED_ERROR =
	 * "error.directoryservice.connectionnottested";
	 * 
	 * 
	 * public static final String LOAD_FILES_SUCCESS = "success.loadfiles"; public
	 * static final String NO_FILES_SUCCESS = "success.nofiles"; public static final
	 * String UNABLE_TO_CONNECT_TO_REPOSITORY_ERROR = "error.connection.repository";
	 * public static final String UNABLE_TO_EXECUTE_RULE_ERROR =
	 * "error.report.ruleexecution"; public static final String LOAD_FILES_ERROR =
	 * "error.loadfiles";
	 * 
	 * public static final String RULE_PARAM_UPDATE_SITE_VALUE_SUCCESS =
	 * "success.update.ruleparam.sitevalue"; public static final String
	 * RULE_PARAM_UPDATE_SITE_VALUE_ERROR = "error.update.ruleparam.sitevalue";
	 * public static final String RULE_PARAM_UPDATE_PROJECT_VALUE_SUCCESS =
	 * "success.update.ruleparam.projectvalue"; public static final String
	 * RULE_PARAM_UPDATE_PROJECT_VALUE_ERROR =
	 * "error.update.ruleparam.projectvalue";
	 * 
	 * public static final String UNKNOWN_ERROR = "error.unknown";
	 * 
	 * public static final String LICENSE_REGISTRATION_SUCCESS =
	 * "success.license.registered"; public static final String
	 * INVALID_LICENSE_ERROR = "error.license.invalid"; public static final String
	 * UNDETERMINED_IP_LICENSE_ERROR = "error.license.undeterminedip"; public static
	 * final String UNALLOWED_IP_LICENSE_ERROR = "error.license.unallowedip"; public
	 * static final String ADVANCE_USAGE_LICENSE_ERROR =
	 * "error.license.advanceusage"; public static final String
	 * EXPIRED_USAGE_LICENSE_ERROR = "error.license.expiredusage"; public static
	 * final String MAX_PROJECTS_REACHED_LICENSE_ERROR =
	 * "error.license.maximum.projects"; public static final String
	 * MAX_REPOSITORIES_REACHED_LICENSE_ERROR =
	 * "error.license.maximum.repositories"; public static final String
	 * MAX_FOLDERS_REACHED_LICENSE_ERROR = "error.license.maximum.folders"; public
	 * static final String MAX_USERS_REACHED_LICENSE_ERROR =
	 * "error.license.maximum.users";
	 * 
	 * public static final String ERRORS_NEWCRITICALITYCREATION_SUCCESS =
	 * "errors.criticality.success"; public static final String
	 * ERRORS_LOOKUP_CRITICALITY = "errors.lookup.criticality"; public static final
	 * String ASSIGN_RULES_TO_CRITICALITY_SUCCESS =
	 * "success.assignrulestocriticality"; public static final String
	 * UNASSIGN_RULES_FROM_CRITICALITY_SUCCESS =
	 * "success.unassignrulesfromcriticality"; public static final String
	 * ASSIGN_RULES_TO_CRITICALITY_ERROR = "error.assignrulestocriticality"; public
	 * static final String UNASSIGN_RULES_FROM_CRITICALITY_ERROR =
	 * "error.unassignrulesfromcriticality";
	 * 
	 * public static final String ERRORS_NEWCATEGORYCREATION_SUCCESS =
	 * "errors.category.success"; public static final String ERRORS_LOOKUP_CATEGORY
	 * = "errors.lookup.category"; public static final String
	 * ASSIGN_RULES_TO_CATEGORY_SUCCESS = "success.assignrulestocategory"; public
	 * static final String UNASSIGN_RULES_FROM_CATEGORY_SUCCESS =
	 * "success.unassignrulesfromcategory"; public static final String
	 * ASSIGN_RULES_TO_CATEGORY_ERROR = "error.assignrulestocategory"; public static
	 * final String UNASSIGN_RULES_FROM_CATEGORY_ERROR =
	 * "error.unassignrulesfromcategory";
	 * 
	 * public static final String CREATE_METRICS_REPORT_SUCCESS =
	 * "success.createmetricsreport"; public static final String
	 * CREATE_METRICS_REPORT_ERROR = "error.createmetricsreport"; public static
	 * final String CREATE_METRICS_REPORT_EXISTS = "errors.lookup.report";
	 * 
	 * public static final String XPLAN_ENABLED_ERROR =
	 * "error.connection.xplanenabled";
	 * 
	 * public static final String REGISTER_CONNECTIONS_WITH_REPOSITORY_SUCCESS =
	 * "success.registerconnectionswithrepository"; public static final String
	 * UNREGISTER_CONNECTIONS_FROM_REPOSITORY_SUCCESS =
	 * "success.unregisterconnectionsfromrepository"; public static final String
	 * REGISTER_CONNECTIONS_WITH_REPOSITORY_ERROR =
	 * "error.registerconnectionswithrepository"; public static final String
	 * UNREGISTER_CONNECTIONS_FROM_REPOSITORY_ERROR =
	 * "error.unregisterconnectionsfromrepository"; public static final String
	 * ERRORS_TEST_IMPORT_CONNECTION_SUCCESS = "success.testimportconnection";
	 * public static final String ERRORS_TEST_IMPORT_CONNECTION_FAILURE =
	 * "error.testimportconnection"; public static final String
	 * MODIFY_IMPORT_CONNECTION_SUCCESS = "success.modifyimportconnection"; public
	 * static final String MODIFY_IMPORT_CONNECTION_ERROR =
	 * "error.modifyimportconnection"; public static final String
	 * IMPORT_CONNECTION_NOT_TESTED_ERROR =
	 * "error.importconnection.connectionnottested";
	 * 
	 * public static final String UPDATE_MAIL_SUCCESS = "success.modifymail"; public
	 * static final String UPDATE_MAIL_ERROR = "error.modifymail";
	 * 
	 * public static final String CREATE_MAILGROUP_SUCCESS =
	 * "success.createmailgroup"; public static final String CREATE_MAILGROUP_ERROR
	 * = "error.createmailgroup"; public static final String
	 * UPDATE_MAILGROUP_SUCCESS = "success.modifymailgroup"; public static final
	 * String UPDATE_MAILGROUP_ERROR = "error.modifymailgroup"; public static final
	 * String DELETE_MAILGROUP_SUCCESS = "success.deletemailgroup"; public static
	 * final String DELETE_MAILGROUP_ERROR = "error.deletemailgroup"; public static
	 * final String ERRORS_LOOKUP_MAILGROUP = "errors.lookup.mailgroup";
	 * 
	 * public static final String ASSIGN_USERS_TO_MAILGROUP_SUCCESS =
	 * "success.assignuserstomailgroup"; public static final String
	 * UNASSIGN_USERS_FROM_MAILGROUP_SUCCESS = "success.unassignusersfrommailgroup";
	 * public static final String ASSIGN_USERS_TO_MAILGROUP_ERROR =
	 * "error.assignuserstomailgroup"; public static final String
	 * UNASSIGN_USERS_FROM_MAILGROUP_ERROR = "error.unassignusersfrommailgroup";
	 * 
	 * 
	 * public static final String CREATE_ENVIRONMENT_SUCCESS =
	 * "success.createenvironment"; public static final String
	 * CREATE_ENVIRONMENT_ERROR = "error.createenvironment"; public static final
	 * String CREATE_STAGE_SUCCESS = "success.createstage"; public static final
	 * String CREATE_STAGE_ERROR = "error.createstage"; public static final String
	 * UPDATE_STAGE_SUCCESS = "success.modifystage"; public static final String
	 * UPDATE_STAGE_ERROR = "error.modifyintegrationservice"; public static final
	 * String UPDATE_ENVIRONMENT_SUCCESS = "success.modifyenvironment"; public
	 * static final String UPDATE_ENVIRONMENT_ERROR = "error.modifyenvironment";
	 * public static final String DELETE_ENVIRONMENT_SUCCESS =
	 * "success.deleteenvironment"; public static final String
	 * DELETE_ENVIRONMENT_ERROR = "error.deleteenvironment"; public static final
	 * String DELETE_STAGE_SUCCESS = "success.deletestage"; public static final
	 * String DELETE_STAGE_ERROR = "error.deletestage"; public static final String
	 * ERRORS_LOOKUP_ENVIRONMENT = "errors.lookup.environment"; public static final
	 * String ERRORS_LOOKUP_STAGE = "errors.lookup.stage"; public static final
	 * String ERRORS_LOOKUP_NUMBER = "errors.lookup.number"; public static final
	 * String UPDATE_WAIVER_ERROR = "errors.modifywaiver"; public static final
	 * String UPDATE_WAIVER_SUCCESS = "success.modfiywaiver";
	 * 
	 * public static final String SHARE_REPORT_SUCCESS = "success.sharereport";
	 * public static final String SHARE_REPORT_ERROR = "error.sharereport"; public
	 * static final String FOLDER_WAIVER_ERROR = "error.folderwaiver"; public static
	 * final String FOLDER_EXISTS_ERROR = "error.folderexists"; public static final
	 * String BATCH_CREATION_SUCCESS = "success.batchcreation"; public static final
	 * String BATCH_NAME_ERROR = "error.batchnameexists";
	 * 
	 * public static final String UPDATE_BATCHGROUP_SUCCESS =
	 * "success.modifybatchgroup"; public static final String
	 * UPDATE_BATCHGROUP_ERROR = "error.modifybatchgroup"; public static final
	 * String DELETE_BATCHGROUP_SUCCESS = "success.deletebatchgroup"; public static
	 * final String DELETE_BATCHGROUP_ERROR = "error.deletebatchgroup";
	 * 
	 * public static final String SEND_MAIL_SUCCESS = "success.sendmail"; public
	 * static final String SEND_MAIL_ERROR = "error.sendmail";
	 * 
	 * public static final String RULELISTPROMOTION_SUCCESS =
	 * "success.rulelistpromotion"; public static final String
	 * RULELISTPROMOTION_ERROR = "errors.rulelistpromotion";
	 * 
	 * public static final String RULE_COMMENTRY_UPDATE_SUCCESS =
	 * "success.rulecommentryupdate";
	 * 
	 * // workflow public static final String ERRORS_CREATEPROCESS_WORKFLOW_SUCCESS
	 * = "errors.createprocessworkflow.success"; public static final String
	 * ASSIGN_PROJECTS_TO_PROCESS_WORKFLOW_SUCCESS =
	 * "success.assignprojectstoprocessworkflow"; public static final String
	 * UNASSIGN_PROJECTS_FROM_PROCESS_WORKFLOW_SUCCESS =
	 * "success.unassignprojectsfromprocessworkflow"; public static final String
	 * ASSIGN_PROJECTS_TO_PROCESS_WORKFLOW_ERROR =
	 * "error.assignprojectstoprocessworkflow"; public static final String
	 * UNASSIGN_PROJECTS_FROM_PROCESS_WORKFLOW_ERROR =
	 * "error.unassignprojectsfromprocessworkflow"; public static final String
	 * ERRORS_CREATEPROCESS_WORKFLOW_FAILURE =
	 * "errors.createprocessworkflow.failure"; public static final String
	 * ERRORS_PROJCESS_FLOW_ALREADY_EXISTS = "errors.processworkflownameexists";
	 * public static final String RULE_KEYWORD_ADD_SUCCESS =
	 * "success.add.rule.keyword"; public static final String RULE_KEYWORD_ADD_ERROR
	 * = "error.add.rule.keyword"; public static final String ERRORS_INVALID_REGEX =
	 * "errors.invalid.regex"; public static final String
	 * MODIFY_PROCESS_WORKFLOW_SUCCESS = "success.modifyprocessworkflow"; public
	 * static final String PASSWORD_EXPIRED_ERROR = "error.password.expired"; public
	 * static final String ERRORS_LOOKUP_MISCPROPERTY =
	 * "errors.lookup.miscproperty";
	 * 
	 * public static final String DELETE_STEP_SUCCESS = "success.deletestep"; public
	 * static final String DELETE_STEP_ERROR = "error.deletestep"; public static
	 * final String CREATE_STEP_SUCCESS = "success.createstep"; public static final
	 * String CREATE_STEP_ERROR = "error.createstep"; public static final String
	 * UPDATE_STEP_SUCCESS = "success.modifystep"; public static final String
	 * UPDATE_STEP_ERROR = "error.modifystep";
	 * 
	 * public static final String ERRORS_LOOKUP_STEP = "errors.lookup.step";
	 * 
	 * // TODO: Handle LegacyCode public static final String UPDATE_LEGACYCODE_ERROR
	 * = "errors.modifylegacycode"; public static final String
	 * UPDATE_LEGACYCODE_SUCCESS = "success.modifylegacycode";
	 * 
	 * public static final String ASSIGN_RULES_TO_LEGACYCODE_SUCCESS =
	 * "success.assignrulestolegacycode"; public static final String
	 * UNASSIGN_RULES_FROM_LEGACYCODE_SUCCESS =
	 * "success.unassignrulesfromlegacycode"; public static final String
	 * ASSIGN_RULES_TO_LEGACYCODE_ERROR = "error.assignrulestolegacycode"; public
	 * static final String UNASSIGN_RULES_FROM_LEGACYCODE_ERROR =
	 * "error.unassignrulesfromlegacycode";
	 */

	/*
	 * public static Map<REErrorCodes, String> exceptionErrorCodeMap = new
	 * HashMap<REErrorCodes, String>();
	 * 
	 * static { exceptionErrorCodeMap.put(REErrorCodes.PROJ_NAME_ALREADY_EXISTS,
	 * ERRORS_LOOKUP_PROJECT);
	 * exceptionErrorCodeMap.put(REErrorCodes.USER_NAME_ALREADY_EXISTS,
	 * ERRORS_LOOKUP_USER);
	 * exceptionErrorCodeMap.put(REErrorCodes.REPOSITORY_NAME_ALREADY_EXISTS,
	 * ERRORS_LOOKUP_REPOSITORY);
	 * exceptionErrorCodeMap.put(REErrorCodes.RULE_GROUP_ALREADY_EXISTS,
	 * ERRORS_LOOKUP_RULELIST);
	 * exceptionErrorCodeMap.put(REErrorCodes.CATEGORY_NAME_ALREADY_EXISTS,
	 * ERRORS_LOOKUP_CATEGORY);
	 * exceptionErrorCodeMap.put(REErrorCodes.CRITICALITY_LEVEL_ALREADY_EXISTS,
	 * ERRORS_LOOKUP_CRITICALITY);
	 * exceptionErrorCodeMap.put(REErrorCodes.FOLDER_NAME_ALREADY_EXISTS,
	 * ERRORS_LOOKUP_FOLDER);
	 * exceptionErrorCodeMap.put(REErrorCodes.REPORT_NAME_ALREADY_EXISTS,
	 * CREATE_METRICS_REPORT_ERROR);
	 * 
	 * exceptionErrorCodeMap.put(REErrorCodes.CREATE_REPORT_ERROR,
	 * CREATE_METRICS_REPORT_ERROR);
	 * exceptionErrorCodeMap.put(REErrorCodes.REPORT_NAME_ALREADY_EXISTS,
	 * CREATE_METRICS_REPORT_EXISTS);
	 * exceptionErrorCodeMap.put(REErrorCodes.UNABLE_TO_CONNECT_TO_REPOSITORY,
	 * UNABLE_TO_CONNECT_TO_REPOSITORY_ERROR);
	 * exceptionErrorCodeMap.put(REErrorCodes.UNABLE_TO_EXECUTE_RULE,
	 * UNABLE_TO_EXECUTE_RULE_ERROR);
	 * exceptionErrorCodeMap.put(REErrorCodes.UNEXPECTED_ERROR, UNKNOWN_ERROR);
	 * 
	 * exceptionErrorCodeMap.put(REErrorCodes.INVALID_LICENSE,
	 * INVALID_LICENSE_ERROR);
	 * exceptionErrorCodeMap.put(REErrorCodes.INVALID_LICENSE_KEY,
	 * INVALID_LICENSE_ERROR);
	 * exceptionErrorCodeMap.put(REErrorCodes.UNDERTERMINED_IP_STATUS,
	 * UNDETERMINED_IP_LICENSE_ERROR);
	 * exceptionErrorCodeMap.put(REErrorCodes.UNALLOWED_IP_ADDRESS,
	 * UNALLOWED_IP_LICENSE_ERROR);
	 * exceptionErrorCodeMap.put(REErrorCodes.LICENSE_ERROR_ADVANCE_USAGE,
	 * ADVANCE_USAGE_LICENSE_ERROR);
	 * exceptionErrorCodeMap.put(REErrorCodes.LICENSE_ERROR_EXPIRED_USAGE,
	 * EXPIRED_USAGE_LICENSE_ERROR);
	 * 
	 * exceptionErrorCodeMap.put(REErrorCodes.REGEX_INVALID, ERRORS_INVALID_REGEX);
	 * 
	 * exceptionErrorCodeMap.put(REErrorCodes.PROJECT_LIMITS_REACHED,
	 * MAX_PROJECTS_REACHED_LICENSE_ERROR);
	 * exceptionErrorCodeMap.put(REErrorCodes.REPOSITORY_LIMITS_REACHED,
	 * MAX_REPOSITORIES_REACHED_LICENSE_ERROR);
	 * exceptionErrorCodeMap.put(REErrorCodes.FOLDER_LIMITS_REACHED,
	 * MAX_FOLDERS_REACHED_LICENSE_ERROR);
	 * exceptionErrorCodeMap.put(REErrorCodes.USERS_LIMIT_REACHED,
	 * MAX_USERS_REACHED_LICENSE_ERROR);
	 * exceptionErrorCodeMap.put(REErrorCodes.FOLDER_WAIVER_EXISTS,
	 * FOLDER_WAIVER_ERROR); exceptionErrorCodeMap.put(REErrorCodes.FOLDER_EXISTS,
	 * FOLDER_EXISTS_ERROR);
	 * exceptionErrorCodeMap.put(REErrorCodes.BATCH_GROUP_NAME_ALREADY_EXISTS,
	 * BATCH_NAME_ERROR);
	 * exceptionErrorCodeMap.put(REErrorCodes.ENVIRONMENT_NAME_ALREADY_EXISTS,
	 * ERRORS_LOOKUP_ENVIRONMENT);
	 * exceptionErrorCodeMap.put(REErrorCodes.MAILGROUP_NAME_ALREADY_EXISTS,
	 * ERRORS_LOOKUP_MAILGROUP);
	 * exceptionErrorCodeMap.put(REErrorCodes.STEP_NAME_ALREADY_EXISTS,
	 * ERRORS_LOOKUP_STEP);
	 * 
	 * exceptionErrorCodeMap.put(REErrorCodes.DIRECTORYSERVICE_NAME_ALREADY_EXISTS,
	 * ERRORS_LOOKUP_DIRECTORYSERVICE); }
	 * 
	 * public static String getErrorCodeFromExceptionCode(REErrorCodes reErrorCode)
	 * { String errorCode = exceptionErrorCodeMap.get(reErrorCode);
	 * 
	 * if (errorCode == null) { errorCode = UNKNOWN_ERROR; }
	 * 
	 * return errorCode; }
	 */
}
