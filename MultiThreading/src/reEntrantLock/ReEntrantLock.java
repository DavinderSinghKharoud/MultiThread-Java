package reEntrantLock;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReEntrantLock {

	private int count = 0;
	private Lock lock = new ReentrantLock();
	private Condition cond = lock.newCondition();
	
	private void increment() {
		for(int i=0; i<10000; i++) {
		    count++;
		}
	}
	
	
	public void firstThread() throws InterruptedException{
		//lock and wait
		lock.lock();
		System.out.println("Waiting.....");
		cond.await();
		
		System.out.println("Woking up");
		
		try {

			increment();
		} finally {
			lock.unlock();
		}
		
	}
	
	public void secondThread() throws InterruptedException{
		
		Thread.sleep(1000);
		lock.lock();
		
		System.out.println("Press the return key....");
		new Scanner(System.in).nextLine();
		System.out.println("Got return key!");
		
		cond.signal();
		
		try {

			increment();
		} finally {
			lock.unlock();
		}
	}
	
	public void finished() {
		System.out.println("Count is: "+ count);
	}
	public static void main(String[] args) throws InterruptedException {
		
		ReEntrantLock obj = new ReEntrantLock();

		Thread thread1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					obj.firstThread();
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
					obj.secondThread();
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
        
        obj.finished();
	}

}
