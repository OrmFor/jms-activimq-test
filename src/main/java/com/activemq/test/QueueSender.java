package com.activemq.test;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;

import javax.jms.*;
import java.text.MessageFormat;

public class QueueSender {
	public static void main(String[] args) throws Exception {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"tcp://127.0.0.1:61616");
//				"tcp://192.168.1.106:61676");
		
		Connection connection = connectionFactory.createConnection();
		connection.start();

		long a1 = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			long a2 = System.currentTimeMillis();
			Session session = connection.createSession(Boolean.TRUE,
					Session.AUTO_ACKNOWLEDGE);

			long a3 = System.currentTimeMillis();
			Destination destination = session.createQueue("my-queue");

			long a4 = System.currentTimeMillis();
			ActiveMQMessageProducer producer = (ActiveMQMessageProducer)session.createProducer(destination);


			long a5 = System.currentTimeMillis();
			TextMessage message = session.createTextMessage("messageAA--" + i);


			long a6 = System.currentTimeMillis();
			message.setStringProperty("extra"+i, "okok");


			// 通过消息生产者发出消息
			producer.send(message);
			long a7 = System.currentTimeMillis();


			System.out.println(MessageFormat.format("session={0},Destination={1},producer={2}" +
					",TextMessage={3},send={4}",(a3-a2),(a4-a3),(a5-a4),(a6-a5),(a7-a6)));
			session.commit();
			session.close();

//			MapMessage message = session.createMapMessage();
//			message.setString("message---"+i, "my map message AAA=="+i);
			
//			producer.setTransformer(new MessageTransformer() {
//				
//				public Message producerTransform(Session session, MessageProducer producer,
//						Message msg) throws JMSException {
//					
//					MapMessage message = session.createMapMessage();
//					message.setString(((TextMessage)msg).getText(), "my map message AAA=="+((TextMessage)msg).getText());
//					message.setStringProperty("extra", "okok");
//					return message;
//				}
//				
//				public Message consumerTransform(Session arg0, MessageConsumer arg1,
//						Message arg2) throws JMSException {
//					// TODO Auto-generated method stub
//					return null;
//				}
//			});
			
		}
		
		long a8 = System.currentTimeMillis();
		System.out.println("total time=="+(a8-a1));
		connection.close();
	}
}