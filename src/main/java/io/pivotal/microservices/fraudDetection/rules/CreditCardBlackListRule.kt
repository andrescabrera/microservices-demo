package io.pivotal.microservices.fraudDetection.rules

import io.pivotal.microservices.services.web.SaleDTO
import org.springframework.stereotype.Component

@Component
class CreditCardBlackListRule : Rule {

    private val blackListedCards = setOf<Number>(1234432112344321, 5678876556788765)

    override fun execute(sale: SaleDTO) : Int {
        when(blackListedCards.stream().anyMatch { it == sale.creditCardNumber }) {
            true -> return 100
            false -> return 0
        }
    }
}