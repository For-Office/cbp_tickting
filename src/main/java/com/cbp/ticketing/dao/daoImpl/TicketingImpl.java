
package com.cbp.ticketing.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
import com.cbp.ticketing.model.TicketApp;
import com.cbp.ticketing.model.TicketEnvResTeamApp;
import com.cbp.ticketing.model.TicketEnvType;
import com.cbp.ticketing.model.TicketResCredentials;
import com.cbp.ticketing.model.TicketResource;
import com.cbp.ticketing.model.TicketResourceType;
import com.cbp.ticketing.model.TicketTeam;
import com.cbp.ticketing.model.UserLogin;

@Repository
public class TicketingImpl implements TicketingDaoService {
	@Autowired
	JdbcDAO jdbcDAO;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(TicketingController.class);
	/*Connection connection = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;*/

	/*
	 * @PostConstruct public void initialize() throws SQLException { connection =
	 * jdbcDAO.getDevDatabaseConection(); }
	 */
	public List<UserLogin> login(UserLogin user) {
		String QUERY = "SELECT * FROM cbp_user_security WHERE cbp_user_name = ? AND cbp_user_password = ?";
		List<UserLogin> users = null;
		try {

			Object[] a = new Object[] { user.getUserName(), ReUtils.encryptPassword(user.getPassword()) };

			users = jdbcTemplate.query(QUERY, a, new RowMapper<UserLogin>() {

				public UserLogin mapRow(ResultSet rs, int rowNum) throws SQLException {
					System.out.println(rs.getString("cbp_user_name"));
					UserLogin a = new UserLogin();
					a.setUserName(rs.getString("cbp_user_name"));
					a.setEmailID(rs.getString("cbp_user_email_id"));
					System.out.println("list" + a.getUserName());
					return a;
				}
			});

		} catch (Exception e) {

		}

		return users;

	}

//TicketApp Querys 

	public void createTicketApp(TicketApp ticketApp) {
		int id;
		String QUERY = "INSERT into CBP_TKT_APP(APP_ID,APP_NAME,CBP_CREATED_TIMESTAMP,CBP_UPDATED_TIMESTAMP) values(? , ? , ? , ?)";
		 id = getMaxId("APP_ID", "cbp_TKT_APP") + 1;
		  List<Object[]> inputList = new ArrayList<Object[]>();
		  List list=ticketApp.getAppNames();
	     for(int i=0;i<list.size();i++) {
	    	 ticketApp.setAppId(id);	
	    	 logger.info("Application name"+list.get(i).toString());
	    	 ticketApp.setAppName(list.get(i).toString());
	            Object[] tmp = {ticketApp.getAppId(), ticketApp.getAppName(), ticketApp.getCreatedDate(),
	    				ticketApp.getModifiedDate()};
	            inputList.add(tmp);
	            id++;
	     }
	     jdbcTemplate.batchUpdate(QUERY, inputList); 
	    }
	
	public List<TicketApp> getTicketAppList() {
		List<TicketApp> ticketAppList = null;
		try {
			String QUERY = "SELECT * FROM  CBP_TKT_APP";
			ticketAppList = jdbcTemplate.query(QUERY, new RowMapper<TicketApp>() {

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

	public boolean updateTicketApp(TicketApp ticketApp) {
		boolean flag = false;
		String UPDATE_QUERY = "UPDATE CBP_TKT_APP set APP_NAME = ?, CBP_UPDATED_TIMESTAMP = ? where APP_ID = ?";
		Object[] a = new Object[] { ticketApp.getAppName(), ticketApp.getModifiedDate(), ticketApp.getAppId() };
		try {
			jdbcTemplate.update(UPDATE_QUERY, a);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}

		return flag;
	}

	public boolean deleteTicketApp(TicketApp ticketApp)throws SQLException {
		boolean flag = false;
		String DELETE_QUERY = "delete from CBP_TKT_APP where APP_ID=" + ticketApp.getAppId();
		flag = true;
		jdbcTemplate.execute(DELETE_QUERY);
		return flag;
	}

	// TicketTeam Querys

	public void createTicketTeam(TicketTeam ticketTeam) {
		try {
			int id = getMaxId("TEAM_ID", "CBP_TKT_TEAM") + 1;
			ticketTeam.setTeamId(id);
			String QUERY = "INSERT into CBP_TKT_TEAM(TEAM_ID,TEAM_NAME,CBP_CREATED_TIMESTAMP,CBP_UPDATED_TIMESTAMP) values(? , ? , ? , ?)";
			Object[] a = new Object[] { ticketTeam.getTeamId(), ticketTeam.getTeamName(), ticketTeam.getCreatedDate(),
					ticketTeam.getModifiedDate() };
			jdbcTemplate.update(QUERY, a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<TicketTeam> getTicketTeamList() {
		List<TicketTeam> ticketTeamList = new ArrayList<TicketTeam>();
		TicketTeam ticketTeam;
		try {
			String QUERY = "SELECT * FROM  CBP_TKT_TEAM";
			ticketTeamList = jdbcTemplate.query(QUERY, new RowMapper<TicketTeam>() {

				public TicketTeam mapRow(ResultSet resultSet, int rowNum) throws SQLException {
					TicketTeam ticketTeam = new TicketTeam();
					ticketTeam.setTeamId(resultSet.getInt("TEAM_ID"));
					ticketTeam.setTeamName(resultSet.getString("TEAM_NAME"));
					ticketTeam.setCreatedDate(resultSet.getDate("CBP_CREATED_TIMESTAMP"));
					ticketTeam.setModifiedDate(resultSet.getDate("CBP_UPDATED_TIMESTAMP"));
					return ticketTeam;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ticketTeamList;
	}

	public void updateTicketTeam(TicketTeam ticketTeam) {
		System.out.println("update" + ticketTeam.getTeamName());
		String UPDATE_QUERY = "UPDATE CBP_TKT_TEAM set TEAM_NAME = ?, CBP_UPDATED_TIMESTAMP = ? where TEAM_ID = ?";
		Object[] a = new Object[] { ticketTeam.getTeamName(), ticketTeam.getModifiedDate(), ticketTeam.getTeamId() };
		try {
			jdbcTemplate.update(UPDATE_QUERY, a);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteTicketTeam(TicketTeam ticketTeam) {
		String DELETE_QUERY = "delete from CBP_TKT_TEAM where TEAM_ID =" + ticketTeam.getTeamId();
		jdbcTemplate.execute(DELETE_QUERY);
	}

	// TicketResourceType Query

	public void createTicketResType(TicketResourceType ticketResourceType) {
		int id = getMaxId("RES_TYPE_ID", "CBP_TKT_RES_TYPE") + 1;
		ticketResourceType.setResTypeId(id);
		String QUERY = "INSERT into CBP_TKT_RES_TYPE(RES_TYPE_ID,RES_TYPE_NAME,CBP_CREATED_TIMESTAMP,CBP_UPDATED_TIMESTAMP) values(? , ? , ? , ?)";
		Object[] a = new Object[] { ticketResourceType.getResTypeId(), ticketResourceType.getResTypeName(),
				ticketResourceType.getCreatedDate(), ticketResourceType.getModifiedDate() };
		jdbcTemplate.update(QUERY, a);
	}

	public List<TicketResourceType> getTicketResorceTypeList() {
		List<TicketResourceType> ticketResTypeList = new ArrayList<TicketResourceType>();
		String QUERY = "SELECT * FROM  CBP_TKT_RES_TYPE";
		ticketResTypeList = jdbcTemplate.query(QUERY, new RowMapper<TicketResourceType>() {

			public TicketResourceType mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				TicketResourceType ticketResType = new TicketResourceType();
				ticketResType.setResTypeId(resultSet.getInt("RES_TYPE_ID"));
				ticketResType.setResTypeName(resultSet.getString("RES_TYPE_NAME"));
				ticketResType.setCreatedDate(resultSet.getDate("CBP_CREATED_TIMESTAMP"));
				ticketResType.setModifiedDate(resultSet.getDate("CBP_UPDATED_TIMESTAMP"));
				return ticketResType;
			}
		});

		return ticketResTypeList;
	}

	public void updateTicketResourceType(TicketResourceType ticketResourceType) {
		String UPDATE_QUERY = "UPDATE CBP_TKT_RES_TYPE set RES_TYPE_NAME = ?, CBP_UPDATED_TIMESTAMP = ? where RES_TYPE_ID = ?";
		Object[] a = new Object[] { ticketResourceType.getResTypeName(), ticketResourceType.getModifiedDate(),
				ticketResourceType.getResTypeId() };
		jdbcTemplate.update(UPDATE_QUERY, a);
	}

	public void deleteTicketResourceType(TicketResourceType ticketResType) {
		String DELETE_QUERY = "delete from CBP_TKT_RES_TYPE where RES_TYPE_ID =" + ticketResType.getResTypeId();
		jdbcTemplate.execute(DELETE_QUERY);
	}

	// TicketResource

	public void createTicketRes(TicketResource ticketResource) {
		int id = getMaxId("RES_TYPE_ID", "CBP_TKT_RES_TYPE") + 1;
		ticketResource.setResTypeId(id);
		String QUERY = "INSERT into CBP_TKT_RES(RES_ID,RES_NAME,RES_TYPE_ID,CBP_CREATED_TIMESTAMP,CBP_UPDATED_TIMESTAMP) values(? , ? , ? , ? , ?)";
		Object[] a = new Object[] { ticketResource.getResId(), ticketResource.getResName(),
				ticketResource.getResTypeId(), ticketResource.getCreatedDate(), ticketResource.getModifiedDate() };
		jdbcTemplate.update(QUERY, a);

	}

	public List<TicketResource> getTicketResourceList() {
		List<TicketResource> ticketResList = new ArrayList<TicketResource>();
		String QUERY = "SELECT * FROM  CBP_TKT_RES";
		ticketResList = jdbcTemplate.query(QUERY, new RowMapper<TicketResource>() {
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

	public void updateTicketResource(TicketResource ticketResource) {

		String UPDATE_QUERY = "UPDATE CBP_TKT_RES set RES_NAME = ?,RES_TYPE_ID = ?, CBP_UPDATED_TIMESTAMP = ? where RES_ID = ?";
		Object[] a = new Object[] { ticketResource.getResName(), ticketResource.getResTypeId(),
				ticketResource.getModifiedDate(), ticketResource.getResId() };
		jdbcTemplate.update(UPDATE_QUERY, a);
	}

	public void deleteTicketResource(TicketResource ticketResource) {
		String DELETE_QUERY = "delete from CBP_TKT_RES where RES_ID =" + ticketResource.getResId();
		jdbcTemplate.execute(DELETE_QUERY);

	}

	// TicketEnvType Querys

	public void createTicketEnvType(TicketEnvType ticketEnvType) {
		int id = getMaxId("ENV_TYPE_ID", "CBP_TKT_ENV_TYPE") + 1;
		ticketEnvType.setEnvTypeId(id);
		String QUERY = "INSERT into CBP_TKT_ENV_TYPE(ENV_TYPE_ID,ENV_TYPE_NAME,CBP_CREATED_TIMESTAMP,CBP_UPDATED_TIMESTAMP) values(? , ? , ? , ?)";
		Object[] a = new Object[] { ticketEnvType.getEnvTypeId(), ticketEnvType.getEnvTypeName(),
				ticketEnvType.getCreatedDate(), ticketEnvType.getModifiedDate() };
		jdbcTemplate.update(QUERY, a);
	}

	public List<TicketEnvType> getTicketEnvType() {
		List<TicketEnvType> ticketEnvTypeList = new ArrayList<TicketEnvType>();
		String QUERY = "SELECT * FROM  CBP_TKT_ENV_TYPE";
		ticketEnvTypeList = jdbcTemplate.query(QUERY, new RowMapper<TicketEnvType>() {
			public TicketEnvType mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				TicketEnvType ticketEnvType = new TicketEnvType();
				ticketEnvType.setEnvTypeId(resultSet.getInt("ENV_TYPE_ID"));
				ticketEnvType.setEnvTypeName(resultSet.getString("ENV_TYPE_NAME"));
				ticketEnvType.setCreatedDate(resultSet.getDate("CBP_CREATED_TIMESTAMP"));
				ticketEnvType.setModifiedDate(resultSet.getDate("CBP_UPDATED_TIMESTAMP"));
				return ticketEnvType;
			}
		});

		return ticketEnvTypeList;
	}

	public void updateTicketEnvType(TicketEnvType ticketEnvType) {

		String UPDATE_QUERY = "UPDATE CBP_TKT_ENV_TYPE set ENV_TYPE_NAME = ?, CBP_UPDATED_TIMESTAMP = ? where ENV_TYPE_ID = ?";
		Object[] a = new Object[] { ticketEnvType.getEnvTypeName(), ticketEnvType.getModifiedDate(),
				ticketEnvType.getEnvTypeId() };
		jdbcTemplate.update(UPDATE_QUERY, a);
	}

	public void deleteTicketEnvType(TicketEnvType ticketEnvType) {

		String DELETE_QUERY = "delete from CBP_TKT_ENV_TYPE where ENV_TYPE_ID =" + ticketEnvType.getEnvTypeId();
		jdbcTemplate.execute(DELETE_QUERY);

	}

	public List<TicketEnvType> getEnvTypes(TicketEnvResTeamApp ticketEnvResTeamApp) {
		String QUERY = "SELECT * FROM CBP_TKT_ENV_TYPE WHERE ENV_TYPE_ID IN (SELECT ENV_TYPE_ID FROM CBP_TKT_TEAM_RES_ENV_APP where TEAM_ID= ? AND APP_ID= ?)";

		List<TicketEnvType> ticketEnvTypeList = new ArrayList<TicketEnvType>();

		Object[] a = new Object[] { ticketEnvResTeamApp.getTeamId(), ticketEnvResTeamApp.getAppId() };

		ticketEnvTypeList = jdbcTemplate.query(QUERY, a, new RowMapper<TicketEnvType>() {

			public TicketEnvType mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				TicketEnvType ticketEnvType = new TicketEnvType();
				ticketEnvType.setEnvTypeId(resultSet.getInt("ENV_TYPE_ID"));
				ticketEnvType.setEnvTypeName(resultSet.getString("ENV_TYPE_NAME"));
				ticketEnvType.setCreatedDate(resultSet.getDate("CBP_CREATED_TIMESTAMP"));
				ticketEnvType.setModifiedDate(resultSet.getDate("CBP_UPDATED_TIMESTAMP"));
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
		String QUERY = "SELECT * FROM CBP_TKT_RES WHERE RES_ID IN (SELECT RES_ID FROM CBP_TKT_TEAM_RES_ENV_APP where TEAM_ID= ? AND APP_ID= ? AND ENV_TYPE_ID= ?)";

		List<TicketResource> ticketResList = new ArrayList<TicketResource>();
		Object[] a = new Object[] { ticketEnvResTeamApp.getTeamId(), ticketEnvResTeamApp.getAppId(),
				ticketEnvResTeamApp.getEnvTypeId() };

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
				TicketResCredentials.setIs_encrypted(resultSet.getString("IS_ENCRYPTED"));
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
}
