/**
 * @Project Name:Spring_JMS
 * @Package Name:com.zx.mq.service
 */
package com.zx.mq.service;

/**
 * @Description:
 * @Author: ZC
 * @Email: chao_actor@163.com
 * @data: 2018/10/9 21:56
 */
public interface ProducerService {
    void sendMsg(String message);
}
