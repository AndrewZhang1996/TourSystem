package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import util.Singleton;
import algorithm.FindAndOrder;

/**
 * 类：OrderArc()
 * 功能：对景点按照欢迎度或景点的路口数进行排序
 */

@WebServlet("/orderArc")
public class OrderArc extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//排序类型
		String type = request.getParameter("type");
		
		FindAndOrder find = new FindAndOrder(Singleton.getGraph());
		Map<String, Integer> orderResults = null;
		//按欢迎度排序
		if(type.equals("按欢迎度")){
			orderResults = find.orderByPopular();
		}else{//按景点路口数排序
			orderResults = find.orderByPathNum();
		}
		
		//返回json结果
		response.setContentType("text/json" + ";charset=UTF-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        PrintWriter pw = response.getWriter();
        pw.write(toJSONString(orderResults));
        pw.flush();
	}
	
	private String toJSONString(Map<String, Integer> orderResults){
		int arcNum = 0;
		for(Map.Entry<String, Integer> entry : orderResults.entrySet()){
			arcNum++;
		}
		String json = "{\"arcNum\":" + arcNum + ",\"names\":[";
		for(Map.Entry<String, Integer> entry : orderResults.entrySet()){
			json  += "{\"name\":\"" + entry.getKey() + "\",\"value\":" + entry.getValue() + "},";
		}
		json = json.substring(0, json.length()-1);
		json += "]}";
		System.out.println("Order: "+ json);
		return json;
	}
}
