package com.zhita.controller.shiro;

import java.io.Serializable;

import org.apache.shiro.authc.UsernamePasswordToken;

public class PhoneToken extends UsernamePasswordToken implements Serializable {
	 
		/**
		 * 
		 */
		private static final long serialVersionUID = 4812793519945855483L;
	 
		// 手机号码
		private String phone;
	 
		/**
		 * 重写getPrincipal方法
		 */
		public Object getPrincipal() {
			return phone;
		}
	 
		/**
		 * 重写getCredentials方法
		 */
		public Object getCredentials() {
			return phone;
		}
	 
		public PhoneToken() {
			// TODO Auto-generated constructor stub
		}
	 
		public PhoneToken(final String phone) {
			this.phone=phone;
		}
	 
		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
	 
		@Override
		public String toString() {
			return "TelphoneToken [telphoneNum=" + phone + "]";
		}
}
