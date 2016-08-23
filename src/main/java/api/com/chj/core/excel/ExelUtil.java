package api.com.chj.core.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import api.com.chj.core.StringUtil;

/**
 * 提供excel的读写功能
 * @author Chen Huajun
 * 测试通过,功能完整
 */
public class ExelUtil {

	private static final Logger logger = Logger.getLogger(ExelUtil.class);
	
	private static final String EXCEL_XLS = "xls";
	private static final String EXCEL_XLSX = "xlsx";
	
	public static void doService(String fileName,ExcelHandler handler) throws Exception{
		Workbook workbook = getWorkBook(fileName);
		handler.handle(workbook);
		workbook.close();
	}
	
	public static void flushWorkBook(Workbook workbook,String fileName) throws Exception{
		workbook.write(new FileOutputStream(fileName));
	}
	
	private static Workbook getWorkBook(String fileName) throws Exception{
		if(!fileName.endsWith(EXCEL_XLS) && 
		   !fileName.endsWith(EXCEL_XLSX)){
			logger.info("file name ["+fileName+"]");
			throw new IllegalArgumentException("unsurppot file formate");
		}
		
		Workbook workBook = null;
		boolean shouldCreate = false;
		File file = new File(fileName);
		if(!file.exists()){
			logger.info("文件不存在,即将创建新文件");
//			file.createNewFile();
			int index = fileName.lastIndexOf(EXCEL_XLS); 
			index = index >= 0 ? index : fileName.lastIndexOf(EXCEL_XLSX); 
			File.createTempFile(fileName.substring(0,index),fileName.substring(index));
			shouldCreate = false;
		}
		if(StringUtil.emptyString(fileName)){
			throw new IllegalArgumentException("filename cannot be null");
		} 
		
		if(fileName.endsWith(EXCEL_XLS)){
			workBook = new HSSFWorkbook(new FileInputStream(file));
		}
		else{ //(fileName.endsWith(EXCEL_XLSX))
			workBook = new XSSFWorkbook(new FileInputStream(file));			
		}
		if(shouldCreate){
			flushWorkBook(workBook,fileName);
		}
		return workBook;
	}
}
