package datastructure;

/**
 * 类：ArcNode()
 * 功能：储存景点信息
 */

public class ArcNode {
	private String name; //景点名称
	private String des; //景点描述
	private int pop; //景点欢迎度
	private boolean hasRest; //有无休息区
	private boolean hasToilet; //有无公厕
	private VNode first; //景点的第一条边
	
	public ArcNode(String name, String des, int pop, boolean hasRest, boolean hasToilet) {
		this.name = name;
		this.des = des;
		this.pop = pop;
		this.hasRest = hasRest;
		this.hasToilet = hasToilet;
		this.first = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public int getPop() {
		return pop;
	}

	public void setPop(int pop) {
		this.pop = pop;
	}

	public boolean isHasRest() {
		return hasRest;
	}

	public void setHasRest(boolean hasRest) {
		this.hasRest = hasRest;
	}

	public boolean isHasToilet() {
		return hasToilet;
	}

	public void setHasToilet(boolean hasToilet) {
		this.hasToilet = hasToilet;
	}

	public VNode getFirst() {
		return first;
	}

	public void setFirst(VNode first) {
		this.first = first;
	}
}
