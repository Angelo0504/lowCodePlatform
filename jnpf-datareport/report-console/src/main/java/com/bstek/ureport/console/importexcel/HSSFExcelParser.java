/*******************************************************************************
 * Copyright 2017 Bstek
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.bstek.ureport.console.importexcel;

import com.bstek.ureport.definition.CellStyle;
import com.bstek.ureport.definition.PaperSize;
import com.bstek.ureport.definition.*;
import com.bstek.ureport.definition.value.SimpleValue;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @since 5月27日
 */
public class HSSFExcelParser extends ExcelParser {
	@Override
	public ReportDefinition parse(InputStream inputStream) throws Exception {
		ReportDefinition report=new ReportDefinition();
		List<RowDefinition> rowDefs=new ArrayList<RowDefinition>();
		report.setRows(rowDefs);
		List<ColumnDefinition> columnDefs=new ArrayList<ColumnDefinition>();
		report.setColumns(columnDefs);
		List<CellDefinition> cellDefs=new ArrayList<CellDefinition>();
		report.setCells(cellDefs);
		HSSFWorkbook book=new HSSFWorkbook(inputStream);
		HSSFSheet sheet=book.getSheetAt(0);
		int firstRow=0;
		int rowCount=sheet.getPhysicalNumberOfRows();
		int maxColumnCount=buildMaxColumn(sheet);
		for(int i=firstRow;i<rowCount;i++){
			HSSFRow row=sheet.getRow(i);
			if(row==null){
				RowDefinition rowDef=new RowDefinition();
				rowDef.setHeight(20);
				rowDef.setRowNumber(i+1);
				rowDefs.add(rowDef);
				addBlankCells(cellDefs, i+1, maxColumnCount);
				continue;
			}
			RowDefinition rowDef=new RowDefinition();
			int height=row.getHeight()/20;
			rowDef.setHeight(height);
			rowDef.setRowNumber(i+1);
			rowDefs.add(rowDef);
			for(int j=0;j<maxColumnCount;j++){
				boolean isMergeRegion=isMergedRegion(sheet, i, j);
				if(isMergeRegion){
					continue;
				}
				HSSFCell cell=row.getCell(j);
				if(cell==null){
					CellDefinition cellDef=new CellDefinition();
					cellDef.setValue(new SimpleValue(""));
					cellDef.setRowNumber(i+1);
					cellDef.setColumnNumber(j+1);
					cellDefs.add(cellDef);
					continue;
				}
				Span span=getSpan(sheet, i, j);

				Object value=null;
				CellType cellType=cell.getCellTypeEnum();
				switch(cellType){
					case STRING:
						value=cell.getStringCellValue();
						break;
					case BOOLEAN:
						value=cell.getBooleanCellValue();
						break;
					case NUMERIC:
						value=cell.getNumericCellValue();
						break;
					case FORMULA:
						value=cell.getCellFormula();
						break;
					default:
						value="";
				}
				CellDefinition cellDef=new CellDefinition();
				cellDef.setValue(new SimpleValue(value !=null ? value.toString() : ""));
				cellDef.setRowNumber(i+1);
				cellDef.setColumnNumber(j+1);
				cellDef.setRowSpan(span.getRowSpan());
				cellDef.setColSpan(span.getColSpan());
				cellDef.setCellStyle(buildCellStyle(cell,book));
				cellDefs.add(cellDef);
			}
		}
		for(int i=0;i<maxColumnCount;i++){
			ColumnDefinition col=new ColumnDefinition();
			int width=sheet.getColumnWidth(i);
			col.setWidth(width/37);
			col.setColumnNumber(i+1);
			columnDefs.add(col);
		}
		book.close();
		inputStream.close();
		Paper paper=new Paper();
		paper.setPaperType(PaperType.A4);
		paper.setOrientation(Orientation.portrait);
		paper.setPagingMode(PagingMode.fitpage);
		PaperSize pageSize=PaperType.A4.getPaperSize();
		paper.setWidth(pageSize.getWidth());
		paper.setHeight(paper.getHeight());
		report.setPaper(paper);
		return report;
	}

	private CellStyle buildCellStyle(HSSFCell cell,HSSFWorkbook book){
		CellStyle style=new CellStyle();
		HSSFCellStyle cellStyle=cell.getCellStyle();
		HorizontalAlignment align=cellStyle.getAlignmentEnum();
		if(align.equals(HorizontalAlignment.CENTER)){
			style.setAlign(Alignment.center);
		}else if(align.equals(HorizontalAlignment.RIGHT)){
			style.setAlign(Alignment.right);
		}else{
			style.setAlign(Alignment.left);
		}
		VerticalAlignment valign=cellStyle.getVerticalAlignmentEnum();
		if(valign.equals(VerticalAlignment.BOTTOM)){
			style.setValign(Alignment.bottom);
		}else if(valign.equals(VerticalAlignment.TOP)){
			style.setValign(Alignment.top);
		}else if(valign.equals(VerticalAlignment.CENTER)){
			style.setValign(Alignment.middle);
		}else{
			style.setValign(Alignment.middle);
		}
		HSSFFont font=cellStyle.getFont(book);
		if(font.getBold()){
			style.setBold(true);
		}
		if(font.getItalic()){
			style.setItalic(true);
		}
		if(font.getUnderline()!=Font.U_NONE){
			style.setUnderline(true);
		}
		HSSFColor color=font.getHSSFColor(book);
		if(color!=null){
			short[] rgb=color.getTriplet();
			style.setForecolor(rgb[0]+","+rgb[1]+","+rgb[2]);
		}else{
			style.setForecolor("0,0,0");
		}
		FillPatternType pattern=cellStyle.getFillPatternEnum();
		if(pattern!=null && pattern.equals(FillPatternType.SOLID_FOREGROUND)){
			HSSFColor bgcolor=cellStyle.getFillForegroundColorColor();
			if(bgcolor!=null){
				short[] rgb=bgcolor.getTriplet();
				style.setBgcolor(rgb[0]+","+rgb[1]+","+rgb[2]);
			}
		}
		int fontSize=font.getFontHeight()/20;
		style.setFontSize(fontSize);
		BorderStyle borderStyle=cellStyle.getBorderLeftEnum();
		if(!borderStyle.equals(BorderStyle.NONE)){
			Border border=new Border();
			border.setColor("0,0,0");
			border.setStyle(com.bstek.ureport.definition.BorderStyle.solid);
			border.setWidth(1);
			style.setLeftBorder(border);
		}
		borderStyle=cellStyle.getBorderRightEnum();
		if(!borderStyle.equals(BorderStyle.NONE)){
			Border border=new Border();
			border.setColor("0,0,0");
			border.setStyle(com.bstek.ureport.definition.BorderStyle.solid);
			border.setWidth(1);
			style.setRightBorder(border);
		}
		borderStyle=cellStyle.getBorderTopEnum();
		if(!borderStyle.equals(BorderStyle.NONE)){
			Border border=new Border();
			border.setColor("0,0,0");
			border.setStyle(com.bstek.ureport.definition.BorderStyle.solid);
			border.setWidth(1);
			style.setTopBorder(border);
		}
		borderStyle=cellStyle.getBorderBottomEnum();
		if(!borderStyle.equals(BorderStyle.NONE)){
			Border border=new Border();
			border.setColor("0,0,0");
			border.setStyle(com.bstek.ureport.definition.BorderStyle.solid);
			border.setWidth(1);
			style.setBottomBorder(border);
		}
		return style;
	}


	private Span getSpan(HSSFSheet sheet,int row ,int column){
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress range = sheet.getMergedRegion(i);
			int firstColumn = range.getFirstColumn();
			int lastColumn = range.getLastColumn();
			int firstRow = range.getFirstRow();
			if(row == firstRow && column==firstColumn){
				int lastRow = range.getLastRow();
				int rowSpan=lastRow-firstRow;
				if(rowSpan>0){
					rowSpan++;
				}
				int colSpan=lastColumn-firstColumn;
				if(colSpan>0){
					colSpan++;
				}
				return new Span(rowSpan,colSpan);
			}
		}
		return new Span(0,0);
	}

	private boolean isMergedRegion(HSSFSheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress range = sheet.getMergedRegion(i);
			int firstColumn = range.getFirstColumn();
			int lastColumn = range.getLastColumn();
			int firstRow = range.getFirstRow();
			int lastRow = range.getLastRow();
			if (row > firstRow && row < lastRow) {
				if (column > firstColumn && column < lastColumn) {
					return true;
				}
			}
		}
		return false;
	}

	private int buildMaxColumn(HSSFSheet sheet){
		int rowCount=sheet.getPhysicalNumberOfRows();
		int maxColumnCount=0;
		for(int i=0;i<rowCount;i++){
			HSSFRow row=sheet.getRow(i);
			if(row==null){
				continue;
			}
			int columnCount=row.getPhysicalNumberOfCells();
			if(columnCount>maxColumnCount){
				maxColumnCount=columnCount;
			}
		}
		return maxColumnCount;
	}

	protected void addBlankCells(List<CellDefinition> cellDefs,int rowNumber,int totalColumn){
		for(int i=0;i<totalColumn;i++){
			CellDefinition cellDef=new CellDefinition();
			cellDef.setValue(new SimpleValue(""));
			cellDef.setRowNumber(rowNumber);
			cellDef.setColumnNumber(i+1);
			cellDefs.add(cellDef);
		}
	}

	@Override
	public boolean support(String name) {
		return name.toLowerCase().endsWith(".xls");
	}
}
