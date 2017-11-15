package Administrator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 类：DeleteSpotServlet()
 * 功能：删除景点
 * */
@WebServlet("/DeleteSpotServlet")
public class DeleteSpotServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
	  
	  
	  
	  String rootFolder = request.getSession().getServletContext().getRealPath("");
	  String info_file = rootFolder + "info.txt";
	  String map_file = rootFolder + "map.txt";
	  int count = 0;
	  String line = null;
	  String line_1 = null;
	  String str_origin = null;
	  String str_1 = null;
	  int offset = 0;
	  String chapter = "&";
	  
	  String deleteSpot = request.getParameter("deleteSpot");
	  
	  
  	  File file = new File(info_file);
	  if(!file.exists()){
             file.createNewFile();
	  }
	  
	  /*读出除删除内容之外的信息*/
	  BufferedReader readInfo = new BufferedReader(new FileReader(info_file));
	  while((line = readInfo.readLine())!=null)
      {
		  if(line.startsWith(deleteSpot)){
				  continue;
		  }else{
		  if(count == 0) {str_1 = line + "&";}
		  else{str_1 = str_1 + line + "&";}
		  count++;
		  }
      }
      readInfo.close();
      
      str_origin = str_1;
      
      
      FileWriter fileWriter =new FileWriter(file);
      fileWriter.write("");
      fileWriter.flush();
      fileWriter.close();
	  
	  /*写入新内容*/
	  FileWriter writer = new FileWriter(info_file, true);
	  
	  while((offset = str_origin.indexOf(chapter, offset)) != -1){
	  
		  line_1 = str_1.substring(0, str_1.indexOf("&"));
		  str_1 = str_1.substring(str_1.indexOf("&") + 1);
		  
		  writer.write(line_1);
		  writer.write("\r\n");
		  
		  offset = offset + chapter.length();
          count++;
	  }
	  
      writer.close();
      
      String line_map = null;
      String str_1_map = null;
      String str_origin_map = null;
      int count_map = 0;
      
      File file_map = new File(map_file);
	  if(!file_map.exists()){
             file_map.createNewFile();
	  }
	  
	  /*删除相关路线*/
	  BufferedReader readInfo_map = new BufferedReader(new FileReader(map_file));
	  while((line_map = readInfo_map.readLine())!=null)
      {
		  if(line_map.contains(deleteSpot)){
			  continue;
		  }else{
		  if(count_map == 0) {str_1_map = line_map + "&";}
		  else{str_1_map = str_1_map + line_map + "&";}
		  System.out.println("KKDD: " + line_map);
		  count_map++;
		  }
      }
      readInfo_map.close();
      
	  
      str_origin_map = str_1_map;
      
      
      
      FileWriter fileWriter_map =new FileWriter(file_map);
      fileWriter_map.write("");
      fileWriter_map.flush();
      fileWriter_map.close();
	  
      int offset_map = 0;
      String line_1_map = null;
	  
	  FileWriter writer_map = new FileWriter(map_file, true);
	  
	  while((offset_map = str_origin_map.indexOf(chapter, offset_map)) != -1){
	  
		  line_1_map = str_1_map.substring(0, str_1_map.indexOf("&"));
		  str_1_map = str_1_map.substring(str_1_map.indexOf("&") + 1);
		  
		  writer_map.write(line_1_map);
		  writer_map.write("\r\n");
		  
		  offset_map = offset_map + chapter.length();
          count_map++;
	  }
	  
      writer_map.close();
      
      
      /*生成中间页*/
      String currentURL = request.getParameter("adID_delete");
      String inputAnnounID = currentURL.substring(currentURL.indexOf("adID")+5);
	  String newURL_1 = "Administrator.jsp?adID=" + inputAnnounID;
	  String newURL_2 = "3;url='" + newURL_1 + "'";
	  response.setHeader("Refresh",newURL_2);
	  PrintWriter deleteSucc = response.getWriter();
  	  response.setContentType("text/html");
      deleteSucc.println("<?xml version='1.0' encoding='UTF-8' ?>" +
      					"<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Frameset//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd'>" +
      						"<html xmlns='http://www.w3.org/1999/xhtml'>" +
      						"<head>" +
      						"<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />" +
      						"<title>Delete Spot Success</title>" +
      						"<link href='css/bootstrap.min.css' rel='stylesheet'></link>" +
      						"<script src='javascript/jquery.min.js'></script>" +
      						"<script src='javascript/bootstrap.min.js'></script>" +
      						"<link href='css/style.css' rel='stylesheet'>" +
      						"</head>" +
      						"<body>" +
      						"<div class='page-header'>" +
      						"<h1 class='text-center lead'> Delete Spot Success! " + "! 3 seconds to jump...</h1>" +
      						"</div>" +
      						"</body>" +
      						"</html>");
  }
  
  @Override
  protected void doPost(HttpServletRequest request,
		  	        HttpServletResponse response){
	  
  }
}
