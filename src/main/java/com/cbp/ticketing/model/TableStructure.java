package com.cbp.ticketing.model;

/**
 * Created by rajeevkumarsingh on 21/11/17.
 */

public class TableStructure  {
	
   // private Long id;
    private String column_name;
    private String datatype;
    private int size;
    private int precision;
    
	public int getPrecision() {
		return precision;
	}
	public void setPrecision(int precision) {
		this.precision = precision;
	}
	public String getColumn_name() {
		return column_name;
	}
	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}
	public String getDatatype() {
		return datatype;
	}
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
    
    

   
    }
