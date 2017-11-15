package io.pivotal.microservices.fraudDetection

import io.pivotal.microservices.saleRegistration.Sale

class DestinationBlackListRule : Rule {

    // TODO it should be fetching the bordering countries from another microservice
    private val borderingCountries = setOf<String>("Chile", "Brasil", "Uruguay", "Bolivia", "Paraguay")

    // TODO it should be fetching the blacklisted countries from another microservice
    private val blackListedCountries = setOf<String>("Demokratic Arab Congo Republic", "Poraki Nacio Bin")

    override fun execute(sale: Sale) : Int {
        when(borderingCountries.union(blackListedCountries).stream().anyMatch { it == sale.destination }) {
            true -> return 40
            false -> return 0
        }
    }
}