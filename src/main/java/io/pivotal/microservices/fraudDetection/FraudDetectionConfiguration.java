package io.pivotal.microservices.fraudDetection;

import io.pivotal.microservices.fraudDetection.rules.CreditCardBlackListRule;
import io.pivotal.microservices.fraudDetection.rules.DestinationBlackListRule;
import io.pivotal.microservices.fraudDetection.rules.Rule;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.logging.Logger;

/**
 * The accounts Spring configuration.
 * 
 * @author Paul Chapman
 */
@Configuration
@ComponentScan
@EntityScan("io.pivotal.microservices.accounts")
@EnableJpaRepositories("io.pivotal.microservices.accounts")
@PropertySource("classpath:db-config.properties")
public class FraudDetectionConfiguration {

	protected Logger logger;

	public FraudDetectionConfiguration() {
		logger = Logger.getLogger(getClass().getName());
	}

	@Bean
	public Rule creditCardBlackListRule() {
		return new CreditCardBlackListRule();
	}

	@Bean
	public Rule destinationBlackListRule() {
		return new DestinationBlackListRule();
	}

	@Bean
	public SaleValidator ruleSetValidator() {
		return new RuleSetValidator(new HashSet<>(Arrays.asList(creditCardBlackListRule(), destinationBlackListRule())), 100);
	}

	/**
	 * Creates an in-memory "rewards" database populated with test data for fast
	 * testing
	 */
	@Bean
	public DataSource dataSource() {
		logger.info("dataSource() invoked");

		// Create an in-memory HSQLDB relational database containing some demo
		// accounts.
		DataSource dataSource = (new EmbeddedDatabaseBuilder()).addScript("classpath:testdb/schema.sql")
				.addScript("classpath:testdb/fraudDetectionData.sql").build();

		logger.info("dataSource = " + dataSource);

		return dataSource;
	}
}
