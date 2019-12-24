package deadLock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {
	private Account acc1 = new Account();
	private Account acc2 = new Account();
	
	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();
	
	
	private static DeadLock deadLock = new DeadLock();
	
	public static void main(String[] args) throws InterruptedException {
		
		
		//Running two different threads
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				deadLock.firstThread();
			}
		});
		
         Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				deadLock.secondThread();
			}
		});
		
		
         t1.start();
         t2.start();
         
         t1.join();
         t2.join();
         
         deadLock.finished();
         
	}
	
	private void finished() {
		System.out.println("Account 1 balance: " + acc1.getBalance());
		System.out.println("Account 2 balance: " + acc2.getBalance());
		System.out.println("Total balance: " + (acc1.getBalance() + acc2.getBalance()));
	}
	
	private void firstThread() {
		
		//transferring random amount
		Random random = new Random();
		
		for (int i=0; i<10000; i++) {
			 
			lock1.lock();
			lock2.lock();
			
			try {
			Account.transfer(acc1, acc2, random.nextInt(100));
			}
			finally {
				lock1.unlock();
				lock2.unlock();
			}
		}
	}
	
    private void secondThread() {
    	//transferring random amount
    			Random random = new Random();
    			
    			for (int i=0; i<10000; i++) {
    				lock1.lock();
    				lock2.lock();
    				
    				try {
    				Account.transfer(acc2, acc1, random.nextInt(100));
    				}
    				finally {
    					lock1.unlock();
    					lock2.unlock();
    				}
    			}
	}


}
