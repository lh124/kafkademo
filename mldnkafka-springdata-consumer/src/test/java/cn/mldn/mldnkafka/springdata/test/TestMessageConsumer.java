package cn.mldn.mldnkafka.springdata.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations= {"classpath:spring/spring-*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestMessageConsumer {
	static {
		System.setProperty("java.security.auth.login.config",
				"e:/kafka_client_jaas.conf");	// 表示系统环境属性
	} 
	@Test
	public void testConsumer() throws Exception {
		Thread.sleep(Long.MAX_VALUE);
	}
}
