package com.safe.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import com.safe.core.filter.WebSocketHandler;
@Controller
@RequestMapping("/socket")
public class WebSocketController {
	@Autowired
	WebSocketHandler handler;
	@RequestMapping("/message")
	@ResponseBody 
	public String sendMessage(String message) {
	        boolean flag = handler.sendMessageToAllUsers(new TextMessage(message));
	        System.out.println(flag);
	        return "·¢ËÍ";
	    }
	@RequestMapping("/user")
	@ResponseBody 
	public String sendMessageOne(String clientId,String message) {
	        boolean flag = handler.sendMessageToUser(clientId,new TextMessage(message));
	        System.out.println(flag);
	        return "·¢ËÍ";
	    }
}
