
package phonebook;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import javafx.collections.ObservableList;
import javax.swing.text.html.parser.Element;



public class PdfGeneration {
    
    public void pdfGeneration(String fileName, ObservableList<Person> data){
        
        Document document = new Document();
        
        try{
            PdfWriter.getInstance(document, new FileOutputStream(fileName+".pdf"));
            document.open();
            
            Image image = Image.getInstance(getClass().getResource("/callcenter-logo.jpg"));
            image.scaleToFit(400,175);
            image.setAbsolutePosition(200f,750f);
            document.add(image);
            
            document.add(new Paragraph("\n\n\n\n\n\n\n"));
            
            float [] columnWidhts = {3,3,4};
            PdfPTable table = new PdfPTable(columnWidhts);
            table.setWidthPercentage(100);
            
            PdfPCell cell = new PdfPCell(new Phrase("Kontaklista"));
            cell.setBackgroundColor(BaseColor.DARK_GRAY);
            cell.setColspan(3);
            table.addCell(cell);
            
            table.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell("Név");
            table.addCell("Telefonszám");
            table.addCell("E-mail");
            
            for(int i=0;i<data.size();i++){
                
                Person actualPerso = data.get(i);
                table.addCell(actualPerso.getName());
                table.addCell(actualPerso.getPhone());
                table.addCell(actualPerso.getEmail());
            }            
           
            document.add(table);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        document.close();
    }
}
