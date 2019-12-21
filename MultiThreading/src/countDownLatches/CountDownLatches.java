package countDownLatches;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements Runnable{

	private CountDownLatch latch;
	
	 public Processor(CountDownLatch latch) {
		 this.latch = latch;
				
	}
	
	@Override
	public void run() {
		System.out.println("Started...");
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		latch.countDown();
	}
	
}
public class CountDownLatches {
	

	public static void main(String[] args) {

		//set the maximum value 
		CountDownLatch latch = new CountDownLatch(3);
	
		//Recyclable threads executed
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		
		for(int i=0; i<3; i++) {
			executorService.submit(new Processor(latch));
		}
		
		try {
			//will be called after count down is 0
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Completed...");
	}

}
