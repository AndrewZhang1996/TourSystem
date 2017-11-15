package Administrator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 类：LoginServlet()
 * 功能：登陆管理员系统
 * */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
	  
	  String rootFolder = request.getSession().getServletContext().getRealPath("");
	  String fileName = null;
	  String inputID = request.getParameter("inputID");
	  String inputPassword = request.getParameter("inputPassword");
	  String newURL_1 = null;
	  String newURL_2 = null;
	  
	  fileName = rootFolder + "file/Administrator.txt";
	  System.out.println(rootFolder);
	  System.out.println(LoginCheck(inputID, inputPassword, fileName));
	  newURL_1 = "Administrator.jsp?adID=" + inputID;
	  newURL_2 = "3;url='" + newURL_1 + "'";
	  
	  /*生成中间页（用户名和密码是否正确）*/
	  if(LoginCheck(inputID, inputPassword, fileName)){
		  response.setHeader("Refresh",newURL_2);
			PrintWriter loginSucc = response.getWriter();
	    	response.setContentType("text/html");
	    	String title = "Login Success!";
	        loginSucc.println("<?xml version='1.0' encoding='UTF-8' ?>" +
	        					"<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Frameset//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd'>" +
	        						"<html xmlns='http://www.w3.org/1999/xhtml'>" +
	        						"<head>" +
	        						"<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />" +
	        						"<title>" + title +"</title>" +
	        						"<link href='css/bootstrap.min.css' rel='stylesheet'></link>" +
	        						"<script src='javascript/jquery.min.js'></script>" +
	        						"<script src='javascript/bootstrap.min.js'></script>" +
	        						"<link href='css/style.css' rel='stylesheet'>" +
	        						"</head>" +
	        						"<body>" +
	        						"<div class='page-header'>" +
	        						"<h1 class='text-center lead'>Welcome " + inputID + "! 3 seconds to jump...</h1>" +
	        						"</div>" +
	        						"</body>" +
	        						"</html>");
	  }else{
		  response.setHeader("Refresh","3;url='Login.html'");
			PrintWriter loginFail = response.getWriter();
	    	response.setContentType("text/html");
	    	String title = "Login Failed!";
	        loginFail.println("<?xml version='1.0' encoding='UTF-8' ?>" +
	        					"<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Frameset//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd'>" +
	        						"<html xmlns='http://www.w3.org/1999/xhtml'>" +
	        						"<head>" +
	        						"<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />" +
	        						"<title>" + title +"</title>" +
	        						"<link href='css/bootstrap.min.css' rel='stylesheet'></link>" +
	        						"<script src='javascript/jquery.min.js'></script>" +
	        						"<script src='javascript/bootstrap.min.js'></script>" +
	        						"<link href='css/style.css' rel='stylesheet'>" +
	        						"</head>" +
	        						"<body>" +
	        						"<div class='page-header'>" +
	        						"<h1 class='text-center lead'>Wrong ID or Password! 3 seconds to jump...</h1>" +
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
  
  /**
   * 方法：LoginCheck()
   * 功能：检查用户名和密码是否正确
   * */
  public boolean LoginCheck(String inputID, String inputPassword, String fileName){
	  
	  	String ID = inputID;
	  	String password = inputPassword;
		boolean flag = false;
		String originalPwd = null;
		
		try
      {
          String file = fileName;
          BufferedReader in = new BufferedReader(new FileReader(file));
          String line = null;
          String str = "";
          while((line = in.readLine())!=null)
          {
           str+=line;
          }
          in.close();
          
          if(str.contains(ID)){
          	
          	String begin = ID;
          	String end = "&";
          	int i = str.indexOf(begin);
          	if(i!=1)
          	{
          		str = str.substring(i);
          		System.out.println(str);
          	}
          	int j = str.indexOf(end);
          	originalPwd = str.substring(ID.length()+3, j);
          	if(originalPwd.equals(password)){
          		flag = true;
          	}
          }
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
		return flag;
	}
}

