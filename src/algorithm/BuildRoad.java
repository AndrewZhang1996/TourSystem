package algorithm;

import java.util.ArrayList;
import java.util.List;

import util.Constants;
import datastructure.ALGraph;
import datastructure.VData;
import datastructure.VNode;

/**
 * 类：BuildRoad()
 * 功能：用kruskal算法构建整个图的最小生成树，即修建道路路线\
 */
public class BuildRoad {
	private ALGraph graph;
	
	public BuildRoad(ALGraph graph) {
		this.graph = graph;
	}
	
	/**
	 * 克鲁斯卡尔算法获取最小生成树
	 * 
	 * @return 边信息的集合
	 */
	public List<VData> kruskal(){
		int index = 0;
		int[] ends = new int[graph.getVetNum()]; //用于保存已有最小生成树中每个顶点在该最小生成树中的终点
		VData[] results = new VData[graph.getVetNum()]; //用于保存结果最小生成树的边
		
		//获取图中所有的边
		VData[] edges = getEdges();
		//将边按照权的大小进行排序（从小到大）
		sortEdges(edges);
		//对所有边进行遍历
		for(int i=0; i<graph.getVetNum(); i++){
			int m = getEnd(ends, edges[i].getStart()); //获取该边起点在已有最小生成树中的终点
			int n = getEnd(ends, edges[i].getEnd()); //获取该边终点在已有最小生成树中的终点
			//如果m!=n，说明在已有最小生成树中添加该边不会形成回路
			if(m != n){
				ends[m] = n;
				results[index++] = edges[i];
			}
		}
		
		List<VData> result = new ArrayList<VData>();
		for(int i=0; i<index; i++){
			result.add(results[i]);
		}
		
		return result;
	}
	
    /**
     * 利用Floyd算法计算两点之间的最短距离
     * 
     * @return 最短路径
     */
    public String Floyd() {

        int distance[][] = new int[graph.getArcNum()][graph.getArcNum()], path[][] = new int[graph.getArcNum()][graph.getArcNum()];

        for (int i = 0; i < graph.getArcNum(); i++) {
            for (int j = 0; j < graph.getArcNum(); j++) {
                distance[i][j] = getLength(i, j);
                path[i][j] = j;
            }
        }

        for (int k = 0; k < graph.getArcNum(); k++) {
            for (int i = 0; i < graph.getArcNum(); i++) {
                for (int j = 0; j < graph.getArcNum(); j++) {
                    int tmp_value;
                    if(distance[i][k] == Constants.INF || distance[k][j]==Constants.INF)
                        tmp_value = Constants.INF;
                    else tmp_value = distance[i][k] + distance[k][j];
                    if (distance[i][j] > tmp_value) {
                        distance[i][j] = tmp_value;
                        path[i][j] = path[i][k];
                    }
                }
            }
        }
        
        //生成结果集合
        String floydString = "";
        for (int i = 0; i < Constants.INF; i++) {
            for (int j = 0; j < Constants.INF; j++)
                floydString += distance[i][j] + " ";
            floydString += "\n";
        }
        
        return floydString;
    }
	
	/**
	 * 获取所有边的信息
	 * 
	 * @return 边信息的数组
	 */
	private VData[] getEdges(){
		int index = 0; 
		VData[] edges;
		
		edges = new VData[graph.getVetNum()];
		for(int i=0; i<graph.getArcNum(); i++){
			VNode node = graph.getNodes().get(i).getFirst();
			while(node != null){
				if(node.getIndex() > i){
					edges[index++] = new VData(i, node.getIndex(), node.getDist());
				}
				node = node.getNext();
			}
		}
		
		return edges;
	}
	
	/**
	 * 按照边权的大小对边进行排序
	 * 
	 * @param edges 边信息的集合
	 */
	private void sortEdges(VData[] edges){
		for(int i=0; i<edges.length; i++){
			for(int j=i+1; j<edges.length; j++){
				if(edges[i].getWeight() > edges[j].getWeight()){
					VData tmp = edges[i];
					edges[i] = edges[j];
					edges[j] = tmp;
				}
			}
		}
	}
	
	/**
	 * 得到固定起始点在最小生成树中的终点位置
	 * 
	 * @param ends 最小生成树中每个顶点在该最小生成树中的终点
	 * @param arcIndex 起始点位置
	 * @return
	 */
	private int getEnd(int[] ends, int arcIndex){
		while(ends[arcIndex] != 0){
			arcIndex = ends[arcIndex];
		}
		
		return arcIndex;
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
