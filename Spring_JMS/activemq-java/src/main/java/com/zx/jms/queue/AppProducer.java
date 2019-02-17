/**
 * @Project Name:testMQ
 * @Package Name:com.zx.jms.queue
 */
package com.zx.jms.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Description:
 * @Author: ZC
 * @Email: chao_actor@163.com
 * @data: 2018/10/4 17:19
 */
public class AppProducer {
    //单节点
    private static final String url = "tcp://127.0.0.1:61616";
    //集群
//    private static final String url = "failover:(tcp://127.0.0.1:61617,tcp://127.0.0.1:61618)?randomize=true";

    private static final String queue_name = "queue_test";

    public static void main(String[] args) throws JMSException {
//        创建连接工厂；
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(url);
//        创建连接；
        Connection connection = activeMQConnectionFactory.createConnection();
//        启动连接
        connection.start();
//        创建会话；事务、应答模式
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//        创建目的地
        Queue queue = session.createQueue(queue_name);
//        创建生产者
        MessageProducer producer = session.createProducer(queue);
//        发送消息
        for (int i = 0; i < 100; i++) {
            TextMessage textMessage = session.createTextMessage("message" + i);
            System.out.println(textMessage.getText());
            producer.send(textMessage);
        }
//        关闭会话和连接
        session.close();
        connection.close();
    }
}
