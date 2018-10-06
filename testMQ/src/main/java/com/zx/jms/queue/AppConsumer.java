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
 * @data: 2018/10/6 13:36
 */
public class AppConsumer {
    private static final String url = "tcp://127.0.0.1:61616";

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
        MessageConsumer consumer = session.createConsumer(queue);
//        创建监听器
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println(textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

//        关闭会话和连接:监听器是一个异步的线程，如果关闭了连接则无法正确获取消息；
//        session.close();
//        connection.close();
    }
}
