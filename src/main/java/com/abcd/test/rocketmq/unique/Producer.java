package com.abcd.test.rocketmq.unique;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageQueue;
import com.alibaba.rocketmq.remoting.exception.RemotingException;


/**
 * Producer，发送顺序消息
 */
public class Producer {
    public static void main(String[] args) throws Exception {
        try {
            DefaultMQProducer producer = new DefaultMQProducer("testuniquegroup_producer");

            producer.setNamesrvAddr("192.168.0.21:9876");

            producer.start();

            String[] tags = new String[] { "TagA", "TagC", "TagD" };

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr = sdf.format(date);
            for (int i = 0; i < 10; i++) {
//            	IndexChangeItem item=new IndexChangeItem();
//            	item.setChangeId(133);
//            	item.setChangeType(IndexChangeType.Goods_Update);
//            	String str=JSONUtils.obj2json(item);
                // 加个时间后缀
                String body = dateStr + " Hello RocketMQ " + i;
                String str=body;
                Message msg = new Message("TopicTestjjj", tags[i % tags.length], "KEY" + i, str.getBytes());

                SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        Integer id = (Integer) arg;
                        return mqs.get(id);
                    }
                }, 0);//0是队列的下标

                System.out.println(sendResult + ", body:" + body);
            }

            producer.shutdown();
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.in.read();
    }
}
