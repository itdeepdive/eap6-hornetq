package com.itdeepdive.javaconfig;

import javax.jms.Message;
import javax.jms.MessageListener;


public class ListenerC implements MessageListener {

	@Override
	public void onMessage(Message message) {
		System.out.println("============================");
		System.out.println("Message Recieved in QUEUE C");
		System.out.println("============================");
		
	}

}