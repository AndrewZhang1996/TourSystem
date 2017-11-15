package Administrator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
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
 * 类：AddRoadServlet()
 * 功能：添加路线
 * */
@WebServlet("/AddRoadServlet")
public class AddRoadServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
	  
	  
	  
	  String rootFolder = request.getSession().getServletContext().getRealPath("");
	  String map_file = rootFolder + "map.txt";
	  int count = 0;
	  String line = null;
	  String line_1 = null;
	  String str_origin = null;
	  String str_1 = null;
	  int offset = 0;
	  String chapter = "&";
	  
	  String fromSpot = request.getParameter("fromSpot");
	  String toSpot = request.getParameter("toSpot");
	  String distance = request.getParameter("distance");
	  String walkingTime = request.getParameter("walkingTime");
	  System.out.println(fromSpot);
	  System.out.println(toSpot);
	  
	  /*输入的数据*/
  	  String data = fromSpot + " " + toSpot + " " + distance + " "  + walkingTime;
	  
  	  File file = new File(map_file);
	  if(!file.exists()){
             file.createNewFile();
	  }
	  
	  /*读出原文件内容*/
	  BufferedReader readInfo = new BufferedReader(new FileReader(map_file));
	  while((line = readInfo.readLine())!=null)
      {
		  if(count == 0) {str_1 = line + "&";}
		  else{str_1 = str_1 + line + "&";}
		  count++;
      }
      readInfo.close();
      
	  
      str_1 = str_1.substring(0, str_1.indexOf("$"));
      
      str_1 = str_1 + data + "&$";
      str_origin = str_1;
      
      
      FileWriter fileWriter =new FileWriter(file);
      fileWriter.write("");
      fileWriter.flush();
      fileWriter.close();
	  
      /*重新写入新内容*/
	  FileWriter writer = new FileWriter(map_file, true);
	  
	  while((offset = str_origin.indexOf(chapter, offset)) != -1){
	  
		  line_1 = str_1.substring(0, str_1.indexOf("&"));
		  str_1 = str_1.substring(str_1.indexOf("&") + 1);
		  
		  writer.write(line_1);
		  writer.write("\r\n");
		  
		  offset = offset + chapter.length();
          count++;
	  }
	  
      writer.write("$");
      writer.close();
      
      
      /*生成中间页*/
      String currentURL = request.getParameter("adID_2");
      System.out.println(currentURL);
      String inputAnnounID = currentURL.substring(currentURL.indexOf("adID")+5);
	  String newURL_1 = "Administrator.jsp?adID=" + inputAnnounID;
	  String newURL_2 = "3;url='" + newURL_1 + "'";
	  response.setHeader("Refresh",newURL_2);
	  PrintWriter addSucc = response.getWriter();
  	  response.setContentType("text/html");
      addSucc.println("<?xml version='1.0' encoding='UTF-8' ?>" +
      					"<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Frameset//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd'>" +
      						"<html xmlns='http://www.w3.org/1999/xhtml'>" +
      						"<head>" +
      						"<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />" +
      						"<title>Add Road Success</title>" +
      						"<link href='css/bootstrap.min.css' rel='stylesheet'></link>" +
      						"<script src='javascript/jquery.min.js'></script>" +
      						"<script src='javascript/bootstrap.min.js'></script>" +
      						"<link href='css/style.css' rel='stylesheet'>" +
      						"</head>" +
      						"<body>" +
      						"<div class='page-header'>" +
      						"<h1 class='text-center lead'> Add Road Success! " + "! 3 seconds to jump...</h1>" +
      						"</div>" +
      						"</body>" +
      						"</html>");
  }
  
  @Override
  protected void doPost(HttpServletRequest request,
		  	        HttpServletResponse response){
	  
  }
}
