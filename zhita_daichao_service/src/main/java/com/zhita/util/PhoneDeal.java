package com.zhita.util;

import java.util.ArrayList;
import java.util.List;

public class PhoneDeal {

	
/*
 * 将手机号码进行加密，加密流程为：把手机号码的第一位和第七位交换，第二位和第八位交换，
 * 第三位和第九位交换，第四位和第十位交换，第五位和第十一位交换，第六位保持不变。
 * 13个位置上除了0和9，其他的都要加1,8也不用加1,因为他要替换成“x”,
 */
	public String encryption(String oldPhone) { 
		   int intPhone = 0;
		   String beiyong = "";
		   String encryptionPhone = "";
		   StringBuffer newPhone = new StringBuffer();
	       StringBuffer phone = new StringBuffer(oldPhone);
	       String yi = phone.substring(0,1);
	       String er = phone.substring(1,2);
	       String san = phone.substring(2,3);
	       String si = phone.substring(3,4);
	       String wu = phone.substring(4,5);
	       String liu = phone.substring(5,6);
	       String qi = phone.substring(6,7);
	       String ba = phone.substring(7,8);
	       String jiu = phone.substring(8,9);
	       String shi = phone.substring(9,10);
	       String shiyi = phone.substring(10,11);
	       
	       beiyong = yi;
	       yi = qi;
	       qi = beiyong;
	       
	       beiyong = er;
	       er = ba;
	       ba = beiyong;
	       
	       beiyong = san;
	       san = jiu;
	       jiu = beiyong;
	       
	       beiyong = si;
	       si = shi;
	       shi = beiyong;
	       
	       beiyong = wu;
	       wu = shiyi;
	       shiyi = beiyong;
	       
	       List<String> list = new ArrayList<>();
	       list.add(yi);
	       list.add(er);
	       list.add(san);
	       list.add(si);
	       list.add(wu);
	       list.add(liu);
	       list.add(qi);
	       list.add(ba);
	       list.add(jiu);
	       list.add(shi);
	       list.add(shiyi);

	       for (String string : list) {
	    	   if(string.equals("8")) {
	    		   string="x";
	    	   }else {
	   	    	intPhone = Integer.parseInt(string);
				if(intPhone>0&&intPhone<9) {
					intPhone=intPhone+1;
				}
				string = intPhone+"";
			}
			newPhone = newPhone.append(string);
			encryptionPhone = newPhone.toString();
		}
		return encryptionPhone;
		
	}
	
	public String decryption(String oldPhone) {   //解密
		   int intPhone = 0;
		   String beiyong = "";
		   String encryptionPhone = "";
		   StringBuffer newPhone = new StringBuffer();
	       StringBuffer phone = new StringBuffer(oldPhone);
	       String yi = phone.substring(0,1);
	       String er = phone.substring(1,2);
	       String san = phone.substring(2,3);
	       String si = phone.substring(3,4);
	       String wu = phone.substring(4,5);
	       String liu = phone.substring(5,6);
	       String qi = phone.substring(6,7);
	       String ba = phone.substring(7,8);
	       String jiu = phone.substring(8,9);
	       String shi = phone.substring(9,10);
	       String shiyi = phone.substring(10,11);
	       
	       beiyong = yi;
	       yi = qi;
	       qi = beiyong;
	       
	       beiyong = er;
	       er = ba;
	       ba = beiyong;
	       
	       beiyong = san;
	       san = jiu;
	       jiu = beiyong;
	       
	       beiyong = si;
	       si = shi;
	       shi = beiyong;
	       
	       beiyong = wu;
	       wu = shiyi;
	       shiyi = beiyong;
	       
	       List<String> list = new ArrayList<>();
	       list.add(yi);
	       list.add(er);
	       list.add(san);
	       list.add(si);
	       list.add(wu);
	       list.add(liu);
	       list.add(qi);
	       list.add(ba);
	       list.add(jiu);
	       list.add(shi);
	       list.add(shiyi);

	       for (String string : list) {
	    	   if(string.equals("x")) {
	    		   string="8";
	    	   }else {
	    	intPhone = Integer.parseInt(string);
			if(intPhone>0&&intPhone<9) {
				intPhone=intPhone-1;
			}
			string = intPhone+"";
	    	   }
			newPhone = newPhone.append(string);
			encryptionPhone = newPhone.toString();
		}
		return encryptionPhone;
		
	}
	
	public static void main(String[] args) {
		PhoneDeal phoneDeal = new PhoneDeal();
		String phone = phoneDeal.encryption("13500000000");
		System.out.println(phone);
		phone = phoneDeal.decryption(phone);
		System.out.println(phone);
	}

}
