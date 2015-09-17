package com.selenium.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.selenium.consts.BrowerType;
import com.selenium.utils.SettingsUtil;
import com.selenium.utils.SlmTstUtil;

public class CreateXls {
	private Workbook wb;
	private String xlsPath;
	private static final short X_COl = 1;

	public CreateXls(String outPath) {
		try {
			SlmTstUtil.createDir(outPath);
			xlsPath = outPath + "\\" + SettingsUtil.getTestName()
					+ SettingsUtil.XLS_FILE;
			wb = new HSSFWorkbook();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addImgs(List<BrowerType> list) throws FileNotFoundException,
			IOException {
		for (BrowerType bt : list)
			writeImgs(SettingsUtil.getImgPath(), bt.getCode());
	}

	private boolean writeImgs(String imgPath, String sheetName)
			throws FileNotFoundException, IOException {

		Sheet sheet1 = wb.createSheet(sheetName);
		HSSFPatriarch patriarch = (HSSFPatriarch) sheet1
				.createDrawingPatriarch();
		List<File> pngs = getPngs(imgPath + "\\" + sheetName);
		int y = 1;

		for (File png : pngs) {
			y = drawImg(patriarch, y, ImageIO.read(new FileInputStream(png)));
		}
		// sheet1.setZoom(3, 4);
		OutputStream stream = new FileOutputStream(xlsPath);
		wb.write(stream);
		stream.close();
		return true;
	}

	private int drawImg(HSSFPatriarch patriarch, int y, BufferedImage img)
			throws IOException {
		ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
		int height = img.getHeight();
		int width = img.getWidth();
		int secondY = getAnchorY(y, height);
		HSSFClientAnchor imgAnchor = new HSSFClientAnchor(0, 0, 255, 255,
				X_COl, y, getAnchorX(width), getAnchorY(y, height));
		ImageIO.write(img, "png", byteArrayOut);
		patriarch.createPicture(imgAnchor, wb.addPicture(
				byteArrayOut.toByteArray(), XSSFWorkbook.PICTURE_TYPE_PNG));
		return secondY + 2;
	}

	private short getAnchorX(int width) {
		return (short) (X_COl + width / 72);
	}

	private int getAnchorY(int y, int height) {
		return y + height / 18;
	}

	private List<File> getPngs(String filePath) {
		List<File> pngs = new ArrayList<File>();
		File file = new File(filePath);
		File[] childFiles = file.listFiles();
		if (childFiles == null) {
			return pngs;
		}
		for (File childFile : childFiles) {
			if (!childFile.isDirectory()) {
				String childFilePath = childFile.getPath();
				if (childFilePath.endsWith(".png")) {
					pngs.add(childFile);
				}
			}
		}
		return pngs;
	}
}
