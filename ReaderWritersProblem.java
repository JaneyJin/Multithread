public class ReaderWritersProblem {
	
	private static Object readLock = new Object();
    private static Object writeLock = new Object();
    private static Object sr_writelock = new Object();
    private static int readCount = 0;
    
    public static class Read implements Runnable {
        public void run() {
        	
	        	try {
	        		synchronized (this) {
	                //wait Section
		                readLock.wait();
		                readCount++;
		                if (readCount == 1) {
		                    writeLock.wait();
		                }
		                readLock.notifyAll();
		
		                //Reading section
		                System.out.println("Thread "+Thread.currentThread().getName() + " is READING");
		                Thread.sleep(1500);
		                System.out.println("Thread "+Thread.currentThread().getName() + " has FINISHED READING");
		
		                //Releasing section
		                readLock.wait();
		                readCount--;
		                if(readCount == 0) {
		                    writeLock.notifyAll();
		                }
		                readLock.notifyAll();
	        		}
	            } catch (InterruptedException e) {
	                System.out.println(e.getMessage());
	            }
        	
        }
    }

    public static class Write implements Runnable {
        public void run() {
	            try {
	            	synchronized (this) {
		            	sr_writelock.wait();
		                writeLock.wait();
		                System.out.println("Thread "+Thread.currentThread().getName() + " is WRITING");
		                Thread.sleep(2500);
		                System.out.println("Thread "+Thread.currentThread().getName() + " has finished WRITING");
		                writeLock.notifyAll();
		                sr_writelock.notifyAll();
	            	}
	            } catch (InterruptedException e) {
	                System.out.println(e.getMessage());
	            }
        }
    }
}

    

