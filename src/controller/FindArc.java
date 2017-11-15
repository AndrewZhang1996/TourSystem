package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import datastructure.ArcNode;
import algorithm.FindAndOrder;
import util.Singleton;

/**
 * 类：FindArc()
 * 功能：根据输入关键字搜索景点
 */

@WebServlet("/findArc")
public class FindArc extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//搜索关键字
		String keyWord = request.getParameter("keyWord");
		//System.out.println(keyWord);
		
		FindAndOrder find = new FindAndOrder(Singleton.getGraph());
		//根据关键字搜索
		List<Integer> searchNodes = find.searchArc(keyWord);
		
		//返回json结果
		response.setContentType("text/json" + ";charset=UTF-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        PrintWriter pw = response.getWriter();
        System.out.println(JSON.toJSONString(searchNodes));
        pw.write(JSON.toJSONString(searchNodes));
        pw.flush();
	}

}
