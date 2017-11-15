<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.io.*, Administrator.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tourist</title>
<link href="css/bootstrap.min.css" rel="stylesheet"></link>
<script src="javascript/jquery.min.js"></script>
<script src="javascript/bootstrap.min.js"></script>
<script src="javascript/toursystem.js"></script> 
<link href="css/style.css" rel="stylesheet">
</head>

<body onLoad="getLangDate(),createGraph()">
    <!-- 顶部导航栏 -->
<ol class="breadcrumb">
	<li><a href="HomePage.html">Home</a></li>
    <li><a href="Contact.html">Contact us</a></li>
    <li id="dateStr" class="word_grey"></li>
</ol>


    
    <!-- 第一层选项 查看景区地图、路线图及搜索最短路线 -->
    <ul class="nav nav-tabs" id="Tab_1">
  <li class="active" onClick="createGraph()"><a href="#map" data-toggle="tab">Scenic Spots Map</a></li>
  <li  onClick="createGraph_1()"><a href="#route" data-toggle="tab">Route Map</a></li>
  <li  onClick="createGraph_2()"><a href="#minRoute" data-toggle="tab">Search For Shortest Route</a></li>
  <li onClick="createGraph_3()"><a href="#sorting" data-toggle="tab">Sorting</a></li>
  <li ><a href="#announcement" data-toggle="tab">Announcement</a></li>
</ul>
 
<div class="tab-content">
<!-- 查看景区地图 -->
<div class="tab-pane active" id="map">
 <div class="btn-group">
  <button class="btn btn-link" onClick="createGraph()" style="margin-left:15px">Map</button>
  <button class="btn btn-link" onClick="showSearchForm()" >Search Spot</button>
</div>
 
   				 <!-- 显示地图 -->
   				 <div id="myCanvasParent" style="height:100%; margin-left:50px">
						<canvas id="myCanvas" style="border:0px solid #000000; width:100%" width="1313px" height="555px"></canvas>
					</div> 
    	
   			  
 

  <div class="modal fade" id="searchModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
		<div class="modal-content">
		<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		×
		</button>
		<h4 class="modal-title" id="myModalLabel">
		Search!
		</h4>
		</div>
		<div class="modal-body">
			<form role="form">
				<div class="form-group">
					<label for="keyWord">Keywords</label>
					<input type="text" class="form-control" id="keyWord" 
						   placeholder="Please input Keywords">
				</div>
			</form>
		</div>
		<div class="modal-footer">
			<button type="submit" class="btn btn-primary" data-dismiss="modal" onClick="searchArc()">Submit</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		</div>
		</div>
		</div>
	</div>  
	</div>
    		
  <!-- 查看景区路线图 -->
  	<div class="tab-pane " id="route">
  	
  	<div class="btn-group">
  <button class="btn btn-link" onClick="createGraph_1()" style="margin-left:15px">Map</button>
  <button class="btn btn-link" onClick="showTourMapForm()" >Find a Tour Route</button>
</div>
  	
 <div id="myCanvasParent_1" style="height:100%; margin-left:50px">
						<canvas id="myCanvas_1" style="border:0px solid #000000; width:100%" width="1313px" height="900px"></canvas>
					</div> 
  	
  	<div class="modal fade" id="tourListModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
		<div class="modal-content">
		<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		×
		</button>
		<h4 class="modal-title" id="myModalLabel">
		Input Starting Spot
		</h4>
		</div>
		<div class="modal-body">
			<form role="form">
				<div class="form-group">
					<label for="start">Starting Spot</label>
					<!-- <input type="text" class="form-control" id="start" 
						   placeholder="Please input starting spot"> -->
				   <select id="start" name="start">
					 <% String fileName_spots_2 = request.getRealPath("/info.txt");  
						FileInputStream in_spots_2 = new FileInputStream(fileName_spots_2);  
			            InputStreamReader inReader_2 = new InputStreamReader(in_spots_2, "UTF-8");  
			            BufferedReader bufReader_2 = new BufferedReader(inReader_2);  
			            String line_spots_2 = null;  
			            String[] infos_2 = null;
			            
			            while(!(line_spots_2 = bufReader_2.readLine()).contains("$")){  
			            	infos_2 = line_spots_2.split(" ");
							   %>
							   <option><%=infos_2[0] %></option> 
			          		
				   <%  }
				     bufReader_2.close();  
		            inReader_2.close();  
		            in_spots_2.close(); 
				    %> 
				</select>
				</div>
				
				<div class="form-group">
					<label for="end">Ending Spot</label>
					<select id="end" name="end">
						 <% String fileName_spots_3 = request.getRealPath("/info.txt");  
							FileInputStream in_spots_3 = new FileInputStream(fileName_spots_3);  
				            InputStreamReader inReader_3 = new InputStreamReader(in_spots_3, "UTF-8");  
				            BufferedReader bufReader_3 = new BufferedReader(inReader_3);  
				            String line_spots_3 = null;  
				            String[] infos_3 = null;
				            
				            while(!(line_spots_3 = bufReader_3.readLine()).contains("$")){  
				            	infos_3 = line_spots_3.split(" ");
								   %>
								   <option><%=infos_3[0] %></option> 
				          		
					   <%  }
					     bufReader_3.close();  
			            inReader_3.close();  
			            in_spots_3.close(); 
					    %> 
					</select>
				</div>
			</form>
		</div>
		<div class="modal-footer">
			<button type="submit" class="btn btn-primary" data-dismiss="modal" onClick="tourMap()">Submit</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		</div>
		</div>
		</div>
	</div>
  		
  	</div>
  	
  	<!-- 搜索最短路线 -->
  	<div class="tab-pane " id="minRoute">
  	
  	<div class="btn-group">
  <button class="btn btn-link" onClick="createGraph_2()" style="margin-left:15px">Map</button>
  <button class="btn btn-link" onClick="showShortestPathForm()" >Search Shortest Path</button>
</div>
  	
 <div id="myCanvasParent_2" style="height:100%; margin-left:50px">
						<canvas id="myCanvas_2" style="border:0px solid #000000; width:100%" width="1313px" height="555px"></canvas>
					</div> 
					
						<!-- 填写起始点和终止点对话框 -->
	<div class="modal fade" id="shortestPathModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
		<div class="modal-content">
		<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		×
		</button>
		<h4 class="modal-title" id="myModalLabel">
		Input starting and ending spot
		</h4>
		</div>
		<div class="modal-body">
			<form role="form">
				<div class="form-group">
					<label for="startName">Starting Spot</label>
					<select id="startName" name="startName">
									   <% String fileName_spots = request.getRealPath("/info.txt");  
											FileInputStream in_spots = new FileInputStream(fileName_spots);  
								            InputStreamReader inReader = new InputStreamReader(in_spots, "UTF-8");  
								            BufferedReader bufReader = new BufferedReader(inReader);  
								            String line_spots = null;  
								            String[] infos = null;
								            
								            while(!(line_spots = bufReader.readLine()).contains("$")){  
								            	infos = line_spots.split(" ");
												   %>
												   <option><%=infos[0] %></option> 
								          		
									   <%  }
									     bufReader.close();  
							            inReader.close();  
							            in_spots.close(); 
									    %> 
					</select>
				</div>
				<div class="form-group">
					<label for="endName">Ending Spot</label>
					<!-- <input type="text" class="form-control" id="endName" 
						   placeholder="Please input ending spot"> -->
					<select id="endName" name="endName">
						 <% String fileName_spots_1 = request.getRealPath("/info.txt");  
							FileInputStream in_spots_1 = new FileInputStream(fileName_spots_1);  
				            InputStreamReader inReader_1 = new InputStreamReader(in_spots_1, "UTF-8");  
				            BufferedReader bufReader_1 = new BufferedReader(inReader_1);  
				            String line_spots_1 = null;  
				            String[] infos_1 = null;
				            
				            while(!(line_spots_1 = bufReader_1.readLine()).contains("$")){  
				            	infos_1 = line_spots_1.split(" ");
								   %>
								   <option><%=infos_1[0] %></option> 
				          		
					   <%  }
					     bufReader_1.close();  
			            inReader_1.close();  
			            in_spots_1.close(); 
					    %> 
					</select>
				</div>
			</form>
		</div>
		<div class="modal-footer">
			<button type="submit" class="btn btn-primary" data-dismiss="modal" onClick="shortestPath()">Submit</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		</div>
		</div>
		</div>
	</div>
  	
	
</div>

<div class="tab-pane" id="sorting">
	<div class="btn-group">
  <button class="btn btn-link" onClick="createGraph_3()" style="margin-left:15px">Map</button>
  <button class="btn btn-link" onClick="showOrderArcForm()" >Sorting</button>
</div>
  	
 <div id="myCanvasParent_3" style="height:100%; margin-left:50px">
						<canvas id="myCanvas_3" style="border:0px solid #000000; width:100%" width="1313px" height="555px"></canvas>
					</div> 
					
		<!-- 选择景点排序方式 -->
	<div class="modal fade" id="orderArcModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
				×
				</button>
				<h4 class="modal-title" id="myModalLabel">
				Choose Sorting way
				</h4>
				</div>
				<div class="modal-body">
					<form role="form">
						<div class="form-group">
					    <label for="name">Sorting Way</label>
					    <select class="form-control" id="selectStyle">
					      <option>按欢迎度</option>
					      <option>按路口数</option>
					    </select>
					  </div>
					</form>
				</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-primary" data-dismiss="modal" onClick="orderArc()">Submit</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
		</div>
	</div>

</div>

<div class="tab-pane " id="announcement" >
				<div id="announcementMessage">
				 		<div id="announcementMessage_current">
				 		<table class="table table-bordered" >
				  <caption class="lead">Current Announcement</caption>
				  <thead>
				    <tr>
				      <th>Administrator ID</th>
				      <th>Date</th>
				      <th>Announcement</th>
				    </tr>
				  </thead>
				  <tbody>
				  	<% String fileName = request.getRealPath("/file/Announcement.txt"); 
				  	   String file = fileName;
			           BufferedReader in = new BufferedReader(new FileReader(file));
			           String line = null;
			           String str_origin = "";
			           String str = "";
			           String str_tem = "";
			           while((line = in.readLine())!=null)
			           {
			             str+=line;
			           }
			           in.close();
			           
			           str_origin = str;
			           String chapter = "&@#";
			           int count = 0;
			           int offset = 0;
			           
			           while((offset = str_origin.indexOf(chapter, offset)) != -1){
			           	   str_tem = str.substring(0, str.indexOf("/+/"));
			           	   %>
			        	   <tr>
			        	   <td><%=str_tem %></td>
			        	   <% str = str.substring(str_tem.length() + 3); 
			        	   	  str_tem = str.substring(0, str.indexOf("/+/")); %>
			        	   <td><%=str_tem %></td>
			        	   <% str = str.substring(str_tem.length() + 3); 
			        	   	  str_tem = str.substring(0, str.indexOf("&@#")); %>
			        	   <td><%=str_tem %></td>
			        	   <% str = str.substring(str_tem.length() + 3); %>
			        	   </tr>
			   
			             <%offset = offset + chapter.length();
			               System.out.println(offset);
			               count++;
			           }
			          System.out.println(count + " " + offset);
			          
			           
				  	%>
				  </tbody>
				  
				</table>
				</div>
				</div>
				</div>
				</div>
				
				
				

    </body>
</html>

<script>
function getLangDate(){
	var dateObj = new Date(); //表示当前系统时间的Date对象 
	var year = dateObj.getFullYear(); //当前系统时间的完整年份值
	var month = dateObj.getMonth()+1; //当前系统时间的月份值 
	var date = dateObj.getDate(); //当前系统时间的月份中的日
	var day = dateObj.getDay(); //当前系统时间中的星期值
	var weeks = ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];
	var week = weeks[day]; //根据星期值，从数组中获取对应的星期字符串 
	var hour = dateObj.getHours(); //当前系统时间的小时值 
	var minute = dateObj.getMinutes(); //当前系统时间的分钟值
	var second = dateObj.getSeconds(); //当前系统时间的秒钟值
	//如果月、日、小时、分、秒的值小于10，在前面补0
	if(month == 1) month = "January";
	if(month == 2) month = "February";
	if(month == 1) month = "March";
	if(month == 1) month = "April";
	if(month == 1) month = "May";
	if(month == 1) month = "June";
	if(month == 1) month = "July";
	if(month == 1) month = "August";
	if(month == 9) month = "September";
	if(month == 1) month = "October";
	if(month == 1) month = "November";
	if(month == 1) month = "December";
	if(date == 1) date = "1st";
	if(date == 2) date = "2nd";
	if(date == 3) date = "3rd";
	if(date > 3) date = date + "th";
	if(hour<10){
	hour = "0"+hour;
	}
	if(minute<10){
	minute = "0"+minute;
	}
	if(second<10){
	second = "0"+second;
	}
	
	var newDate = hour+":"+minute+":"+second + " " + week + " " + date+" " +month+" " + year;
	document.getElementById("dateStr").innerHTML = newDate;
	setTimeout("getLangDate()",1000);//每隔1秒重新调用一次该函数 
	}
</script>