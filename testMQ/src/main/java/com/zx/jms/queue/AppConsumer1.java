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
 * @data: 2018/10/6 13:46
 */
public class AppConsumer1 {
    private static final String url = "tcp://127.0.0.1:61616";

    private static final String queue_name = "queue_test";

    /**
     *@Description: 用于测试一个发送者两个接受者的情况；
     *@Author: ZC
     *@Email: chao_actor@163.com
     *@TIME： 2018/10/6 13:48
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
    }
}
