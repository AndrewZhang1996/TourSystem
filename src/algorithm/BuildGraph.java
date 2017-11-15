package algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import util.Constants;
import util.Singleton;
import datastructure.ALGraph;
import datastructure.ArcNode;
import datastructure.VNode;

/**
 * 类：BuildGraph
 * 功能：该类读取文件中景点和路的所有信息，用来构建图和输出图的信息
 */
public class BuildGraph {
	private String rootFolder;
	private ALGraph graph;
	private Scanner reader;
	private Scanner inforeader;
	
	public BuildGraph(int arcNum, int vetNum, String rootFolder) {
		Singleton.setGraph(arcNum, vetNum);
		graph = Singleton.getGraph();
		try {
			this.rootFolder = rootFolder;
			File file = new File(rootFolder + "map.txt");
			File infofile = new File(rootFolder + "info.txt");
			reader = new Scanner(file);
			inforeader = new Scanner(infofile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 该函数用来读取文件中景点和边的信息，并用邻接链表来存储图
	 * 
	 * @param
	 * @return
	 */
	public void createGraph(){
		int arcNum, vetNum;
	    String tmp, infos[];
	    String name, des;
	    int pop;
	    boolean hasRest, hasToilet;
	    String from, to;
	    int dis, time;
	    int cou;
	    
	    //读取文件中景点的信息
		for(int i=1; i<=graph.getArcNum(); i++){
			System.out.println(i);
			tmp = inforeader.nextLine();
			System.out.println(tmp);
			infos = tmp.split(" ");
			name = infos[0];
			des = infos[1];
			pop = Integer.parseInt(infos[2]);
			hasRest = ((infos[3].equals("1"))?true:false);
			hasToilet = ((infos[4].equals("1"))?true:false);
			graph.getNodes().add(new ArcNode(name, des, pop, hasRest, hasToilet));
		}
		//读取文件中路的信息
		for(int i=1; i<=graph.getVetNum(); i++){
			tmp = reader.nextLine();
			//System.out.println(tmp);
			
			infos = tmp.split(" ");
			from = infos[0];
			to = infos[1];
			dis = Integer.parseInt(infos[2]);
			time = Integer.parseInt(infos[3]);

	        int fromIndex = -1, toIndex = -1;
	        cou = 0;
	        for(ArcNode node : graph.getNodes()){
	            if(to.equals(node.getName())){
	                toIndex = cou;
	                System.out.println("To: " +  node.getName());
	                break;
	            }
	            cou++;
	        }
	        cou = 0;
	        for(ArcNode node : graph.getNodes()){
	            if(from.equals(node.getName())){
	                fromIndex = cou;
	                System.out.print(" From: " +  node.getName());
	                break;
	            }
	            cou++;
	        }
	        
	        VNode node1 = new VNode(toIndex, dis, time, graph.getNodes().get(fromIndex).getFirst());
	        graph.getNodes().get(fromIndex).setFirst(node1);
	        VNode node2 = new VNode(fromIndex, dis, time, graph.getNodes().get(toIndex).getFirst());
	        graph.getNodes().get(toIndex).setFirst(node2);
	        
		}
	}
	
	/**
	 * 该函数用来输出图的信息，以邻接矩阵的方式输出
	 * 
	 * @param
	 * @return
	 */
	public void outputGraph(){
		System.out.print("        ");
		for(ArcNode node : graph.getNodes()){
			System.out.print(node.getName()+"  ");
		}
		System.out.println();
		
		for(int i=0; i<graph.getArcNum(); i++){
			System.out.print(graph.getNodes().get(i).getName()+"  ");
			for(int j=0; j<graph.getArcNum(); j++){
				if(i == j){
					System.out.print("0  ");
					continue;
				}
				int dis = isContact(i, j);
				System.out.print(dis + "  ");
			}
			System.out.println();
		}
	}
	
	/**
	 * 该函数用来获取graph实例
	 * 
	 * @param 
	 * @return 存储图中所有信息的对象
	 */
	public ALGraph getGraph(){
		return graph;
	}
	
	/**
	 * 该函数用来获取两点间的距离大小，若不连接，距离大小为32767
	 * 
	 * @param 起点位置
	 * @param 终点位置
	 * @return 起点和终点间的距离
	 */
	private int isContact(int fromIndex, int toIndex){
		int dis = Constants.INF;
		VNode t = graph.getNodes().get(fromIndex).getFirst();
		while(t != null){
			if(t.getIndex() == toIndex){
				return t.getDist();
			}
			t = t.getNext();
		}
		
		return dis;
	}
}
