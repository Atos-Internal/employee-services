package net.atos.employeeservices.common.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import net.atos.employeeservices.entity.Employee;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;

public class ExportUtils {

    public static byte[] generateDOCX(ByteArrayOutputStream outputStream, XWPFDocument document) throws IOException {
        document.write(outputStream);
        return outputStream.toByteArray();
    }

    public static byte[] generatePDF(ByteArrayOutputStream outputStream, XWPFDocument document) {
        try {
            // Créez un nouveau document PDF
            Document pdfDocument = new Document(PageSize.A4, 72, 72, 72, 72);

            // Initialisez le flux de sortie pour enregistrer le PDF
            PdfWriter.getInstance(pdfDocument, outputStream);

            // Ouvrez le document PDF
            pdfDocument.open();

            ArrayList<Chunk> chunks = new ArrayList<>();

            // Parcourez le contenu du document DOCX et copiez-le dans le document PDF
            for (XWPFParagraph para : document.getParagraphs()) {
                Paragraph paragraph = new Paragraph();

                int paragraphAlignement = Element.ALIGN_LEFT;
                if (para.getAlignment().getValue() == 2)
                    paragraphAlignement = Element.ALIGN_CENTER;
                else if (para.getAlignment().getValue() == 12)
                    paragraphAlignement = Element.ALIGN_RIGHT;

                for (XWPFRun run : para.getRuns()) {

                    Font font;
                    if (run.isBold() && run.isItalic())
                        font = FontFactory.getFont(FontFactory.TIMES_ROMAN, run.getFontSize(), Font.BOLDITALIC);
                    else if (run.isBold())
                        font = FontFactory.getFont(FontFactory.TIMES_ROMAN, run.getFontSize(), Font.BOLD);
                    else if (run.isItalic())
                        font = FontFactory.getFont(FontFactory.TIMES_ROMAN, run.getFontSize(), Font.ITALIC);
                    else
                        font = FontFactory.getFont(FontFactory.TIMES_ROMAN, run.getFontSize(), Font.NORMAL);

                    String text = run.getText(0);
                    byte[] bytes;
                    String str;
                    Chunk chunk;

                    if (text != null) {
                        bytes = text.getBytes();
                        str = new String(bytes, StandardCharsets.UTF_8);
                        chunk = new Chunk(str, font);
                        chunks.add(chunk);
                    } else {
                        chunks.add(new Chunk(Chunk.NEWLINE));
                    }
                }

                paragraph.addAll(chunks);
                chunks.clear();
                paragraph.setAlignment(paragraphAlignement);
                pdfDocument.add(paragraph);
            }

            // Fermez le document PDF
            pdfDocument.close();

            return outputStream.toByteArray();

        } catch (DocumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void AttestationWriter(XWPFDocument document, Employee employee) {
        for (XWPFParagraph paragraph : document.getParagraphs()) {
            for (XWPFRun run : paragraph.getRuns()) {
                String text = run.getText(0);
                if (text != null) {
                    if (text.contains("employeeName")) {
                        text = text.replace("employeeName", employee.getFirstName() + " " + employee.getLastName());
                    }
                    if (text.contains("cin")) {
                        text = text.replace("cin", employee.getCin());
                    }
                    if (text.contains("cnssNumber")) {
                        text = text.replace("cnssNumber", employee.getCnssNumber());
                    }
                    if (text.contains("position")) {
                        text = text.replace("position", employee.getPosition());
                    }
                    if (text.contains("grossMonthlySalary")) {
                        text = text.replace("grossMonthlySalary", employee.getGrossMonthlySalary().toString());
                    }
                    if (text.contains("bankAccountNumber")) {
                        text = text.replace("bankAccountNumber", employee.getBankAccountNumber());
                    }
                    if (text.contains("integrationDate")) {
                        text = text.replace("integrationDate", employee.getIntegrationDate().getDayOfMonth()
                                + "/" + employee.getIntegrationDate().getMonthValue() + "/" + employee.getIntegrationDate().getYear());
                    }
                    if (text.contains("releaseDate")) {
                        text = text.replace("releaseDate", employee.getReleaseDate().getDayOfMonth()
                                + "/" + employee.getReleaseDate().getMonthValue() + "/" + employee.getReleaseDate().getYear());
                    }
                    if (text.contains("currentDate")) {
                        text = text.replace("currentDate", LocalDate.now().getDayOfMonth()
                                + "/" + LocalDate.now().getMonthValue() + "/" + LocalDate.now().getYear());
                    }
                }
                run.setText(text, 0);
            }
        }
    }
}
