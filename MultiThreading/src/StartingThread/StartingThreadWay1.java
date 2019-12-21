package StartingThread;

 class Runner extends Thread{
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
public class StartingThreadWay1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Runner run1 = new Runner();
		run1.start();
		
		Runner run2 = new Runner();
		run2.start();
	}

}
