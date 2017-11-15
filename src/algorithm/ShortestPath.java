package algorithm;

import java.util.ArrayList;
import java.util.List;

import util.Constants;
import datastructure.ALGraph;
import datastructure.VNode;

/**
 * 类：ShortestPath()
 * 功能：寻找两个景点间的最短路径和最短距离
 */
public class ShortestPath {
	private ALGraph graph;
	private int dis[]; //顶点与起始点之间的最短距离
	private int vis[]; //判断顶点是否被访问
	private int fath[]; //父结点的位置
	private int sourceIndex; //起始点
	private int desIndex; //目标点
	
	public ShortestPath(ALGraph graph) {
		this.graph = graph;
		dis = new int[graph.getArcNum()];
		vis = new int[graph.getArcNum()]; //初始化vis数组，将所有顶点设为未访问
		fath = new int[graph.getArcNum()];
	}

	/**
	 * 利用迪杰斯特拉算法寻找两个景点间的最短路径和最短距离
	 * 
	 * @param source 起点名称
	 * @param des 终点名称
	 */
	public void dijkstra(String source, String des){
		sourceIndex = getPos(source);
		desIndex = getPos(des);

		//初始化最短距离数组为INF
		for(int i=0; i<graph.getArcNum(); i++){	
			dis[i] = (i==sourceIndex ? 0 : Constants.INF);
		}
		
		for(int i=0; i<graph.getArcNum(); i++){
			int minPos = -1, m = Constants.INF;
			for(int j=0; j<graph.getArcNum(); j++){
				if(vis[j]==0 && dis[j]<m){
					m = dis[minPos=j];
				}
			}
			vis[minPos] = 1;
			for(int j=0; j<graph.getArcNum(); j++){
				if(vis[j]==0 && dis[minPos]+getLength(minPos, j)<dis[j]){
					dis[j] = dis[minPos]+getLength(minPos, j);
					fath[j] = minPos;
				}
			}
		}
	}

	/**
	 * 输出最短路径
	 * 
	 * @return 最短路径中景点位置列表
	 */
	public List<Integer> outputShortestPath(){
		List<Integer> path = new ArrayList<Integer>();
		//放入最短路径长度
		path.add(dis[desIndex]);
		//逆序放入最短路径中结点的位置
		int t = desIndex;
		while(t != sourceIndex){
			path.add(t);
			t = fath[t];
		}
		path.add(t);
		
		return path;
	}
	
	/**
	 * 根据景点名称寻找景点的位置
	 * 
	 * @param name 景点名称
	 * @return 景点位置
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
	 * 根据起始点位置和终止点位置得到两点间路径长度
	 * 
	 * @param fromIndex 起始点位置
	 * @param toIndex 终止点位置
	 * @return 两点间长度
	 */
	private int getLength(int fromIndex, int toIndex){
		VNode t = graph.getNodes().get(fromIndex).getFirst();
		int length = Constants.INF;
		while(t != null){
			if(t.getIndex() == toIndex){
				length = t.getDist();
				break;
			}
			t = t.getNext();
		}
		
		return length;
	}
}
