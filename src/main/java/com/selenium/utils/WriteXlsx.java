package com.selenium.utils;

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class WriteXlsx {
	
	public boolean writeImgs(String imgPath,String outPath,String sheetName) throws Exception {

		
		// create xlsx
		Workbook wb = new XSSFWorkbook(new FileInputStream(outPath));

		// create sheet
		Sheet sheet1 = (Sheet) wb.createSheet(sheetName);
		//HSSFClientAnchor imgAnchor = new HSSFClientAnchor(0, 0,255,255);
		
		sheet1.setZoom(3,4);
		OutputStream stream = new FileOutputStream(outPath);

		wb.write(stream);

		stream.close();
		return true;
	}
	
	public static int getAnchorX(int px, int colWidth) {
		return (int) Math.round(((double) 701 * 16000.0 / 301)
				* ((double) 1 / colWidth) * px);
	}

	public static int getAnchorY(int px, int rowHeight) {
		return (int) Math.round(((double) 144 * 8000 / 301)
				* ((double) 1 / rowHeight) * px);
	}

	public static int getRowHeight(int px) {
		return (int) Math.round(((double) 4480 / 300) * px);
	}

	public static int getColWidth(int px) {
		return (int) Math.round(((double) 10971 / 300) * px);
	}
}
