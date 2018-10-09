/**
 * @Project Name:Spring_JMS
 * @Package Name:com.zx.mq
 */
package com.zx.mq;

import com.zx.mq.service.ProducerService;
import com.zx.mq.service.imp.ProducerServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description:
 * @Author: ZC
 * @Email: chao_actor@163.com
 * @data: 2018/10/9 22:45
 */
public class TestProducer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-producer.xml");
        ProducerService producerService =(ProducerServiceImpl) context.getBean("producerService");
        for (int i = 0; i < 10; i++) {
            producerService.sendMsg("test" + i);
        }
        context.close();
    }
}
