package semaPhore;

import java.util.concurrent.Semaphore;

public class Connections {

	 private static Connections instance = new Connections();
	 
	 private Semaphore sem = new Semaphore(10);
	 
	 private int count = 0;
	 
	 private Connections() {
	 }
	  
	 public static Connections getInstance() {
		 return instance;
	 }
	 
	 public void connect() {
		 
		 try {
			sem.acquire();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		 synchronized (this) {
			count++;
			System.out.println("Current Connections: " + count);
		}
		 
		 
		 try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 synchronized (this) {
			count--;
		}
		 
		 sem.release();
	 }

}
