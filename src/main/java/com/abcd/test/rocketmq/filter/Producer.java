package com.abcd.test.rocketmq.filter;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
 
 
public class Producer {
    public static void main(String[] args) throws MQClientException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
        producer.setNamesrvAddr("192.168.140.128:9876");
        producer.start();
 
        try {
            for (int i = 0; i < 6000000; i++) {
                Message msg = new Message("TopicFilter7",// topic
                    "TagA",// tag
                    "OrderID001",// key
                    ("Hello MetaQ").getBytes());// body
 
                msg.putUserProperty("SequenceId", String.valueOf(i));
 
                SendResult sendResult = producer.send(msg);
                System.out.println(sendResult);
                System.out.println("executeLocalTransactionBranch--msg=" + new String(msg.getBody()));
//                Thread.sleep(3000);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
 
        producer.shutdown();
    }
}
