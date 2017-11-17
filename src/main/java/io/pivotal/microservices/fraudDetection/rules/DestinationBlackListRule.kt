package io.pivotal.microservices.fraudDetection.rules

import io.pivotal.microservices.services.web.SaleDTO
import org.springframework.stereotype.Component

@Component
class DestinationBlackListRule : Rule {

    // TODO it should be fetching the bordering countries from another microservice
    private val borderingCountries = setOf("Chile", "Brasil", "Uruguay", "Bolivia", "Paraguay")

    // TODO it should be fetching the blacklisted countries from another microservice
    private val blackListedCountries = setOf("Demokratic Arab Congo Republic", "Poraki Nacio Bin")

    override fun execute(sale: SaleDTO) : Int {
        when(borderingCountries.union(blackListedCountries).stream().anyMatch { it == sale.destination }) {
            true -> return 40
            false -> return 0
        }
    }
}