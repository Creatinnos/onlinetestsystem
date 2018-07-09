
package com.creatinnos.onlinetestsystem.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.creatinnos.onlinetestsystem.excel.ProcessExcelData;

@Controller
public class ExcelService {

  @ResponseBody
  @RequestMapping(value = "submitFiles", method = RequestMethod.POST)
  public String submitPapers(MultipartHttpServletRequest request) {
    List < MultipartFile > papers = request.getFiles("file");
    try {
      saveFilesToServer(papers);
    } catch (Exception e) {
    	e.printStackTrace();
      return "error";
    }
    return "success";
  }

  @ResponseBody
  @RequestMapping(value = "uploadExcel", method = RequestMethod.POST)
  public String uploadExcel(MultipartHttpServletRequest request) {
    List < MultipartFile > files = request.getFiles("file");
    String[] orgId=request.getParameterMap().get("orgId");		
    String organizationId = orgId!=null && orgId.length==1 ? orgId[0]:"" ;
    try {
    	String directory = "D:/home/";
    	File file = new File(directory);
    	file.mkdirs();
    	Calendar calendar=Calendar.getInstance();
    	for (MultipartFile multipartFile : files) {
    		if(multipartFile.getInputStream()!=null && multipartFile.getInputStream().available()>0)
    		{
    		file = new File(directory + multipartFile.getOriginalFilename().split("\\.")[0]+calendar.getTimeInMillis()+"."+multipartFile.getOriginalFilename().split("\\.")[1]);
    		IOUtils.copy(multipartFile.getInputStream(), new FileOutputStream(file));
    		ProcessExcelData.readExcel(multipartFile.getOriginalFilename(), organizationId);
    		}
    	}
    } catch (Exception e) {
    	e.printStackTrace();
    	
      return "error";
    }
    return "success";
  }
  
  @ResponseBody
  @RequestMapping(value = "get", method = RequestMethod.GET)
  public String submitPapers1() {
    return "success";
  }
  
  public void saveFilesToServer(@RequestBody List<MultipartFile> multipartFiles) throws IOException {
  	String directory = "D:/home/";
	File file = new File(directory);
	file.mkdirs();
	for (MultipartFile multipartFile : multipartFiles) {
		if(multipartFile.getInputStream()!=null && multipartFile.getInputStream().available()>0)
		{
		file = new File(directory + multipartFile.getOriginalFilename());
		IOUtils.copy(multipartFile.getInputStream(), new FileOutputStream(file));
		}
	}
  }
  
  private String filePath = "D:/home/Template.xlsx";
  
  /**
   * Method for handling file download request from client
   */
  @RequestMapping(value="downloadTemplate", method = RequestMethod.GET)
  public void doDownload(HttpServletRequest request,
          HttpServletResponse response) throws IOException {

      // get absolute path of the application
      ServletContext context = request.getServletContext();
      String appPath = context.getRealPath("");
      System.out.println("appPath = " + appPath);

      // construct the complete absolute path of the file
      String fullPath =  filePath;      
      File downloadFile = new File(fullPath);
      FileInputStream inputStream = new FileInputStream(downloadFile);
       
      // get MIME type of the file
      String mimeType = context.getMimeType(fullPath);
      if (mimeType == null) {
          // set to binary type if MIME mapping not found
          mimeType = "application/octet-stream";
      }
      System.out.println("MIME type: " + mimeType);

      // set content attributes for the response
      response.setContentType(mimeType);
      response.setContentLength((int) downloadFile.length());

      // set headers for the response
      String headerKey = "Content-Disposition";
      String headerValue = String.format("attachment; filename=\"%s\"",
              downloadFile.getName());
      response.setHeader(headerKey, headerValue);

      // get output stream of the response
      OutputStream outStream = response.getOutputStream();

      byte[] buffer = new byte[1024];
      int bytesRead = -1;

      // write bytes read from the input stream into the output stream
      while ((bytesRead = inputStream.read(buffer)) != -1) {
          outStream.write(buffer, 0, bytesRead);
      }

      inputStream.close();
      outStream.close();

  }
}