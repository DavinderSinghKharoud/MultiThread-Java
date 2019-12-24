package semaPhore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Semophore {

	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		for(int i=0; i<200; i++) {
			executorService.submit(new Runnable() {
				
				@Override
				public void run() {

					Connections.getInstance().connect();
					
				}
			});
			
		}
		
		executorService.shutdown();

	}

}
