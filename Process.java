import java.util.Scanner;

public class Process {
	public void produce() throws InterruptedException {
		//synchronized process object itself
		synchronized (this) {
			System.out.println("Producer thread running.....");
			//Regain control after get notify();
			wait();
			System.out.println("Resumed.");
		}
	}
	

	public void consumer() throws InterruptedException{
		Scanner scanner = new Scanner(System.in);
		
		//Sleep to make produce run first
		Thread.sleep(2000);
		
		synchronized(this){
			System.out.println("Waiting for return key.");
			scanner.nextLine();
			System.out.println("Return key presssed");
			notifyAll();
			Thread.sleep(5000);
			//Relinquish the lock here
		}
		
		
	}
	
	
}
