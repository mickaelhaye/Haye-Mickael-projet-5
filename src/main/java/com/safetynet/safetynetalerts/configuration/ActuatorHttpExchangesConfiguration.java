package com.safetynet.safetynetalerts.configuration;

import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository;
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * classe permettant l'affichage du endpoint actuator HttpExchanges
 * 
 * @author Mickael Hayé
 *
 */
@Configuration
public class ActuatorHttpExchangesConfiguration {
	@Bean
	public HttpExchangeRepository httpTraceRepository() {
		return new InMemoryHttpExchangeRepository();
	}
}