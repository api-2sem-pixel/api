package relatorio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.RelatorioModel;

public class GerarRelatorio {

	private static String[] columns = {"Matricula", "Nome", "Verba", "Qtde de H"};
	private static List<RelatorioModel> relatorioModel = new ArrayList<RelatorioModel>();
	
	public static void main(String[] args) throws IOException {
		relatorioModel.add(new RelatorioModel("teste", "teste1", "teste2",0d));
		
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Relatorio");
		
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 12);
		headerFont.setColor(IndexedColors.BLACK.getIndex());
		
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);
		
		Row headerRow = sheet.createRow(0);
		
		for(int i =0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(headerCellStyle);
		}
		
		int rowNum = 1;
		
		for(RelatorioModel relatorio : relatorioModel ) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(relatorio.getMatricula());
			row.createCell(1).setCellValue(relatorio.getNome());
			row.createCell(2).setCellValue(relatorio.getVerba());
			row.createCell(3).setCellValue(relatorio.getQuantidadeHoras());
		}
		
		for(int i = 0; i < columns.length; i++) {
			sheet.autoSizeColumn(i);
		}
		
		FileOutputStream fileOut = new FileOutputStream("teste5.xlsx");
		workbook.write(fileOut);
		fileOut.close();
		workbook.close();
	}
	
	
}
