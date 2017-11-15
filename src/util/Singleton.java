package util;

import algorithm.Parking;
import datastructure.ALGraph;

public class Singleton {
	private static ALGraph graph;
	private static Parking parking;
	
	public static ALGraph getGraph(){
		return graph;
	}
	
	public static Parking getParking(){
		return parking;
	}
	
	public static void setGraph(){
		graph = null;
	}
	
	public static void setGraph(int arcNum, int vetNum){
		graph = new ALGraph(arcNum, vetNum);
	}
	
	public static void setParking(int maxParkingNum){
		parking = new Parking(maxParkingNum);
	}
}
