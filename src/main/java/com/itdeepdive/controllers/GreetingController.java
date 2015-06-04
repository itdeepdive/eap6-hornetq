package com.itdeepdive.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/greeting")
public class GreetingController {
	
	@Autowired
	private JmsTemplate jmsTemplate;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String create() {
		
		jmsTemplate.convertAndSend("Z",new String("Helloworld!!!!"));
		return "sent to desitnation";

	}
}