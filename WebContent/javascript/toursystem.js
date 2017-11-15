var results;
var tourList;
var parkSize;
var first = 0;

function array_diff(a, b) {  
    for(var i=0;i<b.length;i++)  
    {  
      for(var j=0;j<a.length;j++)  
      {  
        if(a[j]==b[i]){  
          a.splice(j,1);  
          j=j-1;  
        }  
      }  
    }   
  return a;  
}  

function paintOrigin(context){
	var rule_1 = createChart_1(context, results.arcNum+1, results.arcNum+1);
	var chartTitle_1 = [];
	for(i=0; i<results.arcNum; i++){
		var arcNode = results.nodes[i];
		var tmp = arcNode.name;
		chartTitle_1[i] = tmp;
	}
	for(i=1; i<results.arcNum+1; i++){
		
		var x = 50 + rule_1[0]*(i + 0.1);
		var y = 20 + rule_1[1]*0.6;
		context.fillText(chartTitle_1[i-1], x, y);
	}
	for(i=1; i<results.arcNum+1; i++){
		var x = 50 + rule_1[0]*0.1;
		var y = 20 + rule_1[1]*(i + 0.6);
		context.fillText(chartTitle_1[i-1], x, y);
	}
	for(i=1; i<results.arcNum+1; i++){
		
		var edges = results.nodes[i-1].edges;
		var edges_index = [];
		var edges_dis = [];
		var edges_no = [];
		
		
		for(j=0; j<edges.length; j++){
			edges_index[j] = edges[j].index; 
			edges_dis[j] = edges[j].dist;
		}
		
		for(j=1; j<results.arcNum+1;j++){
			if(j!=i)
			{
				edges_no[j-1] = j-1;
			}
		}
		
		edges_no = array_diff(edges_no, edges_index);
		
		for(j=0;j<edges_no.length;j++){
			var x = 50 + rule_1[0]*(edges_no[j] + 1.3);
			var y = 20 + rule_1[1]*(i + 0.6);
			context.fillText("32767",x,y);
		}
		
		
		
		for(j=1; j<results.arcNum+1; j++){
			var x = 50 + rule_1[0]*(j + 0.3);
			var y = 20 + rule_1[1]*(i + 0.6);
			if(j == i){
				context.fillText("0", x, y);
			}
			
			for(h=0;h<edges_index.length;h++){
				if(edges_index[h] == (j-1))
				{
					context.fillText(edges_dis[h], x, y);
				} 
			}
		}
	}
	context.closePath();
}

function createGraph(){
	var udata = "";
	$.ajax({url : "createGraph",
		data : udata,
		success : function(data){
			updateCanvas();
			
			var canvas = document.getElementById("myCanvas");
			if(canvas == null){
				return false;
			}
			var context = canvas.getContext('2d');
			
			results = eval(data);

			//绘制原始图
			paintOrigin(context);
			

			//绘制表格，用于展示景点信息
			context.beginPath();
			var rule = createChart(context, 5, results.arcNum+1);
			var chartTitle = ["景点名称", "景点描述", "景点欢迎度", "有无休息区", "有无公厕"];
			for(i=0; i<5; i++){
				var x = 700 + rule[0]*(i + 0.1);
				var y = 20 + rule[1]*0.6;
				context.fillText(chartTitle[i], x, y);
			}
			for(i=0; i<results.arcNum; i++){
				var arcNode = results.nodes[i];
				var nodeInfos = [arcNode.name, arcNode.des, arcNode.pop, arcNode.hasRest==true?"有":"无", arcNode.hasToilet==true?"有":"无"];
				for(j=0; j<5; j++){
					var x = 700 + rule[0]*(j + 0.1);
					var y = 20 + rule[1]*(i + 1.6);
					context.fillText(nodeInfos[j], x, y);
				}
			}
			context.closePath();
		},
		error : "error_()",
		dataType : "json"
	});
}

function createGraph_1(){
	var udata = "";
	$.ajax({url : "createGraph",
		data : udata,
		success : function(data){
			updateCanvas_1();
			
			var canvas = document.getElementById("myCanvas_1");
			if(canvas == null){
				return false;
			}
			var context = canvas.getContext('2d');
			
			results = eval(data);

			//绘制原始图
			paintOrigin(context);

			//绘制表格，用于展示景点信息
			context.beginPath();
			var rule = createChart(context, 5, results.arcNum+1);
			var chartTitle = ["景点名称", "景点描述", "景点欢迎度", "有无休息区", "有无公厕"];
			for(i=0; i<5; i++){
				var x = 700 + rule[0]*(i + 0.1);
				var y = 20 + rule[1]*0.6;
				context.fillText(chartTitle[i], x, y);
			}
			for(i=0; i<results.arcNum; i++){
				var arcNode = results.nodes[i];
				var nodeInfos = [arcNode.name, arcNode.des, arcNode.pop, arcNode.hasRest==true?"有":"无", arcNode.hasToilet==true?"有":"无"];
				for(j=0; j<5; j++){
					var x = 700 + rule[0]*(j + 0.1);
					var y = 20 + rule[1]*(i + 1.6);
					context.fillText(nodeInfos[j], x, y);
				}
			}
			context.closePath();
		},
		error : "error_",
		dataType : "json"
	});
}

function createGraph_2(){
	var udata = "";
	$.ajax({url : "createGraph",
		data : udata,
		success : function(data){
			updateCanvas_2();
			
			var canvas = document.getElementById("myCanvas_2");
			if(canvas == null){
				return false;
			}
			var context = canvas.getContext('2d');
			
			results = eval(data);

			//绘制原始图
			paintOrigin(context);

			//绘制表格，用于展示景点信息
			context.beginPath();
			var rule = createChart(context, 5, results.arcNum+1);
			var chartTitle = ["景点名称", "景点描述", "景点欢迎度", "有无休息区", "有无公厕"];
			for(i=0; i<5; i++){
				var x = 700 + rule[0]*(i + 0.1);
				var y = 20 + rule[1]*0.6;
				context.fillText(chartTitle[i], x, y);
			}
			for(i=0; i<results.arcNum; i++){
				var arcNode = results.nodes[i];
				var nodeInfos = [arcNode.name, arcNode.des, arcNode.pop, arcNode.hasRest==true?"有":"无", arcNode.hasToilet==true?"有":"无"];
				for(j=0; j<5; j++){
					var x = 700 + rule[0]*(j + 0.1);
					var y = 20 + rule[1]*(i + 1.6);
					context.fillText(nodeInfos[j], x, y);
				}
			}
			context.closePath();
		},
		error : "error_",
		dataType : "json"
	});
}

function createGraph_3(){
	var udata = "";
	$.ajax({url : "createGraph",
		data : udata,
		success : function(data){
			updateCanvas_3();
			
			var canvas = document.getElementById("myCanvas_3");
			if(canvas == null){
				return false;
			}
			var context = canvas.getContext('2d');
			
			results = eval(data);

			//绘制原始图
			paintOrigin(context);

			//绘制表格，用于展示景点信息
			context.beginPath();
			var rule = createChart(context, 5, results.arcNum+1);
			var chartTitle = ["景点名称", "景点描述", "景点欢迎度", "有无休息区", "有无公厕"];
			for(i=0; i<5; i++){
				var x = 700 + rule[0]*(i + 0.1);
				var y = 20 + rule[1]*0.6;
				context.fillText(chartTitle[i], x, y);
			}
			for(i=0; i<results.arcNum; i++){
				var arcNode = results.nodes[i];
				var nodeInfos = [arcNode.name, arcNode.des, arcNode.pop, arcNode.hasRest==true?"有":"无", arcNode.hasToilet==true?"有":"无"];
				for(j=0; j<5; j++){
					var x = 700 + rule[0]*(j + 0.1);
					var y = 20 + rule[1]*(i + 1.6);
					context.fillText(nodeInfos[j], x, y);
				}
			}
			context.closePath();
		},
		error : "error_",
		dataType : "json"
	});
}

function showAddSpotForm(){
	if(results == null){
		showTipDialog("Please initialise the map first!");
		return;
	}
	$('#spotName').val("");
	$('#spotIntro').val("");
	$('#spotPopu').val("");
	$('#addSpotModal').modal();
}

function showDeleteSpotForm(){
	if(results == null){
		showTipDialog("Please initialise the map first!");
		return;
	}
	$('#deleteSpotModal').modal();
}

function showAddRoadForm(){
	if(results == null){
		showTipDialog("Please initialise the map first!");
		return;
	}
	$('#distance').val("");
	$('#walkingTime').val("");
	$('#spotPopu').val("");
	$('#addRoadModal').modal();
}

function showDeleteRoadForm(){
	if(results == null){
		showTipDialog("Please initialise the map first!");
		return;
	}
	$('#deleteRoadModal').modal();
}

function showTourMapForm(){
	if(results == null){
		showTipDialog("请先创建景区分布图!");
		return;
	}
	$('#start').val("");
	$('#end').val("");
	$('#tourListModal').modal();
}

function tourMap(){
	if($('#start').val()=="" && $('#end').val()==""){
		showTipDialog("信息不能为空!");
		return;
	}

	var udata = "start=" + $('#start').val() + "&end=" + $('#end').val();
	
	$.ajax({url : "tourList",
		data : udata,
		type : "post",
		success : function(data){
			var listAndCycle = eval(data);
			tourList = listAndCycle.tourList;
			//tourCycle = listAndCycle.tourCycle;

			if(tourList.length == 0){
				showTipDialog("景点名称填写错误，请重新填写!");
				return;
			}
			
			//updateCanvas();
			var canvas = document.getElementById("myCanvas_1");
			if(canvas == null){
				return false;
			}
			var context = canvas.getContext('2d');
			
			context.clearRect(0,0,canvas.width,canvas.height);
			//绘制原始图
			paintOrigin(context);
			
				
			var startPic = new Image();
			var arrowPic = new Image();
			var endPic = new Image();
			startPic.src = "img/start.png";
			arrowPic.src = "img/arrow.png";
			endPic.src = "img/end.png";
			
			var ind = 0;
			var inter = setInterval(function(){
				context.beginPath();
				
				context.drawImage(startPic,700,20+ind*42,30,42);
				context.fillText(results.nodes[tourList[ind]].name, 740, 45+ind*42);
				context.drawImage(arrowPic,840,20+ind*42,35,35);
				context.drawImage(endPic,910,20+ind*42,30,42);
				context.fillText(results.nodes[tourList[ind+1]].name, 950, 45+ind*42);

				ind++;
				if(ind >= tourList.length-1){
					clearInterval(inter);
				}
			},500);
		},
		error : "error_",
		dataType : "json"
	});
}

function showShortestPathForm(){
	if(results == null){
		showTipDialog("请先创建景区分布图!");
		return;
	}
	$('#startName').val("");
	$('#endName').val("");
	$('#shortestPathModal').modal();
}

function shortestPath(){
	if($('#startName').val()=="" || $('#endName').val()==""){
		showTipDialog("信息不能为空!");
		return;
	}

	var udata = "startName=" + $('#startName').val() + "&endName=" + $('#endName').val();
	
	$.ajax({url : "Shortest",
		data : udata,
		type : "post",
		success : function(data){
			var shortest = eval(data);

			if(shortest.pathDis == -1){
				showTipDialog("起始点或终止点名称填写错误，请重新填写!");
				return;
			}
			
			updateCanvas_2();
			var canvas = document.getElementById("myCanvas_2");
			if(canvas == null){
				return false;
			}
			var context = canvas.getContext('2d');
			
			//绘制原始图
			paintOrigin(context);

			var startPic = new Image();
			var arrowPic = new Image();
			var endPic = new Image();
			startPic.src = "img/start.png";
			arrowPic.src = "img/arrow.png";
			endPic.src = "img/end.png";
			
			var ind = 0;
			var inter = setInterval(function(){
				
				context.drawImage(startPic,700,20+ind*70,50,70);
				context.fillText(results.nodes[shortest.nodesIndex[ind]].name, 770, 60+ind*70);
				context.drawImage(arrowPic,840,20+ind*70,50,50);
				context.drawImage(endPic,910,20+ind*70,50,70);
				context.fillText(results.nodes[shortest.nodesIndex[ind+1]].name, 980, 60+ind*70);
				
				ind++;
				if(ind == shortest.nodesIndex.length-1){
					context.font = '22px Arial';
					context.fillText("最短路径长度为"+shortest.pathDis ,750, 60+ind*70);
					context.font = '17px Arial';
				}
				if(ind >= shortest.nodesIndex.length-1){
					clearInterval(inter);
				}
			},500);
		},
		error : "error_",
		dataType : "json"
	});
}
function showSearchForm(){
	if(results == null){
		showTipDialog("请先创建景区分布图!");
		return;
	}
	$('#keyWord').val("");
	$('#searchModal').modal();
}

function searchArc(){
	if($('#keyWord').val()==""){
		showTipDialog("搜索关键字不能为空!");
		return;
	}

	var udata = "keyWord=" + $('#keyWord').val();
	
	$.ajax({url : "findArc",
		data : udata,
		type : "post",
		success : function(data){
			var searchNodes = eval(data);
			
			if(searchNodes.length == 0){
				showTipDialog("根据关键字搜索的结果为空，请输入其它关键字搜索!");
				return;
			}
			
			updateCanvas();
			var canvas = document.getElementById("myCanvas");
			if(canvas == null){
				return false;
			}
			var context = canvas.getContext('2d');
			
			context.clearRect(0,0,canvas.width,canvas.height);
			//绘制原始图
			paintOrigin(context);
			
			context.font = '25px Arial';
			context.fillText("搜索结果", 700, 50);
			context.font = '17px Arial';
			//绘制表格
			for(col=0; col <= 5; col++){
				var x = col * 100 + 700;
				context.moveTo(x,70);
				context.lineTo(x,50*(searchNodes.length+1)+70);
			}
			for(var row = 0; row <= searchNodes.length+1; row++){
				var y = row * 50 + 70;
			    context.moveTo(700,y);
			    context.lineTo(700+5*100,y);
			}
			context.stroke();
			//填写表格信息
			var chartTitle = ["景点名称", "景点描述", "景点欢迎度", "有无休息区", "有无公厕"];
			for(i=0; i<5; i++){
				var x = 700 + 100*(i + 0.1);
				var y = 70 + 50*0.6;
				context.fillText(chartTitle[i], x, y);
			}
			for(i=0; i<searchNodes.length; i++){
				var arcNode = results.nodes[searchNodes[i]];
				var nodeInfos = [arcNode.name, arcNode.des, arcNode.pop, arcNode.hasRest==true?"有":"无", arcNode.hasToilet==true?"有":"无"];
				for(j=0; j<5; j++){
					var x = 700 + 100*(j + 0.1);
					var y = 70 + 50*(i + 1.6);
					context.fillText(nodeInfos[j], x, y);
				}
			}
		},
		error : "error_",
		dataType : "json"
	});
}

function showOrderArcForm(){
	if(results == null){
		showTipDialog("请先创建景区分布图!");
		return;
	}
	$('#orderArcModal').modal();
}

function orderArc(){
	var udata = "type=" + $('#selectStyle').val();

	$.ajax({url : "orderArc",
		data : udata,
		type : "post",
		success : function(data){
			var results = eval(data);
			
			updateCanvas();
			var canvas = document.getElementById("myCanvas_3");
			if(canvas == null){
				return false;
			}
			var context = canvas.getContext('2d');
			context.clearRect(0,0,canvas.width,canvas.height);
			
			//绘制原始图
			paintOrigin(context);
			
			//绘制表格，用于展示景点信息
			context.beginPath();
			var rule = createChart(context, 1, results.arcNum+1);
			var chartTitle = "排序结果";
			var x = 700 + rule[0]*(0 + 0.1);
			var y = 20 + rule[1]*0.6;
			context.fillText(chartTitle, x, y);
			for(i=0; i<results.arcNum; i++){
				var currentName = results.names[i].name;
				var x = 700 + rule[0]*(0 + 0.1);
				var y = 20 + rule[1]*(i + 1.6);
				context.fillText(currentName, x, y);
			}
			context.closePath();
			

		},
		error : "error_",
		dataType : "json"
	});
}


function showInitForm(){
	$('#parkSize').val("");
	$('#initParkingModal').modal();
} 

function initParking(){
	parkSize = $('#parkSize').val();
	if(parkSize == ""){
		showTipDialog("初始化车场大小不能为空!");
		return;
	}
	if(parseInt(parkSize) != parkSize){
		showTipDialog("请输入正确格式的数!");
		return;
	}
	if(parkSize<=0 || parkSize>=6){
		showTipDialog("停车场大小不合适，请重新输入!");
		return;
	}
	
	var udata = "parkSize=" + parkSize;
	
	$.ajax({url : "initPark",
		data : udata,
		type : "post",
		success : function(data){
			first = 1;
			updateCanvas_2();
			var canvas = document.getElementById("myCanvas_2");
			if(canvas == null){
				return false;
			}
			var context = canvas.getContext('2d');

			paintParkingGraph(context, parkSize);
		},
		error : "error_",
		dataType : "json"
	});
}

function showAddParkForm(){
	if(first == 0){
		showTipDialog("请先初始化停车场!");
		return;
	}
	$('#number').val("");
	$('#addParkModal').modal();
} 

function addPark(){
	var number = $('#number').val();
	if(number == ""){
		showTipDialog("车牌号不能为空!");
		return;
	}
	var udata = "number=" + number;
	
	$.ajax({url : "addPark",
		data : udata,
		type : "post",
		success : function(data){
			var results = eval(data);
			console.log(results);
			if(results.exist == true){
				showTipDialog("停车场中已有该车牌号的汽车,请重新输入!");
				return;
			}
			
			updateCanvas_2();
			var canvas = document.getElementById("myCanvas_2");
			if(canvas == null){
				return false;
			}
			var context = canvas.getContext('2d');
			
			paintParkingGraph(context, parkSize);
			
			
			context.beginPath();
			for(i=0; i<results.tempParking.length; i++){
				context.fillText(results.tempParking[i].number, 320,150+150*i);
				context.fillText(new Date(results.tempParking[i].ar_time).Format("hh:mm"), 320,170+150*i);
			}
			for(i=0; i<results.parking.length; i++){
				context.fillText(results.parking[i].number, 495,170+150*i);
				context.fillText(new Date(results.parking[i].ar_time).Format("hh:mm"), 495,190+150*i);
			}
			for(i=0; i<results.shortcut.length; i++){
				context.fillText(results.shortcut[i].number, 650,170+150*i);
				context.fillText(new Date(results.shortcut[i].ar_time).Format("hh:mm"), 650,190+150*i);
			}
		},
		error : "error_",
		dataType : "json"
	});
}

function showDeleteParkForm(){
	if(first == 0){
		showTipDialog("请先初始化停车场!");
		return;
	}
	$('#delNumber').val("");
	$('#deleteParkModal').modal();
}

function deletePark(){
	var delNumber = $('#delNumber').val();
	if(delNumber == ""){
		showTipDialog("车牌号不能为空!");
		return;
	}
	var udata = "number=" + delNumber;
	
	$.ajax({url : "deletePark",
		data : udata,
		type : "post",
		success : function(data){
			var results = eval(data);
			
			if(results.exist == null){
				showTipDialog("停车场中无车辆,请先添加车辆!");
				return;
			}
			if(results.exist == false){
				showTipDialog("停车场中无此号码的车,请重新输入!");
				return;
			}

			
			var index = 1;
			var inter = setInterval(function(){
				updateCanvas_2();
				var canvas = document.getElementById("myCanvas_2");
				if(canvas == null){
					return false;
				}
				var context = canvas.getContext('2d');
				
				paintParkingGraph(context, parkSize);
				
				for(i=0; i<results.tempParking[index].length; i++){
					context.fillText(results.tempParking[index][i].number, 300,150+150*i);
					context.fillText(new Date(results.tempParking[index][i].ar_time).Format("hh:mm"), 300,170+150*i);
				}
				for(i=0; i<results.parking[index].length; i++){
					context.fillText(results.parking[index][i].number,495,170+150*i);
					context.fillText(new Date(results.parking[index][i].ar_time).Format("hh:mm"), 495,190+150*i);
				}
				for(i=0; i<results.shortcut[index].length; i++){
					context.fillText(results.shortcut[index][i].number, 650,170+150*i);
					context.fillText(new Date(results.shortcut[index][i].ar_time).Format("hh:mm"), 650,190+150*i);
				}
				index++;
				if(index >= results.length){
					showTipDialog("车牌号为" + delNumber + "的汽车在停车场停留" + results.parkTime + "分钟,应缴费" + results.cost);
					clearInterval(inter);
				}
			}, 1000);
		},
		error : "error_",
		dataType : "json"
	});
}



function clearAll_1(){
	results = null;
	first = 0;
	
	var canvas = document.getElementById("myCanvas_1");
	if(canvas == null){
		return false;
	}
	var context = canvas.getContext('2d');
	
	context.clearRect(0,0,canvas.width,canvas.height);
}

function error_(){
	alert("出错啦！好伤心");
}


function createChart(context, cols, rows){
	var grid_cols = cols;
	var grid_rows = rows;
	var cell_width = 500/grid_cols;
	var cell_height = 500/grid_rows;
	context.beginPath();
	for(col=0; col <= grid_cols; col++){
		var x = col * cell_width + 700;
		context.moveTo(x,20);
		context.lineTo(x,520);
	}
  for(var row = 0; row <= grid_rows; row++){
    var y = row * cell_height + 20;
    context.moveTo(700,y);
    context.lineTo(1200,y);
  }
  context.stroke();

  var rule = [cell_width, cell_height];
  return rule;
}

function createChart_1(context, cols, rows){
	var grid_cols = cols;
	var grid_rows = rows;
	var cell_width = 500/grid_cols;
	var cell_height = 400/grid_rows;
	context.beginPath();
	for(col=0; col <= grid_cols; col++){
		var x = col * cell_width + 50;
		context.moveTo(x,20);
		context.lineTo(x,420);
	}
  for(var row = 0; row <= grid_rows; row++){
    var y = row * cell_height + 20;
    context.moveTo(50,y);
    context.lineTo(550,y);
  }
  context.stroke();

  var rule = [cell_width, cell_height];
  return rule;
}

function paintParkingGraph(context, parkSize){
	context.beginPath();
	context.moveTo(250, 100);
	context.lineTo(250, 100+150*parkSize);
	context.moveTo(370, 100);
	context.lineTo(370, 100+150*parkSize);
	
	context.moveTo(420, 100);
	context.lineTo(420, 100+150*parkSize);
	context.moveTo(540, 100);
	context.lineTo(540, 100+150*parkSize);
	
	context.moveTo(600, 120);
	context.lineTo(600, 500+150*parkSize);
	context.moveTo(720, 120);
	context.lineTo(720, 500+150*parkSize);
	context.moveTo(600,120);
	context.lineTo(720,120);
	
	context.moveTo(250, 100);
	context.lineTo(370,100);
	for(i=0; i<parkSize; i++){
		context.moveTo(420, 100+i*150);
		context.lineTo(540, 100+i*150);
	}
	
	context.font = '130px Arial';
	for(i=1; i<=parkSize; i++){
		context.fillText(i, 440, 200+(i-1)*150);
	}
	
	context.stroke();
	context.font = '17px Arial';
}

//function paintParkingGraph(context, parkSize){
//	context.beginPath();
//	context.moveTo(50, 100);
//	context.lineTo(50+150*parkSize, 100);
//	context.moveTo(50, 220);
//	context.lineTo(50+150*parkSize, 220);
//	
//	context.moveTo(50, 320);
//	context.lineTo(50+150*parkSize, 320);
//	context.moveTo(50, 440);
//	context.lineTo(50+150*parkSize, 440);
//	
//	context.moveTo(50+150*parkSize+20, 320);
//	context.lineTo(50+150*parkSize+470, 320);
//	context.moveTo(50+150*parkSize+20, 440);
//	context.lineTo(50+150*parkSize+470, 440);
//	
//	context.moveTo(50, 100);
//	context.lineTo(50, 220);
//	for(i=0; i<parkSize; i++){
//		context.moveTo(50+i*150, 320);
//		context.lineTo(50+i*150, 440);
//	}
//	
//	context.font = '130px Arial';
//	for(i=1; i<=parkSize; i++){
//		context.fillText(i, 80+(i-1)*150, 425);
//	}
//	
//	context.stroke();
//	context.font = '17px Arial';
//}

function showTipDialog(message){
	alert(message);
}

function updateCanvas(){
	$('#myCanvas').remove();
	$('#myCanvasParent').append('<canvas id="myCanvas" style="border:0px solid #000000; width:100%" width="1313px" height="555px"></canvas>');
}

function updateCanvas_1(){
	$('#myCanvas_1').remove();
	$('#myCanvasParent_1').append('<canvas id="myCanvas_1" style="border:0px solid #000000; width:100%" width="1313px" height="900px"></canvas>');
}

function updateCanvas_2(){
	$('#myCanvas_2').remove();
	$('#myCanvasParent_2').append('<canvas id="myCanvas_2" style="border:0px solid #000000; width:100%" width="1313px" height="900px"></canvas>');
}

function updateCanvas_3(){
	$('#myCanvas_3').remove();
	$('#myCanvasParent_3').append('<canvas id="myCanvas_3" style="border:0px solid #000000; width:100%" width="1313px" height="555px"></canvas>');
}

Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
