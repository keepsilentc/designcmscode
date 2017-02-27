package com.design.cms.common.utils;

import org.apache.poi.ss.usermodel.Cell;


public class PoiUtil {
	
	public static Object getCellData(Cell cell) {
	    if(cell == null) {
	        return null;
	    }
	    switch (cell.getCellType()) {
	    case Cell.CELL_TYPE_STRING:
	        return cell.getRichStringCellValue().getString();
	    case Cell.CELL_TYPE_NUMERIC:
	        return cell.getNumericCellValue();
	    case Cell.CELL_TYPE_BOOLEAN:
	        return cell.getBooleanCellValue();
	    default:
	        return null;
	    }
	} 
}
