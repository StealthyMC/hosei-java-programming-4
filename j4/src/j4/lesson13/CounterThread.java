package j4.lesson13;

public class CounterThread {
	static int numOfThread = 2;
	public static void main(String[] args) {
		for (int t = 1; t <= numOfThread; t++) {
		   CountUp ct = new CountUp ("CountUpThread-"+t);
		   ct.start();
		}
	}
}

class CountUp extends Thread {
	public CountUp (String threadName){
		super(threadName);
	}
	public void run() {
	    String threadName = getName();
	    System.out.println (threadName + " is started!");
	    for (int i = 0; i < 10; i++) {
	        try {sleep((int)(Math.random()* 1000));}
	        catch( InterruptedException e ) {};
	        System.out.println(threadName+"  i=" + i);
	    }
	    System.out.println(threadName+" is done!");
	}
}
