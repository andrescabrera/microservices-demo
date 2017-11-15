package io.pivotal.microservices.fraudDetection

import io.pivotal.microservices.saleRegistration.Sale

interface SaleValidator {
    fun validate(sale: Sale): Boolean
}