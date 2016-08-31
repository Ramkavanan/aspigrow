/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspigrow.jerseyapp;

import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.StreamingOutput;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLineSpacingRule;

import com.aspigrow.salesforce.SalesforceUtil;

/**
 *
 * @author selvakumarg
 */
@Path("/salesforceServices")
public class ContractDocumentGenerator {

    @GET
    @Path("/generateContractDoc")
    @Produces("application/msword")
    public Response getFile() {
    	System.out.println( "Access Token " +SalesforceUtil.getOauth2Token().getAccess_token());
        ResponseBuilder response = Response.ok().entity(generateContractDoc());
        response.header("Content-Disposition", "attachment; filename=\"Contract_Doc - " + System.currentTimeMillis() + ".doc\"");
        return response.build();
    }
    
    private StreamingOutput generateContractDoc() {
//        String fileContent = String.format(Constants.DOC_TEXT, "Selva", "Chennai", "Tamil Nadu", "India", "Selva", "Selva Supplier");
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph headerPara = document.createParagraph();
        headerPara.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun title = headerPara.createRun();
        title.setText(Constants.DOC_HEADING);
        title.setFontSize(18);
        title.setBold(true);
        headerPara.setSpacingAfterLines(200);

        XWPFParagraph headerLinePara = document.createParagraph();
        setSingleLineSpacing(headerPara);
        headerLinePara.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun hlText = headerLinePara.createRun();
        hlText.setText(String.format(Constants.DOC_HEADER_LINE, "Selva", "Chennai", "Tamil Nadu", "India"));
        hlText.setFontSize(12);
        headerLinePara.setSpacingAfterLines(150);

        List<String> BULLET_POINTS = Arrays.asList(
                Constants.FIRST_POINT,
                Constants.SECOND_POINT,
                Constants.THIRD_POINT,
                Constants.FOURTH_POINT,
                Constants.FIFTH_POINT,
                Constants.SIXTH_POINT,
                Constants.SEVENTH_POINT,
                Constants.EIGHTH_POINT,
                Constants.NINTH_POINT,
                Constants.TENTH_POINT);

        for (String point : BULLET_POINTS) {
            XWPFParagraph paragraph = document.createParagraph();
            setSingleLineSpacing(paragraph);
            paragraph.setAlignment(ParagraphAlignment.BOTH);
            XWPFRun text = paragraph.createRun();
            text.setText(point);
            text.setFontSize(12);
            paragraph.setSpacingAfterLines(80);
        }
        document.setTrackRevisions(true);
        return (OutputStream output) -> document.write(output);
    }

    public void setSingleLineSpacing(XWPFParagraph para) {
        CTPPr ppr = para.getCTP().getPPr();
        if (ppr == null) {
            ppr = para.getCTP().addNewPPr();
        }
        CTSpacing spacing = ppr.isSetSpacing() ? ppr.getSpacing() : ppr.addNewSpacing();
        spacing.setAfter(BigInteger.valueOf(0));
        spacing.setBefore(BigInteger.valueOf(0));
        spacing.setLineRule(STLineSpacingRule.AUTO);
        spacing.setLine(BigInteger.valueOf(240));
    }
}
