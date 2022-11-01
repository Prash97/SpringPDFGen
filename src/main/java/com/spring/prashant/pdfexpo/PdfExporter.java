package com.spring.prashant.pdfexpo;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import java.awt.*;
import java.io.IOException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.spring.prashant.entity.Student;

public class PdfExporter {
	private List<Student> listStudents;
	
	public PdfExporter(List<Student> listStudent) {
		this.listStudents = listStudent;
	}
	
	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setColor(Color.WHITE);
		
		cell.setPhrase(new Phrase("Student ID", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Name", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Standard", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Div", font));
		table.addCell(cell);
				
	}
	
	private void writeTableData(PdfPTable table) {
		for (Student student : listStudents) {
			table.addCell(String.valueOf(student.getId()));
			table.addCell(student.getName());
			table.addCell(student.getStandard());
			table.addCell(student.getDiv());
		}
	}
	
	public void export(HttpServletResponse response) throws DocumentException, IOException{
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		
		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);
		
		Paragraph paragraph = new Paragraph("List Of Students", font);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		
		document.add(paragraph);
		
		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] {1.5f, 4.5f, 3.0f, 3.0f});
		table.setSpacingBefore(10);
		
		writeTableHeader(table);
		writeTableData(table);
		
		document.add(table);
		
		document.close();
	}
	
	
}
