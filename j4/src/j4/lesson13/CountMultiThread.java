package j4.lesson13;

public class CountMultiThread {
	public static void main(String[] args) {
		CountUp ctt1 = 
				new CountUp ("Count-up   Thread ");
		CountDown ctt2 = 
				new CountDown ("Count-down Thread ");
		ctt1.start();
		ctt2.start();
	}
}

class CountDown extends Thread{
	public CountDown (String threadName){
		super(threadName);
	}
	public void run() {
	    String threadName = getName();
	    System.out.println (threadName + " is started!");
	    for (int j = 0; j < 10; j++) {
	        try {sleep((int)(Math.random()* 1000));}
	        catch( InterruptedException e ) {};
	        System.out.println(threadName+"  j=" + (9-j));
	    }
	    System.out.println(threadName+" is done!");
	}
}