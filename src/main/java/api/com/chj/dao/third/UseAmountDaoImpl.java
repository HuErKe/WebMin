package api.com.chj.dao.third;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import api.com.chj.core.CollectionUtil;
import api.com.chj.core.excel.ExcelHandler;
import api.com.chj.core.excel.ExelUtil;
import api.com.chj.po.third.UsageAmountPerDayPerIfcd;
import api.com.chj.po.third.UsageAmountSingleIfcdMultiDaysChart;
import api.com.chj.po.third.UsageAmountSingleIfcdSingleDayChart;
import api.com.chj.service.third.geo.UsageAmountHelperGeo;

public class UseAmountDaoImpl implements UseAmountDao {

	@Override
	public void writeUsageOfEachIfcdEachItemChart(String filename,
			UsageAmountHelperGeo uaHelper) throws Exception{ 
		
		final List<UsageAmountPerDayPerIfcd> finalLs = uaHelper.getLsPerDayPerIfcd();
		final List<UsageAmountSingleIfcdSingleDayChart> lsDay = uaHelper.getLsEachIfcdEachDay();
		final List<UsageAmountSingleIfcdMultiDaysChart> lsMulti = uaHelper.getLsEachIfcdMultiDays();
		if(CollectionUtil.isEmpty(finalLs) && 
		   CollectionUtil.isEmpty(lsDay) &&
		   CollectionUtil.isEmpty(lsMulti)){
			return ;
		}
		
		final String fileName = filename;		
		ExelUtil.doService(fileName, new ExcelHandler() {			
			@Override
			public void handle(Workbook workbook)  throws Exception{ 
				if(null == workbook){
					return ;
				} 
				 
				Sheet sheetTem = null;
				Row row = null;
				Cell cell = null;
				// 明细
				sheetTem = workbook.getSheetAt(0);
				if(null == sheetTem){
					workbook.createSheet();
				} 				
				workbook.setSheetName(0, "接口使用明细");				
				UsageAmountPerDayPerIfcd dataRow = null;
				int cellIndex = -1;
				for(int i=0;i<finalLs.size();i++){
					dataRow = finalLs.get(i);
					row = sheetTem.createRow(i+1);
					cellIndex = 0;
					// ifCode
					cell = row.createCell(cellIndex++, Cell.CELL_TYPE_STRING);
					cell.setCellValue(dataRow.getIfCode());
					// date
					cell = row.createCell(cellIndex++, Cell.CELL_TYPE_STRING);
					cell.setCellValue(dataRow.getDate());
					// queryId
					cell = row.createCell(cellIndex++, Cell.CELL_TYPE_STRING);
					cell.setCellValue(dataRow.getQueryId());
					// hit
					cell = row.createCell(cellIndex++, Cell.CELL_TYPE_STRING);
					cell.setCellValue(dataRow.isHit() ? "命中" : "未命中");
					// rsCode
					cell = row.createCell(cellIndex++, Cell.CELL_TYPE_STRING);
					cell.setCellValue(dataRow.getIfRsCode());
					// rsDes
					cell = row.createCell(cellIndex++, Cell.CELL_TYPE_STRING);
					cell.setCellValue(dataRow.getIfRsDesc());
				}				
				ExelUtil.flushWorkBook(workbook, fileName); 
				 
				// 每天使用量 				
				sheetTem = workbook.getSheetAt(1);
				if(null == sheetTem){
					workbook.createSheet();
				} 				
				workbook.setSheetName(1, "每天使用量");				
				UsageAmountSingleIfcdSingleDayChart dataRow2 = null; 
				for(int i=0;i<lsDay.size();i++){
					dataRow2 = lsDay.get(i);
					row = sheetTem.createRow(i+1);
					cellIndex = 0;
					// date
					cell = row.createCell(cellIndex++, Cell.CELL_TYPE_STRING);
					cell.setCellValue(dataRow2.getDate());
					// ifCode
					cell = row.createCell(cellIndex++, Cell.CELL_TYPE_STRING);
					cell.setCellValue(dataRow2.getIfCods());
					// queryAmount
					cell = row.createCell(cellIndex++, Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(dataRow2.getQueryAmount());
					// hitAmount
					cell = row.createCell(cellIndex++, Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(dataRow2.getHitAmount());
					// unhitAmount
					cell = row.createCell(cellIndex++, Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(dataRow2.getUnhitAmount());
					// hitRate
					cell = row.createCell(cellIndex++, Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(dataRow2.getHitRate());
					// priceUnit
					cell = row.createCell(cellIndex++, Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(dataRow2.getPriceUnit());
					// priceBalance
					cell = row.createCell(cellIndex++, Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(dataRow2.getPriceBalance());
				}
				ExelUtil.flushWorkBook(workbook, fileName); 
				
				
				// 每天使用量
								
				sheetTem = workbook.getSheetAt(2);
				if(null == sheetTem){
					workbook.createSheet();
				} 				
				workbook.setSheetName(2, "当月使用量");				
				UsageAmountSingleIfcdMultiDaysChart dataRow3 = null; 
				for(int i=0;i<lsMulti.size();i++){
					dataRow3 = lsMulti.get(i);
					row = sheetTem.createRow(i+1);
					cellIndex = 0; 
					// ifCode
					cell = row.createCell(cellIndex++, Cell.CELL_TYPE_STRING);
					cell.setCellValue(dataRow3.getIfCods());
					// queryAmount
					cell = row.createCell(cellIndex++, Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(dataRow3.getQueryAmount());
					// hitAmount
					cell = row.createCell(cellIndex++, Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(dataRow3.getHitAmount());
					// unhitAmount
					cell = row.createCell(cellIndex++, Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(dataRow3.getUnhitAmount());
					// hitRate
					cell = row.createCell(cellIndex++, Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(dataRow3.getHitRate());
					// priceUnit
					cell = row.createCell(cellIndex++, Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(dataRow3.getPriceUnit());
					// priceBalance
					cell = row.createCell(cellIndex++, Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(dataRow3.getPriceBalance());
				}
				ExelUtil.flushWorkBook(workbook, fileName); 
				 
			}
		});
	}
}
