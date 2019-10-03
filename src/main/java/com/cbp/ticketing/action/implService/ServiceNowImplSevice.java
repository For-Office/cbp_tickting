package com.cbp.ticketing.action.implService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cbp.ticketing.dao.daoService.TicketingMetaDataService;
import com.cbp.ticketing.model.DownloadAttachement;
import com.cbp.ticketing.model.ServicenowData;


@Service
public class ServiceNowImplSevice {
	@Autowired
	TicketingMetaDataService ticketingMetaDataService;
	
	
	
	@Value("${servicenow.updateurl}")
	private String UPDATE_URL;

	@Value("${servicenow.geturl}")
	private String GET_URL;

	@Value("${servicenow.downloadattachement}")
	private String DOWNLOAD_ATTACHEMENT;

	@Value("${servicenow.createurl}")
	private String CREATE_URL;

	@Value("${servicenow.accept}")
	private String ACCEPT;

	@Value("${servicenow.contenttype}")
	private String CONTENT_TYPE;

	@Value("${servicenow.authorization}")
	private String AUTHORIZATION;

	public ServicenowData ServicenowUpdate(String incidentNumber, String jsonInput) throws IOException, JSONException {

		ServicenowData servicenowData1 = new ServicenowData();
		StringBuffer response = new StringBuffer();
		// final String USER_AGENT = "Mozilla/5.0";
		ServicenowData ServicenowData = getServicenowDetails(incidentNumber);
		
		/* String postData = "{\n" + "\n" + "\n" +
		 * "\"short_description\" : \"DOINGkjjjjjjjjj\", \n" + "\n" + "\n" +
		 * "\"test\" : \"test2FFDF\",\n" + "\"state\" : \"3\"\n" + "\n" + "\n" + "} ";*/
		  String postData = jsonInput;
		System.out.println("postData:::::::::" + postData);
		URL url = new URL(UPDATE_URL + ServicenowData.getSys_id());
		// URL url = new URL("http://localhost:8088/desctable");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("PUT");
		// conn.setRequestProperty("User-Agent", USER_AGENT);
		conn.setRequestProperty("Authorization", AUTHORIZATION);
		conn.setDoOutput(true);
		OutputStream os = conn.getOutputStream();
		os.write(postData.getBytes("utf-8"));
		os.flush();
		os.close();
		int responseCode = conn.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
				servicenowData1 = convertJsonfornew(response.toString());
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("POST request not worked");
		}

		return servicenowData1;

	}
	
	
	public ServicenowData ServicenowCreate(String jsonInput) throws IOException, JSONException {

		StringBuffer response = new StringBuffer();

		
		/* String postData = "{\n" + "\n" + "\n" +
		 * "\"short_description\" : \"DOINGkjjjjjjjjj\", \n" + "\n" + "\n" +
		 * "\"test\" : \"test2FFDF\",\n" + "\"state\" : \"3\"\n" + "\n" + "\n" + "} ";*/
		  String postData = jsonInput;
		 String userid="{\r\n" + 
		 		"'caller_id': 'System Administrator'\r\n" + 
		 		"}";
		System.out.println("postData:::::::::" + postData);
		URL url = new URL(CREATE_URL);
		// URL url = new URL("http://localhost:8088/desctable");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Accept", ACCEPT);
		conn.setRequestProperty("Content-Type", CONTENT_TYPE);
		// conn.setRequestProperty("User-Agent", USER_AGENT);
		conn.setRequestProperty("Authorization", AUTHORIZATION);
		
		  conn.setDoOutput(true); OutputStream os = conn.getOutputStream();
		  os.write(userid.getBytes("utf-8")); os.flush(); os.close();
		 
		int responseCode = conn.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_CREATED) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
				System.out.println(":::::::" + inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("POST request not worked");
		}

		ServicenowData servicenowData = convertJsonfornew(response.toString());
		UploadFileServicenow(servicenowData.getNumber());
		//servicenowData = ServicenowUpdate(servicenowData.getNumber(), postData);

		return servicenowData;

	}
	public List<String> UploadFileServicenow(String incidentNumber) throws IOException, JSONException {
		System.out.println("uploadfileServicenow");
		String charset = "UTF-8";
		
		
		
		File[] files = new File("C:\\Users\\gantim1\\Desktop\\Bhaskar\\sql\\").listFiles();
		//If this pathname does not denote a directory, then listFiles() returns null. 
		List<String> response = null;
		for (File file : files) {
		    if (file.isFile()) {
		       
		    
		//String textFileName = "C:\\Users\\gantim1\\Desktop\\Bhaskar\\StatementTable.java";
		File uploadFile1 = new File(file.getAbsolutePath());
		ServicenowData servicenowData = getServicenowDetails(incidentNumber);
		

		//String requestURL = "https://dev53134.service-now.com/api/now/attachment/file?table_name=incident&table_sys_id=5417fc274f132300f6a7f3117310c741&file_name=StatementTable.java";
		String requestURL = "https://dev53134.service-now.com/api/now/attachment/file?table_name=incident&table_sys_id="+servicenowData.getSys_id()+"&file_name="+uploadFile1.getName();
		
		
		try {
			UploadFileServicenowImplService multipart = new UploadFileServicenowImplService(requestURL, charset, AUTHORIZATION);
			multipart.addFilePart("fileUpload", uploadFile1);
			response = multipart.finish();
			
			System.out.println("SERVER REPLIED:");

			for (String line : response) {
				file.delete();
				System.out.println(line);
				
			}
		} catch (IOException ex) {
			System.err.println(ex);
		}
		    }
		}
		return response;
	}

	public ServicenowData convertJsonfornew(String arrayjson) {

		ServicenowData servicenowData = new ServicenowData();

		JSONObject servicenow;
		try {
			servicenow = (new JSONObject(arrayjson)).getJSONObject("result");

			servicenowData.setSys_id(servicenow.getString("sys_id"));
			servicenowData.setNumber(servicenow.getString("number"));
			servicenowData.setCategory(servicenow.getString("category"));
			servicenowData.setSubcategory(servicenow.getString("subcategory"));
			servicenowData.setState(getStatus(servicenow.getInt("state")));
			servicenowData.setImpact(getImpact(servicenow.getInt("impact")));
			servicenowData.setUrgency(servicenow.getInt("urgency"));
			servicenowData.setPriority(servicenow.getInt("priority"));
			servicenowData.setIncident_state(servicenow.getInt("incident_state"));
			servicenowData.setSeverity(servicenow.getInt("severity"));
			servicenowData.setShort_description(servicenow.getString("short_description"));
			servicenowData.setDescription(servicenow.getString("description"));
			servicenowData.setComments(servicenow.getString("comments"));
			servicenowData.setApproval(servicenow.getString("approval"));
			servicenowData.setReopen_count(servicenow.getInt("reopen_count"));
			servicenowData.setEscalation(servicenow.getInt("escalation"));
			servicenowData.setSys_updated_by(servicenow.getString("sys_updated_by"));
			servicenowData.setSys_created_on(servicenow.getString("sys_created_on"));
			servicenowData.setSys_created_by(servicenow.getString("sys_created_by"));
			servicenowData.setActive(servicenow.getString("active"));

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return servicenowData;
	}
	
	public ServicenowData getServicenowDetails(String incidentNumber) throws IOException, JSONException {

		String arrayjson = null;
		ServicenowData servicenowData = new ServicenowData();
		try {

			URL url = new URL(GET_URL + incidentNumber);
			// URL url = new URL("http://localhost:8088/desctable");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", ACCEPT);
			conn.setRequestProperty("Content-Type", CONTENT_TYPE);
			// conn.setRequestProperty("Authorization", "<your base64 encoded
			// authorization>");
			conn.setRequestProperty("Authorization", AUTHORIZATION);

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			// System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				arrayjson = output;
				System.out.println("json" + arrayjson);

				servicenowData = convertJson(arrayjson);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

		return servicenowData;

	}
	public List<DownloadAttachement> DownloadfileServicenow(String incidentNumber) throws IOException, JSONException {

		String arrayjson = null;
		List<DownloadAttachement> downloadAttachementlst = new ArrayList<DownloadAttachement>();
		try {
			ServicenowData servicenowData = getServicenowDetails(incidentNumber);
			URL url = new URL(DOWNLOAD_ATTACHEMENT + servicenowData.getSys_id());
			// URL url = new URL("http://localhost:8088/desctable");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", ACCEPT);
			conn.setRequestProperty("Content-Type", CONTENT_TYPE);
			// conn.setRequestProperty("Authorization", "<your base64 encoded
			// authorization>");
			conn.setRequestProperty("Authorization", AUTHORIZATION);

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			// System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				arrayjson = output;
				System.out.println("json" + arrayjson);

			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

		downloadAttachementlst = convertAttachementJson(arrayjson);
		for (DownloadAttachement downloadAttachement : downloadAttachementlst) {

			downloadFile(downloadAttachement.getDownload_link(), "C:/Users/gantim1/Desktop/Bhaskar/sql/",
			downloadAttachement.getFile_name());
		}
		return downloadAttachementlst;

	}
	public void downloadFile(String fileURL, String saveDir, String filename) throws IOException {
		int BUFFER_SIZE = 4096;
		URL url = new URL(fileURL);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestProperty("Authorization", AUTHORIZATION);
		int responseCode = httpConn.getResponseCode();

		// always check HTTP response code first
		if (responseCode == HttpURLConnection.HTTP_OK) {
			String fileName = filename;
			System.out.println("fileName = " + fileName);

			// opens input stream from the HTTP connection
			InputStream inputStream = httpConn.getInputStream();
			String saveFilePath = saveDir + File.separator + fileName;

			// opens an output stream to save into file
			FileOutputStream outputStream = new FileOutputStream(saveFilePath);

			int bytesRead = -1;
			byte[] buffer = new byte[BUFFER_SIZE];
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			outputStream.close();
			inputStream.close();

			System.out.println("File downloaded");
		} else {
			System.out.println("No file to download. Server replied HTTP code: " + responseCode);
		}
		httpConn.disconnect();

	}
	public ServicenowData convertJson(String arrayjson) {
		ServicenowData servicenowData = new ServicenowData();

		JSONObject obj;
		try {
			obj = new JSONObject(arrayjson);

			final JSONArray geodata = obj.getJSONArray("result");
			final int n = geodata.length();
			for (int i = 0; i < n; ++i) {
				final JSONObject servicenow = geodata.getJSONObject(i);
				servicenowData.setSys_id(servicenow.getString("sys_id"));
				servicenowData.setNumber(servicenow.getString("number"));
				servicenowData.setCategory(servicenow.getString("category"));
				servicenowData.setSubcategory(servicenow.getString("subcategory"));
				servicenowData.setState(getStatus(servicenow.getInt("state")));
				servicenowData.setImpact(getImpact(servicenow.getInt("impact")));
				servicenowData.setUrgency(servicenow.getInt("urgency"));
				servicenowData.setPriority(servicenow.getInt("priority"));
				servicenowData.setIncident_state(servicenow.getInt("incident_state"));
				servicenowData.setSeverity(servicenow.getInt("severity"));
				servicenowData.setShort_description(servicenow.getString("short_description"));
				servicenowData.setDescription(servicenow.getString("description"));
				servicenowData.setComments(servicenow.getString("comments"));
				servicenowData.setApproval(servicenow.getString("approval"));
				servicenowData.setReopen_count(servicenow.getInt("reopen_count"));
				servicenowData.setEscalation(servicenow.getInt("escalation"));
				servicenowData.setSys_updated_by(servicenow.getString("sys_updated_by"));
				servicenowData.setSys_created_on(servicenow.getString("sys_created_on"));
				servicenowData.setSys_created_by(servicenow.getString("sys_created_by"));
				servicenowData.setActive(servicenow.getString("active"));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return servicenowData;
	}
	public String getStatus(int state) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "New");
		map.put(2, "In Progress");
		map.put(3, "On Hold");
		map.put(6, "Resolved");
		map.put(7, "Closed");
		map.put(8, "Canceled");
		return map.get(state);
	}

	public String getImpact(int impact) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "1 - High");
		map.put(2, "2 - Medium");
		map.put(3, "3 - Low");
		return map.get(impact);
	}
	public List<DownloadAttachement> convertAttachementJson(String arrayjson) {
		List<DownloadAttachement> downloadAttachementlist = new ArrayList<DownloadAttachement>();

		JSONObject obj;
		try {
			obj = new JSONObject(arrayjson);

			final JSONArray geodata = obj.getJSONArray("result");
			final int n = geodata.length();
			for (int i = 0; i < n; ++i) {

				final JSONObject servicenow = geodata.getJSONObject(i);
				DownloadAttachement downloadAttachement = new DownloadAttachement();

				downloadAttachement.setIncident_sysid(servicenow.getString("table_sys_id"));
				downloadAttachement.setFile_name(servicenow.getString("file_name"));
				downloadAttachement.setFile_sys_id(servicenow.getString("sys_id"));
				downloadAttachement.setDownload_link(servicenow.getString("download_link"));
				downloadAttachement.setContent_type(servicenow.getString("content_type"));
				downloadAttachement.setSys_created_on(servicenow.getString("sys_created_on"));
				downloadAttachement.setSize_compressed(servicenow.getInt("size_compressed"));
				downloadAttachementlist.add(downloadAttachement);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return downloadAttachementlist;
	}
	 public String executeSqlScript(String incidentNumber, String fullPath, String fileName) throws IOException, JSONException{
		return ticketingMetaDataService.executeSqlScript(incidentNumber, fullPath, fileName);
		 
	 }
}