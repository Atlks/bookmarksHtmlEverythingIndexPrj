package aaa;

import java.util.Date;

public class timer {

	public static void main(String[] args) {
		    new Thread( new Runnable(){

				@Override
				public void run() {
					
					while(true)
					{
						try {
							Thread.sleep(7000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(new Date());
					}
					
					
				}}).start();
	}

}
