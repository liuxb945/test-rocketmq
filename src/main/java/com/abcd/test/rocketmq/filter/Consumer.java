package com.abcd.test.rocketmq.filter;

import java.util.List;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.MixAll;
import com.alibaba.rocketmq.common.message.MessageExt;
 
 
public class Consumer {
 
    public static void main(String[] args) throws InterruptedException, MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ConsumerGroupNamecc4");
        consumer.setNamesrvAddr("192.168.140.128:9876");
        // 使用Java代码，在服务器做消息过滤
        String filterCode = MixAll.file2String("D:/Workspaces/Eclipse/test-rocketmq/src/main/java/com/abcd/test/rocketmq/filter/MessageFilterImpl.java");
        consumer.subscribe("TopicFilter7", "com.abcd.test.rocketmq.filter.MessageFilterImpl",
            filterCode);
 
        consumer.registerMessageListener(new MessageListenerConcurrently() {
 
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                    ConsumeConcurrentlyContext context) {
                System.out.println(Thread.currentThread().getName() + " Receive New Messages: " + msgs);
                for(MessageExt msg:msgs){
                System.out.println("executeLocalTransactionBranch--msg=" + new String(msg.getBody()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
 
        /**
         * Consumer对象在使用之前必须要调用start初始化，初始化一次即可<br>
         */
        consumer.start();
 
        System.out.println("Consumer Started.");
    }
}
