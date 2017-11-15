package datastructure;

/**
 * 类：VNode
 * 功能：用于链表的边结点
 */

public class VNode {
	private int index; //另一个景点在景点数组中的位置
	private int dist; //两个景点的距离
	private int time; //所需时间
	private VNode next; //与头结点相连的下一条边
	
	public VNode(int index, int dist,int time, VNode next) {
		this.index = index;
		this.dist = dist;
		this.time = time;
		this.next = next;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getDist() {
		return dist;
	}

	public void setDist(int dist) {
		this.dist = dist;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public VNode getNext() {
		return next;
	}

	public void setNext(VNode next) {
		this.next = next;
	}
}
