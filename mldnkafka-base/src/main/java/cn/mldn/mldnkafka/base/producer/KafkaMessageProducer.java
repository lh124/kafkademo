package cn.mldn.mldnkafka.base.producer;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

public class KafkaMessageProducer {
	// 首先需要有一个主题的名称提供，而后才可以实现消息的发送与接收处理
	public static final String TOPIC_NAME = "mldn-three" ;
	// 定义你所有参与到集群之中的主机列表
	public static final String SERVERS = "kafka-single:9095" ;
	static {	// 加载配置文件
		System.setProperty("java.security.auth.login.config",
				"e:/kafka_client_jaas.conf");	// 表示系统环境属性
	}
	public static void main(String[] args) throws Exception {
		// 如果要进行Kafka的消息发送，那么必须要做一些基础的环境属性配置，这种配置要通过Properties完成
		Properties props = new Properties();
		props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, SERVERS); // 定义Kafka服务地址
		// Kafka之中是以key和value的形式进行消息的发送处理， 所以为了保证Kafka的性能，专门提供有统一类型
		props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()) ;
		props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName()) ;
		props.setProperty(SaslConfigs.SASL_MECHANISM, "PLAIN");
		props.setProperty(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
		long start = System.currentTimeMillis() ;	// 准备一个开始时间
		// 如果要进行消息的发送，那么首先需要定义一个消息的生产者
		Producer<String, Integer> producer = new KafkaProducer<String, Integer>(props) ;
		for (int x = 0; x < 100; x++) {
//			TimeUnit.SECONDS.sleep(2); 
			producer.send(new ProducerRecord<String, Integer>(TOPIC_NAME, "mldn-" + x, x));
		}
		long end = System.currentTimeMillis() ;	// 准备一个结束时间
		producer.close();
		System.out.println("**** 本次发送所花费的时间：" + (end - start));
	}
}
