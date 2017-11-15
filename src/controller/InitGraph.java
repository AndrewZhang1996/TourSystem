package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import algorithm.BuildGraph;
import datastructure.ALGraph;
import datastructure.ArcNode;
import datastructure.VNode;

/**
 * 类：InitGraph()
 * 功能：返回所有数据的JSON
 */
@WebServlet("/createGraph")
public class InitGraph extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//创建图
		
		String rootFolder = request.getSession().getServletContext().getRealPath("");
		
		int arcNum = 0;
		int vetNum = 0;
		String arcNum_filePath = rootFolder + "info.txt";
		String vetNum_filePath = rootFolder + "map.txt";
		File arcNum_file = new File(arcNum_filePath);
		File vetNum_file = new File(vetNum_filePath);
		
		Scanner arcNum_reader = new Scanner(arcNum_file);
		Scanner vetNum_reader = new Scanner(vetNum_file);
		
		while(!arcNum_reader.nextLine().contains("$")){
			arcNum++;
		}
		while(!vetNum_reader.nextLine().contains("$")){
			vetNum++;
		}
		System.out.println("arc:" + arcNum + " vet:" + vetNum);
		
		BuildGraph graph = new BuildGraph(arcNum, vetNum, rootFolder);
		graph.createGraph();
		
		//生成图的json格式字符串
		String results = toJSONString(graph.getGraph());
		
		response.setContentType("text/json" + ";charset=UTF-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        PrintWriter pw = response.getWriter();
        pw.write(results);
        pw.flush();
	}
	
	private String toJSONString(ALGraph graph){
		String jsonString = "{\"arcNum\":" + graph.getArcNum() + ",\"vetNum\":" + graph.getVetNum()
				+ ",\"nodes\":[";
		int flag = 1;
		for(ArcNode node : graph.getNodes()){
			if(flag == 1){
				jsonString += "{\"name\":\"" + node.getName() + "\",\"des\":\"" + node.getDes() + "\",\"pop\":"
						+ node.getPop() + ",\"hasRest\":" + node.isHasRest() + ",\"hasToilet\":" + node.isHasToilet()
						+ ",\"edges\":[";
				flag = 0;
			}else{
				jsonString += ",{\"name\":\"" + node.getName() + "\",\"des\":\"" + node.getDes() + "\",\"pop\":"
						+ node.getPop() + ",\"hasRest\":" + node.isHasRest() + ",\"hasToilet\":" + node.isHasToilet()
						+ ",\"edges\":[";
			}
			
			if(node.getFirst() != null)
			{VNode tmp = node.getFirst();
			jsonString += "{\"index\":" + tmp.getIndex() + ",\"dist\":" + tmp.getDist() + ",\"time\":"
					+ tmp.getTime() + "}";
			System.out.println("UUUU:" + tmp.getIndex() + " / " + tmp.getDist() + " / " + tmp.getTime() );
//			jsonString += "{\"index\":" + tmp.getIndex() + ",\"dist\":" + tmp.getDist()  + "}";
			
			tmp = tmp.getNext();
			while(tmp != null){
				jsonString += ",{\"index\":" + tmp.getIndex() + ",\"dist\":" + tmp.getDist() + ",\"time\":"
						+ tmp.getTime() + "}";
//				jsonString += ",{\"index\":" + tmp.getIndex() + ",\"dist\":" + tmp.getDist()  + "}";
				tmp = tmp.getNext();
			}
			jsonString += "]}";
			}else if(node.getFirst() == null){
				jsonString += "]}";
			}
		}
		jsonString += "]}";
		
		System.out.println("JSON" + jsonString);
		return jsonString;
	}
}

