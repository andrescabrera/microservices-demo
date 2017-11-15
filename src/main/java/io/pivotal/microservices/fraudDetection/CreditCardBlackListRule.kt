package io.pivotal.microservices.fraudDetection

import io.pivotal.microservices.saleRegistration.Sale

class CreditCardBlackListRule : Rule {

    private val blackListedCards = setOf<Number>(1234432112344321, 5678876556788765)

    override fun execute(sale: Sale) : Int {
        when(blackListedCards.stream().anyMatch { it == sale.creditCardNumber }) {
            true -> return 100
            false -> return 0
        }
    }
}