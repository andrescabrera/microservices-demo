package io.pivotal.microservices.services.web;

import io.pivotal.microservices.exceptions.AccountNotFoundException;
import io.pivotal.microservices.exceptions.SaleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Hide the access to the micro service inside this local service.
 * 
 * @author Andres Cabrera
 */
@Service
public class WebSalesRegistrationService {

	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;

	protected String serviceUrl;

	protected Logger logger = Logger.getLogger(WebSalesRegistrationService.class
			.getName());

	public WebSalesRegistrationService(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl
				: "http://" + serviceUrl;
	}

	/**
	 * The RestTemplate works because it uses a custom request-factory that uses
	 * Ribbon to look-up the service to use. This method simply exists to show
	 * this.
	 */
	@PostConstruct
	public void demoOnly() {
		// Can't do this in the constructor because the RestTemplate injection
		// happens afterwards.
		logger.warning("The RestTemplate request factory is "
				+ restTemplate.getRequestFactory().getClass());
	}

	public SaleDTO findByNumber(String saleNumber) {
		logger.info("findByNumber() invoked: for " + saleNumber);

		return restTemplate.getForObject(serviceUrl + "/sales/{number}",
				SaleDTO.class, saleNumber);
	}

	public SaleDTO getByNumber(String saleNumber) throws SaleNotFoundException {
		SaleDTO sale = restTemplate.getForObject(serviceUrl
				+ "/sales/{number}", SaleDTO.class, saleNumber);

		if (sale == null)
			throw new SaleNotFoundException(saleNumber);
		else
			return sale;
	}

    public SaleDTO register(SaleDTO sale) {
        return restTemplate.postForObject(serviceUrl
                + "/sales/register", sale, SaleDTO.class);
    }
}
