import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class App {
	//Not use queue here, Just ignore
	//private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
	private static  boolean flag = true;
	
	public static void main(String[] args) throws InterruptedException {
			final Processor processor = new Processor();
			
			Thread t1= new Thread(new Runnable() {

				public void run() {
					try {
						processor.produce();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			});
			
			
			
			Thread t2= new Thread(new Runnable() {

				public void run() {
					try {
						processor.consume();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			});
			t1.start();
			t2.start();
			
			//Press enter to stop the stream
			//Scanner scanner = new Scanner(System.in);
			//scanner.nextLine();
			//shutdown();
			t1.join();
			t2.join();
			
			
	}
	

	
	
	private static void producer() throws InterruptedException {
		Random random =  new Random();
		
		
		while(flag) {
			//Random number from 0 to 99
			queue.put(random.nextInt(100));
		}
		
	}
	
	private static void consumer() throws InterruptedException{
		Random random =  new Random();
		
		while(flag) {
			//10 loop per second
			Thread.sleep(100);
			
			if(random.nextInt(10)==0) {
				Integer value = queue.take();
				
				
				System.out.println("Take value："+value + "; Queue size is: "+ queue.size());
			}
			
		}
		
	}
	
	public static void shutdown() {
		flag = false;
	}
	
	
}
