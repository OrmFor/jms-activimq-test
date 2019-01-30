# jms-activimq-test
优化activimq发送
按照一般的发送顺序
1.获取连接connection
2.创建会话session
3.获取producer
4.创建TextMessage
5.生产者发送消息
由于发送前步骤基本相同，获取连接、创建会话、获取producer都可以放在内存中做缓存
1级缓存为connction 二级缓存为session 三级缓存为producer
测试案例详细看代码
测试类--TestUserQueueSender.java为优化后的消息发送类
     --QueueSender.java为一般发送，未做优化的消息发送类