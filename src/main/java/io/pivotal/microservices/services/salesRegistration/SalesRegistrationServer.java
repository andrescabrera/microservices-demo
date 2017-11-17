package io.pivotal.microservices.services.salesRegistration;

import io.pivotal.microservices.salesRegistration.SaleRegistrationConfiguration;
import io.pivotal.microservices.salesRegistration.SaleRegistrationController;
import io.pivotal.microservices.salesRegistration.SaleRegistrationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

/**
 * Run as a micro-service, registering with the Discovery Server (Eureka).
 * <p>
 * Note that the configuration for this application is imported from
 * {@link SaleRegistrationConfiguration}. This is a deliberate separation of concerns.
 * 
 * @author Paul Chapman
 */
@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(SaleRegistrationConfiguration.class)
public class SalesRegistrationServer {

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		// Tell server to look for salesRegistration-server.properties or
		// salesRegistration-server.yml
		System.setProperty("spring.config.name", "salesRegistration-server");

		SpringApplication.run(SalesRegistrationServer.class, args);
	}
}
