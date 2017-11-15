package datastructure;

import java.util.Date;

/**
 * 类：Car()
 * 功能：储存汽车信息
 */

public class Car {
	private String number; //汽车车号
	private Date ar_time; //汽车到达时间
	
	public Car(String number, Date ar_time) {
		this.number = number;
		this.ar_time = ar_time;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getAr_time() {
		return ar_time;
	}

	public void setAr_time(Date ar_time) {
		this.ar_time = ar_time;
	}
}
