package io.pivotal.microservices.services.fraudDetection;

import io.pivotal.microservices.fraudDetection.FraudDetectionConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

import java.util.logging.Logger;

/**
 * Run as a micro-service, registering with the Discovery Server (Eureka).
 * <p>
 * Note that the configuration for this application is imported from
 * {@link FraudDetectionConfiguration}. This is a deliberate separation of concerns.
 * 
 * @author Paul Chapman
 */
@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(FraudDetectionConfiguration.class)
public class FraudDetectionServer {

	protected Logger logger = Logger.getLogger(FraudDetectionServer.class.getName());

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		// Tell server to look for fraudDetection-server.properties or
		// fraudDetection-server.yml
		System.setProperty("spring.config.name", "fraudDetection-server");

		SpringApplication.run(FraudDetectionServer.class, args);
	}
}
