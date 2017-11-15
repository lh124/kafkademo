package cn.mldn.mldnkafka.springdata.producer.service.impl;

import javax.annotation.Resource;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import cn.mldn.mldnkafka.springdata.producer.service.IMessageService;

@Service
public class MessageServiceImpl implements IMessageService {
	@Resource
	private KafkaTemplate<String, Integer> kafkaTemplate;

	@Override
	public void sendMessage(String key, Integer value) {
		this.kafkaTemplate.sendDefault(key, value);
	}

}
