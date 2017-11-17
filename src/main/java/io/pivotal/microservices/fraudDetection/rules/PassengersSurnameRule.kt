package io.pivotal.microservices.fraudDetection.rules

import io.pivotal.microservices.services.web.SaleDTO

class PassengersSurnameRule : Rule {
    override fun execute(sale: SaleDTO): Int {
        if(sale.passengers.stream().map { it.split(" ").last() }.distinct().count() == 1L) {
            return 0
        } else {
            return 25
        }
    }

}