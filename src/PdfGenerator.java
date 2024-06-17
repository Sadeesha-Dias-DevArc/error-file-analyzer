import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;



public class PdfGenerator {
    public static void printToPDF(String analysisResult, String fileName) {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            // Header
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("Error File High Level Overview Analysis");
            contentStream.endText();
            
            // Date and Time
            String analyzedDateAndTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(100, 680);
            contentStream.showText("Date and Time: " + analyzedDateAndTime);
            contentStream.endText();

            // File name
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(100, 640);
            contentStream.showText("File Name: " + fileName);
            contentStream.endText();

            // Analysis result
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(100, 640);
            contentStream.showText(analysisResult);
            contentStream.endText();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            document.save(fileName + "AnalysisReport.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                document.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
}
