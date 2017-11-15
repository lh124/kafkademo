package cn.mldn.mldnkafka.base.consumer;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class KafkaMessageConsumer {
	// 首先需要有一个主题的名称提供，而后才可以实现消息的发送与接收处理
	public static final String TOPIC_NAME = "mldn-three" ;
	// 定义你所有参与到集群之中的主机列表
	public static final String SERVERS = "kafka-single:9095" ;
	static {	// 加载配置文件
		System.setProperty("java.security.auth.login.config",
				"e:/kafka_client_jaas.conf");	// 表示系统环境属性
	}
	public static void main(String[] args) throws Exception {
		Properties props = new Properties() ; 
		// 定义消息消费者的连接服务器地址
		props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, SERVERS) ;
		// 消息消费者一定要设置反序列化的程序类，与消息生产者完全对应
		props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()) ;
		props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName()) ;
		props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "group-1") ;
		props.setProperty(SaslConfigs.SASL_MECHANISM, "PLAIN");
		props.setProperty(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
		// 定义消费者处理对象
		Consumer<String,Integer> consumer = new KafkaConsumer<String,Integer>(props) ;
		// 消费者可以消费多个主题，所以这个时候需要指派主题的名称
		consumer.subscribe(Arrays.asList(TOPIC_NAME));
		boolean flag = true ;	// 消费者需要一直进行消费处理
		while (flag) {
			TimeUnit.SECONDS.sleep(2); 
			ConsumerRecords<String, Integer> records = consumer.poll(100) ;
			// Kakfa是批量的消息处理机制
			for (ConsumerRecord<String, Integer> record : records) {
				System.err.println(
						"【接收消息 - C】offset = " + record.offset() + "、key = " + record.key() + "、value = " + record.value());
			}
		}
		consumer.close();
	}
}
