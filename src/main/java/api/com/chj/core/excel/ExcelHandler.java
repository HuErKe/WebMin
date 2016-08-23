package api.com.chj.core.excel;

import org.apache.poi.ss.usermodel.Workbook;

public interface ExcelHandler{

	void handle(Workbook workbook) throws Exception;
}
