package com.example.traceableservicea;

import com.example.traceableservicea.feignclient.ServiceBClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.discovery.event.InstanceRegisteredEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationListener;

@Log4j2
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class TraceableServiceAApplication implements ApplicationListener<InstanceRegisteredEvent> {

	@Autowired
	ServiceBClient serviceBClient;

	public static void main(String[] args) {
		SpringApplication.run(TraceableServiceAApplication.class, args);
	}

	@Override
	public void onApplicationEvent(InstanceRegisteredEvent event) {
		String processId = serviceBClient.startProcess();
		log.info("Started process. processId = {}", processId);
	}
}
