package getReturnCallableAndFuture;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.sun.org.apache.regexp.internal.recompile;

public class CallableAndFuture {

	public static void main(String[] args) {


		ExecutorService executorService =Executors.newCachedThreadPool();
		Future<Integer> future = executorService.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
			
				Random random = new Random();
				int duration = random.nextInt(4000);
				
				System.out.println("Starting...");
				
				Thread.sleep(duration);
				
				System.out.println("Finished");
				
				return duration;
			}
			
		});
		
		executorService.shutdown();
		
		try {
			System.out.println(future.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
