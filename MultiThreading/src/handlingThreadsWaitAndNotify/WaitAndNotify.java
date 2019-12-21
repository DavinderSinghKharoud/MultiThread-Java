package handlingThreadsWaitAndNotify;

import java.util.Scanner;

public class WaitAndNotify {

	public void produce	() throws InterruptedException{
		synchronized (this) {
			System.out.println("Producer is running....");
			wait();
			System.out.println("Resumed.");
			
		}
	}
	
	public void consume() throws InterruptedException{
		Scanner scanner = new Scanner(System.in);
		Thread.sleep(1000);
		
		synchronized (this) {
			System.out.println("Waiting for return key....");
			scanner.nextLine();
			System.out.println("Return key pressed");
			notify();
			Thread.sleep(5000);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		
		WaitAndNotify obj = new WaitAndNotify();
	    Thread thread1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					obj.produce();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
        Thread thread2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					obj.consume();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
        thread1.start();
        thread2.start();
        
        thread1.join();
        thread2.join();

	}
}
