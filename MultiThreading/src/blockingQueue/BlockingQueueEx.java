package blockingQueue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.oracle.jrockit.jfr.Producer;

public class BlockingQueueEx {
	//
	
	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

	public static void main(String[] args) throws InterruptedException {
		
		Thread thread1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					producer();
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
					consumer();
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

	//Adding random number to queue
	private static void producer() throws InterruptedException {
		
		Random random = new Random();
		
		while(true) {
			queue.put(random.nextInt(100));
		}
	}
	
	
	//Consuming items from the queue
	private static void consumer() throws InterruptedException{
		
		Random random = new Random();
		
		while(true) {
			Thread.sleep(100);
			
			if(random.nextInt(10) == 0) {
				Integer value = queue.take();
				
				System.out.println("Taken value: "+ value +"; Queue size is: "+ queue.size());
			}
		}
		
	}
}
