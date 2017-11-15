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
 * 类：DeleteRoadServlet()
 * 功能：删除路线
 * */
@WebServlet("/DeleteRoadServlet")
public class DeleteRoadServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
	  
	  
	  
	  String rootFolder = request.getSession().getServletContext().getRealPath("");
	  String map_file = rootFolder + "map.txt";
	  String chapter = "&";
	  
	  String delRoad_start = request.getParameter("delRoad_start");
	  String delRoad_end = request.getParameter("delRoad_end");
	  String delData_1 = delRoad_start + " " + delRoad_end;
	  String delData_2 = delRoad_end + " " + delRoad_start;
      
      String line_map = null;
      String str_1_map = null;
      String str_origin_map = null;
      int count_map = 0;
      
      File file_map = new File(map_file);
	  if(!file_map.exists()){
             file_map.createNewFile();
	  }
	  
	  /*读出除删除内容以外的信息*/
	  BufferedReader readInfo_map = new BufferedReader(new FileReader(map_file));
	  while((line_map = readInfo_map.readLine())!=null)
      {
		  if(line_map.contains(delData_1)||line_map.contains(delData_2)){
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
	  
      /*写入新的内容*/
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
      String currentURL = request.getParameter("adID_delRoad");
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
      						"<title>Delete Road Success</title>" +
      						"<link href='css/bootstrap.min.css' rel='stylesheet'></link>" +
      						"<script src='javascript/jquery.min.js'></script>" +
      						"<script src='javascript/bootstrap.min.js'></script>" +
      						"<link href='css/style.css' rel='stylesheet'>" +
      						"</head>" +
      						"<body>" +
      						"<div class='page-header'>" +
      						"<h1 class='text-center lead'> Delete Road Success! " + "! 3 seconds to jump...</h1>" +
      						"</div>" +
      						"</body>" +
      						"</html>");
  }
  
  @Override
  protected void doPost(HttpServletRequest request,
		  	        HttpServletResponse response){
	  
  }
}
