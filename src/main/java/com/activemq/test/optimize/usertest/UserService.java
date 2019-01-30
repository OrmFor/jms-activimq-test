package com.activemq.test.optimize.usertest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserQueueSender sender;

	public void save(UserModel um){
		this.sender.sendMsg(um,"add");
	}

}
