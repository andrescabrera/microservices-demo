package io.pivotal.microservices.saleRegistration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

/**
 * The Sale Registration Spring configuration.
 * 
 * @author Andres Cabrera
 */
@Configuration
@ComponentScan
@EntityScan("io.pivotal.microservices.salesRegistration")
@EnableJpaRepositories("io.pivotal.microservices.salesRegistration")
@PropertySource("classpath:db-config.properties")
public class SaleRegistrationConfiguration {

	protected Logger logger;

	public SaleRegistrationConfiguration() {
		logger = Logger.getLogger(getClass().getName());
	}

	/**
	 * Creates an in-memory "Sale Registration" database populated with test data for fast
	 * testing
	 */
	@Bean
	public DataSource dataSource() {
		logger.info("dataSource() invoked");

		// Create an in-memory HSQLDB relational database containing some demo
		// accounts.
		DataSource dataSource = (new EmbeddedDatabaseBuilder()).addScript("classpath:testdb/schema.sql")
				.addScript("classpath:testdb/saleRegistrationData.sql").build();

		logger.info("dataSource = " + dataSource);

		// TODO Populate with random old sale data samples

		return dataSource;
	}
}
