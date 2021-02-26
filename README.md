
package dao;

import java.util.Random;

class Thread1 extends Thread{
	private String name;

	public Thread1(String name) {
		this.name = name;
	}
	public void run() {
		Random rand = new Random();
		try {
			while(true) {
				int val = rand.nextInt(10)+1;
				System.out.println("Input value "+val);
				if(val%2 == 0) {
						new Thread2("Thread2",val).start();
				}
				else {
						new Thread3("Thread3",val).start();
				}
				Thread.sleep(1000);
			}
		}
		catch(InterruptedException ie) {
			ie.printStackTrace();
		}
	}	
}

class Thread2 extends Thread{
	public String name;
	private int value;

	public Thread2(String name,int value) {
		super();
		this.name = name;
		this.value = value;
	}
	public void run() {
		int result = value * value;
		//int result = (int)Math.pow(value, 2);
		System.out.println(result);
	}
}
class Thread3 extends Thread{
	public String name;
	private int value;

	public Thread3(String name,int value) {
		super();
		this.name = name;
		this.value = value;
	}
	public void run() {
		int result = value * value * value;
		//int result = (int)Math.pow(value, 3);
		System.out.println(result);
	}
}
public class Experiment11 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Thread1("Thread1").start();
	}

}
