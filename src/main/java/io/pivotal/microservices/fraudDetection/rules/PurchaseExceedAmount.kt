package io.pivotal.microservices.fraudDetection.rules

import io.pivotal.microservices.fraudDetection.rules.Rule
import io.pivotal.microservices.services.web.SaleDTO
import java.math.BigDecimal

class PurchaseExceedAmount : Rule {
    override fun execute(sale: SaleDTO): Int {
       if(sale.amount > BigDecimal(50000)) return 15 else return 0
    }
}