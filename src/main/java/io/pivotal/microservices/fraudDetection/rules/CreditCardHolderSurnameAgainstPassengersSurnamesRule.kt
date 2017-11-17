package io.pivotal.microservices.fraudDetection.rules

import io.pivotal.microservices.services.web.SaleDTO

class CreditCardHolderSurnameAgainstPassengersSurnamesRule : Rule {
    override fun execute(sale: SaleDTO): Int {
        when(sale.passengers.stream().anyMatch {
            it.split(" ").last() == sale.cardHolder.split(" ").last()
        }) {
            true -> return 0
            false -> return 20
        }
    }
}