package io.pivotal.microservices.services.sales

import io.pivotal.microservices.services.web.Sale

interface SaleValidator {
    fun validate(sale: Sale): Boolean
}