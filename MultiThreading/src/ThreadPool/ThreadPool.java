package ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable{

	private int id;
	
	public Processor(int id) {
		this.id = id;
	}
	
	
	@Override
	public void run() {
		
		System.out.println("Starting: "+ id);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Completed: "+ id);
		
	}
	
	
}
public class ThreadPool {

	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		
		for(int i=0; i<5; i++) {
			executorService.submit(new Processor(i));
		}
		
		executorService.shutdown();
		
		System.out.println("All tasks Submitted Succesfully");
		
		try {
			executorService.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("All tasks completed");

	}

}
 	