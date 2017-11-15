package datastructure;
import java.util.ArrayList;
import java.util.List;

/**
 * 类：ALGraph()
 * 功能：景点的无向图
 */

public class ALGraph {
	private int arcNum; //景点数量
    private int vetNum; //路的数量
    private List<ArcNode> nodes; //存储景点的list
    
    public ALGraph(int arcNum){
    	this.arcNum = arcNum;
    	nodes = new ArrayList<ArcNode>();
    }
    
	public ALGraph(int arcNum, int vetNum) {
		this.arcNum = arcNum;
		this.vetNum = vetNum;
		nodes = new ArrayList<ArcNode>();
	}
	
	public int getArcNum() {
		return arcNum;
	}
	
	public void setArcNum(int arcNum) {
		this.arcNum = arcNum;
	}
	
	public int getVetNum() {
		return vetNum;
	}
	
	public void setVetNum(int vetNum) {
		this.vetNum = vetNum;
	}

	public List<ArcNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<ArcNode> nodes) {
		this.nodes = nodes;
	}
}
