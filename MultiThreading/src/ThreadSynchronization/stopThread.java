package ThreadSynchronization;

import java.util.Scanner;

class Processor extends Thread{
	
	private volatile boolean running = true;
	
	@Override
	public void run() {
	
		while(running) {

			System.out.println("Hello, I am running...");
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void shutDown() {
		running = false;
	}
}

public class stopThread {

	
	public static void main(String[] args) {
		
		Processor pr1 = new Processor();
		pr1.start();
		
		System.out.println("Press return to stop....");
		Scanner sn = new Scanner(System.in);
		sn.nextLine();
		
		pr1.shutDown();

	}

}
