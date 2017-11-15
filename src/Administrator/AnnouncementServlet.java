package Administrator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 类：AnnouncementServlet()
 * 功能：发布公告
 * */
@WebServlet("/AnnouncementServlet")
public class AnnouncementServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
	  
	  String rootFolder = request.getSession().getServletContext().getRealPath("");
	  String fileName = null;
	  String inputAnnouncement = null;
	  String inputAnnounID = null;
	  String currentURL = null;
	  String newURL_1 = null;
	  String newURL_2 = null;
	  Date now = new Date(); 
	  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	  String date = dateFormat.format(now);
	  
	  inputAnnouncement = request.getParameter("inputAnnouncement");
	  currentURL = request.getParameter("adID");
	  fileName = rootFolder + "file/Announcement.txt";
	  inputAnnounID = currentURL.substring(currentURL.indexOf("adID")+5);
	  System.out.println(fileName);
	  newURL_1 = "Administrator.jsp?adID=" + inputAnnounID;
	  newURL_2 = "3;url='" + newURL_1 + "'";
	  
	  /*生成中间页（写入成功与失败）*/
	  if(writeAnnouncement(inputAnnounID, date, inputAnnouncement, fileName)){
		  response.setHeader("Refresh",newURL_2);
			PrintWriter updateSucc = response.getWriter();
	    	response.setContentType("text/html");
	        updateSucc.println("<?xml version='1.0' encoding='UTF-8' ?>" +
	        					"<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Frameset//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd'>" +
	        						"<html xmlns='http://www.w3.org/1999/xhtml'>" +
	        						"<head>" +
	        						"<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />" +
	        						"<title>Announcement Update Success</title>" +
	        						"<link href='css/bootstrap.min.css' rel='stylesheet'></link>" +
	        						"<script src='javascript/jquery.min.js'></script>" +
	        						"<script src='javascript/bootstrap.min.js'></script>" +
	        						"<link href='css/style.css' rel='stylesheet'>" +
	        						"</head>" +
	        						"<body>" +
	        						"<div class='page-header'>" +
	        						"<h1 class='text-center lead'> Announcement Update Success! " + "! 3 seconds to jump...</h1>" +
	        						"</div>" +
	        						"</body>" +
	        						"</html>");
	  }else{
		  response.setHeader("Refresh",newURL_2);
			PrintWriter updateFail = response.getWriter();
	    	response.setContentType("text/html");
	        updateFail.println("<?xml version='1.0' encoding='UTF-8' ?>" +
	        					"<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Frameset//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd'>" +
	        						"<html xmlns='http://www.w3.org/1999/xhtml'>" +
	        						"<head>" +
	        						"<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />" +
	        						"<title>Announcement Update Failed</title>" +
	        						"<link href='css/bootstrap.min.css' rel='stylesheet'></link>" +
	        						"<script src='javascript/jquery.min.js'></script>" +
	        						"<script src='javascript/bootstrap.min.js'></script>" +
	        						"<link href='css/style.css' rel='stylesheet'>" +
	        						"</head>" +
	        						"<body>" +
	        						"<div class='page-header'>" +
	        						"<h1 class='text-center lead'> Announcement Update Failed! " + "! 3 seconds to jump...</h1>" +
	        						"</div>" +
	        						"</body>" +
	        						"</html>");
	  }
	  
  }
  
  @Override
  protected void doPost(HttpServletRequest request,
		  			HttpServletResponse response)
  	  throws ServletException, IOException{
	  
  }
  
  /**
   * 方法：writeAnnouncement
   * 功能：把公告写入文件
   * */
  public boolean writeAnnouncement(String inputAnnounID, String date, String inputAnnouncement, String fileName){
	  try{
		  	 String data =inputAnnounID + "/+/" + date + "/+/" + inputAnnouncement + "&@#";
			 File file = new File(fileName);
			 if(!file.exists()){
	                file.createNewFile();
			 }
			 
			 
			 FileWriter writer = new FileWriter(fileName, true);
             writer.write(data);
             writer.close();
             return true;
		 }catch(Exception e){
			 e.printStackTrace();
			 return false;
		 }
  }
  
}
