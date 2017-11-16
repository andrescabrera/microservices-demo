package io.pivotal.microservices.fraudDetection

import io.pivotal.microservices.services.web.SaleDTO

interface SaleValidator {
    fun validate(sale: SaleDTO): Boolean
    fun score(sale: SaleDTO): Int
}