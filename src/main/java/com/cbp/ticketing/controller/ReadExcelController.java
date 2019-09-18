package com.cbp.ticketing.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbp.ticketing.model.Application;
import com.cbp.ticketing.model.Environment;
import com.cbp.ticketing.model.ExcelData;
import com.cbp.ticketing.model.Resource;
import com.cbp.ticketing.model.Team;

@RestController
public class ReadExcelController {
	
	ArrayList<String> teams=new ArrayList<>();
	ArrayList<String> resources=new ArrayList<>();
	ArrayList<String> environments=new ArrayList<>();
	ArrayList<String> applications=new ArrayList<>();
	
	@GetMapping("/readExcel")
	public HashMap readExcel() throws IOException {
//
//		ArrayList<String> topHeaders=new ArrayList<String>();
//		topHeaders.add("Team");
//		topHeaders.add("Resource");
//		topHeaders.add("Environment");
//		topHeaders.add("Application");
		
		FileInputStream excelFile = new FileInputStream(new File("C:\\Users\\Undraleu3\\Downloads\\Bulk.xlsx"));
		// List<Test> tempStudentList = new ArrayList<Test>();
		XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
		XSSFSheet worksheet = workbook.getSheetAt(0);
		HashMap<Integer,String> header=new HashMap();
		HashMap totalData=new HashMap();
		XSSFRow row;
		XSSFCell cell;
		Iterator<Row> rows = worksheet.rowIterator();
		ExcelData excelData=new ExcelData();
		String value = null;
		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			Iterator<Cell> cells = row.cellIterator();
			while (cells.hasNext()) {
				cell = (XSSFCell) cells.next();
				if (cell.getRowIndex() != 0) {
					getValues(cell,header,totalData);
				}
				else {
					if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
						 value=cell.getStringCellValue();
					} else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
						value=cell.getNumericCellValue()+"";
						}
					if(value!=null) {
								header.put(cell.getColumnIndex(), value);
							}
				}
			}
		}
		System.out.println(header);
		return totalData;
	}


	private void getValues(XSSFCell cell, HashMap<Integer,String> header, HashMap totalData) {
		
		String value = null;
		if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
			 value=cell.getStringCellValue();
		} else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
			value=cell.getNumericCellValue()+"";
			}
		
		for(Map.Entry<Integer,String> entry : header.entrySet()) {
			if(cell.getColumnIndex()==entry.getKey())
			totalData.put(entry.getValue(), value);
		}
		
		System.out.println(totalData);
		
	}
	
	
}
