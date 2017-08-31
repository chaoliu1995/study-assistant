package com.chaoliu1995.english.controller;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chaoliu1995.english.base.BaseController;
import com.chaoliu1995.english.entity.shanbay.TabWord;
import com.chaoliu1995.english.service.TabWordService;
import com.chaoliu1995.english.util.Constants;
import com.chaoliu1995.english.util.Pager;

@Controller
@RequestMapping("/excel")
public class ExcelController extends BaseController {

	@Autowired
	private TabWordService tabWordService;

	@RequestMapping("/allWord")
	@ResponseBody
	public void allWord() throws IOException {
		//新建一个Excel文件
		Workbook wb = new XSSFWorkbook();
		//新建一个工作簿sheet
		Sheet wordSheet = wb.createSheet("WordSheet");
		
		//设置列的宽度
		wordSheet.setColumnWidth(0, 100*70);
		wordSheet.setColumnWidth(1, 100*70);
		
		//新建一个字体
		Font font = wb.createFont();
        font.setFontHeightInPoints((short)14);  
        font.setFontName("Consolas");  
        
        //新建一个列的样式
        CellStyle style = wb.createCellStyle();
        //设置样式的字体
        style.setFont(font);
		
		int loopNum = 0;
		//新建行Row和单元格Cell
		Row row = null;
		Cell cell = null;
		row = wordSheet.createRow(loopNum);
		cell = row.createCell(0);
		cell.setCellValue("单词");
		cell = row.createCell(1);
		cell.setCellValue("音标");
		loopNum += 1;

		int total = tabWordService.getCountByCol(new TabWord());
		
		if (total >= 1) {
			
			Pager<TabWord> pager = null;
			
			boolean flag = true;
			
			while (flag) {
				if(pager == null){
					pager = tabWordService.listTabWordForExcel(Pager.DEFAULT_CURRENT_PAGE,Constants.EXCEL_PAGE_SIZE, total);
				}else{
					pager = tabWordService.listTabWordForExcel(pager.getNextPage(),Constants.EXCEL_PAGE_SIZE, total);
				}
				
				for(TabWord word : pager.getRecordList()){
					row = wordSheet.createRow(loopNum);
					//设置当前行单元格的值
					cell = row.createCell(0);
					cell.setCellValue(word.getContent());
					cell.setCellStyle(style);
					cell = row.createCell(1);
					cell.setCellValue(word.getPronunciation());
					cell.setCellStyle(style);
					loopNum += 1;
				}
				
				if (pager.getNextPage() == pager.getCurrentPage()) {
					flag = false;
					break;
				}
				
			}
		}
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "must-revalidate" );
		response.setHeader("Content-Disposition", "attachment; filename=\"" + "word.xlsx" + "\"");
		
		OutputStream filrOut = response.getOutputStream();
		//将Excel文件的内容写入到流中
		wb.write(filrOut);
		wb.close();
		filrOut.flush();
		filrOut.close();
	}

}
