<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.io.*, Administrator.*, java.util.Scanner" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Administrator</title>
<link href="css/bootstrap.min.css" rel="stylesheet"></link>
<script src="javascript/jquery.min.js"></script>
<script src="javascript/bootstrap.min.js"></script>
<script src="javascript/toursystem.js"></script> 
<link href="css/style.css" rel="stylesheet">
</head>

    <body onLoad="getLangDate(),createGraph()">

<ol class="breadcrumb" >
	<li><a href="HomePage.html">Home</a></li>
    <li><a href="#">Contact us</a></li>
    <% String currentURL = null;
       String currentID = null;
       currentURL = request.getQueryString();
       currentID = currentURL.substring(currentURL.indexOf("adID")+5);
       %>
    <li>Welcome! <%=currentID %></li>
    <li id="dateStr" class="word_grey"></li>
</ol>

<!-- 第一层导航栏 -->
<ul class="nav nav-tabs" id="Tab_1">
  <li class="active"><a href="#map" data-toggle="tab">Scenic spots map</a></li>
  <li ><a href="#parking" data-toggle="tab">Parking Lot</a></li>
  <li ><a href="#announcement" data-toggle="tab">Announcement</a></li>
  <li> <a href="#reg" data-toggle="tab">Registration</a></li>
</ul>
 
<div class="tab-content">
			
			<div class="tab-pane" id="reg" >
			<div id="wrongPwd"></div>
			<div id="registerForm">
				<form action="RegisterServlet" class="form-horizontal">
				
					  <div class="control-group">
					    <label class="control-label" for="inputID">ID</label>
					    <div class="controls">
					      <input type="text" id="inputID" placeholder="ID" name = "inputID">
					    </div>
					  </div>
					  <div class="control-group">
					    <label class="control-label" for="inputPassword">Password</label>
					    <div class="controls">
					      <input type="password" id="inputPassword" placeholder="Password" name="inputPassword">
					    </div>
					  </div>
					  <div class="control-group">
					    <label class="control-label" for="inputPassword">Password Again</label>
					    <div class="controls">
					      <input type="password" id="inputPasswordAgain" placeholder="Password Again" name="inputPasswordAgain">
					    </div>
					  </div>
					  <input id="adID_reg" name="adID_reg" type="hidden">
						    <script>

									$("#adID_reg").val(document.location.href);

							</script>
					  <div class="control-group">
					    <div class="controls">
					      
					      <button type="submit" class="btn" id="register-btn">Registered</button>
					    </div>
					  </div>
					</form>
					</div>
				</div> 
			
			<!-- 地图相关选项 -->
			  <div class="tab-pane active" id="map">
			  
			  <div class="btn-group">
  					<button class="btn btn-link" onClick="createGraph()" style="margin-left:15px">Map</button>
  					<button class="btn btn-link" onClick="showAddSpotForm()" >Add a Spot</button>
  					<button class="btn btn-link" onClick="showDeleteSpotForm()">Delete a Spot</button>
  					<button class="btn btn-link" onClick="showAddRoadForm()" >Add a Roads</button>
  					<button class="btn btn-link" onClick="showDeleteRoadForm()">Delete a Road</button>
				</div>
			  <!-- 第二层导航栏 更改景点和路 -->
			  	
				<!-- 显示地图 -->
   				 <div id="myCanvasParent" style="height:100%; margin-left:50px">
						<canvas id="myCanvas" style="border:0px solid #000000; width:100%" width="1313px" height="555px"></canvas>
					</div> 
					
					<!-- 添加一个景点 -->
					<div class="modal fade" id="addSpotModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
						<div class="modal-content">
						<div class="modal-header">
						<button type="button" class="close"  aria-hidden="true">
						×
						</button>
						<h4 class="modal-title" id="myModalLabel">
						Add Scenic Spot
						</h4>
						</div>
						<div class="modal-body">
							<form role="form" action="AddSpotServlet">
								<div class="form-group">
									<label for="spotName">Spot Name</label>
									<input type="text" class="form-control" id="spotName" name="spotName"
										   placeholder="Please input spot name">
									<label for="spotIntro">Introduction</label>
									<input type="text" class="form-control" id="spotIntro" name="spotIntro"
										   placeholder="Please input introduction">
									<label for="spotPopu">Popularity</label>
									<input type="text" class="form-control" id="spotPopu" name="spotPopu"
										   placeholder="Please input Popularity (1-100)">
									<label for="spotRest">Rest Area</label>
									<!-- <input type="text" class="form-control" id="spotRest" 
										   placeholder="Please input rest area"> -->
									<select name="spotRest" id="spotRest">
  										   <option>Yes</option>
  										   <option>No</option>
									</select>
									<label for="spotToilet">Toilet</label>
									<!-- <input type="text" class="form-control" id="spotToilet" 
										   placeholder="Please input rest area"> -->
									<select name="spotToilet" id="spotToilet">
  										   <option>Yes</option>
  										   <option>No</option>
									</select>
									<input id="adID_1" name="adID_1" type="hidden">
						            <script>

									    $("#adID_1").val(document.location.href);

							        </script>
								</div>
								<div class="modal-footer">
							<button type="submit" class="btn btn-primary" >Submit</button>
						</div>
							</form>
						</div>
						
						
						</div>
						</div>
					</div>
					
					<!-- 删除一个景点 -->
					<div class="modal fade" id="deleteSpotModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
						<div class="modal-content">
						<div class="modal-header">
						<button type="button" class="close"  aria-hidden="true">
						×
						</button>
						<h4 class="modal-title" id="myModalLabel">
						Delete Scenic Spot
						</h4>
						</div>
						<div class="modal-body">
							<form role="form" action="DeleteSpotServlet">
								<div class="form-group">
									<label for="spotName">Spot Name</label>
									<select id="deleteSpot" name="deleteSpot">
									   <% String fileName_spots_delete = request.getRealPath("/info.txt");  
											FileInputStream in_spots_delete = new FileInputStream(fileName_spots_delete);  
								            InputStreamReader inReader_delete = new InputStreamReader(in_spots_delete, "UTF-8");  
								            BufferedReader bufReader_delete = new BufferedReader(inReader_delete);  
								            String line_spots_delete = null;  
								            String[] infos_delete = null;
								            
								            while(!(line_spots_delete = bufReader_delete.readLine()).contains("$")){  
								            	infos_delete = line_spots_delete.split(" ");
												   %>
												   <option><%=infos_delete[0] %></option> 
								          		
									   <%  }
									     bufReader_delete.close();  
							            inReader_delete.close();  
							            in_spots_delete.close(); 
									    %> 
									</select>
									<input id="adID_delete" name="adID_delete" type="hidden">
						            <script>

									    $("#adID_delete").val(document.location.href);

							        </script>
								</div>
								<div class="modal-footer">
							<button type="submit" class="btn btn-primary" >Submit</button>
						</div>
							</form>
						</div>
						
						
						</div>
						</div>
					</div>
					
				    <!-- 添加一条路 -->
					<div class="modal fade" id="addRoadModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
						<div class="modal-content">
						<div class="modal-header">
						<button type="button" class="close"  aria-hidden="true">
						×
						</button>
						<h4 class="modal-title" id="myModalLabel">
						Add Road
						</h4>
						</div>
						<div class="modal-body">
							<form role="form" action="AddRoadServlet">
								<div class="form-group">
									<label for="fromSpot">From</label>
									<select id="fromSpot" name="fromSpot">
									   <% String fileName_spots = request.getRealPath("/info.txt");  
											FileInputStream in_spots = new FileInputStream(fileName_spots);  
								            InputStreamReader inReader = new InputStreamReader(in_spots, "UTF-8");  
								            BufferedReader bufReader = new BufferedReader(inReader);  
								            String line_spots = null;  
								            String[] infos_spots = null;
								            
								            while(!(line_spots = bufReader.readLine()).contains("$")){  
								            	infos_spots = line_spots.split(" ");
												   %>
												   <option><%=infos_spots[0] %></option> 
								          		
									   <%  }
									     bufReader.close();  
							            inReader.close();  
							            in_spots.close(); 
									    %> 
									</select>
									<label for="toSpot">To</label>
									<select id="toSpot" name="toSpot">
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
									<label for="distance">Distance (hundreds meters)</label>
									<input type="text" class="form-control" id="distance" name="distance"
										   placeholder="Please input distance">
									<label for="walkingTime">Walking-time (minutes)</label>
									<input type="text" class="form-control" id="walkingTime" name="walkingTime"
										   placeholder="Please input walking time">
									<input id="adID_2" name="adID_2" type="hidden">
						            <script>

									    $("#adID_2").val(document.location.href);

							        </script>
								</div>
								<div class="modal-footer">
							<button type="submit" class="btn btn-primary" >Submit</button>
						</div>
							</form>
						</div>
						
						
						</div>
						</div>
					</div> 
					
					<!-- 删除一条路 -->
					<div class="modal fade" id="deleteRoadModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
						<div class="modal-content">
						<div class="modal-header">
						<button type="button" class="close"  aria-hidden="true">
						×
						</button>
						<h4 class="modal-title" id="myModalLabel">
						Delete Road
						</h4>
						</div>
						<div class="modal-body">
							<form role="form" action="DeleteRoadServlet">
								<div class="form-group">
									<label for="fromSpot">From</label>
									<select id="delRoad_start" name="delRoad_start">
									   <% String fileName_delRoad_start = request.getRealPath("/info.txt");  
											FileInputStream in_delRoad_start = new FileInputStream(fileName_delRoad_start);  
								            InputStreamReader inReader_delRoad_start = new InputStreamReader(in_delRoad_start, "UTF-8");  
								            BufferedReader bufReader_delRoad_start = new BufferedReader(inReader_delRoad_start);  
								            String line_delRoad_start = null;  
								            String[] infos_delRoad_start = null;
								            
								            while(!(line_delRoad_start = bufReader_delRoad_start.readLine()).contains("$")){  
								            	infos_delRoad_start = line_delRoad_start.split(" ");
												   %>
												   <option><%=infos_delRoad_start[0] %></option> 
								          		
									   <%  }
								            bufReader_delRoad_start.close();  
									     inReader_delRoad_start.close();  
							            in_delRoad_start.close(); 
									    %> 
									</select>
									<label for="toSpot">To</label>
									<select id="delRoad_end" name="delRoad_end">
										 <% String fileName_delRoad_end = request.getRealPath("/info.txt");  
											FileInputStream in_delRoad_end = new FileInputStream(fileName_delRoad_end);  
								            InputStreamReader inReader_delRoad_end = new InputStreamReader(in_delRoad_end, "UTF-8");  
								            BufferedReader bufReader_delRoad_end = new BufferedReader(inReader_delRoad_end);  
								            String line_delRoad_end = null;  
								            String[] infos_delRoad_end = null;
								            
								            while(!(line_delRoad_end = bufReader_delRoad_end.readLine()).contains("$")){  
								            	infos_delRoad_end = line_delRoad_end.split(" ");
												   %>
												   <option><%=infos_delRoad_end[0] %></option> 
								          		
									   <%  }
								            bufReader_delRoad_end.close();  
									     inReader_delRoad_end.close();  
							            in_delRoad_end.close(); 
									    %> 
									</select>
									<input id="adID_delRoad" name="adID_delRoad" type="hidden">
						            <script>

									    $("#adID_delRoad").val(document.location.href);

							        </script>
								</div>
								<div class="modal-footer">
							<button type="submit" class="btn btn-primary" >Submit</button>
						</div>
							</form>
						</div>
						
						
						</div>
						</div>
					</div> 
					
					
					
			  </div>
				  <!-- 停车场相关选项 -->
				  	<div class="tab-pane" id="parking">
				  	
				  	<div class="btn-group">
  					<button class="btn btn-link" onClick="showInitForm()" style="margin-left:15px">Initialise</button>
  					<button class="btn btn-link" onClick="showAddParkForm()" style="margin-left:15px">Car In</button>
  					<button class="btn btn-link" onClick="showDeleteParkForm()" style="margin-left:15px">Car Out</button>
				</div>
			  <!-- 第二层导航栏 更改景点和路 -->
			  	
				<!-- 显示地图 -->
   				 <div id="myCanvasParent_2" style="height:100%; margin-left:50px">
						<canvas id="myCanvas_2" style="border:0px solid #000000; width:100%" width="1313px" height="900px"></canvas>
					</div> 
				  	
				  	
				  	
				  	
				  	<!-- 填写停车场最大容量 -->
					<div class="modal fade" id="initParkingModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
						<div class="modal-content">
						<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						×
						</button>
						<h4 class="modal-title" id="myModalLabel">
						填写停车场最大容量
						</h4>
						</div>
						<div class="modal-body">
							<form role="form">
								<div class="form-group">
									<label for="keyWord">最大容量</label>
									<input type="text" class="form-control" id="parkSize" 
										   placeholder="请输入最大容量">
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-primary" data-dismiss="modal" onClick="initParking()">提交</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						</div>
						</div>
						</div>
					</div>
					<!-- 填写到达车辆信息 -->
					<div class="modal fade" id="addParkModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
						<div class="modal-content">
						<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						×
						</button>
						<h4 class="modal-title" id="myModalLabel">
						填写到达车辆信息
						</h4>
						</div>
						<div class="modal-body">
							<form role="form">
								<div class="form-group">
									<label for="keyWord">车牌号</label>
									<input type="text" class="form-control" id="number" 
										   placeholder="请输入车牌号">
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-primary" data-dismiss="modal" onClick="addPark()">提交</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						</div>
						</div>
						</div>
					</div>
					<!-- 填写离开车辆信息 -->
					<div class="modal fade" id="deleteParkModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
						<div class="modal-content">
						<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						×
						</button>
						<h4 class="modal-title" id="myModalLabel">
						填写离开车辆信息
						</h4>
						</div>
						<div class="modal-body">
							<form role="form">
								<div class="form-group">
									<label for="keyWord">离开车辆车牌号</label>
									<input type="text" class="form-control" id="delNumber" 
										   placeholder="请输入离开车辆车牌号">
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-primary" data-dismiss="modal" onClick="deletePark()">提交</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						</div>
						</div>
						</div>
					</div>
				  	
				  	</div>
  	
  	
  	<!-- 通知相关选项 -->
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
			 		<div id="announcementMessage_deliver">
			 		<form role="form"  action="AnnouncementServlet">
					  <div class="form-group">
					    <label for="name" class="lead">Deliver An Announcement</label>
					    <textarea class="form-control" id="announTextArea" name="inputAnnouncement"></textarea>
					    
						    <!-- <div class="controls">
						      <input type="text" name="inputAnnounID" id="inputAnnounID" placeholder="Your ID">
						    </div> -->
						    <input id="adID" name="adID" type="hidden">
						    <script>

									$("#adID").val(document.location.href);

							</script>
						  
					    <div class="controls">
					  <button type="submit" class="btn">Deliver</button>
			  </div>
			</form>
			</div>
			</div>
		</div>
	
		
	
	
</div>
<script>
$(function(){
	//setInterval("getCarNum_pl()",1000);
	});
	
$.ajax({
	url:"ParkingLot",
})
	
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
 

    </body>
