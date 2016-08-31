package com.aspigrow.salesforce.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.JsonObject;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.json.JSONArray;
import org.json.JSONObject;

import pl.jsolve.templ4docx.core.Docx;
import pl.jsolve.templ4docx.core.VariablePattern;
import pl.jsolve.templ4docx.variable.TextVariable;
import pl.jsolve.templ4docx.variable.Variables;

import com.aspigrow.salesforce.Request;
import com.aspigrow.salesforce.SalesforceUtil;
import com.aspigrow.salesforce.model.OAuth;
import com.aspigrow.salesforce.service.GenerateDocument;

public class GenerateDocumnetImpl implements GenerateDocument {
	
	public static final OAuth oauth = SalesforceUtil.getOauth2Token();
	
	private Request request;
	
	private static final String JSON_CONTENT_TYPE = "json";
	
	private final static String serviceURL = "/services/data/v37.0/sobjects/";

	public boolean generateDocumnet(String templateId, String contractMngmtId) throws Exception {
		// TODO Auto-generated method stub
		JSONObject ctrTemplete = getContractTemplate(templateId); 
		if(ctrTemplete == null)
			System.out.println("get Call have some error ");
		request = makeRequestHeader(true, JSON_CONTENT_TYPE);
		JSONObject templateAttachment = getAttachmentJsonObject(templateId);
		JSONArray recordsArray = (JSONArray) templateAttachment.get("records");
		String usrl = recordsArray.getJSONObject(0).get("Body").toString();
		request.setUrl(usrl);
		String fileName =  recordsArray.getJSONObject(0).get("Name").toString();
		String isDocumentSaved = SalesforceUtil.downloadDocument(request, fileName);
		JSONObject ctractMngmt = getContractManagement(contractMngmtId);
		String targetName = ctractMngmt.getString("Id")+"_"+fileName;
		List<String> fileList = new ArrayList<String>();
		if(isDocumentSaved != null && isDocumentSaved.equalsIgnoreCase("1")) {
			boolean isMerged = mergeTemplate(fileName, targetName, ctractMngmt,ctrTemplete.getString("CF_Merge_Fields__c"));
			fileList.add(targetName);
			fileList.add(fileName);
			boolean isDeletedTemp = deleteTempFile(fileList);
			System.out.println("Is Temp File deleted list : "+isDeletedTemp+" Deleted File list"
					+ " :"+fileList.toString()+" Deleted At :"+System.currentTimeMillis());
			if(isMerged)
				return true;
		}
		return false;
	}
	
	/**
	 * Get the contract management object
	 * 
	 * @param contractMangmtId
	 * @return
	 * @throws Exception
	 */
	public JSONObject getContractManagement(String contractMangmtId) throws Exception {
		request = makeRequestHeader(true, JSON_CONTENT_TYPE);
		request.setUrl(serviceURL+"CF_Contract_Management__c/"+contractMangmtId);
		return SalesforceUtil.makeGetCall(request);
	}
	
	/**
	 * Get the contract template 
	 * 
	 * @param templateId
	 * @return
	 * @throws Exception
	 */
	public JSONObject getContractTemplate(String templateId) throws Exception {
		request = makeRequestHeader(true, JSON_CONTENT_TYPE);
		request.setUrl(serviceURL+"CF_Contract_Template__c/"+templateId);
		return SalesforceUtil.makeGetCall(request);
	}
	
	/**
	 * Merge the template with contract element
	 * 
	 * @param fileName
	 * @param targetName
	 * @param ctractMngmt
	 * @param mergeFields
	 * @return
	 * @throws Exception
	 */
	public boolean mergeTemplate(String fileName, String targetName, JSONObject ctractMngmt, String mergeFields) throws Exception {
		try{
			Docx docx = new Docx(fileName);
		    docx.setVariablePattern(new VariablePattern("${", "}"));
		    // preparing variables
		    Variables variables = new Variables();
		    for(String field : mergeFields.split(",")) {
		    	if(ctractMngmt.getString(field.trim()) != null) {
		    		variables.addTextVariable(new TextVariable("${"+field+"}", ctractMngmt.getString(field.trim())));
		    	}
		    }

		    // fill template
		    docx.fillTemplate(variables);
		    // save filled .docx file
		    docx.save(targetName);
		    return updateContractManagementAtachment(targetName, ctractMngmt);
		} catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Exception at extract contract managment getting :"+ex.getMessage());
		}
		return false;
	}
	
	/**
	 * Delete after the file processing finished
	 * 
	 * @param files
	 * @return
	 * @throws Exception
	 */
	public boolean deleteTempFile(List<String> files) throws Exception{
		for(String fileName : files) {
			File f = new File(fileName);
			if(f.exists())
				f.deleteOnExit();
		}
		return true;
	}
	
	/**
	 * Upload the contract documnet file 
	 * 
	 * @param fileName
	 * @param contract
	 * @return
	 */
	public boolean updateContractManagementAtachment(String fileName, JSONObject contract) {
		try{
			request = makeRequestHeader(true, "");
			request.setUrl(serviceURL+"Attachment");
			JSONObject obj = new JSONObject();
			obj.put("name", fileName);
            obj.put("Description", "Last Modified : " + contract.getString("LastModifiedDate") );
            obj.put("ParentId", contract.getString("Id"));
			return SalesforceUtil.attachFile(request, obj.toString(), fileName);
		} catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Exception at template replacement"+ ex.getMessage());
		}
		return false;
	}
	
	/**
	 * Getting the attachment document in json
	 * 
	 * @param templateId
	 * @return
	 * @throws Exception
	 */
	public JSONObject getAttachmentJsonObject(String templateId) throws Exception {
		request =  makeRequestHeader(true, JSON_CONTENT_TYPE);
		request.setUrl(serviceURL+"CF_Contract_Template__c/"+templateId+"/Attachments");
		return SalesforceUtil.makeGetCall(request);
	}
	
	/**
	 * Function used to make the generic header to make call with access token
	 * 
	 * @param oauth
	 * @param isNeedHeader
	 * @return
	 */
	private Request makeRequestHeader(boolean isTokenRequired, String contentType) throws Exception {
		request = new Request();
		Map<String, String> headers = new HashMap<String, String>();
		request.setInstance_url(oauth.getInstance_url());
		if(isTokenRequired) {
			System.out.println("Acces Token checking "+oauth.getAccess_token());
			headers.put("Authorization", "Bearer "+oauth.getAccess_token());
		}
		if(contentType.equalsIgnoreCase(JSON_CONTENT_TYPE)) {
			headers.put("Content-Type", "application/json");
		} 
		request.setHeaders(headers);
		return request;
	}

}
