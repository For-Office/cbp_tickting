package com.cbp.ticketing.action.implService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UploadFileServicenowImplService {
	private final String boundary;
	   // private static final String LINE_FEED = "\r\n";
	    private HttpURLConnection httpConn;
	    private String charset;
	    private OutputStream outputStream;
	    private PrintWriter writer;
	 
	    
	    public UploadFileServicenowImplService(String requestURL, String charset, String authorization)
	            throws IOException {
	        this.charset = charset;
	         
	        // creates a unique boundary based on time stamp
	        boundary = "===" + System.currentTimeMillis() + "===";
	         
	        URL url = new URL(requestURL);
	        httpConn = (HttpURLConnection) url.openConnection();
	        httpConn.setUseCaches(false);
	        httpConn.setDoOutput(true); // indicates POST method
	        httpConn.setDoInput(true);
	        httpConn.setRequestMethod("POST");
	        httpConn.setRequestProperty("Authorization", authorization);
	        httpConn.setRequestProperty("Content-Type",
	                "multipart/form-data; boundary=" + boundary);
	       
	        outputStream = httpConn.getOutputStream();
	        /*
	       
	        try {
	            JSONObject obj = new JSONObject();
	            obj.put("table_name" , "incident");
	            obj.put("table_sys_id" , "5417fc274f132300f6a7f3117310c741");
	            obj.put("file_name" , "StatementTable.java");
	           
	            outputStream.write(obj.toString().getBytes());
	            outputStream.flush();
	            outputStream.close();
	        } catch (JSONException ex) {
	            ex.printStackTrace();
	        }
	        httpConn.connect();*/
	        writer = new PrintWriter(new OutputStreamWriter(outputStream, charset),
	                true);
	    }
	    public void addFilePart(String fieldName, File uploadFile)
	            throws IOException {
	        String fileName = uploadFile.getName();
	      
	 
	        FileInputStream inputStream = new FileInputStream(uploadFile);
	        byte[] buffer = new byte[4096];
	        int bytesRead = -1;
	        while ((bytesRead = inputStream.read(buffer)) != -1) {
	            outputStream.write(buffer, 0, bytesRead);
	        }
	        outputStream.flush();
	        inputStream.close();
	         
	        writer.flush();    
	    }
	 
	    public List<String> finish() throws IOException {
	        List<String> response = new ArrayList<String>();
	 
	        // checks server's status code first
	        int status = httpConn.getResponseCode();
	        if (status == HttpURLConnection.HTTP_CREATED) {
	            BufferedReader reader = new BufferedReader(new InputStreamReader(
	                    httpConn.getInputStream()));
	            String line = null;
	            while ((line = reader.readLine()) != null) {
	                response.add(line);
	            }
	            reader.close();
	            httpConn.disconnect();
	        } else {
	            throw new IOException("Server returned non-OK status: " + status);
	        }
	 
	        return response;
	    }
	}
