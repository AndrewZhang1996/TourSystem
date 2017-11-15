package datastructure;

/**
 * 类：Queue
 * 功能：用于放置汽车结点的队列
 */

public class Queue {
	private CarNode phead; //汽车结点的链表头
	private int size; //队列的元素个数
	
	public Queue(){
		this.phead = new CarNode(null, null);
		this.size = 0;
	}

	public void add(Car car){
		//找到链表最后的结点
		CarNode tmpNode = phead;
		while(tmpNode.getNext() != null){
			tmpNode = tmpNode.getNext();
		}
		tmpNode.setNext(new CarNode(car, null));
		
		size++;
	}
	
	public void pop(){
		if(size == 0) return;
		
		//删除链表中第一个结点
		CarNode delNode = phead.getNext();
		phead.setNext(delNode.getNext());
		delNode = null;
		
		size--;
	}
	
	public CarNode front(){
		if(size == 0) return null;
		
		return phead.getNext();
	}
	
	public int size(){
		return size;
	}
	
	public boolean isEmpty(){
		return size==0 ? true : false;
	}
	
	public CarNode getHeadNode(){
		return phead;
	}
	
	public boolean existNumber(String number){
		boolean exist = false;
		
		CarNode node = phead.getNext();
		while(node != null){
			if(node.getCar().getNumber().equals(number)){
				exist = true;
				break;
			}
			node = node.getNext();
		}
		
		return exist;
	}
}
