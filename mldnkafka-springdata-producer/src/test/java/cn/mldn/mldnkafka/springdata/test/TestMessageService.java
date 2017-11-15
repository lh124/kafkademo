package cn.mldn.mldnkafka.springdata.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.mldnkafka.springdata.producer.service.IMessageService;

@ContextConfiguration(locations = { "classpath:spring/spring-*.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestMessageService {
	static {
		System.setProperty("java.security.auth.login.config",
				"e:/kafka_client_jaas.conf");	// 表示系统环境属性
	} 
	@Resource
	private IMessageService messageService;

	@Test
	public void testSend() {
		for (int x = 0; x < 100; x++) {
			this.messageService.sendMessage("休息正计时 - " + x, x);
		}
	}
}
