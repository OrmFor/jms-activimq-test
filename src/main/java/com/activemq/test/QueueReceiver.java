package com.activemq.test;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageConsumer;
import org.apache.activemq.RedeliveryPolicy;

import javax.jms.*;

public class QueueReceiver {
	public static void main(String[] args) throws Exception {
		ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory(
//				"tcp://localhost:61616");
				"tcp://127.0.0.1:61616");
		
		RedeliveryPolicy policy = new RedeliveryPolicy();
		policy.setMaximumRedeliveries(3);
		cf.setRedeliveryPolicy(policy);
		
		Connection connection = cf.createConnection();
		connection.start();
		
//		Enumeration names = connection.getMetaData().getJMSXPropertyNames();
//		while(names.hasMoreElements()){
//			String name = (String)names.nextElement();
//			System.out.println("jmsx name==="+name);
//		}
		

		final Session session = connection.createSession(Boolean.TRUE,
				Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("my-queue");

		ActiveMQMessageConsumer consumer = (ActiveMQMessageConsumer)session.createConsumer(destination);
		
		
		int i = 0;
		while (i < 10) {
//			i++;
			TextMessage message = (TextMessage) consumer.receive();
			
			
			
			//MapMessage message = (MapMessage) consumer.receive();
			
			
			
			
//			session.commit();
			
//			if(i==2){
//				message.acknowledge();
//			}
//			
			System.out.println("收到消 息：" + message.getText()
					+" , property=="+message.getStringProperty("extra"+i));
			
			i++;
			
			session.commit();
		}
		session.close();
		connection.close();
	}

}
