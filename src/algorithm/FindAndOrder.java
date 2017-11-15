package algorithm;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import datastructure.ALGraph;
import datastructure.ArcNode;
import datastructure.VNode;

/**
 * 类：FindAndOrder()
 * 该类有两个功能，搜索和排序，
 * 搜索是根据关键字进行KMP匹配查询，
 * 排序包括按景点欢迎度查询和按景点
 * 路口数查询
 */
public class FindAndOrder {
	private ALGraph graph;
	
	public FindAndOrder(ALGraph graph) {
		this.graph = graph;
	}

	/**
	 * 根据搜索关键字搜索匹配景点
	 * 
	 * @param keyWord 搜索关键字
	 * @return 匹配景点的位置的集合
	 */
	public List<Integer> searchArc(String keyWord){
		List<Integer> searchNodes = new ArrayList<Integer>();
		for(int i=0; i<graph.getArcNum(); i++){
			String nameDoc = graph.getNodes().get(i).getName();
			String desDoc = graph.getNodes().get(i).getDes();
			//对景点名称和景点描述进行KMP匹配查询
			if(KMP(nameDoc, keyWord) || KMP(desDoc, keyWord)){
				searchNodes.add(i);
			}
		}
		
		return searchNodes;
	}
	
	/**
	 * 按景点欢迎度对景点进行排序
	 * 
	 * @return 景点名称和景点欢迎度
	 */
	public Map<String, Integer> orderByPopular(){
		int index = 0;
		ArcNode[] nodes = new ArcNode[graph.getArcNum()];
		for(ArcNode node : graph.getNodes()){
			nodes[index++] = new ArcNode(node.getName(), node.getDes(), node.getPop(), 
					node.isHasRest(), node.isHasToilet());
		}
		
		//冒泡排序
		for(int i=0; i<graph.getArcNum(); i++){
			for(int j=i+1; j<graph.getArcNum(); j++){
				if(nodes[i].getPop() < nodes[j].getPop()){
					ArcNode tmp = nodes[i];
					nodes[i] = nodes[j];
					nodes[j] = tmp;
				}
			}
		}
		
		Map<String, Integer> orderResults = new LinkedHashMap<String, Integer>();
		for(int i=0; i<graph.getArcNum(); i++){
			orderResults.put(nodes[i].getName(), nodes[i].getPop());
		}
		
		return orderResults;
	}
	
	/**
	 * 按景点的路口数排序
	 * 
	 * @return 景点名称和景点的路口数
	 */
	public Map<String, Integer> orderByPathNum(){
		int index = 0;
		ArcNode[] nodes = new ArcNode[graph.getArcNum()];
		int[] pathNums = new int[graph.getArcNum()];
		for(ArcNode arcNode : graph.getNodes()){
			nodes[index] = new ArcNode(arcNode.getName(), arcNode.getDes(), arcNode.getPop(), 
					arcNode.isHasRest(), arcNode.isHasToilet());
			VNode vNode = arcNode.getFirst();
			while(vNode != null){
				pathNums[index]++;
				vNode = vNode.getNext();
			}
			index++;
		}
		
		for(int i=0; i<graph.getArcNum(); i++){
			for(int j=i+1; j<graph.getArcNum(); j++){
				if(pathNums[i] < pathNums[j]){
					int t = pathNums[i];
					pathNums[i] = pathNums[j];
					pathNums[j] = t;
					ArcNode tmp = nodes[i];
					nodes[i] = nodes[j];
					nodes[j] = tmp;
				}
			}
		}
		
		Map<String, Integer> orderResults = new LinkedHashMap<String, Integer>();
		for(int i=0; i<graph.getArcNum(); i++){
			orderResults.put(nodes[i].getName(), pathNums[i]);
		}
		
		return orderResults;
	}
	
	/**
	 * KMP匹配算法
	 * 
	 * @param doc 模板字符串
	 * @param keyword 匹配字符串
	 * @return 是否匹配上
	 */
	private boolean KMP(String doc, String keyword){
		String newDoc = "无" + doc;
		String newKeyword = "无" + keyword;
		
		int[] K = calculateK(keyword);
		
		int i = 1, j = 1;
		while(i<=doc.length() && j<=keyword.length()){
			if(j==0 || newDoc.charAt(i)==newKeyword.charAt(j)){
				i++;
				j++;
			}else{
				j = K[j];
			}
		}
		
		if(j > keyword.length()){
			return true;
		}
		return false;
	}
	
	/**
	 * 对关键字计算移动步长的数组
	 * 
	 * @param keyword 匹配字符串
	 * @return
	 */
	private int[] calculateK(String keyword){
		String newKeyword = "无" + keyword;
		int[] K = new int[newKeyword.length()];
		int i = 1;
		K[1] = 0;
		int j = 0;
		
		while(i < keyword.length()){
			if(j==0 || newKeyword.charAt(i)==newKeyword.charAt(j)){
				i++;
				j++;
				K[i] = j;
			}else{
				j = K[j];
			}
		}
		
		return K;
	}
}
