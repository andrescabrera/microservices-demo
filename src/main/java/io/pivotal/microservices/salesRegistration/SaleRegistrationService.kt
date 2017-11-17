package io.pivotal.microservices.salesRegistration

import io.pivotal.microservices.services.web.WebAccountsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.logging.Logger

@Service
class SaleRegistrationService(var restTemplate: RestTemplate, var serviceUrl: String) {

    init {
        this.serviceUrl = if (serviceUrl.startsWith("http"))
            serviceUrl
        else
            "http://" + serviceUrl
    }

    protected var logger = Logger.getLogger(WebAccountsService::class.java
            .name)

    fun register(sale: Sale): Sale {
        logger.info("""
            register() invoked: for $sale
            registering the sale on DB just for auditing purposes
            and later calling FraudDetection microservice to do the sale validation
            """)

        // TODO sale persistence

        sale.valid = restTemplate.getForObject("$serviceUrl/validate/{number}",
                Boolean::class.java, sale.number)

        return sale
    }
}