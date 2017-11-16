package io.pivotal.microservices.fraudDetection.rules

import io.pivotal.microservices.services.web.SaleDTO

interface Rule {

    fun execute(sale : SaleDTO) : Int
}