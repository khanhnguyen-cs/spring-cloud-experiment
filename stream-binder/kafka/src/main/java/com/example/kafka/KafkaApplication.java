package com.example.kafka;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;


@SpringBootApplication
public class KafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}

	@Log4j2
	@Configuration
	static class BindingsConfig {

		@Autowired
		private StreamBridge streamBridge;

		@Bean
		public Supplier<String> inputDataProducer() {
			return () -> {
				if (System.currentTimeMillis() % 7 == 0) {
					return String.valueOf((char) new Random().nextInt(127));
				}
				return String.valueOf(new Random().nextInt());
			};
		}

		@Bean
		public Function<Message<String>, Message<Integer>> inputDataProcessor() {
			return inMessage -> {
				log.info("Received input message: {}", inMessage);
				int integer = Integer.parseInt(inMessage.getPayload());
				acknowledge(inMessage);
				return MessageBuilder
						.withPayload(integer)
						.copyHeaders(inMessage.getHeaders())
						.build();
			};
		}

		@Bean
		public Function<Message<Integer>, Message<Integer>> numberRouter() {
			return (message) -> {
				log.info("Received number message for routing: {}", message);
				return MessageBuilder
						.fromMessage(message)
						.setHeader(
								"spring.cloud.stream.sendto.destination",
								message.getPayload() % 2 == 0 ? "evenNumberChanel" : "oddNumberChanel")
						.build();
			};
		}

		@Bean
		public MessageChannel evenNumberChanel() {
			return new DirectChannel();
		}

		@ServiceActivator(inputChannel = "evenNumberChanel")
		public void evenNumberHandler(Message<Integer> message) {
			log.info("Received even number message: {}", message);
			streamBridge.send("even-number", message);
		}

		@Bean
		public MessageChannel oddNumberChanel() {
			return new DirectChannel();
		}

		@ServiceActivator(inputChannel = "oddNumberChanel")
		public void oddNumberHandler(Message<Integer> message) {
			log.info("Received odd number message: {}", message);
			streamBridge.send("odd-number", message);
		}

		private void acknowledge(Message<?> message) {
			Acknowledgment acknowledgment = message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
			if (acknowledgment != null) {
				acknowledgment.acknowledge();
				log.debug("Acknowledged message: {}", message);
			}
		}
	}
}
