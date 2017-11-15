package algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;

import datastructure.ALGraph;
import datastructure.ArcNode;
import datastructure.Stack;
import datastructure.VNode;

/**
 * 类：TourMap()
 * 功能：根据输入的起始点生成旅游路线图，并判断是否有回路
 */
public class TourMap {
	private ALGraph graph;
	private ALGraph directGraph;
	private boolean[] visited;
	private List<Integer> tourIndexList;
	private int startIndex;
	
	public TourMap(ALGraph graph) {
		this.graph = graph;
		directGraph = new ALGraph(graph.getArcNum());
		visited = new boolean[graph.getArcNum()];
		tourIndexList = new ArrayList<Integer>();
	}
	
	/**
	 * 利用深度遍历得到旅游路线图
	 * 
	 * @param start 起始点名称
	 * @return 旅游路线景点的位置列表
	 */
	public List<Integer> DFSTraverse(String start){
		Stack<Integer> traverseNodes = new Stack<Integer>(Integer.class, graph.getArcNum());
		
		startIndex = getPos(start);
		traverseNodes.push(startIndex);
		while(!traverseNodes.isEmpty() && !hasAllVisited()){
			int arcNodeIndex = traverseNodes.peek();
			visited[arcNodeIndex] = true;
			VNode vNode = graph.getNodes().get(arcNodeIndex).getFirst();
			while(vNode != null){
				if(!visited[vNode.getIndex()]){
					traverseNodes.push(vNode.getIndex());
					break;
				}
				vNode = vNode.getNext();
			}
			if(vNode == null){
				traverseNodes.pop();
			}
			tourIndexList.add(arcNodeIndex);
		}
		
		return tourIndexList;
	}
	
	/**
	 * 获取指点景点名称的位置
	 * 
	 * @param name 景点名称
	 * @return 景点的位置
	 */
	public int getPos(String name){
		int pos = -1;
		for(int i=0; i<graph.getArcNum(); i++){
			if(name.equals(graph.getNodes().get(i).getName())){
				pos = i;
				break;
			}
		}
		
		return pos;
	}
	
	/**
	 * 判断是否所有景点已经被访问
	 * 
	 * @return 是否访问所有景点
	 */
	private boolean hasAllVisited(){
		for(boolean visit : visited){
			if(!visit){
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * 获取边结点信息
	 * 
	 * @param fromIndex 起始点位置
	 * @param toIndex 终点位置
	 * @return 边结点信息
	 */
	private VNode getVNode(int fromIndex, int toIndex){
		VNode newVNode = null;
		
		VNode node = graph.getNodes().get(fromIndex).getFirst();
		while(node != null){
			if(node.getIndex() == toIndex){
				newVNode = new VNode(node.getIndex(), node.getDist(), node.getTime(), null);
				break;
			}
			node = node.getNext();
		}
		
		return newVNode;
	}
}
