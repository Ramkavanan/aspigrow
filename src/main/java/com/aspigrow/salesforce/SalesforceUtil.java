package com.aspigrow.salesforce;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.PartBase;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.json.JSONObject;
import org.json.JSONTokener;

import pl.jsolve.templ4docx.core.Docx;
import pl.jsolve.templ4docx.core.VariablePattern;
import pl.jsolve.templ4docx.variable.TextVariable;
import pl.jsolve.templ4docx.variable.Variables;

import com.aspigrow.salesforce.model.OAuth;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class provide the salesforce util to performce the CRUD operation
 * and it return perform login utility to validate the appropriate user
 * 
 * @author ramachandran
 */
public class SalesforceUtil {
	
	private static final String OAUTH2LOGINURL = "https://login.salesforce.com/services/oauth2/token";
	
	private static final String CLIENT_ID = "3MVG9ZL0ppGP5UrBJ.AgSdOpF6qfjo05PoM3HviB4LCTULTn6YDmVNhbc1Xd.cWbHbM.rbWD1obgJXkoxhR7v";
	
	private static final String CLIENT_SECRET = "7593501340155976689";
	
	private static final String S_USERNAME = "ramakavanan@aspigrow.com";
	
	private static final String S_PASSWORD = "Damaru_123Ram0AO5lLunW2J6oASz35Gb0xMML";
	
	private static final String LOGIN_GRANT_TYPE = "password";
	
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	private static final HttpClient httpClient = new HttpClient();
	
	/**
	 * Function used to get the salesforce access token
	 * for further operation to access the salesforce object data
	 * 
	 * @return
	 */
	public static final OAuth getOauth2Token() {
		OAuth oauth= null;
		PostMethod post = new PostMethod(OAUTH2LOGINURL);
		try{
			String postUrlBody = "grant_type="+LOGIN_GRANT_TYPE+"&client_id="+CLIENT_ID+"&client_secret="+CLIENT_SECRET+"&username="+
								  S_USERNAME+"&password="+S_PASSWORD;
			post.setRequestBody(postUrlBody);
			post.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			post.setRequestHeader("Accept", "application/json");
			httpClient.executeMethod(post);
			if(post.getStatusCode() == HttpStatus.SC_OK){
				JSONObject response = new JSONObject(
	                    new JSONTokener(new InputStreamReader(
	                            post.getResponseBodyAsStream())));
	           oauth = (OAuth) objectMapper.readValue(response.toString(2), OAuth.class);
	           oauth.setLoggedIn(true);
	           return oauth;
			} else {
				oauth = new OAuth();
				oauth.setLoggedIn(false);
				return oauth;
			}
		} catch(Exception ex) {
			System.out.println("Exception Whle salesforce Login duo to "+ ex.getMessage());
			ex.printStackTrace();
			oauth = new OAuth();
			oauth.setLoggedIn(false);
			return oauth;
		} finally {
			post.releaseConnection();
		}
	}
	
	/**
	 * Function used to make post call to access salesforce instance 
	 * 
	 * @param request
	 * @return
	 */
	public static final JSONObject makePostCall(Request request) {
		PostMethod post = new PostMethod(request.getUrl());
		try{
			if(request == null || request.getUrl() == null)
				return null;
			post.setRequestBody(request.getBody());
			for(String key : request.getHeaders().keySet()) {
				post.setRequestHeader(key, request.getHeaders().get(key));
			}
			httpClient.executeMethod(post);
			if(post.getStatusCode() == HttpStatus.SC_OK)
				return new JSONObject( new JSONTokener(new InputStreamReader(
					post.getResponseBodyAsStream())));
			return null;
		} catch(Exception ex) {
			System.out.println("Exception occured while make post call : "+ex.getMessage());
			return null;
		} finally {
			post.releaseConnection();
		}
	}
	
	/**
	 * Make generic get call to saleforce instance
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static final JSONObject makeGetCall(Request request) throws Exception {
		GetMethod get = new GetMethod(request.getUrl());
		try{
			if(request == null || request.getUrl() == null)
				return null;
			for(String key : request.getHeaders().keySet()) {
				get.setRequestHeader(key, request.getHeaders().get(key));
			}
			httpClient.executeMethod(get);
			if(get.getStatusCode() == HttpStatus.SC_OK)
				return new JSONObject( new JSONTokener(new InputStreamReader(
					get.getResponseBodyAsStream())));
			return null;
		} catch(Exception ex) {
			System.out.println("Exception occured while make get call : "+ex.getMessage());
			return null;
		} finally {
			get.releaseConnection();
		}
	}
	
	/**
	 * Download the file from salesforce instance and write back in local server
	 * 
	 * @param request
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static final String downloadDocument(Request request, String fileName) throws Exception {
		GetMethod get = new GetMethod(request.getUrl());
		try{
			if(request == null || request.getUrl() == null)
				return null;
			for(String key : request.getHeaders().keySet()) {
				get.setRequestHeader(key, request.getHeaders().get(key));
			}
			httpClient.executeMethod(get);
			if(get.getStatusCode() == HttpStatus.SC_OK) {
				byte[] contents = get.getResponseBody();
				XWPFDocument doc = new XWPFDocument();
				File f = new File(fileName);
				if(f.exists())
					f.deleteOnExit();
				FileOutputStream out = new FileOutputStream(f);
				out.write(contents);
				doc.setTrackRevisions(true);
			    doc.write(out);
			    out.close();
			    doc.close();
			    return "1";
			}
			return null;
		} catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Exception occured while make get call : "+ex.getMessage());
			return null;
		} finally {
			get.releaseConnection();
		}
	}
	
	/**
	 * Attachment the document file to salesforce instance object
	 * 
	 * @param request
	 * @param params
	 * @param fileName
	 * @return
	 */
	public static final boolean attachFile(Request request, String params, String fileName) {
		PostMethod post = new PostMethod(request.getUrl());
		try{
			File attachmentFile = new File(fileName);
			Part[] parts = new Part[] {
					 new JsonPart("Json", params) ,
		            new FilePart("Body", attachmentFile)
		     };
			for(String key : request.getHeaders().keySet()) {
				post.setRequestHeader(key, request.getHeaders().get(key));
			}
			post.setRequestEntity(new MultipartRequestEntity(parts, post.getParams()));
			int status = httpClient.executeMethod(post); 
           // String response = post.getResponseBodyAsString();
			System.out.println(" status : " + status);          
	      //  System.out.println("Response Content Length : " + post.getResponseContentLength());         
	       // System.out.println("response : " + response);            
	      //  System.out.println(" getStatusCode " + post.getStatusCode() + " getStatusText : " + post.getStatusText());
			if (post.getStatusCode() == HttpStatus.SC_CREATED) {
                return true;
            } 
		} catch(Exception ex){
			ex.printStackTrace();
		} finally {
	        post.releaseConnection();
	    }
		return false;
	}

	/**
	 * Class to provide the json part params
	 * 
	 * @author ramachandran
	 */
    private static class JsonPart extends PartBase {

        private byte[] bytes;

        public JsonPart(String name, String json) throws IOException {
            super(name, "application/json", "UTF-8", null);
            this.bytes = json.getBytes("UTF-8");
        }

        @Override
        protected void sendData(OutputStream os) throws IOException {
            os.write(bytes);
        }

        @Override
        protected long lengthOfData() throws IOException {
            return bytes.length;
        }
    }
}