package com.digimenu.main.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/stomp");
	}
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config
			.enableSimpleBroker("/restaurant/message")
			.setTaskScheduler(heartBeatScheduler());
		config
			.setApplicationDestinationPrefixes("/app"); //@MessageMapping kullandığım zaman işe yarar yani client-to-sv msg
	}

	@Bean
	public TaskScheduler heartBeatScheduler(){
		return new ThreadPoolTaskScheduler();
	}
}
