package com.cbp.ticketing.model;


import java.util.Date;

/**
 * Created by mahesh ganti.
 */

public class DownloadAttachement  {
	String file_name;
	String file_sys_id;
	String incident_sysid;
	String download_link;
	String content_type;
	String sys_created_on;
	int size_compressed;
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_sys_id() {
		return file_sys_id;
	}
	public void setFile_sys_id(String file_sys_id) {
		this.file_sys_id = file_sys_id;
	}
	public String getIncident_sysid() {
		return incident_sysid;
	}
	public void setIncident_sysid(String incident_sysid) {
		this.incident_sysid = incident_sysid;
	}
	public String getDownload_link() {
		return download_link;
	}
	public void setDownload_link(String download_link) {
		this.download_link = download_link;
	}
	public String getContent_type() {
		return content_type;
	}
	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}
	public String getSys_created_on() {
		return sys_created_on;
	}
	public void setSys_created_on(String sys_created_on) {
		this.sys_created_on = sys_created_on;
	}
	public int getSize_compressed() {
		return size_compressed;
	}
	public void setSize_compressed(int size_compressed) {
		this.size_compressed = size_compressed;
	}
	
}