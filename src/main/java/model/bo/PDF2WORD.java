package model.bo;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.parser.PdfTextExtractor;

import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;

public class PDF2WORD {
	static model.dao.Data2 data2 = new model.dao.Data2();
	public static void ConvertToWord(model.bean.PDF2WORD pdf) {
		try {
            data2.setStatusResult(pdf.getSourcePath(), new Timestamp(System.currentTimeMillis()), 1);

            // Working...            
            String inputFile = pdf.getSourcePath();
            String outputFile = pdf.getTargetPath();

            // Create Word document
            XWPFDocument doc = new XWPFDocument();

            // Read the PDF
            PdfReader reader = new PdfReader(inputFile);
            int numberOfPages = reader.getNumberOfPages();

            // Create a PdfTextExtractor object
            PdfTextExtractor extractor = new PdfTextExtractor(reader);

            // Read the PDF page by page
            for (int i = 1; i <= numberOfPages; i++) {
                // Extract the text
                String text = extractor.getTextFromPage(i);
                // Create a new paragraph in the word document, adding the extracted text
                XWPFParagraph p = doc.createParagraph();
                XWPFRun run = p.createRun();
                run.setText(text);
                // Adding a page break
                run.addBreak(BreakType.PAGE);
            }

            // Write the word document
            FileOutputStream out = new FileOutputStream(outputFile);
            doc.write(out);

            // Close all open files
            out.close();
            reader.close();
            
            // Set to successful
            System.out.println("Successful--------------------------------");
            data2.setStatusResult(pdf.getSourcePath(), new Timestamp(System.currentTimeMillis()), 2);
			
		} catch (Exception ex) {
            // Failed
            System.out.println(ex);
            System.out.println("Failed--------------------------------");
            data2.setStatusResult(pdf.getSourcePath(), new Timestamp(System.currentTimeMillis()), -1);
		}
	}
}
