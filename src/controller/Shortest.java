package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import util.Singleton;
import algorithm.ShortestPath;

/**
 * 类：Shortest()
 * 功能：根据指定起始点名称和终点名称生成最短路径和最短距离
 */

@WebServlet("/Shortest")
public class Shortest extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//起始点名称
		String startName = request.getParameter("startName");
		//终点名称
		String endName = request.getParameter("endName");
		
		
		ShortestPath shortestPath = new ShortestPath(Singleton.getGraph());
		
		response.setContentType("text/json" + ";charset=UTF-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        PrintWriter pw = response.getWriter();
        //两点不可达
		if(shortestPath.getPos(startName)==-1 || shortestPath.getPos(endName)==-1){
			pw.write("{\"pathDis\":-1}");
		}else{//两点可达
			shortestPath.dijkstra(startName, endName);
			List<Integer> result = shortestPath.outputShortestPath();
			List<Integer> path = new ArrayList<Integer>();
			for(int i=result.size()-1; i>0; i--){
				path.add(result.get(i));
			}
			
			pw.write("{\"pathDis\":" + result.get(0) + ",\"nodesIndex\":" + JSON.toJSONString(path) + "}");
		}
		pw.flush();
	}
}
