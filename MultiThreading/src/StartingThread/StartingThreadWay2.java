package StartingThread;

class Runner2 implements Runnable{
	@Override
	public void run() {
	
		for(int i=0; i<10; i++) {
			System.out.println("Hello "+i);
		}
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public class StartingThreadWay2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Thread th1 = new Thread(new Runner2());
		Thread th2 = new Thread(new Runner2());
		
		th1.start();
		th2.start();
	}

}
