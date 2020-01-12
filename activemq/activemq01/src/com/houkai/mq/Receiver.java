package com.houkai.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息发送
 */
public class Receiver {
    public static void main(String[] args) throws JMSException {
//        1.获取连接工厂
        ActiveMQConnectionFactory connectionFactory= new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                "tcp://localhost:61616"
        );
//        2.获取一个向activemq的连接
        Connection connection = connectionFactory.createConnection();
        connection.start();
//        3.获取session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//        4.找到目的地,获取destination
        Destination queue = session.createQueue("HOUKAIQueue");
//        5获取消息
        MessageConsumer consumer = session.createConsumer(queue);
        while (true) {
            TextMessage receive = (TextMessage) consumer.receive();
            System.out.println("message: "+receive.getText());
        }
    }


}
