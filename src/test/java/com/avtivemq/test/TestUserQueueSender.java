package com.avtivemq.test;

import com.activemq.test.optimize.usertest.UserModel;
import com.activemq.test.optimize.usertest.UserQueueSender;
import com.activemq.test.optimize.usertest.UserService;
import com.avtivemq.test.base.test.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-user-aq.xml"})
public class TestUserQueueSender extends BaseTest {
    @Autowired
    private UserQueueSender sender;

    @Autowired
    private UserService t;

    @Test
    public void test(){

        long a1 = System.currentTimeMillis();
        for(int i=0;i<10000;i++){
            UserModel um = new UserModel();
            um.setUserId("userId"+i);
            um.setName("name"+i);
            um.setAge(i+5);
            t.save(um);
        }
        long a2 = System.currentTimeMillis();
        System.out.println("time =="+(a2-a1));

        //由于10000条发送太快，activimq服务器端需要延时接收，否则服务器端还未完全接收，客户端已经把session关闭了
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

/*    public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-user-aq.xml");
		UserService t = (UserService)ctx.getBean("userService");

		long a1 = System.currentTimeMillis();
		for(int i=0;i<10000;i++){
			UserModel um = new UserModel();
			um.setUserId("userId"+i);
			um.setName("name"+i);
			um.setAge(i+5);

			t.save(um);
		}
		long a2 = System.currentTimeMillis();
		System.out.println("time =="+(a2-a1));

	}*/
}
