package com.design.cms.web.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.common.collect.Lists;

public class ImportAndExportUtil {
	
	public static void export(String fileName,String sheetName,LinkedHashMap<String,String> head,List<Map<String,Object>> data){
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(sheetName);
		XSSFRow row = sheet.createRow((int) 0);
		XSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		//设置表头
		XSSFCell cell = null;
		// excel头
		Iterator<String> it = head.keySet().iterator();
		String key = null;
		List<String> headList = Lists.newArrayList();
		int n = 0;
		while(it.hasNext()){
			key = String.valueOf(it.next());
			headList.add(key);
			cell = row.createCell(n++);
			cell.setCellValue(head.get(key));
			cell.setCellStyle(style);
		}
		
		Map<String,Object> tmp = null;
		for (int i = 0; i < data.size(); i++) {
			int j=0;
			row = sheet.createRow((int) i + 1);
			tmp = data.get(i);
			for(String headKey:headList){
				insertCell(row, j++,tmp.get(headKey));
			}
		}
		sheet.autoSizeColumn(1);
		try {
			wb.write(out);
			byte[] bytes =  out.toByteArray();
			HttpServletResponse response =	Evn.getResponse();
			response.setContentType("application/vnd.ms-excel");
			response.setContentLength(bytes.length);
			response.setHeader("Content-Disposition", "attachment;filename="+ fileName+".xlsx");
			response.getOutputStream().write(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private static void insertCell(XSSFRow row,int i,Object object){
		if(object==null){
			row.createCell(i).setCellValue("");
		}else{
			row.createCell(i).setCellValue(object.toString());
		}
	}
}
