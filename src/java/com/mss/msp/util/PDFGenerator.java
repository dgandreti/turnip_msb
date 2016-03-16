/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.util;

import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mss.msp.usrajax.UserAjaxHandlerAction;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author miracle
 */
public class PDFGenerator {
   
    private static Font font = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
    private static Font bigFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLDITALIC);
    private static Font smallFont = new Font(Font.FontFamily.HELVETICA, 8, Font.ITALIC);

    public static void getPOPDF(UserAjaxHandlerAction userAjaxHandlerAction, String pdfFileName,String customerName) {
        try {

            //  File f = new File(pdfFileName);

            // tries to create new file in the system
            //   f.createNewFile();
            OutputStream file = new FileOutputStream(new File(pdfFileName));
//            Rectangle rect = new Rectangle(216, 720);
//            rect.setBackgroundColor(BaseColor.BLUE);
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, file);
            
            
            document.open();
            addImage(document, writer, userAjaxHandlerAction,customerName);
            // FileUtils.copyFile(document, pdfFileName);
            document.close();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    private static void addImage(Document document, PdfWriter writer, UserAjaxHandlerAction userAjaxHandlerAction,String customerName) throws BadElementException, MalformedURLException, IOException, DocumentException {

        // writer.setBoxSize("art", new Rectangle(36, 54, 559, 788));

        //Rectangle pageSize = new Rectangle(216, 720);
        Image image = Image.getInstance(userAjaxHandlerAction.getAcclogo());

        image.setAlignment(Element.ALIGN_CENTER);

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);

        PdfPCell cell = new PdfPCell();

        cell.addElement(new Chunk(image, 0, 0));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setFixedHeight(23);


        PdfPCell cell1 = new PdfPCell();
        cell1.setFixedHeight(23);
        cell1.setBorder(Rectangle.NO_BORDER);
        cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);


        PdfPCell cell2 = new PdfPCell();

        cell2.setFixedHeight(23);

        cell2.setBorder(Rectangle.NO_BORDER);
        cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);

        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

        table.addCell(cell1);
        table.addCell(cell);
        table.addCell(cell2);
        // table.getDefaultCell().setFixedHeight(16);
        document.add(table);

        Paragraph preface3 = new Paragraph();
        preface3.add(new Paragraph(""));
        document.add(preface3);

        document.add(Chunk.NEWLINE);


        Paragraph combs = new Paragraph();
        combs.add(new Chunk("Dear "));
        combs.add(new Chunk(userAjaxHandlerAction.getAccountName() + ",", bigFont));
        document.add(combs);


        document.add(Chunk.NEWLINE);


//        Paragraph preface1 = new Paragraph();
//        preface1.add(new Paragraph("Approved Purchase Order for " + userAjaxHandlerAction.getUserName() + " (Candidate ID - " + userAjaxHandlerAction.getUserId() + ")"));
//        document.add(preface1);
        
        
         Paragraph preface1 = new Paragraph();
        preface1.add(new Chunk("Approved Purchase Order for "));
        preface1.add(new Chunk(userAjaxHandlerAction.getUserName(), bigFont));
        preface1.add(new Chunk(" (Candidate ID - " + userAjaxHandlerAction.getUserId() + ")"));
        document.add(preface1);
        document.add(Chunk.NEWLINE);


        Paragraph preface2 = new Paragraph();
        preface2.add(new Paragraph("Following are the details of the Purchase Order for your reference: "));
        document.add(preface2);
        document.add(Chunk.NEWLINE);

        createTable(document, userAjaxHandlerAction,customerName);
    }

    private static void createTable(Document document, UserAjaxHandlerAction userAjaxHandlerAction,String customerName)
            throws BadElementException, DocumentException, MalformedURLException, IOException {

        PdfPTable table = new PdfPTable(2);

        table.setWidths(new int[]{100, 200});

        PdfPCell tablecell1 = new PdfPCell(new Phrase("PO Number ", font));
        tablecell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablecell1.setBorderColor(BaseColor.CYAN);
        table.addCell(tablecell1);

        PdfPCell tablecell2 = new PdfPCell(new Phrase(userAjaxHandlerAction.getSowId() + "", font));
        tablecell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablecell2.setBorderColor(BaseColor.CYAN);
        table.addCell(tablecell2);

        PdfPCell tablecell3 = new PdfPCell(new Phrase("Subcontractor", font));
        tablecell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablecell3.setBorderColor(BaseColor.CYAN);
        table.addCell(tablecell3);

        PdfPCell tablecell4 = new PdfPCell(new Phrase("" + userAjaxHandlerAction.getUserName() + " (Candidate ID - " + userAjaxHandlerAction.getUserId() + ")", font));
        tablecell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablecell4.setBorderColor(BaseColor.CYAN);
        table.addCell(tablecell4);

        PdfPCell tablecell5 = new PdfPCell(new Phrase("PO Start Date", font));
        tablecell5.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablecell5.setBorderColor(BaseColor.CYAN);
        table.addCell(tablecell5);

        PdfPCell tablecell6 = new PdfPCell(new Phrase(userAjaxHandlerAction.getPoStartDate() + "", font));
        tablecell6.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablecell6.setBorderColor(BaseColor.CYAN);
        table.addCell(tablecell6);

        PdfPCell tablecell7 = new PdfPCell(new Phrase("PO End Date ", font));
        tablecell7.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablecell7.setBorderColor(BaseColor.CYAN);
        table.addCell(tablecell7);

         PdfPCell tablecell8 = new PdfPCell(new Phrase(userAjaxHandlerAction.getPoEndDate() + "", font));
        tablecell8.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablecell8.setBorderColor(BaseColor.CYAN);
        table.addCell(tablecell8);
        
        PdfPCell tablecell9 = new PdfPCell(new Phrase("Recruiter Mail ID", font));
        tablecell9.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablecell9.setBorderColor(BaseColor.CYAN);
        table.addCell(tablecell9);
        
        PdfPCell tablecell10 = new PdfPCell(new Phrase(userAjaxHandlerAction.getEmailId() + "", font));
        tablecell10.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablecell10.setBorderColor(BaseColor.CYAN);
        table.addCell(tablecell10);
        


        document.add(table);


        Paragraph preface3 = new Paragraph();
        preface3.add(new Paragraph("Rate Details:", font));
        document.add(preface3);
        document.add(Chunk.NEWLINE);

        PdfPTable rateDetailstable = new PdfPTable(3);
        rateDetailstable.setWidths(new int[]{100, 100, 100});

        PdfPCell c11 = new PdfPCell(new Phrase("Base Rate", font));
        c11.setHorizontalAlignment(Element.ALIGN_CENTER);
        c11.setBorderColor(BaseColor.CYAN);
        rateDetailstable.addCell(c11);

        PdfPCell c12 = new PdfPCell(new Phrase("Overtime Rate", font));
        c12.setHorizontalAlignment(Element.ALIGN_CENTER);
        c12.setBorderColor(BaseColor.CYAN);
        rateDetailstable.addCell(c12);



        PdfPCell c14 = new PdfPCell(new Phrase("Overtime Limit", font));
        c14.setHorizontalAlignment(Element.ALIGN_CENTER);
        c14.setBorderColor(BaseColor.CYAN);
        rateDetailstable.addCell(c14);

        rateDetailstable.setHeaderRows(1);
        
        PdfPCell c15 = new PdfPCell(new Phrase(userAjaxHandlerAction.getBaseRate() + "", font));
        c15.setHorizontalAlignment(Element.ALIGN_CENTER);
        c15.setBorderColor(BaseColor.CYAN);
        rateDetailstable.addCell(c15);
        
        PdfPCell c16 = new PdfPCell(new Phrase(userAjaxHandlerAction.getOverTimeRate(), font));
        c16.setHorizontalAlignment(Element.ALIGN_CENTER);
        c16.setBorderColor(BaseColor.CYAN);
        rateDetailstable.addCell(c16);
        
        PdfPCell c17 = new PdfPCell(new Phrase(userAjaxHandlerAction.getOverTimeLimit(), font));
        c17.setHorizontalAlignment(Element.ALIGN_CENTER);
        c17.setBorderColor(BaseColor.CYAN);
        rateDetailstable.addCell(c17);
        
        document.add(rateDetailstable);

        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        Paragraph preface21 = new Paragraph();
        preface21.add(new Paragraph("Regards,"));
        document.add(preface21);

        Paragraph preface22 = new Paragraph();
        preface22.add(new Paragraph("ServicesBay Team "));
        document.add(preface22);

        document.add(Chunk.NEWLINE);


        Image image = Image.getInstance(userAjaxHandlerAction.getAcclogo());
        // image.scaleAbsolute(120f, 60f);

        image.setAlignment(Element.ALIGN_CENTER);
        PdfPTable table1 = new PdfPTable(3);
        table1.setWidthPercentage(100);

        PdfPCell cell = new PdfPCell();
        cell.addElement(new Chunk(image, 0, 0));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        PdfPCell cell1 = new PdfPCell();

        cell1.setBorder(Rectangle.NO_BORDER);
        cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);

        PdfPCell cell2 = new PdfPCell();

        cell2.setBorder(Rectangle.NO_BORDER);
        cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell2.addElement(new Chunk(customerName, smallFont));

        table1.addCell(cell);
        table1.addCell(cell1);
        table1.addCell(cell2);

        document.add(table1);

    }
}
