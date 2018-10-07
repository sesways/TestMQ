/**
 * @Project Name:testMQ
 * @Package Name:com.zx.jms.queue
 */
package com.zx.jms.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Description:
 * @Author: ZC
 * @Email: chao_actor@163.com
 * @data: 2018/10/4 17:19
 */
public class AppProducer {
    private static final String url = "tcp://127.0.0.1:61616";

    private static final String topic_name = "topic_test";

    /**
     *@Description: 需要先启动消费者，再启动生产者；
     *@Author: ZC
     *@Email: chao_actor@163.com
     *@TIME： 2018/10/7 21:27
     *@Params: [args]
     *@ReturnType: void
     */
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
        Topic topic = session.createTopic(topic_name);
//        创建生产者
        MessageProducer producer = session.createProducer(topic);
//        发送消息
        for (int i = 0; i < 100; i++) {
            TextMessage textMessage = session.createTextMessage("message" + i);
            System.out.println(textMessage.getText());
            producer.send(textMessage);
        }
        System.out.println("主题消息发送完毕！");
//        关闭会话和连接
        session.close();
        connection.close();
    }
}
