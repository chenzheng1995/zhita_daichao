package com.zhita.util;


public class MyThread implements Runnable{


	
   public void run() {
 PostAndGet pGet = new PostAndGet();
     pGet.sendGet("http://39.98.83.65:8080/zhita_daichao_app/footprint/insertfootprint?footprintName=万金花&footprintType=1&userId=9&company=多米记");

	}

	
	public static void main(String[] args) {
		
		Thread threads[]=new Thread[100];
		for(int i=0;i<100;i++){
			threads[i]=new Thread(new MyThread());
			threads[i].start();

	}
	


}
	
}
