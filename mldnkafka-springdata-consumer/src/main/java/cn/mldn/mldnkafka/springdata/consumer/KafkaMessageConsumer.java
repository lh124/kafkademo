package cn.mldn.mldnkafka.springdata.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

public class KafkaMessageConsumer implements MessageListener<String, String> {

	@Override
	public void onMessage(ConsumerRecord<String, String> record) {	// 做消息处理的
		System.err.println("【SpringKafka消费端】offset = " + record.offset() + "、key = " + record.key() + "、value = "
				+ record.value());
	}
}
