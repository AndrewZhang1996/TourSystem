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

import algorithm.ShortestPath;
import algorithm.TourMap;
import util.Singleton;

/**
 * 类：TourList()
 * 功能：根据指定景点名称生成旅游路线图，并判断是否有回路
 */

@WebServlet("/tourList")
public class TourList extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//指定起始点名称
		String startName = request.getParameter("start");
		String endName = request.getParameter("end");
		
		response.setContentType("text/json" + ";charset=UTF-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        PrintWriter pw = response.getWriter();
        
        TourMap tourMap = new TourMap(Singleton.getGraph());
        ShortestPath shortestPath = new ShortestPath(Singleton.getGraph());
		//判断是否存在指定的起始点
        if(tourMap.getPos(startName) != -1){
        	//若存在，生成旅游路线图
			List<Integer> tourIndexList = tourMap.DFSTraverse(startName);
			shortestPath.dijkstra(startName, endName);
			List<Integer> result = shortestPath.outputShortestPath();
			List<Integer> path = new ArrayList<Integer>();
			for(int i=result.size()-1; i>0; i--){
				path.add(result.get(i));
			}
			tourIndexList.addAll(path);
			//List<String> tourCycle = tourMap.TopoSort();
//	        pw.write("{\"tourList\":"+ JSON.toJSONString(tourIndexList) + ",\"tourCycle\":"+ JSON.toJSONString(tourCycle) + "}");
			System.out.println("UUIKD: " + "{\"tourList\":"+ JSON.toJSONString(tourIndexList) + "}");
	        pw.write("{\"tourList\":"+ JSON.toJSONString(tourIndexList) + "}");
	        
		}else{
			//若不存在，返回空数组
			pw.write("{\"tourList\":[]}");
		}
        pw.flush();
	}

}
