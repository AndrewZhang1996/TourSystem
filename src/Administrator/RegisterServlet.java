package Administrator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 类：RegisterServlet()
 * 功能：注册成为管理员
 * */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
	  
	  
	  String rootFolder = request.getSession().getServletContext().getRealPath("");
	  String fileName = null;
	  String newURL_1 = null;
	  String newURL_2 = null;
	  String newURL_org_1 = null;
	  String newURL_org_2 = null;
	  String currentURL = null;
	  String originID = null;
	  
	  
	  String inputID = request.getParameter("inputID");
	  String inputPwd = request.getParameter("inputPassword");
	  String inputPwdAgain = request.getParameter("inputPasswordAgain");
	  currentURL = request.getParameter("adID_reg");
	  originID = currentURL.substring(currentURL.indexOf("adID")+5);
	  newURL_1 = "Administrator.jsp?adID=" + inputID;
	  newURL_org_1 = "Administrator.jsp?adID=" + originID;
	  newURL_2 = "3;url='" + newURL_1 + "'";
	  newURL_org_2 = "3;url='" + newURL_org_1 + "'";
	  
	  /*生成中间页*/
	  if(inputPwd.equals(inputPwdAgain)){
		  fileName = rootFolder + "file/Administrator.txt";
		  if(writePwd(inputID, inputPwd, fileName)){
		  
		  response.setHeader("Refresh",newURL_2);
			PrintWriter registerSucc = response.getWriter();
	    	response.setContentType("text/html");
	        registerSucc.println("<?xml version='1.0' encoding='UTF-8' ?>" +
	        					"<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Frameset//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd'>" +
	        						"<html xmlns='http://www.w3.org/1999/xhtml'>" +
	        						"<head>" +
	        						"<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />" +
	        						"<title>Registered Success</title>" +
	        						"<link href='css/bootstrap.min.css' rel='stylesheet'></link>" +
	        						"<script src='javascript/jquery.min.js'></script>" +
	        						"<script src='javascript/bootstrap.min.js'></script>" +
	        						"<link href='css/style.css' rel='stylesheet'>" +
	        						"</head>" +
	        						"<body>" +
	        						"<div class='page-header'>" +
	        						"<h1 class='text-center lead'>" + inputID + " Registered Success! " + "! 3 seconds to jump...</h1>" +
	        						"</div>" +
	        						"</body>" +
	        						"</html>");
		  }else{
			  response.setHeader("Refresh",newURL_org_2);
				PrintWriter registerFail_3 = response.getWriter();
		    	response.setContentType("text/html");
		        registerFail_3.println("<?xml version='1.0' encoding='UTF-8' ?>" +
		        					"<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Frameset//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd'>" +
		        						"<html xmlns='http://www.w3.org/1999/xhtml'>" +
		        						"<head>" +
		        						"<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />" +
		        						"<title>Registered Success</title>" +
		        						"<link href='css/bootstrap.min.css' rel='stylesheet'></link>" +
		        						"<script src='javascript/jquery.min.js'></script>" +
		        						"<script src='javascript/bootstrap.min.js'></script>" +
		        						"<link href='css/style.css' rel='stylesheet'>" +
		        						"</head>" +
		        						"<body>" +
		        						"<div class='page-header'>" +
		        						"<h1 class='text-center lead'>" + " Registered failed (Write Password Failed)! " + "! 3 seconds to jump...</h1>" +
		        						"</div>" +
		        						"</body>" +
		        						"</html>");
		  }
	  }else{
		  response.setHeader("Refresh",newURL_org_2);
		  
			PrintWriter registerFail_2 = response.getWriter();
	    	response.setContentType("text/html");
	        registerFail_2.println("<?xml version='1.0' encoding='UTF-8' ?>" +
	        					"<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Frameset//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd'>" +
	        						"<html xmlns='http://www.w3.org/1999/xhtml'>" +
	        						"<head>" +
	        						"<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />" +
	        						"<title>Registered Failed</title>" +
	        						"<link href='css/bootstrap.min.css' rel='stylesheet'></link>" +
	        						"<script src='javascript/jquery.min.js'></script>" +
	        						"<script src='javascript/bootstrap.min.js'></script>" +
	        						"<link href='css/style.css' rel='stylesheet'>" +
	        						"</head>" +
	        						"<body>" +
	        						"<div class='page-header'>" +
	        						"<h1 class='text-center lead'>Two passwords are not consistent " + "! 3 seconds to jump...</h1>" +
	        						"</div>" +
	        						"</body>" +
	        						"</html>");
	  }
  }
  
  @Override
  protected void doPost(HttpServletRequest request,
		             HttpServletResponse response)
      throws ServletException, IOException {
	  doGet(request, response);
  }
  
  /*将用户名和密码写入文件*/
  public boolean writePwd(String inputID, String inputPwd, String fileName){
	  try{
		  	 String data = inputID + "/+/" + inputPwd + "&";
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
