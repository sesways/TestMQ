/**
 * @Project Name:Spring_JMS
 * @Package Name:com.zx.mq.service.imp
 */
package com.zx.mq.service.imp;

import com.zx.mq.service.ProducerService;
import org.apache.activemq.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

/**
 * @Description:
 * @Author: ZC
 * @Email: chao_actor@163.com
 * @data: 2018/10/9 21:56
 */
@Service
public class ProducerServiceImpl implements ProducerService {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Resource(name = "activeMQQueue")
    private Destination destination;

    @Override
    public void sendMsg(String message) {
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public javax.jms.Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
        System.out.println(message);
    }
}
