package com.houkai.mq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息发送
 */
public class Sender {
    public static void main(String[] args) throws JMSException, InterruptedException {
//        1.获取连接工厂
        ActiveMQConnectionFactory connectionFactory= new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                "tcp://localhost:61616"
        );
//        2.获取一个向activemq的连接
        Connection connection = connectionFactory.createConnection();
//        3.获取session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//        4.找到目的地,获取destination
        Queue queue = session.createQueue("HOUKAIQueue");
//        5.1.消息创建者
        MessageProducer producer = session.createProducer(queue);
//        consumer -->消费者
//        producer -->创建者
//        5.2创建消息
        for (int i = 0; i < 100; i++) {
            TextMessage textMessage = session.createTextMessage("helloworld"+i);
//        5.3向目的地写入
            producer.send(textMessage);
            Thread.sleep(1000);
        }

//        6.关闭连接
        connection.close();
        System.out.println("System exit ");
    }


}
