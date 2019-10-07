
package com.cbp.ticketing.dao.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.cbp.ticketing.Utility.ReUtils;
import com.cbp.ticketing.controller.TicketingController;
import com.cbp.ticketing.dao.daoService.TicketingDaoService;
import com.cbp.ticketing.model.OptionType;
import com.cbp.ticketing.model.TicketApp;
import com.cbp.ticketing.model.TicketEnvResTeamApp;
import com.cbp.ticketing.model.TicketEnvType;
import com.cbp.ticketing.model.TicketResCredentials;
import com.cbp.ticketing.model.TicketResSubType;
import com.cbp.ticketing.model.TicketResource;
import com.cbp.ticketing.model.TicketResourceType;
import com.cbp.ticketing.model.TicketRole;
import com.cbp.ticketing.model.TicketRoleTypeOption;
import com.cbp.ticketing.model.TicketTeam;
import com.cbp.ticketing.model.TicketUser;
import com.cbp.ticketing.model.UserLogin;

@Repository

public class TicketingImpl implements TicketingDaoService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(TicketingController.class);

	public List<TicketUser> login(TicketUser user) {

		String QUERY = "SELECT * FROM CBP_TKT_USER WHERE USER_NAME = ? AND USER_PASSWORD = ?";
		List<TicketUser> users = null;
		try {

			Object[] a = new Object[] { user.getUserName(), user.getPassword() };

			users = jdbcTemplate.query(QUERY, a, new RowMapper<TicketUser>() {

				public TicketUser mapRow(ResultSet rs, int rowNum) throws SQLException {
					TicketUser ticketUser = new TicketUser();
					ticketUser.setUserName(rs.getString("USER_NAME"));
					ticketUser.setEmailId(rs.getString("EMAIL_ID"));
					ticketUser.setUserId(rs.getInt("USER_ID"));
					ticketUser.setIsSiteAdmin(rs.getString("IS_SITE_ADMIN"));
					ticketUser.setIsLocked(rs.getString("IS_LOCKED"));
					return ticketUser;
				}
			});

		} catch (Exception e) {

		}

		return users;

	}

//TicketApp Querys 

	public void createTicketApp(TicketApp ticketApp) {
		String Query_Check = "SELECT * FROM CBP_TKT_APP WHERE APP_NAME = ?";
		int id;
		String QUERY = "INSERT into CBP_TKT_APP(APP_ID,APP_NAME,CREATED_TS,UPDATED_TS,DELETED_TS,IS_DELETED) values(? , ? , ? , ? ,? ,?)";
		id = getMaxId("APP_ID", "cbp_TKT_APP") + 1;
		List<Object[]> inputList = new ArrayList<Object[]>();
		List list = ticketApp.getAppNames();
		for (int i = 0; i < list.size(); i++) {
			ticketApp.setAppId(id);
			logger.info("Application name" + list.get(i).toString());
			ticketApp.setAppName(list.get(i).toString());
			Object[] tmp = { ticketApp.getAppId(), ticketApp.getAppName(), ticketApp.getCreatedDate(),
					ticketApp.getModifiedDate(), ticketApp.getDeleted_ts(), ticketApp.getIs_deleted() };
			inputList.add(tmp);
			id++;
		}
		jdbcTemplate.batchUpdate(QUERY, inputList);
	}

	public List<TicketApp> getTicketAppList() {
		List<TicketApp> ticketAppList = null;
		try {
			String QUERY = "SELECT * FROM  CBP_TKT_APP WHERE IS_DELETED='N'";
			ticketAppList = jdbcTemplate.query(QUERY, new RowMapper<TicketApp>() {

				public TicketApp mapRow(ResultSet resultSet, int rowNum) throws SQLException {
					TicketApp ticketApp = new TicketApp();
					ticketApp.setAppId(resultSet.getInt("APP_ID"));
					ticketApp.setAppName(resultSet.getString("APP_NAME"));
					ticketApp.setCreatedDate(resultSet.getDate("CREATED_TS"));
					ticketApp.setModifiedDate(resultSet.getDate("UPDATED_TS"));
					return ticketApp;
				}
			});
		} catch (Exception e) {

		}
		return ticketAppList;
	}

	public boolean updateTicketApp(TicketApp ticketApp) {
		boolean flag = false;
		String UPDATE_QUERY = "UPDATE CBP_TKT_APP set APP_NAME = ?, UPDATED_TS = ? where APP_ID = ?";
		Object[] a = new Object[] { ticketApp.getAppName(), ticketApp.getModifiedDate(), ticketApp.getAppId() };
		try {
			jdbcTemplate.update(UPDATE_QUERY, a);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}

		return flag;
	}

	public boolean deleteTicketApp(TicketApp ticketApp) throws SQLException {
		boolean flag = false;
		String DELETE_QUERY = "UPDATE CBP_TKT_APP set IS_DELETED= ? ,DELETED_TS= ? where APP_ID = ?";
		Object[] a = new Object[] { ticketApp.getIs_deleted(), ticketApp.getDeleted_ts(), ticketApp.getAppId() };
		try {
			jdbcTemplate.update(DELETE_QUERY, a);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	// TicketTeam Querys

	/*
	 * public void createTicketTeam(TicketTeam ticketTeam) { int id; String QUERY =
	 * "INSERT into CBP_TKT_TEAM(TEAM_ID,TEAM_NAME,CBP_CREATED_TIMESTAMP,CBP_UPDATED_TIMESTAMP) values(? , ? , ? , ?)"
	 * ; id = getMaxId("TEAM_ID", "CBP_TKT_TEAM") + 1; List<Object[]> inputList =
	 * new ArrayList<Object[]>(); List list = ticketTeam.getTeamNames(); for (int i
	 * = 0; i < list.size(); i++) { ticketTeam.setTeamId(id);;
	 * logger.info("Application name" + list.get(i).toString());
	 * ticketTeam.setTeamName(list.get(i).toString()); Object[] tmp = new Object[] {
	 * ticketTeam.getTeamId(), ticketTeam.getTeamName(),
	 * ticketTeam.getCreatedDate(), ticketTeam.getModifiedDate() };
	 * inputList.add(tmp); id++; } jdbcTemplate.batchUpdate(QUERY, inputList); }
	 * public List<TicketTeam> getTicketTeamList() { List<TicketTeam> ticketTeamList
	 * = new ArrayList<TicketTeam>(); TicketTeam ticketTeam; try { String QUERY =
	 * "SELECT * FROM  CBP_TKT_TEAM"; ticketTeamList = jdbcTemplate.query(QUERY, new
	 * RowMapper<TicketTeam>() {
	 * 
	 * public TicketTeam mapRow(ResultSet resultSet, int rowNum) throws SQLException
	 * { TicketTeam ticketTeam = new TicketTeam();
	 * ticketTeam.setTeamId(resultSet.getInt("TEAM_ID"));
	 * ticketTeam.setTeamName(resultSet.getString("TEAM_NAME"));
	 * ticketTeam.setCreatedDate(resultSet.getDate("CBP_CREATED_TIMESTAMP"));
	 * ticketTeam.setModifiedDate(resultSet.getDate("CBP_UPDATED_TIMESTAMP"));
	 * return ticketTeam; } });
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } return ticketTeamList; }
	 * 
	 * public void updateTicketTeam(TicketTeam ticketTeam) {
	 * System.out.println("update" + ticketTeam.getTeamName()); String UPDATE_QUERY
	 * =
	 * "UPDATE CBP_TKT_TEAM set TEAM_NAME = ?, CBP_UPDATED_TIMESTAMP = ? where TEAM_ID = ?"
	 * ; Object[] a = new Object[] { ticketTeam.getTeamName(),
	 * ticketTeam.getModifiedDate(), ticketTeam.getTeamId() }; try {
	 * jdbcTemplate.update(UPDATE_QUERY, a);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } }
	 * 
	 * public void deleteTicketTeam(TicketTeam ticketTeam) { String DELETE_QUERY =
	 * "delete from CBP_TKT_TEAM where TEAM_ID =" + ticketTeam.getTeamId();
	 * jdbcTemplate.execute(DELETE_QUERY); }
	 */
	// TicketResourceType Query

	public void createTicketResType(TicketResourceType ticketResourceType) {
		int id;
		String QUERY = "INSERT into CBP_TKT_RES_TYPE(RES_TYPE_ID,RES_TYPE_NAME,CREATED_TS,UPDATED_TS,DELETED_TS,IS_DELETED) values(? , ? , ? , ? ,? ,?)";
		id = getMaxId("RES_TYPE_ID", "CBP_TKT_RES_TYPE") + 1;
		List<Object[]> inputList = new ArrayList<Object[]>();
		List list = ticketResourceType.getResTypeNames();
		for (int i = 0; i < list.size(); i++) {
			ticketResourceType.setResTypeId(id);
			logger.info("Application name" + list.get(i).toString());
			ticketResourceType.setResTypeName(list.get(i).toString());
			Object[] tmp = new Object[] { ticketResourceType.getResTypeId(), ticketResourceType.getResTypeName(),
					ticketResourceType.getCreatedDate(), ticketResourceType.getModifiedDate(),
					ticketResourceType.getDeletedDate(), ticketResourceType.getIsDeleted() };
			inputList.add(tmp);
			id++;
		}
		jdbcTemplate.batchUpdate(QUERY, inputList);
	}

	public List<TicketResourceType> getTicketResorceTypeList() {
		List<TicketResourceType> ticketResTypeList = new ArrayList<TicketResourceType>();
		String QUERY = "SELECT * FROM  CBP_TKT_RES_TYPE WHERE IS_DELETED='N'";
		ticketResTypeList = jdbcTemplate.query(QUERY, new RowMapper<TicketResourceType>() {

			public TicketResourceType mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				TicketResourceType ticketResType = new TicketResourceType();
				ticketResType.setResTypeId(resultSet.getInt("RES_TYPE_ID"));
				ticketResType.setResTypeName(resultSet.getString("RES_TYPE_NAME"));
				ticketResType.setCreatedDate(resultSet.getDate("CREATED_TS"));
				ticketResType.setModifiedDate(resultSet.getDate("UPDATED_TS"));
				return ticketResType;
			}
		});

		return ticketResTypeList;
	}

	public void updateTicketResourceType(TicketResourceType ticketResourceType) {
		String UPDATE_QUERY = "UPDATE CBP_TKT_RES_TYPE set RES_TYPE_NAME = ?, UPDATED_TS = ? where RES_TYPE_ID = ?";
		Object[] a = new Object[] { ticketResourceType.getResTypeName(), ticketResourceType.getModifiedDate(),
				ticketResourceType.getResTypeId() };
		jdbcTemplate.update(UPDATE_QUERY, a);
	}

	public void deleteTicketResourceType(TicketResourceType ticketResourceType) {
		String DELETE_QUERY = "UPDATE CBP_TKT_RES_TYPE set DELETED_TS= ? , IS_DELETED= ? where RES_TYPE_ID = ?";
		Object[] a = new Object[] { ticketResourceType.getDeletedDate(), ticketResourceType.getIsDeleted(),
				ticketResourceType.getResTypeId() };
		jdbcTemplate.update(DELETE_QUERY, a);
	}

	// TicketResource

	public void createTicketRes(TicketResource ticketResource) {
		int resId = getMaxId("RES_ID", "CBP_TKT_RES") + 1;
		System.out.println("id...."+resId);
		ticketResource.setResId(resId);
		String QUERY = "INSERT into CBP_TKT_RES(RES_ID,RES_TYPE_ID,CREATED_TS,UPDATED_TS,DELETED_TS,IS_DELETED) values(? , ? , ? , ? , ? ,?)";
			Object[] tmp = new Object[] { ticketResource.getResId(), ticketResource.getResTypeId(),
					ticketResource.getCreatedDate(), ticketResource.getModifiedDate(), ticketResource.getDeletedDate(),
					ticketResource.getIsDeleted() };   
			jdbcTemplate.update(QUERY, tmp);
		createResCredentials(ticketResource);
	}
	public void createResCredentials(TicketResource ticketResource) {
		List<Object[]> inputLists = new ArrayList<Object[]>();
		//int id=ticketResource.getResId();
		System.out.println("id...."+ticketResource.getResId());
		TicketResCredentials ticketResCredentials = ticketResource.getTicketResCredentials();
		List<String> resourceTypekeys = ticketResource.getResTypeKeys();
		String SECOUND_QUERY = "INSERT into CBP_TKT_RES_CRED(RES_ID,RES_KEY,RES_VALUE,IS_ENCRYPTED,CREATED_TS,UPDATED_TS,DELETED_TS,IS_DELETED) values(? , ? , ? , ? , ? ,? ,? ,?)";
List<String> listOfValues=ticketResource.getResTypeValues();
		for (int i = 0; i < resourceTypekeys.size(); i++) {
			String key=resourceTypekeys.get(i).toString();
			String value=listOfValues.get(i).toString();
			System.out.println(key);
			Object[] tmp = new Object[] { ticketResource.getResId(),key ,
					value,ticketResCredentials.getIsEncrypted(),  ticketResCredentials.getCreatedDate(),
					ticketResCredentials.getModifiedDate(), ticketResCredentials.getDeletedDate(),
					ticketResCredentials.getIsDeleted() };
			inputLists.add(tmp);
		}
		jdbcTemplate.batchUpdate(SECOUND_QUERY, inputLists);
		System.out.println("success");
	}


	public List<TicketResource> getTicketResourceList() {
		List<TicketResource> ticketResList = new ArrayList<TicketResource>();
		String QUERY = "SELECT * FROM  CBP_TKT_RES WHERE IS_DELETED=N";
		ticketResList = jdbcTemplate.query(QUERY, new RowMapper<TicketResource>() {
			public TicketResource mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				TicketResource ticketRes = new TicketResource();
				ticketRes.setResId(resultSet.getInt("RES_ID"));
				ticketRes.setResName(resultSet.getString("RES_NAME"));
				ticketRes.setResTypeId(resultSet.getInt("RES_TYPE_ID"));
				ticketRes.setCreatedDate(resultSet.getDate("CREATED_TS"));
				ticketRes.setModifiedDate(resultSet.getDate("UPDATED_TS"));
				return ticketRes;
			}
		});

		return ticketResList;
	}

	public void updateTicketResource(TicketResource ticketResource) {

		String UPDATE_QUERY = "UPDATE CBP_TKT_RES set RES_NAME = ?,RES_TYPE_ID = ?, CUPDATED_TS = ? where RES_ID = ?";
		Object[] a = new Object[] { ticketResource.getResName(), ticketResource.getResTypeId(),
				ticketResource.getModifiedDate(), ticketResource.getResId() };
		jdbcTemplate.update(UPDATE_QUERY, a);
	}

	public void deleteTicketResource(TicketResource ticketResource) {
		String DELETE_QUERY = " UPDATE from CBP_TKT_RESset , DELETED_TS = ? ,IS_DELETED= ? where RES_ID = ?";
		jdbcTemplate.execute(DELETE_QUERY);

	}

	// TicketEnvType Querys

	public void createTicketEnvType(TicketEnvType ticketEnvType) {
		int id = getMaxId("ENV_TYPE_ID", "CBP_TKT_ENV_TYPE") + 1;

		String QUERY = "INSERT into CBP_TKT_ENV_TYPE(ENV_TYPE_ID,ENV_TYPE_NAME,CREATED_TS,UPDATED_TS,DELETED_TS,IS_DELETED) values(? , ? , ? , ? , ?, ?)";
		List<Object[]> inputList = new ArrayList<Object[]>();
		List list = ticketEnvType.getEnvTypeNames();
		for (int i = 0; i < list.size(); i++) {
			ticketEnvType.setEnvTypeId(id);
			logger.info("Application name" + list.get(i).toString());
			ticketEnvType.setEnvTypeName(list.get(i).toString());
			Object[] tmp = new Object[] { ticketEnvType.getEnvTypeId(), ticketEnvType.getEnvTypeName(),
					ticketEnvType.getCreatedDate(), ticketEnvType.getModifiedDate(), ticketEnvType.getDeleted_ts(),
					ticketEnvType.getIs_deleted() };
			inputList.add(tmp);
			id++;
		}

		jdbcTemplate.batchUpdate(QUERY, inputList);
	}

	public List<TicketEnvType> getTicketEnvType() {
		List<TicketEnvType> ticketEnvTypeList = new ArrayList<TicketEnvType>();
		String QUERY = "SELECT * FROM  CBP_TKT_ENV_TYPE WHERE IS_DELETED='N'";
		ticketEnvTypeList = jdbcTemplate.query(QUERY, new RowMapper<TicketEnvType>() {
			public TicketEnvType mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				TicketEnvType ticketEnvType = new TicketEnvType();
				ticketEnvType.setEnvTypeId(resultSet.getInt("ENV_TYPE_ID"));
				ticketEnvType.setEnvTypeName(resultSet.getString("ENV_TYPE_NAME"));
				ticketEnvType.setCreatedDate(resultSet.getDate("CREATED_TS"));
				ticketEnvType.setModifiedDate(resultSet.getDate("UPDATED_TS"));
				ticketEnvType.setDeleted_ts(resultSet.getDate("DELETED_TS"));
				ticketEnvType.setIs_deleted(resultSet.getString("IS_DELETED"));
				return ticketEnvType;
			}
		});

		return ticketEnvTypeList;
	}

	public void updateTicketEnvType(TicketEnvType ticketEnvType) {

		String UPDATE_QUERY = "UPDATE CBP_TKT_ENV_TYPE set ENV_TYPE_NAME = ?, UPDATED_TS = ? where ENV_TYPE_ID = ?";
		Object[] a = new Object[] { ticketEnvType.getEnvTypeName(), ticketEnvType.getModifiedDate(),
				ticketEnvType.getEnvTypeId() };
		jdbcTemplate.update(UPDATE_QUERY, a);
	}

	public void deleteTicketEnvType(TicketEnvType ticketEnvType) {

		String DELETE_QUERY = "UPDATE CBP_TKT_ENV_TYPE SET IS_DELETED=?,DELETED_TS=? where ENV_TYPE_ID =?";
		Object[] a = new Object[] { ticketEnvType.getIs_deleted(), ticketEnvType.getDeleted_ts(),
				ticketEnvType.getEnvTypeId() };
		jdbcTemplate.update(DELETE_QUERY, a);

	}

	public List<TicketEnvType> getEnvTypes(TicketEnvResTeamApp ticketEnvResTeamApp) {
		String QUERY = "SELECT * FROM CBP_TKT_ENV_TYPE WHERE ENV_TYPE_ID IN (SELECT ENV_TYPE_ID FROM CBP_TKT_APP_ENV_PROMO where  APP_ID= ?)";

		List<TicketEnvType> ticketEnvTypeList = new ArrayList<TicketEnvType>();

		Object[] a = new Object[] { ticketEnvResTeamApp.getAppId()};

		ticketEnvTypeList = jdbcTemplate.query(QUERY, a, new RowMapper<TicketEnvType>() {

			public TicketEnvType mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				TicketEnvType ticketEnvType = new TicketEnvType();
				ticketEnvType.setEnvTypeId(resultSet.getInt("ENV_TYPE_ID"));
				ticketEnvType.setEnvTypeName(resultSet.getString("ENV_TYPE_NAME"));
				ticketEnvType.setCreatedDate(resultSet.getDate("CREATED_TS"));
				ticketEnvType.setModifiedDate(resultSet.getDate("UPDATED_TS"));
				ticketEnvType.setDeleted_ts(resultSet.getDate("DELETED_TS"));
				ticketEnvType.setIs_deleted(resultSet.getString("IS_DELETED"));

				return ticketEnvType;
			}
		});

		return ticketEnvTypeList;

	}

	public List<TicketApp> getAppNames(TicketTeam ticketTeam) {
		String QUERY = "SELECT * FROM CBP_TKT_APP WHERE App_ID IN (SELECT App_ID FROM CBP_TKT_TEAM_RES_ENV_APP where TEAM_ID= ?)";
		List<TicketApp> ticketAppList = null;
		try {
			Object[] a = new Object[] { ticketTeam.getTeamId() };

			ticketAppList = jdbcTemplate.query(QUERY, a, new RowMapper<TicketApp>() {

				public TicketApp mapRow(ResultSet resultSet, int rowNum) throws SQLException {
					TicketApp ticketApp = new TicketApp();
					ticketApp.setAppId(resultSet.getInt("APP_ID"));
					ticketApp.setAppName(resultSet.getString("APP_NAME"));
					ticketApp.setCreatedDate(resultSet.getDate("CBP_CREATED_TIMESTAMP"));
					ticketApp.setModifiedDate(resultSet.getDate("CBP_UPDATED_TIMESTAMP"));
					return ticketApp;
				}
			});

		} catch (Exception e) {

		}

		return ticketAppList;

	}

	public List<TicketResource> getResNames(TicketEnvResTeamApp ticketEnvResTeamApp) {
		String QUERY = "SELECT * FROM CBP_TKT_RES WHERE RES_ID IN (SELECT RES_ID FROM CBP_TKT_TEAM_RES_ENV_APP where TEAM_ID= ? AND APP_ID= ?)";
		List<TicketResource> ticketResList = new ArrayList<TicketResource>();
		Object[] a = new Object[] { ticketEnvResTeamApp.getTeamId(), ticketEnvResTeamApp.getAppId() };

		ticketResList = jdbcTemplate.query(QUERY, a, new RowMapper<TicketResource>() {
			public TicketResource mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				TicketResource ticketRes = new TicketResource();
				ticketRes.setResId(resultSet.getInt("RES_ID"));
				ticketRes.setResName(resultSet.getString("RES_NAME"));
				ticketRes.setResTypeId(resultSet.getInt("RES_TYPE_ID"));
				ticketRes.setCreatedDate(resultSet.getDate("CBP_CREATED_TIMESTAMP"));
				ticketRes.setModifiedDate(resultSet.getDate("CBP_UPDATED_TIMESTAMP"));
				return ticketRes;
			}
		});

		return ticketResList;

	}

	public List<TicketResCredentials> getResCredentials(TicketResource ticketResource) {
		String QUERY = "SELECT * FROM CBP_TKT_RES_CRED WHERE RES_ID IN (SELECT RES_ID FROM CBP_TKT_RES where RES_TYPE_ID= ?)";

		List<TicketResCredentials> TicketResCredentialsList = new ArrayList<TicketResCredentials>();
		Object[] a = new Object[] { ticketResource.getResTypeId() };

		TicketResCredentialsList = jdbcTemplate.query(QUERY, a, new RowMapper<TicketResCredentials>() {
			public TicketResCredentials mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				TicketResCredentials TicketResCredentials = new TicketResCredentials();
				TicketResCredentials.setResId(resultSet.getInt("RES_ID"));
				TicketResCredentials.setResKey(resultSet.getString("RES_KEY"));
				TicketResCredentials.setResValue(resultSet.getString("RES_VALUE"));
				TicketResCredentials.setIsEncrypted(resultSet.getString("IS_ENCRYPTED"));
				return TicketResCredentials;
			}
		});

		return TicketResCredentialsList;

	}

	public int getMaxId(String columnName, String tableName) {
		StringBuilder MAX_ID_QUERY = new StringBuilder();
		int maxIdValue;
		MAX_ID_QUERY.append("SELECT max(");
		MAX_ID_QUERY.append(columnName);
		MAX_ID_QUERY.append(") as max_id FROM ");
		MAX_ID_QUERY.append(tableName);
		String maxId = MAX_ID_QUERY.toString();
		try {
			maxIdValue = jdbcTemplate.queryForObject(maxId, null, Integer.class);
		} catch (NullPointerException e) {
			maxIdValue = 0;
		}
		return maxIdValue;
	}

	// Resource SubType
	public void createTicketResSubType(TicketResSubType ticketResSubType) {
		int id = getMaxId("RES_SUB_TYPE_ID", "CBP_TKT_RES_SUBTYPE") + 1;
		System.out.println(id);
		String QUERY = "INSERT into CBP_TKT_RES_SUBTYPE(RES_SUB_TYPE_ID,RES_SUB_TYPE_NAME,CBP_CREATED_TIMESTAMP,CBP_UPDATED_TIMESTAMP,IS_DELETED,SORT_KEY) VALUES(?,?,?,?,?,?)";
		System.out.println(QUERY);
		Object[] tmp = new Object[] { id, ticketResSubType.getResSubTypeName(), ticketResSubType.getCreatedDate(),
				ticketResSubType.getModifiedDate(), ticketResSubType.getIsdeleted(), ticketResSubType.getSortKey() };
		jdbcTemplate.update(QUERY, tmp);

	}

	public List<TicketResSubType> getTicketResSubTypeList() {

		List<TicketResSubType> ticketAppList = null;
		try {
			String QUERY = "SELECT * FROM  CBP_TKT_RES_SUBTYPE WHERE IS_DELETED='N'";
			ticketAppList = jdbcTemplate.query(QUERY, new RowMapper<TicketResSubType>() {

				public TicketResSubType mapRow(ResultSet resultSet, int rowNum) throws SQLException {
					TicketResSubType ticketResSubType = new TicketResSubType();
					ticketResSubType.setResSubTypeId(resultSet.getInt("RES_SUB_TYPE_ID"));
					ticketResSubType.setResSubTypeName(resultSet.getString("RES_SUB_TYPE_NAME"));
					ticketResSubType.setCreatedDate(resultSet.getDate("CBP_CREATED_TIMESTAMP"));
					ticketResSubType.setModifiedDate(resultSet.getDate("CBP_UPDATED_TIMESTAMP"));
					ticketResSubType.setDeletedts(resultSet.getDate("CBP_DELETED_TIMESTAMP"));
					ticketResSubType.setSortKey(resultSet.getInt("SORT_KEY"));
					return ticketResSubType;
				}
			});
		} catch (Exception e) {

		}
		return ticketAppList;
	}

	// User
	public void createTicketUser(TicketUser ticketUser) {
		int id = getMaxId("USER_ID", "CBP_TKT_USER") + 1;
		System.out.println(id + "...userName...." + ticketUser.getUserName());
		String QUERY = "INSERT into CBP_TKT_USER(USER_ID,USER_NAME,USER_PASSWORD,IS_SITE_ADMIN,EMAIL_ID,IS_LOCKED,IS_DELETED) VALUES(?,?,?,?,?,?,?)";
		System.out.println(QUERY);
		Object[] tmp = new Object[] { id, ticketUser.getUserName(), ticketUser.getPassword(),
				ticketUser.getIsSiteAdmin(), ticketUser.getEmailId(), ticketUser.getIsLocked(),
				ticketUser.getIsDeleted() };
		jdbcTemplate.update(QUERY, tmp);

	}

	public List<TicketUser> getTicketUserList() {
		List<TicketUser> userList = null;
		try {
			String QUERY = "SELECT * FROM  CBP_TKT_USER WHERE IS_DELETED='N'";
			userList = jdbcTemplate.query(QUERY, new RowMapper<TicketUser>() {

				public TicketUser mapRow(ResultSet resultSet, int rowNum) throws SQLException {
					TicketUser ticketUser = new TicketUser();
					ticketUser.setUserId(resultSet.getInt("USER_ID"));
					ticketUser.setUserName(resultSet.getString("USER_NAME"));
					ticketUser.setPassword(resultSet.getString("USER_PASSWORD"));
					ticketUser.setIsSiteAdmin(resultSet.getString("IS_SITE_ADMIN"));
					ticketUser.setIsDeleted(resultSet.getString("IS_DELETED"));
					ticketUser.setIsLocked(resultSet.getString("IS_LOCKED"));
					return ticketUser;
				}
			});
		} catch (Exception e) {

		}
		return userList;

	}
	public void deletedUser(TicketUser ticketUser) {
		String UPDATE_QUERY = "UPDATE CBP_TKT_USER set USER_NAME = ? where  USER_ID=?";
		Object[] a = new Object[] {ticketUser.getIsDeleted() ,ticketUser.getUserId()  };
		jdbcTemplate.update(UPDATE_QUERY, a);
	}
	public void updateUser(TicketUser ticketUser) {
		String UPDATE_QUERY = "UPDATE CBP_TKT_USER set IS_DELETED = ? USER_PASSWORD= ? IS_SITE_ADMIN=? where  USER_ID=?";
		Object[] a = new Object[] {ticketUser.getUserName(), ticketUser.getPassword(),ticketUser.getIsSiteAdmin(),ticketUser.getUserId()  };
		jdbcTemplate.update(UPDATE_QUERY, a);
	}

	public List<OptionType> getListOfOptionTypes() {
		List<OptionType> ListOfOptions = null;
		try {
			String QUERY = "SELECT * FROM  CBP_TKT_OP_TYPE WHERE IS_DELETED='N'";
			ListOfOptions = jdbcTemplate.query(QUERY, new RowMapper<OptionType>() {

				public OptionType mapRow(ResultSet resultSet, int rowNum) throws SQLException {
					OptionType optionType = new OptionType();
					optionType.setOpTypeId(resultSet.getInt("OP_TYPE_ID"));
					optionType.setOpTypeName(resultSet.getString("OP_TYPE_NAME"));
					optionType.setCreatedDate(resultSet.getDate("CREATED_TS"));
					optionType.setModifiedDate(resultSet.getDate("UPDATED_TS"));
					optionType.setDeletedDate(resultSet.getDate("DELETED_TS"));
					optionType.setIsDeleted(resultSet.getString("IS_DELETED"));
					return optionType;
				}
			});
		} catch (Exception e) {

		}
		return ListOfOptions;

	}

	public void createTicketRole(TicketRole ticketRole) {

		/*
		 * String SELECTQUERY=
		 * "SELECT ROLE_NAME FROM CBP_TKT_ROLE WHERE ROLE_NAME=ticketRole.getRoleName()"
		 * ; jdbcTemplate.execute(SELECTQUERY);
		 */

		int id = getMaxId("ROLE_ID", "CBP_TKT_ROLE") + 1;
		ticketRole.setRoleId(id);
		String QUERY = "INSERT into CBP_TKT_ROLE(ROLE_ID,ROLE_NAME,CREATED_TS,UPDATED_TS,DELETED_TS,IS_DELETED) VALUES(? , ? , ? , ? , ? , ?)";

		Object[] tmp = new Object[] { ticketRole.getRoleId(), ticketRole.getRoleName(), ticketRole.getCreatedDate(),
				ticketRole.getModifiedDate(), ticketRole.getDeletedDate(), ticketRole.getIsDeleted() };
		jdbcTemplate.update(QUERY, tmp);
		ticketRoleTypeOption(ticketRole);
	}

	public void ticketRoleTypeOption(TicketRole ticketRole) {
		String INSERT_QUERY = "INSERT into CBP_TKT_ROLE_OP_TYPE(ROLE_ID,OP_TYPE_ID,IS_LOCKED,CREATED_TS,UPDATED_TS,DELETED_TS,IS_DELETED) VALUES(?,?,?,?,?,?,?)";

		List<Object[]> inputList = new ArrayList<Object[]>();
		List<Integer> list = ticketRole.getOptionTypeIds();
		TicketRoleTypeOption ticketRoleTypeOption = ticketRole.getTicketRoleTypeOption();
		for (int i = 0; i < list.size(); i++) {
			int optionId = list.get(i);
			Object[] tmps = new Object[] { ticketRole.getRoleId(), optionId, ticketRoleTypeOption.getIsLocked(),
					ticketRoleTypeOption.getCreatedDate(), ticketRoleTypeOption.getModifiedDate(),
					ticketRoleTypeOption.getDeletedDate(), "N" };
			inputList.add(tmps);
		}

		jdbcTemplate.batchUpdate(INSERT_QUERY, inputList);
	}

	public List<OptionType> getSeletedOptionTypes(TicketRole ticketRole) {
		List<OptionType> ListOfOptions = null;
		String UPDATE_QUERY = "select * from CBP_TKT_OP_TYPE where OP_TYPE_ID IN(select OP_TYPE_ID from  CBP_TKT_ROLE_OP_TYPE where ROLE_ID="+ ticketRole.getRoleId() + " AND IS_DELETED='N') AND IS_DELETED='N'";

		try {
			ListOfOptions = jdbcTemplate.query(UPDATE_QUERY, new RowMapper<OptionType>() {

				public OptionType mapRow(ResultSet resultSet, int rowNum) throws SQLException {
					OptionType optionType = new OptionType();
					optionType.setOpTypeId(resultSet.getInt("OP_TYPE_ID"));
					optionType.setOpTypeName(resultSet.getString("OP_TYPE_NAME"));
					optionType.setCreatedDate(resultSet.getDate("CREATED_TS"));
					optionType.setModifiedDate(resultSet.getDate("UPDATED_TS"));
					optionType.setDeletedDate(resultSet.getDate("DELETED_TS"));
					optionType.setIsDeleted(resultSet.getString("IS_DELETED"));
					return optionType;
				}
			});
		} catch (Exception e) {

		}
		return ListOfOptions;

	}

	public void deleteRoleTypes(TicketRole ticketRole) {
		String UPDATE_QUERY = "UPDATE CBP_TKT_ROLE_OP_TYPE set DELETED_TS = ?, IS_DELETED = ? where  ROLE_ID=? AND OP_TYPE_ID = ?";

		List<Object[]> inputList = new ArrayList<Object[]>();
		List<Integer> list = ticketRole.getDeletedTypeIds();

		TicketRoleTypeOption ticketRoleTypeOption = ticketRole.getTicketRoleTypeOption();
		for (int i = 0; i < list.size(); i++) {
			int optionId = list.get(i);

			// INSERT_QUERY = "INSERT into
			// CBP_TKT_ROLE_OP_TYPE(ROLE_ID,OP_TYPE_ID,IS_LOCKED,CREATED_TS,UPDATED_TS,DELETED_TS,IS_DELETED)
			// VALUES(?,?,?,?,?,?,?)";
			Object[] tmps = new Object[] { ticketRoleTypeOption.getDeletedDate(), "Y", ticketRole.getRoleId(),
					optionId };
			inputList.add(tmps);
		}

		jdbcTemplate.batchUpdate(UPDATE_QUERY, inputList);
	}

	public void UpdateRoleTypes(TicketRole ticketRole) {

		deleteRoleTypes(ticketRole);
		ticketRoleTypeOption(ticketRole);
	}

	public List<TicketRole> getListRoles() {
		List<TicketRole> roleList = null;
		try {
			String QUERY = "SELECT * FROM  CBP_TKT_ROLE WHERE IS_DELETED='N'";
			roleList = jdbcTemplate.query(QUERY, new RowMapper<TicketRole>() {
//		String QUERY = "INSERT into CBP_TKT_ROLE(ROLE_ID,ROLE_NAME,CREATED_TS,UPDATED_TS,DELETED_TS,IS_DELETED) VALUES(? , ? , ? , ? , ? , ?)";

				public TicketRole mapRow(ResultSet resultSet, int rowNum) throws SQLException {
					TicketRole ticketRole = new TicketRole();
					ticketRole.setRoleId(resultSet.getInt("ROLE_ID"));
					ticketRole.setRoleName(resultSet.getString("ROLE_NAME"));
					ticketRole.setCreatedDate(resultSet.getDate("CREATED_TS"));
					ticketRole.setModifiedDate(resultSet.getDate("UPDATED_TS"));
					ticketRole.setDeletedDate(resultSet.getDate("DELETED_TS"));
					ticketRole.setIsDeleted(resultSet.getString("IS_DELETED"));
					return ticketRole;
				}
			});
		} catch (Exception e) {

		}
		return roleList;

	}
	public void deletedRole(TicketRole ticketRole) {
		String UPDATE_QUERY = "UPDATE CBP_TKT_ROLE_OP_TYPE set DELETED_TS = ?, IS_DELETED = ? where  ROLE_ID=?";
		Object[] a = new Object[] {ticketRole.getDeletedDate(),ticketRole.getIsDeleted() ,ticketRole.getRoleId()  };
		jdbcTemplate.update(UPDATE_QUERY, a);
		String DELETE_QUERY = "UPDATE CBP_TKT_ROLE set DELETED_TS= ?, IS_DELETED = ? where ROLE_ID = ?";	
				jdbcTemplate.update(DELETE_QUERY, a);
	}

	

}
