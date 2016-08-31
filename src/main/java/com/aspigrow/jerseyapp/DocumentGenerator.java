package com.aspigrow.jerseyapp;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.aspigrow.salesforce.service.GenerateDocument;
import com.aspigrow.salesforce.service.impl.GenerateDocumnetImpl;

@Path("/documemt")
public class DocumentGenerator {
	
	private GenerateDocument generateDocument = new GenerateDocumnetImpl();
	
	@GET
	@Path("/generateContractDoc/{contractTemplate}/{contractManagement}")
	public Response generateContractDoc(@PathParam("contractTemplate") String templateId, @PathParam("contractManagement") String contractMngmntId) {
		JSONObject jsonObject = new JSONObject();
		try{
			//String templateId = "a032800000F8M5Q";
			//String contractManagementId = "a002800000j4OMo";
			boolean isGenerated = generateDocument.generateDocumnet(templateId, contractMngmntId);
			jsonObject.put("isDocGenerated", isGenerated);
			return Response.status(200).entity(jsonObject.toString()).build();
		} catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Exception while Generate Document  :"+ex.getMessage());
			jsonObject.put("Exception", ex.getMessage());
			return Response.status(500).entity(jsonObject).build();
		}
	}
}
