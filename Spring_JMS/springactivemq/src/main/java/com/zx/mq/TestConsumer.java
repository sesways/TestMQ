/**
 * @Project Name:Spring_JMS
 * @Package Name:com.zx.mq
 */
package com.zx.mq;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description:
 * @Author: ZC
 * @Email: chao_actor@163.com
 * @data: 2018/10/9 22:52
 */
public class TestConsumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-consumer.xml");
        context.start();
    }
}
