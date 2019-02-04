import java.util.LinkedList;
//import java.util.Scanner;
import java.util.Random;

public class Processor {
	//Producer add items into the list while consumer take items from the list
	private LinkedList<Integer> list = new LinkedList<Integer>();
	//buffer of length N
	private final int BUFFER = 10;
	//call wait and notifyAll of the object
	private Object lock = new Object();
	
	public void produce() throws InterruptedException {
		int value =0;
		
		//synchronized process object itself
		
		while(true) {
			synchronized (lock) {
				//wait if the list is full
				while(list.size() == BUFFER) {
					lock.wait();
				}
				list.add(value++);
				
				//Notify produce work in the list
				lock.notifyAll();
			}

		}
	}
	

	public void consume() throws InterruptedException{
		Random random = new Random();
		while(true) {
			//Remove item from the list
			synchronized (lock) {
				
				//List is empty wait produce work
				while(list.size() == 0) {
					lock.wait();
				}
				
				
				System.out.print("List size is: "+list.size());
				int value = list.removeFirst();
				System.out.println("; value is: " + value);
				lock.notifyAll();
				
				//Relinquish the lock here
			}
			
			Thread.sleep(random.nextInt(1000));
		}
		
		
	}
	
	
}
