package algorithm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import util.Constants;
import datastructure.Car;
import datastructure.CarNode;
import datastructure.Queue;
import datastructure.Stack;

/**
 * 类：Parking()
 * 功能：停车场管理系统，包括车辆到达和车辆离开
 */
public class Parking {
	private Stack<Car> parking;
	private Queue shortcut;
	private Stack<Car> tempParking;
	private Stack<Car> existStack;
	
	public Parking(int maxParkingNum){
		parking = new Stack<Car>(Car.class, maxParkingNum);
		shortcut = new Queue();
		tempParking = new Stack<Car>(Car.class, maxParkingNum);
		existStack = new Stack<Car>(Car.class, maxParkingNum);
	}
	
	/**
	 * 车辆到达，添加进停车场或候车道
	 * 
	 * @param number 车牌号
	 * @return json数据
	 */
	public String arrivePark(String number){
		//判断输入车牌号是否存在
		if(parking.size() != 0){
			//对停车场遍历
			while(parking.peek() != null){
				if(parking.peek().getNumber().equals(number)){
					break;
				}
				existStack.push(parking.peek());
				parking.pop();
			}
			//判断停车场中是否存在
			if(parking.size() == 0){
				while(existStack.size() != 0){
					parking.push(existStack.peek());
					existStack.pop();
				}
				//判断候车道是否存在
				if(shortcut.existNumber(number)){
					return "{\"exist\":true}";
				}	
			}else{
				while(existStack.size() != 0){
					parking.push(existStack.peek());
					existStack.pop();
				}
				return "{\"exist\":true}";
			}
		}
		
		//判断停车场是否满了，若满了则添加车到候车道，否则直接添加		
		if(parking.isFull()){
			shortcut.add(new Car(number, null));
		}else{
			parking.push(new Car(number, new Date()));
		}	
		
		String JSONString = "{\"exist\":false,"
							+ "\"parking\":" + generateStackJSON(parking) 
							+ ",\"tempParking\":" + generateStackJSON(tempParking)
							+ ",\"shortcut\":" + generateQueueJSON(shortcut) + "}";
		
		return JSONString;
	}
	
	/**
	 * 车辆离开，计算缴纳费用和停车时间
	 * 
	 * @param number 车牌号
	 * @param l_time 车辆离开时间
	 * @return json结果
	 */
	public String leavePark(String number, Date l_time){
		//判断停车场中是否存在输入车牌号
		if(parking.size() == 0){
			return "{\"exist\":null}";
		}else{
			//对停车场遍历
			while(parking.peek() != null){
				if(parking.peek().getNumber().equals(number)){
					break;
				}
				existStack.push(parking.peek());
				parking.pop();
			}
			//判断停车场中是否存在
			if(parking.size() == 0){
				while(existStack.size() != 0){
					parking.push(existStack.peek());
					existStack.pop();
				}
				return "{\"exist\":false}";
			}
			while(existStack.size() != 0){
				parking.push(existStack.peek());
				existStack.pop();
			}
		}
		
		
		List<String> parkingList = new ArrayList<String>(); //存储停车场信息
		List<String> tempParkingList = new ArrayList<String>(); //存储暂时停车场信息
		List<String> shortcutList = new ArrayList<String>(); //存储候车道信息
		//查找指定车牌号的car，将其后的car停入暂时的停车场
		while(!parking.peek().getNumber().equals(number)){
			parkingList.add(generateStackJSON(parking));
			tempParkingList.add(generateStackJSON(tempParking));
			shortcutList.add(generateQueueJSON(shortcut));
			tempParking.push(parking.peek());
			parking.pop();
		}
		parkingList.add(generateStackJSON(parking));
		tempParkingList.add(generateStackJSON(tempParking));
		shortcutList.add(generateQueueJSON(shortcut));
		
		//计算停车时间和应缴费用
		float parkTime = (float) ((l_time.getTime() - parking.peek().getAr_time().getTime())/1000.0/60.0);
		double cost = Constants.PARKING_FEE*parkTime;
		parking.pop();
		parkingList.add(generateStackJSON(parking));
		tempParkingList.add(generateStackJSON(tempParking));
		shortcutList.add(generateQueueJSON(shortcut));
		
		//将暂时停车场中的车辆再次停进车场
		while(!tempParking.isEmpty()){
			parking.push(tempParking.peek());
			tempParking.pop();
			parkingList.add(generateStackJSON(parking));
			tempParkingList.add(generateStackJSON(tempParking));
			shortcutList.add(generateQueueJSON(shortcut));
		}
		
		//判断候车道
		if(!shortcut.isEmpty()){
			shortcut.front().getCar().setAr_time(l_time);
			parking.push(shortcut.front().getCar());
			shortcut.pop();
			parkingList.add(generateStackJSON(parking));
			tempParkingList.add(generateStackJSON(tempParking));
			shortcutList.add(generateQueueJSON(shortcut));
		}
		
		String JSONString = generateDynamicJSONString(parkingList, tempParkingList, shortcutList, parkTime, cost);

		return JSONString;
	}
	
	/**
	 * 对栈数据结构中的数据进行json格式化
	 * 
	 * @param stack
	 * @return json结果
	 */
	private String generateStackJSON(Stack<Car> stack){
		Car[] cars = stack.getAll();
		List<Car> list = new ArrayList<Car>();
		for(int i=0; i<cars.length; i++){
			if(cars[i] != null){
				list.add(cars[i]);
			}
		}
		
		return JSON.toJSONString(list);
	}
	
	/**
	 * 对队列数据结构中的数据进行json格式化
	 * 
	 * @param queue
	 * @return json结果
	 */
	private String generateQueueJSON(Queue queue){		
		CarNode carNode = queue.getHeadNode().getNext();
		List<Car> list = new ArrayList<Car>();
		while(carNode != null){
			list.add(carNode.getCar());
			carNode = carNode.getNext();
		}
		
		return JSON.toJSONString(list);
	}
	
	/**
	 * 对整个停车场车辆调动情况产生json结果
	 * 
	 * @param parkingList 停车场集合
	 * @param tempParkingList 暂时停车场集合
	 * @param shortcutList 候车道集合
	 * @param parkTime 停车时间
	 * @param cost 应缴纳费用
	 * @return json结果
	 */
	private String generateDynamicJSONString(List<String> parkingList, List<String> tempParkingList, 
											List<String> shortcutList, float parkTime, double cost){
		String JSONString = "{" + "\"exist\":true,"
				+ "\"length\":" + parkingList.size() + ","
				+ "\"parking\":[";
		
		for(int i=0; i<parkingList.size()-1; i++){
			JSONString += parkingList.get(i) + ",";
		}
		JSONString += parkingList.get(parkingList.size()-1) + "],";
				
		JSONString += "\"tempParking\":[";
		
		for(int i=0; i<tempParkingList.size()-1; i++){
			JSONString += tempParkingList.get(i) + ",";
		}
		JSONString += tempParkingList.get(tempParkingList.size()-1) + "],";
				
		JSONString += "\"shortcut\":[";
		
		for(int i=0; i<shortcutList.size()-1; i++){
			JSONString += shortcutList.get(i) + ",";
		}
		JSONString += shortcutList.get(shortcutList.size()-1) + "],";
		
		JSONString += "\"parkTime\":" + parkTime + ",";
		JSONString += "\"cost\":" + cost + "}";
		
		return JSONString;
	}
}
